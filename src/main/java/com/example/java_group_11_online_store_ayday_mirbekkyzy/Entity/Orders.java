package com.example.java_group_11_online_store_ayday_mirbekkyzy.Entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "orders")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products product;


    private Integer quantity;

}
