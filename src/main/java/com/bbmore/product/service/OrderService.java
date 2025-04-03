package com.bbmore.product.service;

import com.bbmore.product.dto.OrderDTO;
import com.bbmore.product.dto.OrderHistDTO;
import com.bbmore.product.dto.OrderProductDTO;
import com.bbmore.product.entity.*;
import com.bbmore.product.repository.MemberRepository;
import com.bbmore.product.repository.OrderRepository;
import com.bbmore.product.repository.ProductImgRepository;
import com.bbmore.product.repository.ProductRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    /**
     * 상품과 회원 정보 조회
     * 주문 상품 객체 생성
     * 주문 객체 생성
     * 주문 저장
     * 주문 ID 반환
     */

    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final ProductImgRepository productImgRepository;

    public Long order(OrderDTO orderDTO, String email) {
        Product product = productRepository.findById(orderDTO.getProductId())
                .orElseThrow(EntityNotFoundException::new);
        Member member = memberRepository.findByEmail(email);

        List<OrderProduct> orderProductList = new ArrayList<>();
        OrderProduct orderProduct =
                OrderProduct.createOrderProduct(product, orderDTO.getCount());
        orderProductList.add(orderProduct);

        Order order = Order.createOrder(member, orderProductList);
        orderRepository.save(order);

        return order.getId();


    }

    @Transactional(readOnly = true)
    public boolean validateOrder(Long orderId, String email) {

        Member curMember = memberRepository.findByEmail(email);
        Order order = orderRepository.findById(orderId)
                .orElseThrow(EntityNotFoundException::new);
        Member savedMember = order.getMember();

        if (!StringUtils.equals(curMember.getEmail(),
                savedMember.getEmail())) {
            return false;
        }

        return true;
    }

    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(EntityNotFoundException::new);
        order.cancelOrder();
    }

    @Transactional(readOnly = true)
    public Page<OrderHistDTO> getOrderList(String email, Pageable pageable) {

        List<Order> orders = orderRepository.findOrders(email, pageable);
        Long totalCount = orderRepository.countOrder(email);

        List<OrderHistDTO> orderHistDTOList = new ArrayList<>();

        for(Order order : orders) {
            OrderHistDTO orderHistDTO = new OrderHistDTO(order);
            List<OrderProduct> orderProducts = order.getOrderProducts();
            for (OrderProduct orderProduct : orderProducts) {
                ProductImg productImg = productImgRepository
                        .findByProductIdAndRepresentativeImg(orderProduct.getProduct().getId(), "Y");
                OrderProductDTO orderProductDTO = new OrderProductDTO(orderProduct, productImg.getImgUrl());
                orderHistDTO.addOrderProductDTO(orderProductDTO);
            }
            orderHistDTOList.add(orderHistDTO);
        }
        return new PageImpl<>(orderHistDTOList, pageable, totalCount);
    }

}
