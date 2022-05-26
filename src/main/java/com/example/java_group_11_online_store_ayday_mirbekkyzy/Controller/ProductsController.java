package com.example.java_group_11_online_store_ayday_mirbekkyzy.Controller;

import com.example.java_group_11_online_store_ayday_mirbekkyzy.DTO.ProductsDTO;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Service.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class ProductsController {
    public final ProductsService productsService;

    @GetMapping
    public String showProducts(Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC, value = 4) Pageable page) {
        var products = productsService.getAllProducts(page);

        model.addAttribute("products", products.getContent());
        model.addAttribute("pages", products.getPageable());
        model.addAttribute("lastPages", products.hasNext());
        return "main";
    }

    @GetMapping("/oneProduct")
    public String showOneProduct(@RequestParam Integer id, Model model){
        var product = productsService.getOneProduct(id);
        model.addAttribute("product",product);
        return "one_product";
    }

    @RequestMapping("/search")
    public String searchProducts(Model model, @Param("keyword") String keyword, @PageableDefault(sort = "id", direction = Sort.Direction.DESC, value = 4) Pageable page) {
        Page<ProductsDTO> products = productsService.searchProducts(keyword, page);

        model.addAttribute("products", products.getContent());
        model.addAttribute("keyword", keyword);
        model.addAttribute("pages", products.getPageable());
        model.addAttribute("lastPages", products.hasNext());
        return "searchProducts";
    }

    @RequestMapping("/priceSearch")
    public String searchByPrice(Model model, @Param("more") Float more, @Param("less") Float less, @PageableDefault(sort = "id", direction = Sort.Direction.DESC, value = 4) Pageable page) {
        Page<ProductsDTO> products = productsService.priceSearch(more, less, page);

        model.addAttribute("products", products.getContent());
        model.addAttribute("more", more);
        model.addAttribute("less", less);
        model.addAttribute("pages", products.getPageable());
        model.addAttribute("lastPages", products.hasNext());

        return "searchProducts";
    }
}
