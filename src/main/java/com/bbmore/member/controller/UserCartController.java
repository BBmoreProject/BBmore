package com.bbmore.member.controller;

import com.bbmore.member.dto.UserCartDTO;
import com.bbmore.member.entity.UserCart;
import com.bbmore.member.service.UserCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cart")
public class UserCartController {

    @Autowired
    private UserCartService userCartService;

    // 사용자의 장바구니 목록 조회
    @GetMapping("/viewcart/{userCode}")
    public List<UserCart> getUserCart(@PathVariable Integer userCode) {
        return userCartService.getCartListByUser(userCode);
    }

    // 2. 장바구니 상품 수량 조정
    @PutMapping("/{cartCode}")
    public String updateCartQuantity(@PathVariable Integer cartCode, @RequestParam Integer quantity) {
        userCartService.updateCartQuantity(cartCode, quantity);
        return "장바구니 수량이 업데이트되었습니다.";
    }


    // 장바구니에 상품 추가
    @PostMapping("/add")
    public String addProductToCart(@RequestBody CartRequest cartRequest) {
        // 서비스 호출
        return userCartService.addProductToCart(cartRequest.getProductCode(), cartRequest.getUserCode(), cartRequest.getCartProductQuantity());
    }

    // DTO 클래스 (요청을 받기 위한 클래스)
    public static class CartRequest {
        private Integer productCode;
        private Integer userCode;
        private Integer cartProductQuantity;

        // Getters and Setters
        public Integer getProductCode() {
            return productCode;
        }

        public void setProductCode(Integer productCode) {
            this.productCode = productCode;
        }

        public Integer getUserCode() {
            return userCode;
        }

        public void setUserCode(Integer userCode) {
            this.userCode = userCode;
        }

        public Integer getCartProductQuantity() {
            return cartProductQuantity;
        }

        public void setCartProductQuantity(Integer cartProductQuantity) {
            this.cartProductQuantity = cartProductQuantity;
        }
    }

    //  // 장바구니에서 상품 제거
    @DeleteMapping("/")
    public String deleteCartItems(@RequestParam List<Integer> cartCodes) {
        userCartService.deleteCartItems(cartCodes);
        return "선택한 상품들이 장바구니에서 삭제되었습니다.";
    }
}
