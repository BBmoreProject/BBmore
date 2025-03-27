package com.bbmore.order.service;

import com.bbmore.order.dto.OrderDTO;
import com.bbmore.order.entity.Order;
import com.bbmore.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    /* findById */
    public OrderDTO findMenuByMenuCode(int menuCode) {

        Order foundMenu = orderRepository.findById(menuCode).orElseThrow(IllegalArgumentException::new);

        return modelMapper.map(foundMenu, OrderDTO.class);
    }


    /* findAll : Sort */
    public List<OrderDTO> findOrderList() {
        List<Order> orderList = orderRepository.findAll(Sort.by("orderCode").descending());
        return orderList.stream()
                .map(order -> modelMapper.map(order, OrderDTO.class))
                .toList();
    }

    /* findAll : Pageable */
    public Page<OrderDTO> findOrderList(Pageable pageable){
        pageable = PageRequest.of(
                pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
                pageable.getPageSize(),
                Sort.by("OrderCode").descending()
        );
        Page<Order> OrderList = orderRepository.findAll(pageable);
        return OrderList.map(order -> modelMapper.map(order, OrderDTO.class));
    }
}
