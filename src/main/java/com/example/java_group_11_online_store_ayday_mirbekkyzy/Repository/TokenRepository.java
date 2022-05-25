package com.example.java_group_11_online_store_ayday_mirbekkyzy.Repository;

import com.example.java_group_11_online_store_ayday_mirbekkyzy.Entity.TokensMaker;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TokenRepository extends CrudRepository<TokensMaker, Integer> {
    Optional<TokensMaker> findTokensMakerByUserEmail(String email);
    Optional<TokensMaker> findTokensMakerByToken(String token);
    Optional<TokensMaker> findTokensMakerByUserEmailAndToken(String email, String token);
}
