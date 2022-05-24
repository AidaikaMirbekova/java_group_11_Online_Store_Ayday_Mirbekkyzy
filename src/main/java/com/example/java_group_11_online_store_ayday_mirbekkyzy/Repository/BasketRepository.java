package com.example.java_group_11_online_store_ayday_mirbekkyzy.Repository;

import com.example.java_group_11_online_store_ayday_mirbekkyzy.Entity.Basket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface BasketRepository extends PagingAndSortingRepository<Basket, Integer> {

    void deleteBasketByCustomerEmail(String email);

    Optional<Basket> findBasketByIdAndCustomerEmail(Integer id, String email);

    Page<Basket> findBasketsByCustomerEmail(String useremail, Pageable pageable);

    List<Basket> findBasketsByCustomerEmail(String email);

}
