package com.samsung.sds.finalproject.controller;

import com.samsung.sds.finalproject.entity.Product;
import com.samsung.sds.finalproject.service.IService.IProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private final IProductService productService;

    public HomeController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String homePage(Model model, HttpSession session) {
        // Kiểm tra nếu chưa có user trong session, thì lấy từ SecurityContextHolder
        if (session.getAttribute("user") == null) {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof UserDetails) {
                String username = ((UserDetails) principal).getUsername();
                session.setAttribute("user", username);
            }
        }

        // Lấy danh sách sản phẩm
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);

        return "home";  // Trả về trang home.html
    }
}
