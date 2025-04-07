package com.bbmore.member.controller;

import com.bbmore.member.dto.PurchaseListDTO;
import com.bbmore.member.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/user/purchaseList")
    public String getUserPurchaseList(Model model){
        Integer userCode = 3; // 특정 회원의 주문 내역

        List<PurchaseListDTO> purchaseList = orderService.getUserPurchaseList(userCode);

        model.addAttribute("purchaseList", purchaseList);
        return "user_purchase_list";
    }

}
