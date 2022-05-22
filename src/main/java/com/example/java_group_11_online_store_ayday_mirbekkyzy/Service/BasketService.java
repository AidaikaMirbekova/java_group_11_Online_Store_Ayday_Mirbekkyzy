package com.example.java_group_11_online_store_ayday_mirbekkyzy.Service;

import com.example.java_group_11_online_store_ayday_mirbekkyzy.DTO.BasketDTO;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Entity.Basket;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Repository.BasketRepository;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Repository.ProductsRepository;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class BasketService {
    private final BasketRepository basketRepository;
    private final UserRepository userRepository;
    private final ProductsRepository productsRepository;

    public void addToBasket(Basket basketForm, Integer productId,String email){
        var user = userRepository.findUserByEmail(email);
        var product = productsRepository.findById(productId);
        var basket = Basket.builder()
                .id(basketForm.getId())
                .product(product.get())
                .customer(user.get())
                .price(product.get().getPrice())
                .quantity(basketForm.getQuantity())
                .build();
        basketRepository.save(basket);
    }

    public void deleteBasket(String useremail){
        basketRepository.deleteAllByCustomerEmail(useremail);
    }

    public Page<BasketDTO> getUserBasket(String useremail, Pageable pageable){
        var basket = basketRepository.findAllByCustomerEmail(useremail,pageable);
        return basket.map(BasketDTO::from);
    }
}
