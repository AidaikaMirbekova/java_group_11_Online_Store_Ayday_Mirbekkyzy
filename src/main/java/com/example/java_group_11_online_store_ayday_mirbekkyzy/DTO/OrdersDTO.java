package com.example.java_group_11_online_store_ayday_mirbekkyzy.DTO;

import lombok.*;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class OrdersDTO {

    private Integer id;
    private Integer customer_id;
    private Integer product_id;
    private Integer quantity;
}
