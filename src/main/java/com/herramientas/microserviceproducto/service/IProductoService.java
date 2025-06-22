package com.herramientas.microserviceproducto.service;

import com.herramientas.microserviceproducto.entity.Producto;

import java.util.List;

public interface IProductoService {
    Producto getProductById(Long id);
    List<Producto> getAllProducts();
    List<Producto> getAllProductsByCategory(Long categoryId);
    Producto createProduct(Producto producto);
    Producto updateProduct(Long id, Producto producto);
    void deleteProduct(Long id);
}
