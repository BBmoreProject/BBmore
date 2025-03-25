package com.bbmore.admin.anotice.repository;

import com.bbmore.admin.anotice.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

// AdminNoticeRepository 와 연결될 Entity => Notice
// Notice Entity id의 데이터 타입=>Integer
//JpaRepository 인터페이스 상속 받아서 자동 빈등록됨 어노테이션 추가할 필요 없음

@Repository
public interface AdminNoticeRepository extends JpaRepository<Notice, Integer> {

//필요없는듯
//    @Query(
//            value = "SELECT notice_code, notice_type, notice_title, notice_created_date , notice_view FROM tbl_notice ORDER BY notice_code",
//            nativeQuery = true
//    )
//    List<Notice> findAllMenu();





}
