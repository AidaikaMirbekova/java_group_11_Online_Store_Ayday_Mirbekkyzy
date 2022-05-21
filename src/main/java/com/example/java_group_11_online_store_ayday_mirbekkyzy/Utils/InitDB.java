package com.example.java_group_11_online_store_ayday_mirbekkyzy.Utils;

import com.example.java_group_11_online_store_ayday_mirbekkyzy.Entity.RolesEnum;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Entity.User;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@AllArgsConstructor
@Configuration
public class InitDB {
    UserRepository userRepository;
    PasswordEncoder encoder;

    @Bean
    public CommandLineRunner init() {
        return (args) -> {
            User user = new User();
            user.setEmail("test@test");
            user.setLogin("test123");
            user.setName("Test");
            user.setRole(RolesEnum.ADMIN);
            user.setPassword(encoder.encode("test"));
            userRepository.save(user);
        };
    }

}
