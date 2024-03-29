package com.example.java_group_11_online_store_ayday_mirbekkyzy.DTO;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserRegisterForm {

    @NotBlank
    @Email
    private String email = "";

    @Size(min=4, max=24, message = "Length must be >= 4 and <= 24")
    @Pattern(regexp = "^[^\\d\\s]+$", message = "Should contain only letters")
    private String name = "";

    @NotBlank
    @Size(min = 5,max = 15,message = "Login cannot be shorter than 5 letters and longer than 15 letters!!!")
    private String login="";

    @NotBlank
    private String password = "";
}
