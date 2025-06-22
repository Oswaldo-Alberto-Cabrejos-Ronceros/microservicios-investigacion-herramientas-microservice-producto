package com.herramientas.microserviceproducto.dao;

import com.herramientas.microserviceproducto.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProductoDao extends JpaRepository<Producto,Long> {
    List<Producto> findAllByCategoryId(Long id);
}
