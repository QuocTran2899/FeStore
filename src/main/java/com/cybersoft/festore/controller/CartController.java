package com.cybersoft.festore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/cart")
public class CartController {

    @GetMapping("")
    public String viewCart(){
        return "cart";
    }
}
