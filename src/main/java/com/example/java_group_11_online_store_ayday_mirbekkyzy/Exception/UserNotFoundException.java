package com.example.java_group_11_online_store_ayday_mirbekkyzy.Exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
@Getter @Setter
public class UserNotFoundException extends RuntimeException{
    private String resource;
    private String email;

    public UserNotFoundException(String resource, String email) {
        this.resource = resource;
        this.email = email;
    }
}
