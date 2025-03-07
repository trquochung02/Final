package com.samsung.sds.finalproject.service.IService;

import com.samsung.sds.finalproject.entity.Cart;
import java.util.List;

public interface ICartService {
    List<Cart> getCartItems(String username);
    double getTotalAmount(String username);
}
