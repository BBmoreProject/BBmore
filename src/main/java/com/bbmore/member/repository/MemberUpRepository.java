package com.bbmore.member.repository;

import com.bbmore.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberUpRepository extends JpaRepository<Member, Integer> {
    Member findByUserId(String userId);
}
