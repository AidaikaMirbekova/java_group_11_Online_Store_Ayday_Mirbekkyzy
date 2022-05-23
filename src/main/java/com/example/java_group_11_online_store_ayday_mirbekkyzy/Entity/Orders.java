package com.example.java_group_11_online_store_ayday_mirbekkyzy.Entity;

import lombok.*;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.List;

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
    private User customer;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products product;

    private Integer quantity;

    @Positive
    private Float price;

    @Past
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private Status status;

}
