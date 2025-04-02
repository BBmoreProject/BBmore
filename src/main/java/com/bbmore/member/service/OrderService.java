package com.bbmore.member.service;

import com.bbmore.member.dto.PurchaseListDTO;
import com.bbmore.member.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List<PurchaseListDTO> getUserPurchaseList(Integer userCode) {
        return orderRepository.findUserPurchaseList(userCode);
    }
}
