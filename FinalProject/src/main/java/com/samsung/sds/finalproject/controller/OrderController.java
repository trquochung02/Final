package com.samsung.sds.finalproject.controller;

import com.samsung.sds.finalproject.entity.Order;
import com.samsung.sds.finalproject.entity.User;
import com.samsung.sds.finalproject.service.IService.IOrderService;
import com.samsung.sds.finalproject.service.IService.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final IOrderService orderService;
    private final IUserService userService;

    public OrderController(IOrderService orderService, IUserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @PostMapping("/place")
    public ResponseEntity<Order> placeOrder(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(orderService.placeOrder(user));
    }

    @GetMapping("/history")
    public ResponseEntity<List<Order>> getOrders(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(orderService.getOrders(user));
    }
}
