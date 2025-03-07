package com.samsung.sds.finalproject.repository;

import com.samsung.sds.finalproject.entity.Cart;
import com.samsung.sds.finalproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUser(User user);  // Thay vì findByUsername, ta tìm bằng User
}
