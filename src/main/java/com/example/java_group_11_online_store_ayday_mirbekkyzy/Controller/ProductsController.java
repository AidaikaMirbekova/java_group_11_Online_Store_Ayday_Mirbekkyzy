package com.example.java_group_11_online_store_ayday_mirbekkyzy.Controller;

import com.example.java_group_11_online_store_ayday_mirbekkyzy.DTO.ProductsDTO;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Repository.ProductsRepository;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Service.ProductsService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ProductsController {
    public final ProductsService productsService;
    private final ModelMapper mapper = new ModelMapper();

    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;

    }

    @GetMapping
    public String showProducts(Model model){
     var products = productsService.getAllProducts();
     model.addAttribute("products",products.getContent());
     return "main";
    }
}
