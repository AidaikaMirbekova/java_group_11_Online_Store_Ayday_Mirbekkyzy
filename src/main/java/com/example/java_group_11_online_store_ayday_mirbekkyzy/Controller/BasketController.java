package com.example.java_group_11_online_store_ayday_mirbekkyzy.Controller;

import com.example.java_group_11_online_store_ayday_mirbekkyzy.DTO.BasketDTO;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Entity.Products;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Repository.ProductsRepository;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Service.BasketService;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@Data
@AllArgsConstructor
public class BasketController {

    private final BasketService basketService;
    private final UserService userService;
    private final ProductsRepository productRepository;

    @GetMapping("/api/basket")
    public String basket(Model model, @SessionAttribute(name = Constants.BASKET_ID, required = false) List<Products> basket, Principal principal,
                         @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable page) {
        var user = userService.login(principal.getName());
        var userBasket = basketService.getUserBasket(user.getEmail(), page);
        model.addAttribute("pages", userBasket.getPageable());
        model.addAttribute("lastPages", userBasket.hasNext());
        if (!userBasket.isEmpty()) {
            model.addAttribute("userBasket", userBasket.getContent());
            return "userBasket";
        } else if (userBasket.isEmpty() && !basket.isEmpty()) {
            model.addAttribute("baskets", basket);
            return "basket";
        }
        return "basket";
    }


    @PostMapping("/api/basket/add")
    public String addToBasket(@RequestParam Integer id, HttpSession session, Model model, @Valid BasketDTO basket, Principal principal) {
        if (session != null) {
            var user = userService.login(principal.getName());
            var product = productRepository.findById(id);
            model.addAttribute("user", user);
            model.addAttribute("product", product);
            basketService.addToBasket(basket, id, user.getEmail());

            var attr = session.getAttribute(Constants.BASKET_ID);
            if (attr == null) {
                session.setAttribute(Constants.BASKET_ID, new ArrayList<String>());
            }
            try {
                var list = (List<Products>) session.getAttribute(Constants.BASKET_ID);
                list.add(product.get());
            } catch (ClassCastException ignored) {
                ignored.printStackTrace();
            }
        }
        return "redirect:/";
    }


    @PostMapping("/api/basket/empty")
    public String emptyBasket(HttpSession session, Principal principal, Model model) {
        session.removeAttribute(Constants.BASKET_ID);
        var user = userService.login(principal.getName());
        basketService.deleteBasket(user.getEmail());
        model.addAttribute("user", user);
        return "redirect:/api/basket";
    }

    @PostMapping("/api/basket/delete")
    public String deleteOneBasket(@RequestParam Integer id, Principal principal, Model model) {
        var user = userService.login(principal.getName());
        model.addAttribute("user", user);
        basketService.deleteOneBasket(id,user.getEmail());
        return "redirect:/api/basket";
    }

    @PostMapping("/api/basket/changeQuantity")
    public String changeQuantityBasket(@RequestParam Integer id, Principal principal, Model model, Integer quantity) {
        var user = userService.login(principal.getName());
        basketService.changeQuantityBasket(id, user.getEmail(), quantity);
        model.addAttribute("user", user);
        model.addAttribute("quantity", quantity);
        return "redirect:/api/basket";
    }
}
