package com.bbmore.notice.controller;



import com.bbmore.admin.anotice.dto.NoticeDTO;
import com.bbmore.admin.anotice.entity.Notice;
import com.bbmore.notice.dto.UserNoticeDTO;
import com.bbmore.notice.service.UserNoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/members")
public class UserNoticeController {

  private final UserNoticeService userNoticeService;

  @Autowired
  public UserNoticeController(UserNoticeService userNoticeService) {
    this.userNoticeService = userNoticeService;
  }


//  // 공지사항 목록 페이지
//  @GetMapping("/notice-list")
//  public String getNotices(@RequestParam(value = "noticeType", defaultValue = "공지사항") String noticeType,
//                           @RequestParam(value = "page", defaultValue = "0") int page,
//                           @RequestParam(value = "size", defaultValue = "10") int size, Model model) {
//    Page<Notice> noticePage = userNoticeService.getNotices(noticeType, page, size);
//    model.addAttribute("notices", noticePage);
//    model.addAttribute("noticeType", noticeType);
//    model.addAttribute("currentPage", page);
//    model.addAttribute("totalPages", noticePage.getTotalPages());
//    return "members/notice-list"; // notice-list.html
//  }
//
//  @GetMapping("/faq-list")
//  public String getFaqs(@RequestParam(value = "page", defaultValue = "0") int page,
//                        @RequestParam(value = "size", defaultValue = "10") int size, Model model) {
//    Page<Notice> faqPage = userNoticeService.getNotices("자주묻는질문", page, size);  // "자주묻는질문" 타입만 필터링
//    model.addAttribute("notices", faqPage);
//    model.addAttribute("noticeType", "자주묻는질문");
//    model.addAttribute("currentPage", page);
//    model.addAttribute("totalPages", faqPage.getTotalPages());
//    return "members/faq-list"; // faq-list.html
//  }

  // 파일이름: user-notice-list
  // 공지사항과 자주 묻는 질문 목록 페이지 (하나의 페이지에서 관리)
//  @GetMapping("/notice-faq-list")
  @GetMapping("/user-notice-list")
  public String getNoticesAndFaqs(@RequestParam(value = "page", defaultValue = "0") int page,
                                  @RequestParam(value = "size", defaultValue = "10") int size, Model model) {
    Page<Notice> noticePage = userNoticeService.getNotices("공지사항", page, size);  // "공지사항" 타입만 필터링
    Page<Notice> faqPage = userNoticeService.getNotices("자주묻는질문", page, size);  // "자주묻는질문" 타입만 필터링

    model.addAttribute("noticePage", noticePage);
    model.addAttribute("faqPage", faqPage);
    model.addAttribute("currentPage", page);
    model.addAttribute("totalPages", noticePage.getTotalPages());  // 공지사항 페이지 수 기준
    return "members/user-notice-list";  // 하나의 HTML 페이지로 처리
  }



  // 공지사항 상세 페이지
//  @GetMapping("/user-notice-view/{noticeCode}")
//  public String getNoticeDetail(@PathVariable Integer noticeCode, Model model, String noticeType) {
//    Notice notice = userNoticeService.getNoticeDetail(noticeCode, noticeType);
//    model.addAttribute("notice", notice);
//
//    // 조회된 공지사항을 모델에 추가
//    model.addAttribute("notice", notice );
//
//
//    return "members/user-notice-view"; // notice-detail.html
//  }

  // 공지사항 상세보기(수정금지)
  @GetMapping("/user-notice-view/{noticeCode}")
  @Transactional
  public String viewNotice(@PathVariable Integer noticeCode, Model model) {
    // noticeCode로 공지사항 조회
    UserNoticeDTO userNoticeDTO = userNoticeService.findNoticeByNoticeCode(noticeCode);

    // 이전 글과 다음 글 조회
    Optional<Notice> prevNotice = userNoticeService.getPrevNotice(noticeCode, "공지사항");
    Optional<Notice> nextNotice = userNoticeService.getNextNotice(noticeCode, "공지사항");


    // 조회수 증가(같은 트랜젝션 안에서 조회수 증가되도록 @Transactional 사용)
    userNoticeService.increaseViewCount(noticeCode);


    // 조회된 공지사항을 모델에 추가
    model.addAttribute("notice", userNoticeDTO);

    prevNotice.ifPresent(n -> model.addAttribute("prevNotice", n)); // 이전 글이 있을 경우만 추가
    nextNotice.ifPresent(n -> model.addAttribute("nextNotice", n)); // 다음 글이 있을 경우만 추가

    // 첫번째 글 누르면 1406 에러나서 null 값인지 확인하고자 추가
    log.info("Notice DTO: {}", userNoticeDTO);
    log.info("Prev Notice: {}", prevNotice.orElse(null));
    log.info("Next Notice: {}", nextNotice.orElse(null));

    return "members/user-notice-view"; // 공지사항 상세보기 페이지로 이동
  }





  // 자주 묻는 질문 상세 페이지
  @GetMapping("/user-faq-view/{noticeCode}")
  public String getFaqDetail(@PathVariable Integer noticeCode, Model model, String noticeType) {
    Notice faq = userNoticeService.getNoticeDetail(noticeCode, noticeType);
    model.addAttribute("faq", faq);
    return "members/user-faq-view"; // faq-detail.html
  }


  }


