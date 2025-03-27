package com.bbmore.admin.amember.repository;


import com.bbmore.admin.amember.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
}
