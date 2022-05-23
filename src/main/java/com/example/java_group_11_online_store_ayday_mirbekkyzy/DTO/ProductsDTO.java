package com.example.java_group_11_online_store_ayday_mirbekkyzy.DTO;

import com.example.java_group_11_online_store_ayday_mirbekkyzy.Entity.Products;
import lombok.*;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class ProductsDTO {

    private Integer id;
    private String name;
    private String image;
    private Integer quantity;
    private String description;
    private Float price;

    public static ProductsDTO from(Products products) {
        return builder()
                .id(products.getId())
                .name(products.getName())
                .image(products.getImage())
                .quantity(products.getQuantity())
                .description(products.getDescription())
                .price(products.getPrice())
                .build();
    }
}
