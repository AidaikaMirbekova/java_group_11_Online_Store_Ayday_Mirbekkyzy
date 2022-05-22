package com.example.java_group_11_online_store_ayday_mirbekkyzy.Repository;

import com.example.java_group_11_online_store_ayday_mirbekkyzy.Entity.Basket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface BasketRepository extends PagingAndSortingRepository<Basket, Integer> {
    Optional<Basket> deleteAllByCustomerEmail(String email);

    Page<Basket> findAllByCustomerEmail(String useremail, Pageable pageable);

}
