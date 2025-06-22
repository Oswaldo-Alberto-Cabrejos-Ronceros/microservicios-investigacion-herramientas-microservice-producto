package com.herramientas.microserviceproducto.controller;

import com.herramientas.microserviceproducto.entity.Producto;
import com.herramientas.microserviceproducto.service.IProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/producto")
public class ProductoController {

    private final IProductoService productoService;

    public ProductoController(IProductoService productoService){
        this.productoService=productoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductById(@PathVariable Long id){
        return ResponseEntity.ok(productoService.getProductById(id));
    }

    @GetMapping
    public ResponseEntity<List<Producto>> getAllProducts(){
        return ResponseEntity.ok(productoService.getAllProducts());
    }

    @GetMapping("/categoria/{id}")
    public ResponseEntity<List<Producto>> getProductsByCategoria(@PathVariable Long id){
        return ResponseEntity.ok(productoService.getAllProductsByCategory(id));
    }

    @PostMapping
    public ResponseEntity<Producto> createProduct(@RequestBody Producto producto){
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.createProduct(producto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProduct(@PathVariable Long id,@RequestBody Producto producto){
        return ResponseEntity.ok(productoService.updateProduct(id,producto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        productoService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
