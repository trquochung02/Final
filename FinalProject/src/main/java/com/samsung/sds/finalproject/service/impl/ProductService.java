package com.samsung.sds.finalproject.service.impl;

import com.samsung.sds.finalproject.entity.Product;
import com.samsung.sds.finalproject.repository.ProductRepository;
import com.samsung.sds.finalproject.service.IService.IProductService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService implements IProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> searchProducts(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
}
