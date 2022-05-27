package com.example.java_group_11_online_store_ayday_mirbekkyzy.Controller;

import com.example.java_group_11_online_store_ayday_mirbekkyzy.Exception.UserAlreadyRegister;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(basePackageClasses = {
        UserController.class,
        BasketController.class,
        ProductsController.class
})
public class UserControllerAdvice {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private String userNotFound(UserNotFoundException ex, Model model){
        model.addAttribute("recource", ex.getResource());
        model.addAttribute("email",ex.getEmail());
        return "user-not-found";
    }

    @ExceptionHandler(UserAlreadyRegister.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    private String userExistRegister(UserAlreadyRegister ex, Model model){
        model.addAttribute("recource", ex.getResource());
        model.addAttribute("email",ex.getEmail());
        return "user-not-found";
    }
}
