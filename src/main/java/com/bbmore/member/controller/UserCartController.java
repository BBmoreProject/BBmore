package com.bbmore.member.controller;

import com.bbmore.member.dto.UserCartDTO;
import com.bbmore.member.entity.UserCart;
import com.bbmore.member.service.UserCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class UserCartController {

  @Autowired
  private UserCartService userCartService;

  // 장바구니에 상품 추가
  @PostMapping("/add")
  public UserCart addProductToCart(@RequestBody UserCartDTO userCartDTO) {
    return userCartService.addProductToCart(userCartDTO);
  }

  // 장바구니에서 상품 제거
  @DeleteMapping("/remove/{cartCode}")
  public void removeProductFromCart(@PathVariable Integer cartCode) {
    userCartService.removeProductFromCart(cartCode);
  }

  // 사용자의 장바구니 목록 조회
  @GetMapping("/list/{userCode}")
  public List<UserCart> getUserCart(@PathVariable Integer userCode) {
    return userCartService.getUserCart(userCode);
  }
}
