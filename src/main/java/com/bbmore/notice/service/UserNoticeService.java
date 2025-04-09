package com.bbmore.notice.service;

import com.bbmore.admin.anotice.dto.NoticeTypeDTO;
import com.bbmore.admin.anotice.entity.Notice;
import com.bbmore.notice.dto.UserNoticeDTO;
import com.bbmore.notice.repository.UserNoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserNoticeService {

  private final UserNoticeRepository userNoticeRepository;
  private final ModelMapper modelMapper;

  // 1-1 게시글타입 - 공지사항 타입 조회해오기 (수정금지)
  public Page<UserNoticeDTO> findNoticeList(Pageable pageable) {
    pageable = PageRequest.of(
            pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
            pageable.getPageSize(),
            Sort.by("noticeCreatedDate").descending()
    );
    Page<Notice> noticeList = userNoticeRepository.findByNoticeType("공지사항", pageable);
    // map 함수로 notice를 하나 하나 ㅏ다 꺼내서 modelMapper.. DTO 타입으로
    return noticeList.map(notice -> modelMapper.map(notice, UserNoticeDTO.class));
  }


  // 1-2 게시글타입 - 자주묻는질문 타입 조회해오기 (수정금지)
  public Page<UserNoticeDTO> findFaqList(Pageable pageable) {
    pageable = PageRequest.of(
            pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
            pageable.getPageSize(),
            Sort.by("noticeCreatedDate").descending()
    );
    Page<Notice> faqList = userNoticeRepository.findByNoticeType("자주묻는질문", pageable);
    // map 함수로 notice를 하나 하나 ㅏ다 꺼내서 modelMapper.. DTO 타입으로
    return faqList.map(faq -> modelMapper.map(faq, UserNoticeDTO.class));
  }
//--------------------------------------------------------------------------------------------------------------

  
  // 검색 기능: 검색어로 제목을 포함하는 공지사항 조회- test  15:15
  public Page<UserNoticeDTO> noticeSearchList(String searchKeyword, String noticeType , Pageable pageable) {
    pageable = PageRequest.of(
            pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
            pageable.getPageSize(),
            Sort.by("noticeCreatedDate").descending()
    );

    // 검색어가 제목에 포함된 게시글만 필터링
    Page<Notice> noticeList = userNoticeRepository.findByNoticeTypeAndNoticeTitleContaining(noticeType, searchKeyword, pageable);

    // notice를 DTO로 변환
    return noticeList.map(notice -> modelMapper.map(notice, UserNoticeDTO.class));
  }



  //------------------------------------------------------------------------------------

  // 안씀
//  public Page<Notice> getNotices(String noticeType, int page, int size) {
//    Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.desc("noticeCreatedDate")));
//    return userNoticeRepository.findByNoticeType(noticeType, pageable);
//  }

  //------------------------------------------------------------------------------------



  // 조회수 증가 (가능)
  @Transactional
  public void increaseViewCount(Integer noticeCode) {
    Notice notice = userNoticeRepository.findById(noticeCode)
            .orElseThrow(() -> new RuntimeException("공지사항을 찾을 수 없습니다."));

    notice.setNoticeView(notice.getNoticeView() + 1); // Getter를 활용한 조회수 증가
  }

  //------------------------------------------------------------------------------------


  // noticeCode로 공지사항 조회
  public UserNoticeDTO findNoticeByNoticeCode(Integer noticeCode) {
    Notice foundNotice = userNoticeRepository.findById(noticeCode)
            .orElseThrow(() -> new IllegalArgumentException("공지사항을 찾을 수 없습니다."));

    return modelMapper.map(foundNotice, UserNoticeDTO.class);
  }

  //------------------------------------------------------------------------------------


  // 이전 공지사항 조회 (타입별로)
  public Optional<Notice> getPrevNotice(Integer noticeCode, String noticeType) {
    return userNoticeRepository.findFirstByNoticeCodeLessThanAndNoticeTypeOrderByNoticeCodeDesc(noticeCode, noticeType);
  }

  // 다음 공지사항 조회 (타입별로)
  public Optional<Notice> getNextNotice(Integer noticeCode, String noticeType) {
    return userNoticeRepository.findFirstByNoticeCodeGreaterThanAndNoticeTypeOrderByNoticeCodeAsc(noticeCode, noticeType);
  }






  }








