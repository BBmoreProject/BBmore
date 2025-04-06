package com.bbmore.admin.anotice.controller;


import com.bbmore.admin.anotice.common.Pagenation;
import com.bbmore.admin.anotice.common.PagingButton;
import com.bbmore.admin.anotice.dto.NoticeDTO;
import com.bbmore.admin.anotice.dto.NoticeTypeDTO;
import com.bbmore.admin.anotice.entity.Notice;
import com.bbmore.admin.anotice.repository.AdminNoticeRepository;
import com.bbmore.admin.anotice.service.NoticeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;


@Slf4j
@Controller
@RequestMapping("/notice")
@RequiredArgsConstructor
@SessionAttributes("searchKeyword")
public class AdminNoticeController {


  private final NoticeService noticeService;
  private final AdminNoticeRepository adminNoticeRepository;


  // 공지사항 조회 가능(원본)
//  @GetMapping("/notice-list_ver1")
//  public String findNoticeList(Model model, @PageableDefault Pageable pageable,
//                               @RequestParam(value = "searchKeyword", required = false) String searchKeyword) {
//
//    // 검색어가 있을 때와 없을 때 처리
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
//
//
//    /* 페이징 처리 이후 */
//    // {}: 위치홀더라고 생각할 것
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
//    // 모델에 전달
//    model.addAttribute("noticeList", noticeList);
//    model.addAttribute("paging", paging);
//    model.addAttribute("searchKeyword", searchKeyword); // 검색어도 함께 전달
//
//
//    return "notice/notice-list_ver1"; //목록으로
//  }

  @GetMapping("/notice-list_ver1")
  public String findNoticeList(Model model, @PageableDefault Pageable pageable,
                               @RequestParam(value = "searchKeyword", required = false) String searchKeyword,
                               @RequestParam(value = "noticeType", required = false, defaultValue = "공지사항") String noticeType) {

    Page<NoticeTypeDTO> noticeList;

    // 검색어가 있을 때와 없을 때 처리
    if (searchKeyword == null || searchKeyword.trim().isEmpty()) {
      // noticeType에 맞는 목록을 조회
      if ("자주묻는질문".equals(noticeType)) {
        noticeList = noticeService.findFaqList(pageable);  // 자주 묻는 질문 목록 조회
      } else {
        noticeList = noticeService.findNoticeList(pageable);  // 공지사항 목록 조회
      }
    } else {
      // 검색어가 있을 경우 해당 검색어로 필터링하여 조회
      if ("자주묻는질문".equals(noticeType)) {
        noticeList = noticeService.noticeSearchList(searchKeyword, pageable, "자주묻는질문");  // 자주 묻는 질문 검색
      } else {
        noticeList = noticeService.noticeSearchList(searchKeyword, pageable, "공지사항");  // 공지사항 검색
      }
    }

    // 페이지네이션 정보
    PagingButton paging = Pagenation.getPagingButtonInfo(noticeList, searchKeyword);

    // 모델에 데이터 전달
    model.addAttribute("noticeList", noticeList);
    model.addAttribute("paging", paging);
    model.addAttribute("searchKeyword", searchKeyword);
    model.addAttribute("noticeType", noticeType);  // noticeType을 추가로 전달하여 UI에서 사용

    return "notice/notice-list_ver1"; // 동일한 목록 화면을 사용
  }






  // 자주묻는질문 조회 원본 (가능)
//  @GetMapping("/faq-list")
//  public String findFaqList(Model model, @PageableDefault Pageable pageable,
//                            @RequestParam(value = "searchKeyword", required = false) String searchKeyword) {
//
//    // 검색어가 있을 때와 없을 때 처리
//    Page<NoticeTypeDTO> faqList;
//
//    if (searchKeyword == null || searchKeyword.trim().isEmpty()) {
//      faqList = noticeService.findFaqList(pageable);  // 검색어가 없으면 전체 리스트 조회
//      System.out.println("검색어 없을때 faqList:  " + faqList);
//    } else {
//      // 검색어가 있을 경우 해당 검색어로 필터링하여 조회
//      faqList = noticeService.noticeSearchList(searchKeyword, pageable);  // 검색어로 필터링
//      System.out.println("검색어 있을때 noticeList: " + faqList); }
//
//
//
//    /* 페이징 처리 이후 */
//    // {}: 위치홀더라고 생각할 것
//    log.info("pageable: {}", pageable);
//    log.info("{}", faqList.getContent());
//    log.info("{}", faqList.getTotalPages());
//    log.info("{}", faqList.getTotalElements());
//    log.info("{}", faqList.getSize());
//    log.info("{}", faqList.getNumberOfElements());
//    log.info("{}", faqList.isFirst());
//    log.info("{}", faqList.isLast());
//    log.info("{}", faqList.getSort());
//    log.info("{}", faqList.getNumber());
//
//    // 페이지네이션 정보 추가
//    PagingButton paging = Pagenation.getPagingButtonInfo(faqList, searchKeyword);
//
//    // 모델에 전달
//    model.addAttribute("faqList", faqList);
//    model.addAttribute("paging", paging);
//    model.addAttribute("searchKeyword", searchKeyword); // 검색어도 함께 전달
//
//
//    return "notice/faq-list"; }//목록으로
//


