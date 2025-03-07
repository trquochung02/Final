package com.samsung.sds.finalproject.controller;

import com.samsung.sds.finalproject.entity.Cart;
import com.samsung.sds.finalproject.service.IService.ICartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CartController {
    private final ICartService cartService;

    public CartController(ICartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/cart")
    public String viewCart(Model model, HttpSession session) {
        String username = (String) session.getAttribute("user");
        if (username == null) {
            return "redirect:/auth/login";
        }

        List<Cart> cartItems = cartService.getCartItems(username);
        double totalAmount = cartService.getTotalAmount(username);

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalAmount", totalAmount);
        return "cart";
    }
}
