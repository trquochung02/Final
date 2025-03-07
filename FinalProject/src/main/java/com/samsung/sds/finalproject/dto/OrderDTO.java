package com.samsung.sds.finalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long orderId;
    private String username;
    private LocalDateTime orderDate;
    private List<CartItemDTO> items;
    private double totalAmount;
}
