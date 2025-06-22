package com.herramientas.microserviceproducto.dao;

import com.herramientas.microserviceproducto.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductoDao extends JpaRepository<Producto,Long> {
}
