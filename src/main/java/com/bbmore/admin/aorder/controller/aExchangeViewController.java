package com.bbmore.admin.aorder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class aExchangeViewController {

    @GetMapping("/order-3")
    public String exchangePage() {
        return "order/order-3";
    }
}