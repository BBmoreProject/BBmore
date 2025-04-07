//package com.bbmore.admin.amember.repository;
//
//import com.bbmore.member.entity.Member;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import java.util.List;
//
//public interface MemberRepository extends JpaRepository<Member, Integer> {
//
//    // (:name IS NULL OR :name = '' OR m.userName LIKE %:name%)
//    @Query("""
//    SELECT m FROM Member m
//    WHERE (:name IS NULL OR :name = '' OR m.userName LIKE %:name%)
//      AND (:phone IS NULL OR :phone = '' OR m.userPhoneNumber = :phone)
//      AND (:grade IS NULL OR :grade = '' OR m.membership.membershipName = :grade)
//""")
//    List<Member> searchMembers(@Param("name") String name,
//                               @Param("phone") String phone,
//                               @Param("grade") String grade);
//
//}