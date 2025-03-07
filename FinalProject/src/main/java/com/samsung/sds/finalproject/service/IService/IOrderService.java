package com.samsung.sds.finalproject.service.IService;

import com.samsung.sds.finalproject.entity.Order;
import com.samsung.sds.finalproject.entity.User;
import java.util.List;

public interface IOrderService {
    Order placeOrder(User user);
    List<Order> getOrders(User user);
}
