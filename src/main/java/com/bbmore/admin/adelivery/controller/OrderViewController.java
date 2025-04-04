package com.bbmore.admin.adelivery.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderViewController {

    @GetMapping("/delivery-1")
    public String deliveryPage() {
        return "members/delivery-1";
    }
}