  // 공지사항 상세보기(가능)
  @GetMapping("/notice-view/{noticeCode}")
  @Transactional
  public String viewNotice(@PathVariable Integer noticeCode, Model model) {
    // noticeCode로 공지사항 조회
    NoticeDTO noticeDTO = noticeService.findNoticeByNoticeCode(noticeCode);

    Optional<Notice> prevNotice = noticeService.getPrevNotice(noticeCode, "공지사항");
    Optional<Notice> nextNotice = noticeService.getNextNotice(noticeCode, "공지사항");


    // 조회수 증가(같은 트랜젝션 안에서 조회수 증가되도록 @Transactional 사용)
    noticeService.increaseViewCount(noticeCode);


    // 조회된 공지사항을 모델에 추가
    model.addAttribute("notice", noticeDTO);

    prevNotice.ifPresent(n -> model.addAttribute("prevNotice", n)); // 이전 글 추가
    nextNotice.ifPresent(n -> model.addAttribute("nextNotice", n)); // 다음 글 추가


    return "notice/notice-view"; // 공지사항 상세보기 페이지로 이동
  }


  // 자주묻는질문 상세보기(가능)
  @GetMapping("/faq-view/{noticeCode}")
  @Transactional
  public String viewFaq(@PathVariable Integer noticeCode, Model model) {

    NoticeDTO faqDTO = noticeService.findNoticeByNoticeCode(noticeCode);

    // FAQ 조회
    Optional<Notice> prevFaq = noticeService.getPrevNotice(noticeCode, "자주묻는질문");
    Optional<Notice> nextFaq = noticeService.getNextNotice(noticeCode, "자주묻는질문");

    // 조회수 증가(같은 트랜젝션 안에서 조회수 증가되도록 @Transactional 사용)
    noticeService.increaseViewCount(noticeCode);

    // FAQ 관련 정보 모델에 추가
    model.addAttribute("faq", faqDTO);

    prevFaq.ifPresent(n -> model.addAttribute("prevFaq", n)); // 이전 faq 추가
    nextFaq.ifPresent(n -> model.addAttribute("nextFaq", n)); // 다음 faq 추가

    return "notice/faq-view";
  }


  // 공지사항 등록 시 요청 url 이 뷰가 되도록 void 로 작성
  @GetMapping("/notice-write_ver1")
  public void registPage() {
  }

  // 공지사항 등록
  @PostMapping("/notice-write_ver1")
  public String registNotice(@ModelAttribute NoticeDTO noticeDTO) {
    noticeService.registNotice(noticeDTO);

    return "redirect:/notice/notice-list_ver1";
  }

  // 공지사항 수정 페이지
  @GetMapping("/modify/{id}")
  public String noticeModify(@PathVariable("id") Integer noticeCode, Model model) {
    // 공지사항을 noticeCode로 조회
    model.addAttribute("notice", noticeService.findNoticeByNoticeCode(noticeCode));
    
    return "notice/noticemodify";     // 글쓰기 html과 동일
  }

  // 공지사항 수정 진행
  @PostMapping("/update/{id}")
  public String noticeUpdate(@PathVariable("id") Integer noticeCode, NoticeDTO noticedto) {
    // 서비스 레이어에서 수정 처리
    noticeService.updateNotice(noticeCode, noticedto);
    return "redirect:/notice/notice-list_ver1";
  }

  // 공지사항 삭제
  @PostMapping("/delete")
  public String deleteNotice(@RequestParam("noticeCode") Integer noticeCode) {
    noticeService.deleteNotice(noticeCode);
    return "redirect:/notice/notice-list_ver1";
  }


}





