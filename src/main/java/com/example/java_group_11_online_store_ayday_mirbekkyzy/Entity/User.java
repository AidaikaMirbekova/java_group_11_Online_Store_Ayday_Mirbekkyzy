package com.example.java_group_11_online_store_ayday_mirbekkyzy.Entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;


@Entity
@Table(name = "user_table")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(min = 2, max = 24, message = "Name cannot be shorter than 2 letters and longer than 24 letters!!!")
    @Pattern(regexp = "^[^\\d\\s]+$", message = "Should contain only letters!!!")
    private String name;

    @NotBlank
    @Size(min = 5, max = 15, message = "Login cannot be shorter than 5 letters and longer than 15 letters!!!")
    private String login;

    @NotNull
    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 5)
    private String password;


    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private RolesEnum role;

    @Builder.Default
    private boolean enabled = true;

}
