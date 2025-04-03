package com.bbmore.admin.adelivery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
public class OrderViewController {

    @GetMapping("/delivery-1")
    public String deliveryPage() {
        return "members/delivery-1";
    }
}