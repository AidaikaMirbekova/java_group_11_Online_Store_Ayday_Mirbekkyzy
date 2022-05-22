package com.example.java_group_11_online_store_ayday_mirbekkyzy.Controller;

import com.example.java_group_11_online_store_ayday_mirbekkyzy.Entity.Basket;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Entity.Products;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Repository.BasketRepository;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Repository.ProductsRepository;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Service.BasketService;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    BasketRepository basketRepository;

    @GetMapping("/api/basket")
    public String basket(Model model, @SessionAttribute(name = Constants.BASKET_ID, required = false) List<Products> basket) {
        if (basket != null) {
            model.addAttribute("baskets", basket);
        }
        return "basket";
    }


    @PostMapping("/api/basket")
    public String basketPage(Model model, @Valid Basket basket,
                             BindingResult validationResult,
                             RedirectAttributes attributes, Principal principal) {

        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/basket";
        }
//        var user = userService.login(principal.getName());
//        model.addAttribute("user", user);
//        basketService.addToBasket(basket,id,user.getEmail());
        return "redirect:/api/basket";
    }


    // метод для добавления в "корзину" через форму
    // демонстрация добавления через объект HttpSession session
    @PostMapping("/api/basket/add")
    public String addToBasket(@RequestParam Integer id, HttpSession session,Model model,@Valid Basket basket, Principal principal) {
        if (session != null) {

            var user = userService.login(principal.getName());
            var product = productRepository.findById(id);
            model.addAttribute("user", user);
            model.addAttribute("product", product);
            basketService.addToBasket(basket,id,user.getEmail());

            var attr = session.getAttribute(Constants.BASKET_ID);
            if (attr == null) {
                session.setAttribute(Constants.BASKET_ID, new ArrayList<String>());
            }
            try {
                var list = (List<Products>) session.getAttribute(Constants.BASKET_ID);
                list.add(product.get());

            } catch (ClassCastException ignored) {

            }
        }

        return "redirect:/api/basket";

    }



    @PostMapping("/api/basket/empty")
    public String emptyBasket(HttpSession session) {
        session.removeAttribute(Constants.BASKET_ID);

        return "redirect:/api/basket";
    }

}
