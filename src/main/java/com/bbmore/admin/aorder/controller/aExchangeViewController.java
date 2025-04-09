package com.bbmore.admin.aorder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

public class aExchangeViewController {

    @Controller
    @RequestMapping("/order")
    public class aOrderViewController {


        @GetMapping("/order-3")
        public String deliveryPage() {
            return "order/order-3";
        }
    }
}