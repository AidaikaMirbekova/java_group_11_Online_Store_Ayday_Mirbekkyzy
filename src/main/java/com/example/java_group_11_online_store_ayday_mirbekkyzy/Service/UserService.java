package com.example.java_group_11_online_store_ayday_mirbekkyzy.Service;

import com.example.java_group_11_online_store_ayday_mirbekkyzy.DTO.UserDTO;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.DTO.UserRegisterForm;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.DTO.UserResponseDTO;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Entity.RolesEnum;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Entity.User;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Exception.UserAlreadyRegister;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Exception.UserNotFoundException;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class UserService  {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserResponseDTO register(UserRegisterForm form) {
        if (userRepository.existsUserByEmail(form.getEmail())) {
            throw new UserAlreadyRegister();
        }

        var user = User.builder()
                .email(form.getEmail())
                .name(form.getName())
                .login(form.getLogin())
                .password(encoder.encode(form.getPassword()))
                .role(RolesEnum.USER)
                .build();

        userRepository.save(user);

        return UserResponseDTO.from(user);
    }

    public UserDTO login (String email){
        var user = userRepository.findUserByEmail(email).orElseThrow(UserNotFoundException::new);
        return UserDTO.from(user);
    }

}
