package com.bbmore.member.controller;

import com.bbmore.member.entity.UserCart;
import com.bbmore.member.service.UserCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")    // "/cart" 경로로 시작하는 모든 요청 처리
public class UserCartController {


    private final UserCartService userCartService;

    // 사용자의 장바구니 목록 조회
    @GetMapping("/cart-list")   // 실제 경로는 "/cart/cart-list"
    public String getUserCart(Model model) {
        Integer userCode = 3; // 실제 사용자 코드로 교체
        List<UserCart> cartList = userCartService.getCartListByUser(userCode);

        // 상품 금액 계산: 각 상품의 가격 * 수량을 합산
        int totalProductPrice = cartList.stream()
            .mapToInt(cart -> cart.getProduct().getProductPrice() * cart.getCartProductQuantity())
            .sum();

        // 배송비 설정: 예를 들어, 배송비를 고정 값으로 설정
        int deliveryFee = 3000; // 예시: 3000원 배송비

        // 총 결제 금액 계산: 상품 금액 + 배송비
        int totalAmount = totalProductPrice + deliveryFee;

        // 디버깅
        System.out.println("Total Product Price: " + totalProductPrice);
        System.out.println("Delivery Fee: " + deliveryFee);
        System.out.println("Total Amount (Total Product Price + Delivery Fee): " + totalAmount);

        // Model에 장바구니 목록& 총 금액 전달
        model.addAttribute("cartList", cartList);
        model.addAttribute("totalAmount", totalAmount);     // 주문총금액=상품총금액+배송비
        model.addAttribute("totalProductPrice", totalProductPrice); // 상품총금액
        model.addAttribute("deliveryFee", deliveryFee);     // 배송비

        return "cart/cart-list";
    }

    // 장바구니 수량 변경
    @PostMapping("/cart-list/updateQuantity")
    @ResponseBody
    public Map<String, Object> updateCartQuantity(@RequestBody Map<String, Object> requestData) {

//        int cartCode = (int) payload.get("cartCode");
//        int newQuantity = (int) payload.get("newQuantity");
//
//        boolean success = userCartService.updateCartQuantity(cartCode, newQuantity);

        Map<String, Object> response = new HashMap<>();
//        response.put("success", success);


        // cartCode, newQuantity 값을 가져옴
        // Optional을 사용하여 null을 안전하게 처리
        Optional<Object> cartCodeObj = Optional.ofNullable(requestData.get("cartCode"));
        Optional<Object> newQuantityObj = Optional.ofNullable(requestData.get("newQuantity"));

        if (cartCodeObj.isPresent() && newQuantityObj.isPresent()) {
            try {
                // cartCode와 newQuantity가 존재하면 숫자로 변환
                Integer cartCode = Integer.parseInt(cartCodeObj.get().toString());
                Integer newQuantity = Integer.parseInt(newQuantityObj.get().toString());

                // 서비스 호출하여 db에 장바구니 수량 업데이트
                userCartService.updateCartQuantity(cartCode, newQuantity);

                // 성공하면
                response.put("success", true);

            } catch (NumberFormatException e) {
                response.put("success", false);
                response.put("error", "숫자 형식이 올바르지 않습니다.");
            }
        } else {
            System.out.println("cartCode: " + cartCodeObj);
            System.out.println("newQuantity: " + newQuantityObj);
            response.put("success", false);
            response.put("error", "cartCode 또는 newQuantity가 요청에서 누락되었습니다.");
        }

        return response;    // JSON 응답 반환
    }

    // 장바구니에서 상품 제거
    @DeleteMapping("/")
    @ResponseBody   // JSON 응답을 반환할 수 있게 또는 메시지 텍스트를 직접 반환
    public String deleteCartItems(@RequestParam List<Integer> cartCodes) {  //URL 파라미터로 전달된 여러 개의 cartCode를 한 번에 받습니다.
        userCartService.deleteCartItems(cartCodes);
        return "선택한 상품들이 장바구니에서 삭제되었습니다.";
    }


}



        // 예외처리 ver1. 정상작동
//        try {
//            Integer cartCode = Integer.parseInt(requestData.get("cartCode").toString());
//            Integer newQuantity = Integer.parseInt(requestData.get("newQuantity").toString());
//
//            System.out.println("✅ 업데이트 요청: cartCode = " + cartCode + ", newQuantity = " + newQuantity);
////            Integer cartCode = (Integer) requestData.get("cartCode");
////            Integer newQuantity = (Integer) requestData.get("newQuantity");
//
//            // 장바구니 수량 업데이트
//            userCartService.updateCartQuantity(cartCode, newQuantity); // 서비스 호출
//
//            response.put("success", true);
//        } catch (Exception e) {
//            System.out.println("업데이트 실패: " + e.getMessage());   // 인텔리제이 콘솔창에 오류 내용 보여주기
//            response.put("success", false);
//            response.put("error", e.getMessage());
//        }
//
//        return response;



        // 장바구니에 상품 추가
//    @PostMapping("/add")
//    public String addProductToCart(@RequestBody CartRequest cartRequest) {
//        // 서비스 호출
//        return userCartService.addProductToCart(cartRequest.getProductCode(), cartRequest.getUserCode(), cartRequest.getCartProductQuantity());
//    }




