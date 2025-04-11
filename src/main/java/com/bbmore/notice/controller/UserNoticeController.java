package com.bbmore.notice.controller;


import com.bbmore.admin.anotice.common.Pagenation;
import com.bbmore.admin.anotice.common.PagingButton;
import com.bbmore.admin.anotice.entity.Notice;
import com.bbmore.notice.dto.UserNoticeDTO;
import com.bbmore.notice.repository.UserNoticeRepository;
import com.bbmore.notice.service.UserNoticeService;
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
@RequestMapping("/members")
@RequiredArgsConstructor
@SessionAttributes("searchKeyword")
public class UserNoticeController {

  private final UserNoticeService userNoticeService;
  private final UserNoticeRepository userNoticeRepository;


  // 공지사항 타입만 찾아오기(원본-수정금지)
  @GetMapping("/user-notice-list")
  public String findNoticeList(Model model, @PageableDefault Pageable pageable,
                               @RequestParam(value = "searchKeyword", required = false) String searchKeyword,
                               @RequestParam(value = "noticeType", required = false) String noticeType) {


//    Page<NoticeTypeDTO> noticeList = noticeService.findNoticeList(pageable);

    // 검색어가 있을 때와 없을 때 처리
    Page<UserNoticeDTO> noticeList;

    if (searchKeyword == null || searchKeyword.trim().isEmpty()) {
      noticeList = userNoticeService.findNoticeList(pageable);  // 검색어가 없으면 전체 리스트 조회
      System.out.println("검색어 없을때 noticeList:  " + noticeList);
    } else {
      // 검색어가 있을 경우 해당 검색어로 필터링하여 조회
      noticeList = userNoticeService.noticeSearchList(searchKeyword, "공지사항", pageable);  // 타입+제목 검색으로 필터링
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
    model.addAttribute("searchKeyword", searchKeyword);
    model.addAttribute("noticeType", noticeType);


    // 목록으로
    return "members/user-notice-list";
  }

//---------------------------------------------------------------------------------------


  // 자주묻는질문 타입만 조회 (원본-수정금지)
  @GetMapping("/user-faq-list")
  public String findFaqList(Model model, @PageableDefault Pageable pageable,
                            @RequestParam(value = "searchKeyword", required = false) String searchKeyword,
                            @RequestParam(value = "noticeType", required = false) String noticeType) {

    // 검색어가 있을 때와 없을 때 처리
    Page<UserNoticeDTO> faqList;

    if (searchKeyword == null || searchKeyword.trim().isEmpty()) {
      faqList = userNoticeService.findFaqList(pageable);  // 검색어가 없으면 전체 리스트 조회
      System.out.println("검색어 없을때 faqList:  " + faqList);
    } else {
      // 검색어가 있을 경우 해당 검색어로 필터링하여 조회
      faqList = userNoticeService.noticeSearchList(searchKeyword, "자주묻는질문",pageable);  // 검색어로 필터링
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
    model.addAttribute("noticeType", noticeType);


    return "members/user-faq-list";
  }

  //-----------------------------------------------------------------------------------------------


  //  USER - 공지사항 상세보기(수정금지)
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
    log.info("userNotice DTO: {}", userNoticeDTO);
    log.info("Prev Notice: {}", prevNotice.orElse(null));
    log.info("Next Notice: {}", nextNotice.orElse(null));

    return "members/user-notice-view"; // 공지사항 상세보기 페이지로 이동
  }


  // USER - 자주묻는질문 상세보기(가능)
  @GetMapping("/user-faq-view/{noticeCode}")
  @Transactional
  public String viewFaq(@PathVariable Integer noticeCode, Model model) {

    UserNoticeDTO faqDTO = userNoticeService.findNoticeByNoticeCode(noticeCode);

    // FAQ 조회
    Optional<Notice> prevFaq = userNoticeService.getPrevNotice(noticeCode, "자주묻는질문");
    Optional<Notice> nextFaq = userNoticeService.getNextNotice(noticeCode, "자주묻는질문");

    // 조회수 증가(같은 트랜젝션 안에서 조회수 증가되도록 @Transactional 사용)
    userNoticeService.increaseViewCount(noticeCode);

    // FAQ 관련 정보 모델에 추가
    model.addAttribute("faq", faqDTO);

    prevFaq.ifPresent(n -> model.addAttribute("prevFaq", n)); // 이전 faq 추가
    nextFaq.ifPresent(n -> model.addAttribute("nextFaq", n)); // 다음 faq 추가

    return "members/user-faq-view";
  }


  }


