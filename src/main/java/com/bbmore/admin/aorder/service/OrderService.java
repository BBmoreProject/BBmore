package com.bbmore.admin.aorder.service;


import com.bbmore.admin.aorder.repository.OrderRepository;
import com.bbmore.member.dto.MemberDTO;
import com.bbmore.order.dto.OrderDTO;
import com.bbmore.order.entity.Order;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    public List<OrderDTO> searchOrders(String code, String name, String phone, LocalDate startDate, LocalDate endDate) {
        String c = (code == null || code.isBlank()) ? null : code;
        String n = (name == null || name.isBlank()) ? null : name;
        String p = (phone == null || phone.isBlank()) ? null : phone;

        List<Order> orders = orderRepository.searchOrders(c, n, p, startDate, endDate);
        return orders.stream().map(this::toDto).collect(Collectors.toList());
    }

    private OrderDTO toDto(Order o) {
        return OrderDTO.builder()
                .orderCode(o.getOrderCode())
                .orderDate(o.getOrderDate())
                .productName(o.getProductName())
                .orderTotalPrice(o.getOrderTotalPrice())
                .orderStatus(o.getOrderStatus())
                .recipientAddress(o.getRecipientAddress())
                .recipientPhone(o.getRecipientPhone())
                .orderDeliveryRequest(o.getOrderDeliverYRequest())
                .productQuantity(o.getProductQuantity())
                .member(MemberDTO.builder()
                        .userName(o.getMember().getUserName())
                        .userPhoneNumber(o.getMember().getUserPhoneNumber())
                        .userAddress(o.getMember().getUserAddress())
                        .build())
                .build();
    }



//    private OrderDTO toDto(Order o) {
//        System.out.println("USER: " + o.getMember().getUserName() + ", PHONE: " + o.getMember().getUserPhoneNumber());
//
//        return OrderDTO.builder()
//                .orderCode(o.getOrderCode())
//                .orderDate(o.getOrderDate())
//                .productName(o.getProductName())
//                .member(MemberDTO)
//                .build();
////                MemberDTO.builder()
////                .userName(o.getMember().getUserName())
////                .userPhoneNumber(o.getMember().getUserPhoneNumber())
////                .userAddress(o.getMember().getUserAddress())
////                .build();
//    }



//    /* findById */
//    public OrderDTO findMenuByMenuCode(int menuCode) {
//
//        Order foundMenu = orderRepository.findById(menuCode).orElseThrow(IllegalArgumentException::new);
//
//        return modelMapper.map(foundMenu, OrderDTO.class);
//    }
//
//
//    /* findAll : Sort */
//    public List<OrderDTO> findOrderList() {
//        List<Order> orderList = orderRepository.findAll(Sort.by("orderCode").descending());
//        return orderList.stream()
//                .map(order -> modelMapper.map(order, OrderDTO.class))
//                .toList();
//    }
//
//    /* findAll : Pageable */
//    public Page<OrderDTO> findOrderList(Pageable pageable){
//        pageable = PageRequest.of(
//                pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
//                pageable.getPageSize(),
//                Sort.by("OrderCode").descending()
//        );
//        Page<Order> orderList = orderRepository.findAll(pageable);
//        return orderList.map(order -> {
//            OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);
//            // MemberDTO 매핑
//            orderDTO.setMember(modelMapper.map(order.getMember(), MemberDTO.class));
//            return orderDTO;
//        });
//    }
//
//    public List<Order> getOrdersByUsernameAndDate(String username, LocalDate startDate, LocalDate endDate) {
//        return orderRepository.findOrdersByUsernameAndDateRange(username, startDate, endDate);
//    }


}
