package com.bbmore.admin.amember.repository;

import com.bbmore.admin.amember.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    @Query("SELECT m FROM MemberEntity m " +
            "WHERE (:name IS NULL OR m.name LIKE %:name%) " +
            "AND (:phone IS NULL OR m.phone LIKE %:phone%) " +
            "AND (:grade IS NULL OR m.grade LIKE %:grade%)")
    List<MemberEntity> searchMembers(@Param("name") String name,
                               @Param("phone") String phone,
                               @Param("grade") String grade);
}
