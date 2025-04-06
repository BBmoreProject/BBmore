package com.bbmore.admin.anotice.controller;


import com.bbmore.admin.anotice.common.Pagenation;
import com.bbmore.admin.anotice.common.PagingButton;
import com.bbmore.admin.anotice.dto.NoticeDTO;
import com.bbmore.admin.anotice.dto.NoticeTypeDTO;
import com.bbmore.admin.anotice.entity.Notice;
import com.bbmore.admin.anotice.repository.AdminNoticeRepository;
import com.bbmore.admin.anotice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
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


  // 공지사항 타입만 찾아오기 (완료)
  @GetMapping("/notice-list_ver1")
  public String findNoticeList(Model model, @PageableDefault Pageable pageable, String searchKeyword) {

//    Page<NoticeTypeDTO> noticeList = noticeService.findNoticeList(pageable);

    //    // 검색어가 있을 때와 없을 때 처리
    Page<NoticeTypeDTO> noticeList;

    if (searchKeyword == null || searchKeyword.trim().isEmpty()) {
      noticeList = noticeService.findNoticeList(pageable);  // 검색어가 없으면 전체 리스트 조회
      System.out.println("검색어 없을때 noticeList:  " + noticeList);
    } else {
      // 검색어가 있을 경우 해당 검색어로 필터링하여 조회
      noticeList = noticeService.noticeSearchList(searchKeyword, pageable);  // 검색어로 필터링
      System.out.println("검색어 있을때 noticeList: " + noticeList);
    }

    //    /* 페이징 처리 이후 */
    //    // {}: 위치홀더라고 생각할 것
    log.info("pageable: {}", pageable);
    log.info("{}", noticeList.getContent());
    log.info("{}", noticeList.getTotalPages());
    log.info("{}", noticeList.getTotalElements());
    log.info("{}", noticeList.getSize());
    log.info("{}", noticeList.getNumberOfElements());
    log.info("{}", noticeList.isFirst());
    log.info("{}", noticeList.isLast());
    log.info("{}", noticeList.getSort());
    log.info("{}", noticeList.getNumber());

    // 페이지네이션 정보 추가
    PagingButton paging = Pagenation.getPagingButtonInfo(noticeList, searchKeyword);


    // 모델에 전달
    model.addAttribute("noticeList", noticeList);
    model.addAttribute("paging", paging);


    // 목록으로
    return "notice/notice-list_ver1";
  }


  // 자주묻는질문 조회 (가능)
  @GetMapping("/faq-list")
  public String findFaqList(Model model, @PageableDefault Pageable pageable,
                            @RequestParam(value = "searchKeyword", required = false) String searchKeyword) {

    // 검색어가 있을 때와 없을 때 처리
    Page<NoticeTypeDTO> faqList;

    if (searchKeyword == null || searchKeyword.trim().isEmpty()) {
      faqList = noticeService.findFaqList(pageable);  // 검색어가 없으면 전체 리스트 조회
      System.out.println("검색어 없을때 faqList:  " + faqList);
    } else {
      // 검색어가 있을 경우 해당 검색어로 필터링하여 조회
      faqList = noticeService.noticeSearchList(searchKeyword, pageable);  // 검색어로 필터링
      System.out.println("검색어 있을때 noticeList: " + faqList);
    }



    /* 페이징 처리 이후 */
    // {}: 위치홀더라고 생각할 것
    log.info("pageable: {}", pageable);
    log.info("{}", faqList.getContent());
    log.info("{}", faqList.getTotalPages());
    log.info("{}", faqList.getTotalElements());
    log.info("{}", faqList.getSize());
    log.info("{}", faqList.getNumberOfElements());
    log.info("{}", faqList.isFirst());
    log.info("{}", faqList.isLast());
    log.info("{}", faqList.getSort());
    log.info("{}", faqList.getNumber());

    // 페이지네이션 정보 추가
    PagingButton paging = Pagenation.getPagingButtonInfo(faqList, searchKeyword);

    // 모델에 전달
    model.addAttribute("faqList", faqList);
    model.addAttribute("paging", paging);
    model.addAttribute("searchKeyword", searchKeyword); // 검색어도 함께 전달


    return "notice/faq-list";
  }//목록으로


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

  // 자주묻는질문 등록 시 요청 url 이 뷰가 되도록 void 로 작성
  @GetMapping("/faq-write")
  public void registFaqPage() {
  }

  // 자주묻는질문 등록
  @PostMapping("/faq-write")
  public String registFaq(@ModelAttribute NoticeDTO noticeDTO) {
    noticeService.registFaq(noticeDTO);

    return "redirect:/notice/faq-list";
  }

//---------------------------------------------------------------------------------------------

  // 공지사항 수정 페이지
  @GetMapping("/modify/notice/{id}")
  public String noticeModify(@PathVariable("id") Integer noticeCode, Model model) {
    // 공지사항을 noticeCode로 조회
    model.addAttribute("notice", noticeService.findNoticeByNoticeCode(noticeCode));

    return "notice/notice-modify";     // 글쓰기 html과 동일
  }

  // 자주묻는질문 수정 페이지
  @GetMapping("/modify/faq/{id}")
  public String faqModify(@PathVariable("id") Integer noticeCode, Model model) {
    // 공지사항을 noticeCode로 조회
    model.addAttribute("faq", noticeService.findNoticeByNoticeCode(noticeCode));

    return "notice/faq-modify";     // 글쓰기 html과 동일
  }

  // 공지사항 수정 진행
//  @PostMapping("/update/{id}")
//  public String noticeUpdate(@PathVariable("id") Integer noticeCode, NoticeDTO noticedto) {
//    // 서비스 레이어에서 수정 처리
//    noticeService.updateNotice(noticeCode, noticedto);
//    return "redirect:/notice/notice-list_ver1"};
//

  @PostMapping("/update/{id}")
  public String updateNotice(@PathVariable("id") Integer noticeCode, NoticeDTO noticedto, @RequestParam("noticeType") String noticeType) {
    // 타입에 따라 수정 처리
    if ("공지사항".equals(noticeType)) {
      noticeService.updateNotice(noticeCode, noticedto, "공지사항");
      return "redirect:/notice/notice-list_ver1";  // 공지사항 리스트로 리다이렉트
    } else if ("자주묻는질문".equals(noticeType)) {
      noticeService.updateNotice(noticeCode, noticedto, "자주묻는질문");
      return "redirect:/notice/faq-list";  // 자주 묻는 질문 리스트로 리다이렉트
    }
    return "redirect:/notice/faq-list";
//    return "redirect:/notice/notice-list_ver1";  // 기본 리다이렉트 경로 (예: 공지사항 기본 리스트)
  }


  // 공지사항 삭제
  @PostMapping("/delete")
  public String deleteNotice(@RequestParam("noticeCode") Integer noticeCode) {
    noticeService.deleteNotice(noticeCode);
    return "redirect:/notice/notice-list_ver1";
  }





}





