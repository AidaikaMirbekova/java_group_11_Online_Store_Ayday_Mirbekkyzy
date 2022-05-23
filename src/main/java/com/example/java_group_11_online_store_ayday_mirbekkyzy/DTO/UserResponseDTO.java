package com.example.java_group_11_online_store_ayday_mirbekkyzy.DTO;

import com.example.java_group_11_online_store_ayday_mirbekkyzy.Entity.User;
import lombok.*;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PACKAGE)
public class UserResponseDTO {

    private int id;
    private String name;
    private String login;
    private String email;

    public static UserResponseDTO from(User user) {
        return builder()
                .id(user.getId())
                .name(user.getName())
                .login(user.getLogin())
                .email(user.getEmail())
                .build();
    }
}
