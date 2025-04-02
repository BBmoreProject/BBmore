package com.bbmore.order.controller;


import com.bbmore.order.common.Pagenation;
import com.bbmore.order.common.PagingButton;
import com.bbmore.order.dto.OrderDTO;
import com.bbmore.order.entity.Order;
import com.bbmore.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j      // 로거 객체 선언을 위한 어노테이션
@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @GetMapping("/order-2")
    public String orderReturn() {
        return "order/order-2";
    }

    //    @PostMapping
    @GetMapping("/order-3")
    public String orderChange() {
        return "order/order-3";
    }

//    @GetMapping("/order-4")
//    public String orderAllFind() {
//        return "order/order-4";
//    }

//    @GetMapping("/{orderCode}")
//    public String findOrderByCode(@PathVariable int menuCode, Model model) {
//
//        OrderDTO resultMenu = orderService.findMenuByMenuCode(menuCode);
//        model.addAttribute("menu",resultMenu);
//
//        return "menu/detail")
//

    @GetMapping("/order-1")
    public String findOrderList(Model model, @PageableDefault Pageable pageable){

        /* 페이징 처리 이전 */
//        List<MenuDTO> menuList = menuService.findMenuList();
//        model.addAttribute("menuList", menuList);

        /* 페이징 처리 이후 */
        log.info("pageable: {}", pageable);

        Page<OrderDTO> orderList = orderService.findOrderList(pageable);

        log.info("{}", orderList.getContent());
        log.info("{}", orderList.getTotalPages());
        log.info("{}", orderList.getTotalElements());
        log.info("{}", orderList.getSize());
        log.info("{}", orderList.getNumberOfElements());
        log.info("{}", orderList.isFirst());
        log.info("{}", orderList.isLast());
        log.info("{}", orderList.getSort());
        log.info("{}", orderList.getNumber());


        PagingButton paging = Pagenation.getPagingButtonInfo(orderList);

        model.addAttribute( "orderList", orderList);
        model.addAttribute("paging", paging);


        return "order/order-1";
    }


    @GetMapping("/order-1-data")
    public String searchOrders(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            Model model, @PageableDefault Pageable pageable
    ) {
        List<Order> orders = new ArrayList<>();

        if (username != null && startDate != null && endDate != null) {
            orders = orderService.getOrdersByUsernameAndDate(username, startDate, endDate);
        }

        model.addAttribute("orders", orders);

        log.info("pageable: {}", pageable);

        Page<OrderDTO> orderList = orderService.findOrderList(pageable);

        log.info("{}", orderList.getContent());
        log.info("{}", orderList.getTotalPages());
        log.info("{}", orderList.getTotalElements());
        log.info("{}", orderList.getSize());
        log.info("{}", orderList.getNumberOfElements());
        log.info("{}", orderList.isFirst());
        log.info("{}", orderList.isLast());
        log.info("{}", orderList.getSort());
        log.info("{}", orderList.getNumber());


        PagingButton paging = Pagenation.getPagingButtonInfo(orderList);

        model.addAttribute( "orderList", orderList);
        model.addAttribute("paging", paging);

        return "order/order-1-data";
    }


    @GetMapping("/order-4")
    public String searchOrders1(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            Model model
    ) {
        List<Order> orders = new ArrayList<>();

        if (username != null && startDate != null && endDate != null) {
            orders = orderService.getOrdersByUsernameAndDate(username, startDate, endDate);
        }

        model.addAttribute("orders", orders);
        return "order/order-4";
    }

}
