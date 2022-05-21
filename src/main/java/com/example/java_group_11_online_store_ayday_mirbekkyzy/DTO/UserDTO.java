package com.example.java_group_11_online_store_ayday_mirbekkyzy.DTO;

import com.example.java_group_11_online_store_ayday_mirbekkyzy.Entity.User;
import lombok.*;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class UserDTO {


    public static UserDTO from(User user) {
        return builder()
                .name(user.getName())
                .login(user.getLogin())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole().toString())
                .build();
    }

    private Integer id;
    private String name;
    private String login;
    private String email;
    private String password;
    private String role;
}
