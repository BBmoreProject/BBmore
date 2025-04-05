package com.bbmore.admin.adelivery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
public class OrderViewController {

    @GetMapping("/delivery-1")
    public String deliveryPage() {
        // 뷰 템플릿 → templates/members/delivery-1.html
        return "members/delivery-1";
    }
}
