package com.example.java_group_11_online_store_ayday_mirbekkyzy.Service;

import com.example.java_group_11_online_store_ayday_mirbekkyzy.DTO.ProductsDTO;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Entity.Products;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Repository.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class ProductsService {
    public final ProductsRepository productsRepository;

    public Page<ProductsDTO> getAllProducts(Pageable pageable) {
        Page<Products> products = productsRepository.findAll(pageable);
        return products.map(ProductsDTO::from);
    }

    public Page<ProductsDTO> searchProducts(String keyword, Pageable pageable) {
        if (keyword != null) {
            Page<Products> products = productsRepository.search(keyword, pageable);
            return products.map(ProductsDTO::from);
        } else {
            return productsRepository.findAll(pageable).map(ProductsDTO::from);
        }
    }

    public Page<ProductsDTO> priceSearch(Float more, Float less, Pageable pageable) {
        Page<Products> products = productsRepository.findProductsByPriceGreaterThanAndPriceIsLessThan(more, less, pageable);
        return products.map(ProductsDTO::from);
    }
}
