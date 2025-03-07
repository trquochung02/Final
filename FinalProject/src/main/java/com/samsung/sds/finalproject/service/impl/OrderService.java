package com.samsung.sds.finalproject.service.impl;

import com.samsung.sds.finalproject.entity.Cart;
import com.samsung.sds.finalproject.entity.Order;
import com.samsung.sds.finalproject.entity.User;
import com.samsung.sds.finalproject.repository.CartRepository;
import com.samsung.sds.finalproject.repository.OrderRepository;
import com.samsung.sds.finalproject.service.IService.IOrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;

    public OrderService(OrderRepository orderRepository, CartRepository cartRepository) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    public Order placeOrder(User user) {
        List<Cart> cartItems = cartRepository.findByUser(user);
        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setItems(cartItems);

        Order savedOrder = orderRepository.save(order);
        cartRepository.deleteAll(cartItems);
        return savedOrder;
    }

    @Override
    public List<Order> getOrders(User user) {
        return orderRepository.findByUser(user);
    }
}

