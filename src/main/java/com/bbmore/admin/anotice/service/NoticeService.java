package com.bbmore.admin.anotice.service;


import com.bbmore.admin.anotice.dto.NoticeDTO;
import com.bbmore.admin.anotice.entity.Notice;
import com.bbmore.admin.anotice.repository.AdminNoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeService {


    private final AdminNoticeRepository adminNoticeRepository;
    private final ModelMapper modelMapper;


    /* findAll : Pageable 사용*/
    // Pageable 객체 : 스프링 data domain 에서 제공
    // PageRequest.of : PageNumber, PageSize, sort 3가지 전달 필요
    // PageNumber 는 0부터
    public Page<NoticeDTO> findNoticeList(Pageable pageable) {
        pageable = PageRequest.of(
                pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
                pageable.getPageSize(),
                Sort.by("noticeCreatedDate").descending()
        );
        Page<Notice> noticeList = adminNoticeRepository.findAll(pageable);
        // map 함수로 menu를 하나 하나 ㅏ다 꺼내서 modelMapper.. DTO 타입으로
        return noticeList.map(notice -> modelMapper.map(notice, NoticeDTO.class));
    }
    


    /* 공지사항 등록 save */
    @Transactional
    public void registNotice(NoticeDTO noticeDTO) {
        adminNoticeRepository.save(modelMapper.map(noticeDTO, Notice.class));  // DTO를 Entity로 가공
    }


    // noticeCode로 공지사항 조회
    public NoticeDTO findNoticeByNoticeCode(int noticeCode) {
        Notice foundNotice = adminNoticeRepository.findById(noticeCode)
                .orElseThrow(() -> new IllegalArgumentException("공지사항을 찾을 수 없습니다."));

        return modelMapper.map(foundNotice, NoticeDTO.class);
    }

    // 이전글 조회
    public Notice getPreviousNotice(int noticeCode){
        Optional<Notice> previousNotice = adminNoticeRepository.findPreviousNotice(noticeCode);
//        if (!previousNotice.isPresent()) {
//            log.info("이전글이 없습니다. noticeCode: {}", noticeCode);
//        } else {
//            log.info("이전글 제목: {}", previousNotice.get().getNoticeTitle());
//        }
        return previousNotice.orElseThrow(() -> new IllegalArgumentException("이전글을 찾을 수 없습니다."));
    }


     // 다음글 조회
    public Notice getNextNotice(int noticeCode){
        Optional<Notice> nextNotice = adminNoticeRepository.findNextNotice(noticeCode);
        return nextNotice.orElseThrow(() -> new IllegalArgumentException("다음글을 찾을 수 없습니다."));

    }



    /* 수정(엔티티 객체의 필드 값 변경) */
    @Transactional
    public void modifyNotice(NoticeDTO noticeDTO) {
       Notice foundNotice = adminNoticeRepository.findById(noticeDTO.getNoticeCode()).orElseThrow(IllegalArgumentException::new);

        /* setter 사용 지양 , 기능에 맞는 메소드를 정의해서 사용할 것 */
        foundNotice.modifyNoticeTitle(noticeDTO.getNoticeTitle());
        foundNotice.modifyNoticeContent(noticeDTO.getNoticeContent());
    }

    /* deleteById */
    @Transactional
    public void deleteNotice(int noticeCode) {
        adminNoticeRepository.deleteById(noticeCode);
    }


}
