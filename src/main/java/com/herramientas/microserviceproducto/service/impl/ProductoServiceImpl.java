package com.herramientas.microserviceproducto.service.impl;

import com.herramientas.microserviceproducto.configuration.RestTemplateConfig;
import com.herramientas.microserviceproducto.dao.IProductoDao;
import com.herramientas.microserviceproducto.entity.Producto;
import com.herramientas.microserviceproducto.models.Categoria;
import com.herramientas.microserviceproducto.service.IProductoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductoServiceImpl implements IProductoService {

    private final IProductoDao productoDao;
    private final RestTemplateConfig restTemplateConfig;

    public ProductoServiceImpl(IProductoDao productoDao, RestTemplateConfig restTemplateConfig) {
        this.productoDao = productoDao;
        this.restTemplateConfig = restTemplateConfig;
    }

    @Transactional(readOnly = true)
    @Override
    public Producto getProductById(Long id) {
        return productoDao.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Producto> getAllProducts() {
        return productoDao.findAll();
    }

    @Override
    public List<Producto> getAllProductsByCategory(Long categoryId) {
        Categoria categoria = restTemplateConfig.restTemplate().getForObject("http://localhost:8001/api/categoria/" + categoryId, Categoria.class);
        if (categoria == null) {
            throw new RuntimeException("Categoria no encontrada");
        }
        return productoDao.findAllByCategoryId(categoria.getId());
    }

    @Transactional
    @Override
    public Producto createProduct(Producto producto) {
        Categoria categoria = restTemplateConfig.restTemplate().getForObject("http://localhost:8001/api/categoria/" + producto.getCategoryId(), Categoria.class);
        if (categoria == null) {
            throw new RuntimeException("Categoria no encontrada");
        }
        return productoDao.save(producto);
    }

    @Transactional
    @Override
    public Producto updateProduct(Long id, Producto producto) {
        Producto oldProducto = this.getProductById(id);
        oldProducto.setName(producto.getName());
        oldProducto.setDescription(producto.getDescription());
        oldProducto.setPrice(producto.getPrice());
        oldProducto.setCategoryId(producto.getCategoryId());
        return productoDao.save(oldProducto);
    }

    @Transactional
    @Override
    public void deleteProduct(Long id) {
        productoDao.deleteById(id);
    }
}
