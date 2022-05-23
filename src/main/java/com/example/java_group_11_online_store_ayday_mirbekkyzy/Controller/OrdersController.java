package com.example.java_group_11_online_store_ayday_mirbekkyzy.Controller;

import com.example.java_group_11_online_store_ayday_mirbekkyzy.DTO.OrdersDTO;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Service.BasketService;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Service.OrdersService;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;
import java.security.Principal;


@Controller
@RequestMapping()
@AllArgsConstructor
public class OrdersController {

    private final OrdersService ordersService;
    private final UserService userService;

    @PostMapping("/api/addOrder")
    public String addOrder(@Valid OrdersDTO orders, Principal principal, Model model) {
        var user = userService.login(principal.getName());
        model.addAttribute("user", user);
        ordersService.checkOrder(user.getEmail(),orders);
        return "redirect:/api/showOrders";
    }

    @GetMapping("/api/showOrders")
    public String showOrders(Principal principal, Model model,
                             @PageableDefault(sort = "id", direction = Sort.Direction.DESC,value = 11) Pageable page) {
        var user = userService.login(principal.getName());
        var orders = ordersService.showOrders(user.getEmail(), page);
        if (!orders.isEmpty()) {
            model.addAttribute("ordersList", orders.getContent());
            var count = orders.getTotalPages();
            model.addAttribute("pages", orders.getPageable());
            model.addAttribute("lastPage", count);
            return "orders";
        }
        return "orders";
    }
}
