package com.example.java_group_11_online_store_ayday_mirbekkyzy.Service;


import com.example.java_group_11_online_store_ayday_mirbekkyzy.DTO.TokenDTO;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Entity.TokensMaker;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Exception.UserNotFoundException;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Repository.TokenRepository;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Transactional
@AllArgsConstructor
@Service
public class TokenService {

    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public void createToken(String email) {
        var user = userRepository.findUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        tokenRepository.save(TokensMaker.builder()
                .user(user)
                .token(generateToken())
                .build());

    }

    public void resetPassword(String email,String tokenValue,String newPassword){
        var tokenOp = tokenRepository.findTokensMakerByUserEmailAndToken(email, tokenValue);
        if(tokenOp.isPresent()){
        var user = userRepository.findUserByEmail(email).orElseThrow(UserNotFoundException::new);
            user.setPassword(encoder.encode(newPassword));
            userRepository.save(user);
            tokenRepository.delete(tokenOp.get());
        }else {
           throw new UserNotFoundException();
        }
    }

    public TokenDTO getToken(String useremail) {
        var token = tokenRepository.findTokensMakerByUserEmail(useremail);
        return TokenDTO.from(token.get());
    }

    private String generateToken() {
        return UUID.randomUUID().toString();
    }

}
