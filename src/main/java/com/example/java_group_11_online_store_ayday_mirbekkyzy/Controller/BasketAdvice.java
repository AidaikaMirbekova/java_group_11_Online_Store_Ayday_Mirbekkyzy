package com.example.java_group_11_online_store_ayday_mirbekkyzy.Controller;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class BasketAdvice {
    @ModelAttribute(Constants.BASKET_ID)
    public List<String> getBasketModel(HttpSession session) {
        var list = session.getAttribute(Constants.BASKET_ID);
        if (list == null) {
            session.setAttribute(Constants.BASKET_ID, new ArrayList<>());
        }
        return (List<String>) session.getAttribute(Constants.BASKET_ID);
    }
}
