package com.example.First.project.controller;

import com.example.First.project.dto.ProductRequestDTO;
import com.example.First.project.dto.ProductResponseDTO;
import com.example.First.project.model.Product;
import org.springframework.data.domain.Page;
import com.example.First.project.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductC(@PathVariable int id) {
        return ResponseEntity.ok(service.getProduct(id));
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponseDTO>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
            return ResponseEntity.ok(service.getAllProducts(page,size));

    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createNewProduct(@Valid @RequestBody ProductRequestDTO p) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createProduct(p));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable int id, @Valid @RequestBody ProductRequestDTO p) {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateProduct(id, p));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ProductResponseDTO>> getProductByName(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(service.getProductByName(name,page,size));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable int id) {
        service.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }
}
