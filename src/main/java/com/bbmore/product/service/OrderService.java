package com.bbmore.product.service;

import com.bbmore.product.dto.OrderDTO;
import com.bbmore.product.entity.Member;
import com.bbmore.product.entity.Order;
import com.bbmore.product.entity.OrderProduct;
import com.bbmore.product.entity.Product;
import com.bbmore.product.repository.MemberRepository;
import com.bbmore.product.repository.OrderRepository;
import com.bbmore.product.repository.ProductRepository;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Long order(OrderDTO orderDTO, String email) {
        Product product = productRepository.findById(orderDTO.getProductId())
                .orElseThrow(EntityExistsException::new);
        Member member = memberRepository.findByEmail(email);

        List<OrderProduct> orderProductList = new ArrayList<>();
        OrderProduct orderProduct =
                OrderProduct.createOrderProduct(product, orderDTO.getCount());
        orderProductList.add(orderProduct);

        Order order = Order.createOrder(member, orderProductList);
        orderRepository.save(order);

        return order.getId();


    }
}
