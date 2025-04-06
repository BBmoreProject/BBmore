package com.bbmore.product.repository;

import com.bbmore.product.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    Member findByUserEmail(String userEmail);
    // Custom query methods can be defined here if needed
}
