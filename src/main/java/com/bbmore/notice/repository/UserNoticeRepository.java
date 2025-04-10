package com.bbmore.notice.repository;

import com.bbmore.admin.anotice.entity.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserNoticeRepository extends JpaRepository<Notice, Integer> {

  // noticeType에 따라 공지사항과 자주 묻는 질문을 구분하여 조회
  Page<Notice> findByNoticeType(String noticeType, Pageable pageable);


}

