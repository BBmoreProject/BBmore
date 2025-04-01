package com.bbmore.member.controller;

import com.bbmore.member.entity.UserCart;
import com.bbmore.member.service.UserCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class UserCartController {

    @Autowired
    private UserCartService userCartService;

//     사용자의 장바구니 목록 조회
    @GetMapping("/cart-list")
    public String getUserCart(Model model) {
        Integer userCode = 2; // 실제 사용자 코드로 교체
        List<UserCart> cartList = userCartService.getCartListByUser(userCode);
        model.addAttribute("cartList", cartList);
        return "cart/cart-list";
    }

    // 2. 장바구니 상품 수량 조정
//    @PutMapping("/{cartCode}")
//    public String updateCartQuantity(@PathVariable Integer cartCode, @RequestParam Integer quantity) {
//        userCartService.updateCartQuantity(cartCode, quantity);
//        return "장바구니 수량이 업데이트되었습니다.";
//    }


    // 장바구니에 상품 추가
//    @PostMapping("/add")
//    public String addProductToCart(@RequestBody CartRequest cartRequest) {
//        // 서비스 호출
//        return userCartService.addProductToCart(cartRequest.getProductCode(), cartRequest.getUserCode(), cartRequest.getCartProductQuantity());
//    }

    //  // 장바구니에서 상품 제거
//    @DeleteMapping("/")
//    public String deleteCartItems(@RequestParam List<Integer> cartCodes) {
//        userCartService.deleteCartItems(cartCodes);
//        return "선택한 상품들이 장바구니에서 삭제되었습니다.";
//    }
}
