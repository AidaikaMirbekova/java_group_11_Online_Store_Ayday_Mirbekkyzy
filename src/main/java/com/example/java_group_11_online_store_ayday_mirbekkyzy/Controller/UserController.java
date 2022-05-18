package com.example.java_group_11_online_store_ayday_mirbekkyzy.Controller;

import com.example.java_group_11_online_store_ayday_mirbekkyzy.DTO.UserDTO;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Entity.User;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import static java.util.stream.Collectors.toList;

@Controller
@RequestMapping
public class UserController {
    public final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/register")
    public String register(@Valid User form, BindingResult validResult, RedirectAttributes attributes) throws Exception {
        attributes.addFlashAttribute("form",form);
        if (validResult.hasFieldErrors()){
            attributes.addFlashAttribute("errors",validResult.getFieldErrors());
            return "redirect:/main";
        }
        return "redirect:/profile";
    }

    @PostMapping("/login")
    public String login(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        userService.loadUserByUsername(user.getUsername());
        return "redirect:/main";
    }

    @GetMapping("/profile")
    public String pageCustomerProfile() {
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
