package com.example.First.project.service;

import com.example.First.project.dto.ProductRequestDTO;
import com.example.First.project.dto.ProductResponseDTO;
import com.example.First.project.exceptions.ProductNotFoundException;
import com.example.First.project.model.Product;
import com.example.First.project.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public ProductResponseDTO getProduct(int id) {
        Product product = repo.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found"));
        return toResponseDTO(product);
    }

    public Page<ProductResponseDTO> getAllProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repo.findAll(pageable).map(this::toResponseDTO);
    }

    public ProductResponseDTO createProduct(ProductRequestDTO p) {
        Product product = new Product();
        product.setName(p.getName());
        product.setPrice(p.getPrice());
        Product saved = repo.save(product);
        return toResponseDTO(saved);
    }

    public ProductResponseDTO updateProduct(int id, ProductRequestDTO np) {
        Product existing = repo.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found"));
        existing.setName(np.getName());
        existing.setPrice(np.getPrice());
        Product saved = repo.save(existing);
        return toResponseDTO(saved);
    }

    public Page<ProductResponseDTO> getProductByName(String name,int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repo.findByNameContainingIgnoreCase(name, pageable)
                .map(this::toResponseDTO);
    }

    public void deleteProductById(int id) {
        repo.findById(id).orElseThrow(() -> new ProductNotFoundException("Item not found"));
        repo.deleteById(id);
    }

    private ProductResponseDTO toResponseDTO(Product product) {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        return dto;
    }
}