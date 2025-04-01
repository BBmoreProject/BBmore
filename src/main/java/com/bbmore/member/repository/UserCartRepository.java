package com.bbmore.member.repository;

import com.bbmore.member.entity.UserCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCartRepository extends JpaRepository<UserCart, Integer> {

  // 특정 사용자에 대한 장바구니 목록 조회
  List<UserCart> findByMember_UserCode(Integer userCode);

  // 장바구니 상품 제거
//  void deleteById(Integer cartCode);


}
