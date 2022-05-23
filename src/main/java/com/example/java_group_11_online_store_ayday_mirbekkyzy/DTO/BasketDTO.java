package com.example.java_group_11_online_store_ayday_mirbekkyzy.DTO;

import com.example.java_group_11_online_store_ayday_mirbekkyzy.Entity.Basket;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Entity.Products;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Entity.User;
import lombok.*;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class BasketDTO {

    public static BasketDTO from(Basket basket){
        return builder()
                .id(basket.getId())
                .products_id(basket.getProduct())
                .customers_id(basket.getCustomer())
                .quantity(basket.getQuantity())
                .price(basket.getPrice())
                .build();
    }


    private Integer id;
    private Products products_id;
    private User customers_id;
    private Integer price;
    private Integer quantity;
}
