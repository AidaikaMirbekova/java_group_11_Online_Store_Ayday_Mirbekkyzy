package com.example.java_group_11_online_store_ayday_mirbekkyzy.Controller;

import com.example.java_group_11_online_store_ayday_mirbekkyzy.DTO.ReviewsDTO;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Service.ReviewsService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@Data
@AllArgsConstructor
public class ReviewController {

    private final UserService userService;
    private final ReviewsService reviewsService;

    @GetMapping("/reviews")
    public String showReview(Model model,@PageableDefault(sort = "date", direction = Sort.Direction.DESC) Pageable page){
        var reviews= reviewsService.getReview(page);
        model.addAttribute("pages", reviews.getPageable());
        model.addAttribute("lastPages", reviews.hasNext());
        if (!reviews.isEmpty()) {
            model.addAttribute("reviewsList", reviews.getContent());
        }

        return "reviews";
    }

    @PostMapping("/api/addReview")
    public String addReview(Principal principal, Model model, @Valid ReviewsDTO reviewsDTO){
        var user = userService.login(principal.getName());
        model.addAttribute("user", user);
        reviewsService.addReview(user.getEmail(),reviewsDTO);
        return "redirect:/reviews";
    }
}
