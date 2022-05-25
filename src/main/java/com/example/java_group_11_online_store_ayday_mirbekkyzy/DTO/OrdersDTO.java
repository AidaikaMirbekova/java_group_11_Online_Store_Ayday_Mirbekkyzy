package com.example.java_group_11_online_store_ayday_mirbekkyzy.DTO;

import com.example.java_group_11_online_store_ayday_mirbekkyzy.Entity.*;
import lombok.*;

import java.time.LocalDateTime;


@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class OrdersDTO {

    private Integer id;
    private User customer_id;
    private Products product_id;
    private Integer quantity;
    private Float price;
    private Status status;
    private LocalDateTime date;

    public static OrdersDTO from(Orders orders){
        return builder()
                .id(orders.getId())
                .customer_id(orders.getCustomer())
                .product_id(orders.getProduct())
                .quantity(orders.getQuantity())
                .price(orders.getPrice())
                .status(orders.getStatus())
                .date(orders.getDate())
                .build();
    }
}
