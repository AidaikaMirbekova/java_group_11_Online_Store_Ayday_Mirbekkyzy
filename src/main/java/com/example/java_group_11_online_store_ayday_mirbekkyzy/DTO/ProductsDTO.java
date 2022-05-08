package com.example.java_group_11_online_store_ayday_mirbekkyzy.DTO;

import lombok.*;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class ProductsDTO {
    private Integer id;
    private String name;
    private String image;
    private String quantity;
    private String description;
    private String price;
}
