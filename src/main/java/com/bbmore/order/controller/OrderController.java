package com.bbmore.order.controller;


import com.bbmore.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j      // 로거 객체 선언을 위한 어노테이션
@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/order-1")
    public String orderSearch() {
        return "order/order-1";
    }

    @GetMapping("/order-2")
    public String orderReturn() {
        return "order/order-2";
    }

    //    @PostMapping
    @GetMapping("/order-3")
    public String orderChange() {
        return "order/order-3";
    }

}
