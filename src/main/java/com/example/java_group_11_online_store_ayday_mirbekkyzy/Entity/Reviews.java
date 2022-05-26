package com.example.java_group_11_online_store_ayday_mirbekkyzy.Entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reviews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotEmpty(message = "Review may not be empty")
    @Size(min = 6, message = "Review must be more 2 characters long")
    private String review;

    @Past
    private LocalDateTime date;


}
