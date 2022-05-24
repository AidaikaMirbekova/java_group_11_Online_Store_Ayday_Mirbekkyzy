package com.example.java_group_11_online_store_ayday_mirbekkyzy.Service;

import com.example.java_group_11_online_store_ayday_mirbekkyzy.DTO.OrdersDTO;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Entity.Basket;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Entity.Orders;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Entity.Status;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Repository.BasketRepository;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Repository.OrdersRepository;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Repository.ProductsRepository;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Transactional
@AllArgsConstructor
@Service
public class OrdersService {
    private final OrdersRepository ordersRepository;
    private final BasketRepository basketRepository;
    private final UserRepository userRepository;
    private final ProductsRepository productsRepository;


    public void checkOrder(String useremail,OrdersDTO orders) {
        var user = userRepository.findUserByEmail(useremail).get();
        var baskets = basketRepository.findBasketsByCustomerEmail(useremail);
        for (int i = 0; i < baskets.size(); i++) {
            var basketQuantity = baskets.get(i).getQuantity();
            var order = Orders.builder()
                    .id(orders.getId())
                    .product(baskets.get(i).getProduct())
                    .customer(user)
                    .quantity(baskets.get(i).getQuantity())
                    .price(baskets.get(i).getPrice())
                    .status(Status.WAIT)
                    .date(LocalDateTime.now())
                    .build();
            ordersRepository.save(order);
            var products  = productsRepository.findAllById(baskets.get(i).getProduct().getId());
            for (int j = 0; j < products.size(); j++) {
                var quantity = products.get(j).getQuantity();
                products.get(j).setQuantity(quantity-basketQuantity);
                productsRepository.save(products.get(j));
            }
        }
        basketRepository.deleteAll(baskets);
    }

    public Page<OrdersDTO> showOrders(String useremail, Pageable pageable) {
        var orders = ordersRepository.getOrdersByCustomerEmail(useremail, pageable);
        return orders.map(OrdersDTO::from);
    }

}
