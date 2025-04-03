package com.bbmore.product.controller;

import com.bbmore.product.dto.OrderDTO;
import com.bbmore.product.dto.OrderHistDTO;
import com.bbmore.product.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping(value = "/order")
    public @ResponseBody ResponseEntity order(@RequestBody @Valid OrderDTO orderDTO,
            BindingResult bindingResult, Principal principal) {

        if (bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                sb.append(fieldError.getDefaultMessage());
            }
            return new ResponseEntity<String>(sb.toString(),
                    HttpStatus.BAD_REQUEST);
        }

        String email = principal.getName();
        Long orderId;

        try{
            orderId = orderService.order(orderDTO, email);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Long>(orderId, HttpStatus.OK);
    }

    @PostMapping("/order/{orderId}/cancel")
    public @ResponseBody ResponseEntity cancelOrder
            (@PathVariable Long orderId, Principal principal) {

        if(!orderService.validateOrder(orderId, principal.getName())) {
            return new ResponseEntity<String>("주문 취소 권한이 없습니다.",
                    HttpStatus.FORBIDDEN);
        }
        orderService.cancelOrder(orderId);
        return new ResponseEntity<Long>(orderId, HttpStatus.OK);
    }

    @GetMapping(value = {"/orders", "/orders/{page}"})
    public String orderHist(@PathVariable("page") Optional<Integer> page,
                            Principal principal, Model model) {
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 4);

        Page<OrderHistDTO> orderHistDTOList =
                orderService.getOrderList(principal.getName(), pageable);

        model.addAttribute("orders", orderHistDTOList);
        model.addAttribute("page", pageable.getPageNumber());
        model.addAttribute("maxPage", 5);
        return "order/orderHist";
    }
}
