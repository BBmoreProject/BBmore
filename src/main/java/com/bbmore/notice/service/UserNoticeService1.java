//package com.bbmore.notice.service;
//
//import com.bbmore.admin.anotice.entity.Notice;
//import com.bbmore.notice.dto.UserNoticeDTO;
//import com.bbmore.notice.repository.UserNoticeRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.modelmapper.ModelMapper;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Optional;
//
//@Slf4j
//@RequiredArgsConstructor
//@Service
//public class UserNoticeService1 {
//
//  private final UserNoticeRepository userNoticeRepository;
//  private final ModelMapper modelMapper;
//
//
//  public Page<Notice> getNotices(String noticeType, int page, int size) {
//    Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.desc("noticeCreatedDate")));
//    return userNoticeRepository.findByNoticeType(noticeType, pageable);
//  }
//
//
//
//  // 조회수 증가 (가능)
//  @Transactional
//  public void increaseViewCount(Integer noticeCode) {
//    Notice notice = userNoticeRepository.findById(noticeCode)
//            .orElseThrow(() -> new RuntimeException("공지사항을 찾을 수 없습니다."));
//
//    notice.setNoticeView(notice.getNoticeView() + 1); // Getter를 활용한 조회수 증가
//  }
//
//
//  // noticeCode로 공지사항 조회
//  public UserNoticeDTO findNoticeByNoticeCode(Integer noticeCode) {
//    Notice foundNotice = userNoticeRepository.findById(noticeCode)
//            .orElseThrow(() -> new IllegalArgumentException("공지사항을 찾을 수 없습니다."));
//
//    return modelMapper.map(foundNotice, UserNoticeDTO.class);
//  }
//
//  // 이전 공지사항 조회 (타입별로)
//  public Optional<Notice> getPrevNotice(Integer noticeCode, String noticeType) {
//    return userNoticeRepository.findFirstByNoticeCodeLessThanAndNoticeTypeOrderByNoticeCodeDesc(noticeCode, noticeType);
//  }
//
//  // 다음 공지사항 조회 (타입별로)
//  public Optional<Notice> getNextNotice(Integer noticeCode, String noticeType) {
//    return userNoticeRepository.findFirstByNoticeCodeGreaterThanAndNoticeTypeOrderByNoticeCodeAsc(noticeCode, noticeType);
//  }
//
//
//
//
//
//
//  }
//
//
//
//
//
//
//
//
