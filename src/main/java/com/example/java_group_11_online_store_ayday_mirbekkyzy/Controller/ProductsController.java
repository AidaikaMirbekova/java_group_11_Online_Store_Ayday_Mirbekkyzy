package com.example.java_group_11_online_store_ayday_mirbekkyzy.Controller;

import com.example.java_group_11_online_store_ayday_mirbekkyzy.Service.ProductsService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ProductsController {
    public final ProductsService productsService;

    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping
    public String showProducts(Model model,@PageableDefault (sort = "id",direction = Sort.Direction.DESC,value = 4) Pageable page) {
        var products = productsService.getAllProducts(page);
        var count = products.getTotalPages()-1;
        model.addAttribute("products", products.getContent());
        model.addAttribute("pages", products.getPageable());
        model.addAttribute("lastPage",count);
        return "main";
    }
}
