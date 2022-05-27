package com.example.java_group_11_online_store_ayday_mirbekkyzy.Exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
@Getter @Setter
public class UserAlreadyRegister extends RuntimeException{
    private String resource;
   private String email;

    public UserAlreadyRegister(String resource, String email) {
        super();
        this.resource = resource;
        this.email = email;
    }
}
