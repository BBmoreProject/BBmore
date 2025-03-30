package com.bbmore.product.repository;

import com.bbmore.product.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    ///  중복된 회원이 있는지 검사하기 위해 쿼리 메서드 작성
    /**
     * 반환 타입 : Member
     * email 필드로 조회
     * 매개변수는 조회할 때 사용할 이메일 값
     */
    Member findByEmail(String email); // 앞에 Member 반환 타입
}
