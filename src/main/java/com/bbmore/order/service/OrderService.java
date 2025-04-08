package com.bbmore.order.service;

import com.bbmore.order.dto.OrderHistDto;
import com.bbmore.order.dto.OrderRequestDto;
import com.bbmore.order.entity.Order;
import com.bbmore.order.entity.OrderDetail;
import com.bbmore.order.repository.OrderRepository;
import com.bbmore.product.entity.Member;
import com.bbmore.product.entity.Product;
import com.bbmore.product.repository.MemberRepository;
import com.bbmore.product.repository.ProductImgRepository;
import com.bbmore.product.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final ProductImgRepository productImgRepository;

    public Integer order(OrderRequestDto orderRequestDto,
                      String email) {
        Product product = productRepository.findById(
                        (orderRequestDto.getProductCode())
                ) .orElseThrow(EntityNotFoundException::new);

        Member member = memberRepository.findByUserEmail(email);

        List<OrderDetail> orderProductList = new ArrayList<>();
        OrderDetail orderDetail =
                OrderDetail.createOrderProduct(product, orderRequestDto.getCount());
        orderProductList.add(orderDetail);

        Order order = Order.createOrder(member, orderProductList);
        orderRepository.save(order);

        return order.getOrderCode();
    }

//    @Transactional(readOnly = true)
//    public Page<OrderHistDto> getOrderList(String userEmail, Pageable pageable) {
//
//        List<Order> orders = orderRepository.findOrders(userEmail, pageable);
//        Long totalCount = orderRepository.
//
//    }
}
