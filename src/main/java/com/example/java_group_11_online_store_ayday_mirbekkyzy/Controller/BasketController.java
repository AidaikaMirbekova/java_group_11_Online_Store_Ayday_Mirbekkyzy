package com.example.java_group_11_online_store_ayday_mirbekkyzy.Controller;

import com.example.java_group_11_online_store_ayday_mirbekkyzy.Entity.Basket;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Entity.Products;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Repository.BasketRepository;
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
    public String basket(Model model, @SessionAttribute(name = Constants.BASKET_ID, required = false) List<Products> basket, Principal principal,
                         @PageableDefault(sort = "id", direction = Sort.Direction.DESC, value = 30) Pageable page) {
        var user = userService.login(principal.getName());
        var userBasket = basketService.getUserBasket(user.getEmail(), page);
        if (!userBasket.isEmpty()) {
            var count = userBasket.getTotalPages() - 1;
            var number = userBasket.getNumber();
            model.addAttribute("number", number);
            model.addAttribute("userBasket", userBasket.getContent());
            model.addAttribute("pages", userBasket.getPageable());
            model.addAttribute("lastPage", count);
            return "userBasket";
        } else if (userBasket.isEmpty() && !basket.isEmpty()) {
            model.addAttribute("baskets", basket);
            return "basket";
        }
        return "basket";
    }


//@PostMapping("/api/basket")
//public String basketPage(@Valid Basket basket,
//        BindingResult validationResult,
//        RedirectAttributes attributes,Principal principal){
//
//        if(validationResult.hasFieldErrors()){
//        attributes.addFlashAttribute("errors",validationResult.getFieldErrors());
//        return"redirect:/basket";
//        }
//        return"redirect:/api/basket";
//        }


    // метод для добавления в "корзину" через форму
// демонстрация добавления через объект HttpSession session
    @PostMapping("/api/basket/add")
    public String addToBasket(@RequestParam Integer id, HttpSession session, Model model, @Valid Basket basket, Principal principal) {
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
        return "redirect:/api/basket";
    }


    @PostMapping("/api/basket/empty")
    public String emptyBasket(HttpSession session, Principal principal,Model model) {
        session.removeAttribute(Constants.BASKET_ID);
        var user = userService.login(principal.getName());
        basketService.deleteBasket(user.getEmail());
        model.addAttribute("user", user);
        return "redirect:/api/basket";
    }
}
