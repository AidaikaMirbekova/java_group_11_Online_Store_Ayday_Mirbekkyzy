package com.example.java_group_11_online_store_ayday_mirbekkyzy.Service;

import com.example.java_group_11_online_store_ayday_mirbekkyzy.DTO.BasketDTO;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Entity.Basket;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Repository.BasketRepository;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Repository.ProductsRepository;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@AllArgsConstructor
@Service
public class BasketService {
    private final BasketRepository basketRepository;
    private final UserRepository userRepository;
    private final ProductsRepository productsRepository;

    public void addToBasket(BasketDTO basketForm, Integer productId, String email) {
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

    public void deleteBasket(String useremail) {
        basketRepository.deleteAllByCustomerEmail(useremail);
    }

    public Page<BasketDTO> getUserBasket(String useremail, Pageable pageable) {
        var basket = basketRepository.findAllByCustomerEmail(useremail, pageable);
        return basket.map(BasketDTO::from);
    }

    public BasketDTO changeQuantityBasket(Integer idBasket, String useremail, Integer quantity) {
        var basket = basketRepository.findBasketByIdAndCustomerEmail(idBasket, useremail).get();
        var product = productsRepository.findById(basket.getProduct().getId()).get().getPrice();
        basket.setQuantity(quantity);
        basket.setPrice(product * basket.getQuantity());
        basketRepository.save(basket);
        return BasketDTO.from(basket);
    }
}
