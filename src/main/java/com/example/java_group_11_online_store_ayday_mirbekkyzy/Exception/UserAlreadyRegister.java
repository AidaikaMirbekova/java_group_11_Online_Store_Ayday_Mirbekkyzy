package com.example.java_group_11_online_store_ayday_mirbekkyzy.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class UserAlreadyRegister extends RuntimeException{
}
