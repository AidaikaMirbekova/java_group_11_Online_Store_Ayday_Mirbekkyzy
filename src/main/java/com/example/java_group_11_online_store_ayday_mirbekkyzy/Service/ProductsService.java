package com.example.java_group_11_online_store_ayday_mirbekkyzy.Service;

import com.example.java_group_11_online_store_ayday_mirbekkyzy.DTO.ProductsDTO;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Entity.Products;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Repository.ProductsRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class ProductsService {
    public final ProductsRepository productsRepository;

    public Page<ProductsDTO> getAllProducts(){
        Pageable pageable = PageRequest.of(0, 2, Sort.by("price").descending());
        Page<Products> products= productsRepository.findAll(pageable);
        return products.map(ProductsDTO::from);
    }
}
