package com.example.java_group_11_online_store_ayday_mirbekkyzy.Controller;

import com.example.java_group_11_online_store_ayday_mirbekkyzy.DTO.TokenDTO;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.DTO.UserRegisterForm;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Exception.UserNotFoundException;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Repository.UserRepository;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Service.TokenService;
import com.example.java_group_11_online_store_ayday_mirbekkyzy.Service.UserService;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class UserController {
    public final UserService userService;
    public final TokenService tokenService;
    public final UserRepository userRepository;


    @GetMapping("/register")
    public String pageRegisterCustomer(Model model) {
        if (!model.containsAttribute("user")) {
            model.addAttribute("user", new UserRegisterForm());
        }
        return "register";
    }


    @PostMapping("/register")
    public String register(@Valid UserRegisterForm form, BindingResult validResult, RedirectAttributes attributes) {
        attributes.addFlashAttribute("user", form);
        if (validResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validResult.getFieldErrors());
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

    @GetMapping("/api/profile")
    public String pageCustomerProfile(Model model, Principal principal) {
        var user = userService.login(principal.getName());
        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping("/createToken")
    public String showCreateTokenPage(Model model) {
        return "init_user";
    }

    @GetMapping("/resetPassword")
    public String showResetPage(Model model) {
        return "reset_password";
    }


    @PostMapping("/createToken")
    public String createToken(String email, @Valid TokenDTO token, Model model, BindingResult validResult, RedirectAttributes attributes) {
        var user = userRepository.findUserByEmail(email).orElseThrow(()->new UserNotFoundException("User not found!!!!"));
        model.addAttribute("user", user.getEmail());
        tokenService.createToken(user.getEmail());
        return "redirect:/resetPassword";
    }

    @PostMapping("/resetPassword")
    public String resetPassword(@RequestPart String email, @RequestPart String token, @RequestPart(name = "password") String newPassword, Model model, BindingResult validResult, RedirectAttributes attributes) {

        model.addAttribute("email", email);
        model.addAttribute("token", token);
        model.addAttribute("password", newPassword);
        tokenService.resetPassword(email, token, newPassword);
        return "redirect:/login";
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
