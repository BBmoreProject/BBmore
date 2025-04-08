package com.bbmore.notice.controller;



import com.bbmore.admin.anotice.entity.Notice;
import com.bbmore.notice.service.UserNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


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
  @GetMapping("/notice-detail/{noticeCode}")
  public String getNoticeDetail(@PathVariable Integer noticeCode, Model model, String noticeType) {
    Notice notice = userNoticeService.getNoticeDetail(noticeCode, noticeType);
    model.addAttribute("notice", notice);
    return "members/notice-detail"; // notice-detail.html
  }


  // 자주 묻는 질문 상세 페이지
  @GetMapping("/faq-detail/{noticeCode}")
  public String getFaqDetail(@PathVariable Integer noticeCode, Model model, String noticeType) {
    Notice faq = userNoticeService.getNoticeDetail(noticeCode, noticeType);
    model.addAttribute("faq", faq);
    return "members/faq-detail"; // faq-detail.html
  }


  }


