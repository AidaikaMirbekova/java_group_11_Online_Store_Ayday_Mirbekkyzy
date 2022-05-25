package com.example.java_group_11_online_store_ayday_mirbekkyzy.DTO;

import com.example.java_group_11_online_store_ayday_mirbekkyzy.Entity.Orders;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Entity.Reviews;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Entity.User;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class ReviewsDTO {

    private Integer id;
    private User user;
    private Orders order;
    private String review;
    private LocalDateTime date;

    public static ReviewsDTO from(Reviews reviews) {
        return builder()
                .id(reviews.getId())
                .user(reviews.getUser())
                .order(reviews.getOrder())
                .review(reviews.getReview())
                .date(reviews.getDate())
                .build();
    }


}
