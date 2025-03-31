package com.bbmore.member.repository;


import com.bbmore.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
  // 회원 코드로 회원 조회
  Member findByUserCode(Integer userCode);
}
