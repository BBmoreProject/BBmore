package com.bbmore.admin.anotice.repository;

import com.bbmore.admin.anotice.dto.AdminNoticeDTO;
import com.bbmore.admin.anotice.entity.AdminNotice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//AdminNotice 엔티티로 JpaRepository를 생성한다. AdminNotice 기본키는 int다.
//JpaRepository 인터페이스 상속 받아서 자동 빈등록됨 어노테이션 추가할 필요 없음

@Repository
public interface NoticeAdminRepository extends JpaRepository<AdminNotice, Integer> {

  @Query(value = "SELECT * FROM tbl_notice ORDER BY notice_code DESC", nativeQuery = true)
  List<AdminNotice> findAll();;

}
