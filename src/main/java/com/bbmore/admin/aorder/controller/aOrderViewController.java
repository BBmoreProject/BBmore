package com.bbmore.admin.aorder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class aOrderViewController {


    @GetMapping("/order-1-data")
    public String deliveryPage() {
        return "order/order-1-data";
    }

}
