package com.bbmore.member.service;

import com.bbmore.member.dto.UserCartDTO;
import com.bbmore.member.entity.Member;
import com.bbmore.member.entity.Product;
import com.bbmore.member.entity.UserCart;
import com.bbmore.member.repository.MemberRepository;
import com.bbmore.member.repository.ProductRepository;
import com.bbmore.member.repository.UserCartRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import java.util.List;
@Slf4j
@Service
public class UserCartService {

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

    // 2. 장바구니 상품 수량 업데이트
    public void updateCartQuantity(Integer cartCode, Integer quantity) {
        UserCart cart = userCartRepository.findById(cartCode)
                .orElseThrow(() -> new IllegalArgumentException("장바구니가 존재하지 않습니다."));

        cart.setCartProductQuantity(quantity);
        userCartRepository.save(cart);  // 수량 업데이트 후 저장
    }


    // 3. 장바구니에서 선택된 상품 삭제
    public void deleteCartItems(List<Integer> cartCodes) {
        for (Integer cartCode : cartCodes) {
            userCartRepository.deleteById(cartCode);  // 해당 장바구니 상품을 삭제
        }
    }

    // 장바구니에 상품 추가
//    @Transactional
//    public String addProductToCart(Integer productCode, Integer userCode, Integer quantity) {
//        // 회원과 상품 조회
//        Member member = memberRepository.findById(userCode)
//                .orElseThrow(() -> new RuntimeException("회원이 존재하지 않습니다."));
//        Product product = productRepository.findById(productCode)
//                .orElseThrow(() -> new RuntimeException("상품이 존재하지 않습니다."));
//
//        // 이미 장바구니에 해당 상품이 존재하는지 확인
//        UserCart existingCart = userCartRepository.findByMember_UserCodeAndProduct_ProductCode(userCode, productCode);
//
//        if (existingCart != null) {
//            // 기존에 존재하면 수량만 업데이트
//            existingCart.setCartProductQuantity(existingCart.getCartProductQuantity() + quantity);
//            userCartRepository.save(existingCart);
//            return "상품 수량이 업데이트되었습니다.";
//        } else {
//            // 새로 장바구니에 추가
//            UserCart newCart = new UserCart(quantity, member, product);
//            userCartRepository.save(newCart);
//            return "상품이 장바구니에 추가되었습니다.";
//        }
    }


