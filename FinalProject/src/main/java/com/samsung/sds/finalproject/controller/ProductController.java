package com.samsung.sds.finalproject.controller;

import com.samsung.sds.finalproject.entity.Product;
import com.samsung.sds.finalproject.service.IService.IProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String getAllProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "product"; // Giao diện Thymeleaf
    }

    @GetMapping("/search")
    @ResponseBody
    public List<Product> searchProducts(@RequestParam String name) {
        return productService.searchProducts(name);
    }
}
