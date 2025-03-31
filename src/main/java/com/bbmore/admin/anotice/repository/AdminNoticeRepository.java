package com.bbmore.admin.anotice.repository;

import com.bbmore.admin.anotice.dto.NoticeDTO;
import com.bbmore.admin.anotice.entity.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//AdminNotice 엔티티로 JpaRepository를 생성한다. AdminNotice 기본키는 int다.
//JpaRepository 인터페이스 상속 받아서 자동 빈등록됨 어노테이션 추가할 필요 없음

@Repository
public interface AdminNoticeRepository extends JpaRepository<Notice, Integer> {


    // 이전글 조회 (현재 noticeCode보다 작은 값 중 가장 큰 값을 찾기)
    Optional<Notice> findTopByNoticeCodeLessThanOrderByNoticeCodeDesc(Integer noticeCode);

    // 다음글 조회 (현재 noticeCode보다 작은 값 중 가장 큰 값을 찾기)
    Optional<Notice> findTopByNoticeCodeGreaterThanOrderByNoticeCodeAsc(Integer noticeCode);

    // 검색 기능 (Notice 엔티티 반환)
    Page<Notice> findByNoticeTitleContaining(String searchKeyword, Pageable pageable);
}