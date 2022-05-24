package com.example.java_group_11_online_store_ayday_mirbekkyzy.Repository;

import com.example.java_group_11_online_store_ayday_mirbekkyzy.Entity.Products;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProductsRepository extends PagingAndSortingRepository<Products, Integer> {

    @Query("SELECT p FROM Products p WHERE CONCAT(p.name, ' ', p.description, ' ', p.price) LIKE %?1%")
    Page<Products> search(String keyword, Pageable pageable);

    Page<Products> findProductsByPriceGreaterThanAndPriceIsLessThan(Float less, Float more, Pageable pageable);

    List<Products> findAllById(Integer id);

}
