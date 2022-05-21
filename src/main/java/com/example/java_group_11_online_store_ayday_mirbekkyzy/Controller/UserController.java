package com.example.java_group_11_online_store_ayday_mirbekkyzy.Controller;

import com.example.java_group_11_online_store_ayday_mirbekkyzy.DTO.UserRegisterForm;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import java.security.Principal;

import static java.util.stream.Collectors.toList;

@Controller
@RequestMapping
public class UserController {
    public final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/register")
    public String pageRegisterCustomer(Model model) {
        if (!model.containsAttribute("user")) {
            model.addAttribute("user", new UserRegisterForm());
        }
        return "register";
    }


    @PostMapping("/register")
    public String register(@Valid UserRegisterForm form, BindingResult validResult, RedirectAttributes attributes) {
        attributes.addFlashAttribute("user",form);
        if (validResult.hasFieldErrors()){
            attributes.addFlashAttribute("errors",validResult.getFieldErrors());
            return "redirect:/register";
        }
        userService.register(form);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(required = false, defaultValue = "false") Boolean error, Model model) {
        model.addAttribute("error", error);
        return "login";
    }

    @GetMapping("/profile")
    public String pageCustomerProfile(Model model, Principal principal) {
        var user = userService.login(principal.getName());
        model.addAttribute("user", user);
        return "profile";
    }


    @ExceptionHandler(BindException.class)
    private ResponseEntity<Object> handleBindExceptionResponseEntity(BindException ex) {
        var apiFieldErrors = ex.getFieldErrors()
                .stream()
                .map(fe -> String.format("%s -> %s", fe.getField(), fe.getDefaultMessage()))
                .collect(toList());
        return ResponseEntity.unprocessableEntity()
                .body(apiFieldErrors);
    }
}
