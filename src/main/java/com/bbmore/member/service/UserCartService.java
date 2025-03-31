package com.bbmore.member.service;

import com.bbmore.member.dto.UserCartDTO;
import com.bbmore.member.entity.Member;
import com.bbmore.member.entity.Product;
import com.bbmore.member.entity.UserCart;
import com.bbmore.member.repository.MemberRepository;
import com.bbmore.member.repository.ProductRepository;
import com.bbmore.member.repository.UserCartRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import java.util.List;

@Service
public class UserCartService {

  @Autowired
  private UserCartRepository userCartRepository;

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private MemberRepository memberRepository;

  // 장바구니에 상품 추가
  public UserCart addProductToCart(UserCartDTO userCartDTO) {
    Member member = memberRepository.findById(userCartDTO.getUserCode())
        .orElseThrow(() -> new EntityNotFoundException("회원 정보를 찾을 수 없습니다."));
    Optional<Product> product = productRepository.findById(userCartDTO.getProductCode());

//    if (member.isPresent() && product.isPresent()) {
//      UserCart userCart = new UserCart();
//      userCart.setCartProductQuantity(userCartDTO.getCartProductQuantity());
//      userCart.setMember(member.get());
//      userCart.setProduct(product.get());
//      return userCartRepository.save(userCart);
//    }
//    return null; }


  // 장바구니에서 상품 제거
  public void removeProductFromCart(Integer cartCode) {
    userCartRepository.deleteById(cartCode);
  }

  // 사용자의 장바구니 목록 조회
    Member member = memberRepository.findById(userCode)
        .orElseThrow(() -> new EntityNotFoundException("회원 정보를 찾을 수 없습니다."));
    List<UserCart> userCartList = userCartRepository.findByMember(member);
}
