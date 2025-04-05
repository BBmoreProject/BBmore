//package com.bbmore.admin.anotice.controller;
//
//
//import com.bbmore.admin.anotice.common.Pagenation;
//import com.bbmore.admin.anotice.common.PagingButton;
//import com.bbmore.admin.anotice.dto.*;
//import com.bbmore.admin.anotice.entity.Notice;
//import com.bbmore.admin.anotice.repository.AdminNoticeRepository;
//import com.bbmore.admin.anotice.service.NoticeService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.web.PageableDefault;
//import org.springframework.stereotype.Controller;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//
//import java.util.Optional;
//
//
//@Slf4j
//@Controller
//@RequestMapping("/notice")
//@RequiredArgsConstructor
//public class AdminNoticeController1 {
//
//  private final NoticeService noticeService;
//  private final AdminNoticeRepository adminNoticeRepository;
//
//
//  // 원본
////  @GetMapping("/notice-list_ver1")
////  public String findNoticeList(Model model, @PageableDefault Pageable pageable,
////                               @RequestParam(value = "searchKeyword", required = false) String searchKeyword) {
////
////    // 검색어가 있을 때와 없을 때 처리
////    Page<NoticeDTO> noticeList;
////
////    if (searchKeyword == null || searchKeyword.trim().isEmpty()) {
////      noticeList = noticeService.findNoticeList(pageable);  // 검색어가 없으면 전체 리스트 조회
////      System.out.println("검색어 없을때 noticeList:  " + noticeList);
////    } else {
////      // 검색어가 있을 경우 해당 검색어로 필터링하여 조회
////      noticeList = noticeService.noticeSearchList(searchKeyword, pageable);  // 검색어로 필터링
////      System.out.println("검색어 있을때 noticeList: " + noticeList);
////    }
////
////
////
////    /* 페이징 처리 이후 */
////    // {}: 위치홀더라고 생각할 것
////    log.info("pageable: {}", pageable);
////    log.info("{}", noticeList.getContent());
////    log.info("{}", noticeList.getTotalPages());
////    log.info("{}", noticeList.getTotalElements());
////    log.info("{}", noticeList.getSize());
////    log.info("{}", noticeList.getNumberOfElements());
////    log.info("{}", noticeList.isFirst());
////    log.info("{}", noticeList.isLast());
////    log.info("{}", noticeList.getSort());
////    log.info("{}", noticeList.getNumber());
////
////    // 페이지네이션 정보 추가
////    PagingButton paging = Pagenation.getPagingButtonInfo(noticeList, searchKeyword);
////
////    // 모델에 전달
////    model.addAttribute("noticeList", noticeList);
////    model.addAttribute("paging", paging);
////    model.addAttribute("searchKeyword", searchKeyword); // 검색어도 함께 전달
////
////
////    return "notice/notice-list_ver1"; //목록으로
////  }
//
//
//  // 공지사항 타입만 찾아오기 (완료)
//  @GetMapping("/notice-list_ver1")
//  public String findNoticeList(Model model, @PageableDefault Pageable pageable, String searchKeyword) {
//
////    Page<NoticeTypeDTO> noticeList = noticeService.findNoticeList(pageable);
//
//    //    // 검색어가 있을 때와 없을 때 처리
//    Page<NoticeTypeDTO> noticeList;
//
//    if (searchKeyword == null || searchKeyword.trim().isEmpty()) {
//      noticeList = noticeService.findNoticeList(pageable);  // 검색어가 없으면 전체 리스트 조회
//      System.out.println("검색어 없을때 noticeList:  " + noticeList);
//    } else {
//      // 검색어가 있을 경우 해당 검색어로 필터링하여 조회
//      noticeList = noticeService.noticeSearchList(searchKeyword, pageable);  // 검색어로 필터링
//      System.out.println("검색어 있을때 noticeList: " + noticeList);
//    }
//
//    //    /* 페이징 처리 이후 */
////    // {}: 위치홀더라고 생각할 것
//    log.info("pageable: {}", pageable);
//    log.info("{}", noticeList.getContent());
//    log.info("{}", noticeList.getTotalPages());
//    log.info("{}", noticeList.getTotalElements());
//    log.info("{}", noticeList.getSize());
//    log.info("{}", noticeList.getNumberOfElements());
//    log.info("{}", noticeList.isFirst());
//    log.info("{}", noticeList.isLast());
//    log.info("{}", noticeList.getSort());
//    log.info("{}", noticeList.getNumber());
//
//    // 페이지네이션 정보 추가
//    PagingButton paging = Pagenation.getPagingButtonInfo(noticeList, searchKeyword);
//
//
//    // 모델에 전달
//    model.addAttribute("noticeList", noticeList);
//    model.addAttribute("paging", paging);
//
//
//    // 목록으로
//    return "notice/notice-list_ver1";
//  }
//
//
//  // 공지사항 상세보기
//  @GetMapping("/notice-view/{noticeCode}")
//  @Transactional
//  public String viewNotice(@PathVariable Integer noticeCode, Model model) {
//
//    // noticeCode로 공지사항 조회
//    NoticeViewDTO noticeViewDTO = noticeService.getNoticeView(noticeCode);
//
//    // 이전글 다음글 조회
//    Optional<Notice> prevNotice = noticeService.getPrevNotice(noticeCode);  // 이전글
//    Optional<Notice> nextNotice = noticeService.getNextNotice(noticeCode);  // 다음글
//
//    // 조회수 증가(같은 트랜젝션 안에서 조회수 증가되도록 @Transactional 사용)
//    noticeService.increaseViewCount(noticeCode);
//
//
//    // 조회된 공지사항을 모델에 추가
//    model.addAttribute("notice", noticeViewDTO);
//    prevNotice.ifPresent(n -> model.addAttribute("prevNotice", n)); // 이전 글 추가
//    nextNotice.ifPresent(n -> model.addAttribute("nextNotice", n)); // 다음 글 추가
//
//    return "notice/notice-view"; // 공지사항 상세보기 페이지로 이동
//  }
//
//
//  // 조회수 증가
//  @Transactional
//  public void increaseViewCount(Integer noticeCode) {
//    Notice notice = adminNoticeRepository.findById(noticeCode)
//        .orElseThrow(() -> new RuntimeException("공지사항을 찾을 수 없습니다."));
//
//    // 빌더를 사용하여 조회수 증가 후 새로운 Notice 객체 생성
//    Notice updatedNotice = Notice.builder()
//        .noticeCode(notice.getNoticeCode())
//        .noticeType(notice.getNoticeType())
//        .noticeTitle(notice.getNoticeTitle())
//        .noticeCreatedDate(notice.getNoticeCreatedDate())
//        .noticeView(notice.getNoticeView() + 1) // 조회수 증가
//        .noticeContent(notice.getNoticeContent())
//        .build();
//
//    // 변경된 Notice 객체 저장
//    adminNoticeRepository.save(updatedNotice);
//  }
//
//
//  // 공지사항 등록 시 요청 url 이 뷰가 되도록 void 로 작성
//  @GetMapping("/notice-write_ver1")
//  public void registPage() {
//  }
//
//
//  // 공지사항 등록
//  @PostMapping("/notice-write_ver1")
//  public String registNotice(@ModelAttribute NoticeModifyDTO noticeModifyDTO) {
//    noticeService.registNotice(noticeModifyDTO);
//
//    return "redirect:/notice/notice-list_ver1";
//  }
//
//  // 공지사항 수정
//  @GetMapping("/modify/{id}")
//  public String noticeModify(@PathVariable("id") Integer noticeCode, Model model) {
//
//    model.addAttribute("notice", noticeService.findNoticeByNoticeCode(noticeCode));
//
//    return "notice/noticemodify";     // 글쓰기 html과 동일
//  }
//
////  // 공지사항 수정 진행
////  @PostMapping("/update/{id}")
////  public String noticeUpdate(@PathVariable("id") Integer noticeCode,
////                             @ModelAttribute NoticeEditDTO noticeEditDTO) {
////
////    NoticeTypeDTO existingNotice = noticeService.findNoticeByNoticeCode(noticeCode); // 기존내용 찾기
////
////// Notice 엔티티를 빌더 패턴을 통해 업데이트
////    Notice updatedNotice = Notice.builder()
////        .noticeCode(noticeCode)
////        .noticeType(existingNotice.getNoticeType()) // 기존 타입 그대로 사용
////        .noticeTitle(existingNotice.getNoticeTitle())        // 제목 수정
////        .noticeContent(existingNotice.getNoticeContent())  // 내용 수정
////        .noticeCreatedDate(existingNotice.getNoticeCreatedDate())
////        .noticeView(existingNotice.getNoticeView())
////        .build();
////
////    // 수정된 공지사항 저장
////    noticeService.saveNotice(updatedNotice);
////
////    return "redirect:/notice/notice-list_ver1";
////  }
//
//
//
//  // 공지사항 삭제
////  @PostMapping("/delete")
////  public String deleteNotice(@RequestParam("noticeCode") Integer noticeCode) {
////    noticeService.deleteNotice(noticeCode);
////    return "redirect:/notice/notice-list_ver1";
////  }
//
//
//}
//
//
//
//
//
//
