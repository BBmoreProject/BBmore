package com.bbmore.admin.aorder.controller;

import com.bbmore.admin.aorder.dto.OrderSearchResultDTO;
import com.bbmore.admin.aorder.service.OrderService;
import com.bbmore.order.dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/search")
    public List<OrderSearchResultDTO> searchOrders(
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        return orderService.searchOrders(code, name, phone, startDate, endDate);
    }
}






//    @GetMapping("/order-1")
//    public String findOrderList(Model model, @PageableDefault Pageable pageable){
//
//        /* 페이징 처리 이전 */
//      List<MenuDTO> menuList = menuService.findMenuList();
//       model.addAttribute("menuList", menuList);
//
//        /* 페이징 처리 이후 */
//        log.info("pageable: {}", pageable);
//
//        Page<OrderDTO> orderList = orderService.findOrderList(pageable);
//
//        log.info("{}", orderList.getContent());
//        log.info("{}", orderList.getTotalPages());
//        log.info("{}", orderList.getTotalElements());
//        log.info("{}", orderList.getSize());
//        log.info("{}", orderList.getNumberOfElements());
//        log.info("{}", orderList.isFirst());
//        log.info("{}", orderList.isLast());
//        log.info("{}", orderList.getSort());
//        log.info("{}", orderList.getNumber());
//
//
//        PagingButton paging = Pagenation.getPagingButtonInfo(orderList);
//
//        model.addAttribute( "orderList", orderList);
//        model.addAttribute("paging", paging);
//
//
//        return "order/order-1";
//    }

//@GetMapping("/order-1-data")
//public String searchOrders(
//        @RequestParam(required = false) String username,
//        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
//        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
//        Model model, @PageableDefault Pageable pageable
//) {
//    List<Order> orders = new ArrayList<>();
//
//    if (username != null && startDate != null && endDate != null) {
//        orders = orderService.getOrdersByUsernameAndDate(username, startDate, endDate);
//    }
//
//    model.addAttribute("orders", orders);
//
//    log.info("pageable: {}", pageable);
//
//    Page<OrderDTO> orderList = orderService.findOrderList(pageable);
//
//    log.info("{}", orderList.getContent());
//    log.info("{}", orderList.getTotalPages());
//    log.info("{}", orderList.getTotalElements());
//    log.info("{}", orderList.getSize());
//    log.info("{}", orderList.getNumberOfElements());
//    log.info("{}", orderList.isFirst());
//    log.info("{}", orderList.isLast());
//    log.info("{}", orderList.getSort());
//    log.info("{}", orderList.getNumber());
//
//
//    PagingButton paging = Pagenation.getPagingButtonInfo(orderList);
//
//    model.addAttribute( "orderList", orderList);
//    model.addAttribute("paging", paging);
//
//    return "order/order-1-data";
//}



