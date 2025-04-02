package com.bbmore.member.service;


import com.bbmore.member.entity.UserCart;
import com.bbmore.member.repository.MemberRepository;
import com.bbmore.member.repository.ProductRepository;
import com.bbmore.member.repository.UserCartRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
@Slf4j
@Service
public class UserCartService {

//    @Autowired  // ver.1 수량은 불러와짐
//    private  UserCartRepository userCartRepository;

    @Autowired
    private UserCartRepository userCartRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ProductRepository productRepository;

    // 1. 특정 사용자에 대한 장바구니 목록 조회
    public List<UserCart> getCartListByUser(Integer userCode) {
        // 주어진 userCode에 해당하는 장바구니 정보 조회

        return userCartRepository.findByMember_UserCode(userCode);
    }

    // 장바구니 상품 수량 업데이트
    @Transactional
    public void updateCartQuantity(Integer cartCode, Integer newQuantity) {

        userCartRepository.updateQuantity(cartCode, newQuantity);

    }


    // 3. 장바구니에서 선택된 상품 삭제
//    public void deleteCartItems(List<Integer> cartCodes) {
//        for (Integer cartCode : cartCodes) {
//            userCartRepository.deleteById(cartCode);  // 해당 장바구니 상품을 삭제
//        }
//    }

}


