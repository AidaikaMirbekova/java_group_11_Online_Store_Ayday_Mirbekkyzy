package com.example.java_group_11_online_store_ayday_mirbekkyzy.Repository;

import com.example.java_group_11_online_store_ayday_mirbekkyzy.Entity.Reviews;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface ReviewsRepository extends PagingAndSortingRepository<Reviews,Integer> {

    Page<Reviews> findAll(Pageable pageable);

}
