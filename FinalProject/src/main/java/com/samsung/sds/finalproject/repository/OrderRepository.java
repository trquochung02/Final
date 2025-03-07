package com.samsung.sds.finalproject.repository;

import com.samsung.sds.finalproject.entity.Order;
import com.samsung.sds.finalproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
}
