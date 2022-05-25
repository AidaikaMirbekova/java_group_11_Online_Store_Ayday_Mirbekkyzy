package com.example.java_group_11_online_store_ayday_mirbekkyzy.DTO;

import com.example.java_group_11_online_store_ayday_mirbekkyzy.Entity.TokensMaker;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Entity.User;
import lombok.*;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class TokenDTO {

    private Integer id;
    private User user;
    private String token;

    public static TokenDTO from(TokensMaker pass){
        return builder()
                .id(pass.getId())
                .user(pass.getUser())
                .token(pass.getToken())
                .build();
    }
}
