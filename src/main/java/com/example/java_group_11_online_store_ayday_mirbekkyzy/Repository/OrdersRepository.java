package com.example.java_group_11_online_store_ayday_mirbekkyzy.Repository;

import com.example.java_group_11_online_store_ayday_mirbekkyzy.Entity.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface OrdersRepository extends PagingAndSortingRepository<Orders, Integer> {

    Page<Orders> getOrdersByCustomerEmail(String email, Pageable pageable);

}
