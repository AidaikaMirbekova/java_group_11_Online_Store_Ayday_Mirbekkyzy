package com.example.java_group_11_online_store_ayday_mirbekkyzy.Service;

import com.example.java_group_11_online_store_ayday_mirbekkyzy.DTO.UserDTO;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Entity.User;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public void register(UserDTO user) throws Exception {
        if (userRepository.existsUserByEmail(user.getEmail())) {
            throw new Exception("Have an account registered with this email");
        }
        userRepository.save(User.builder()
                .name(user.getName())
                .login(user.getLogin())
                .email(user.getEmail())
                .password(encoder.encode(user.getPassword()))
                .build());
    }


    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        return userRepository.getByEmail(userEmail).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }
}
