package com.bbmore.notice.repository;

import com.bbmore.admin.anotice.entity.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserNoticeRepository extends JpaRepository<Notice, Integer> {

    // noticeType에 따라 공지사항과 자주 묻는 질문을 구분하여 조회
    Page<Notice> findByNoticeType(String noticeType, Pageable pageable);

    // 타입별 이전글 조회 (수정금지)
    // noticeCode보다 작은 값 중, 주어진 타입의 공지사항을 내림차순으로 조회
    Optional<Notice> findFirstByNoticeCodeLessThanAndNoticeTypeOrderByNoticeCodeDesc(Integer noticeCode, String noticeType);

    // 타입별 다음글 조회 (수정금지)
    Optional<Notice> findFirstByNoticeCodeGreaterThanAndNoticeTypeOrderByNoticeCodeAsc(Integer noticeCode, String noticeType);


}

