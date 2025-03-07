package com.samsung.sds.finalproject.service.impl;

import com.samsung.sds.finalproject.entity.Cart;
import com.samsung.sds.finalproject.entity.User;
import com.samsung.sds.finalproject.repository.CartRepository;
import com.samsung.sds.finalproject.repository.UserRepository;
import com.samsung.sds.finalproject.service.IService.ICartService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService implements ICartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    public CartService(CartRepository cartRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Cart> getCartItems(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));
        return cartRepository.findByUser(user);
    }

    @Override
    public double getTotalAmount(String username) {
        return getCartItems(username).stream()
                .mapToDouble(cart -> cart.getProduct().getPrice() * cart.getQuantity())
                .sum();
    }
}
