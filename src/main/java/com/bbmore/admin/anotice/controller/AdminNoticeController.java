package com.bbmore.admin.anotice.controller;


import com.bbmore.admin.anotice.common.Pagenation;
import com.bbmore.admin.anotice.common.PagingButton;
import com.bbmore.admin.anotice.dto.NoticeDTO;
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
public class AdminNoticeController {

  private final NoticeService noticeService;
  private final AdminNoticeRepository adminNoticeRepository;
  

  // @PageableDefault Pageable pageable :
  @GetMapping("/notice-list_ver1")
  public String findNoticeList(Model model, @PageableDefault Pageable pageable) {

    /* 페이징 처리 이후 */
    // {}: 위치홀더라고 생각할 것
    log.info("pageable: {}", pageable);

    Page<NoticeDTO> noticeList = noticeService.findNoticeList(pageable);

    log.info("{}", noticeList.getContent());
    log.info("{}", noticeList.getTotalPages());
    log.info("{}", noticeList.getTotalElements());
    log.info("{}", noticeList.getSize());
    log.info("{}", noticeList.getNumberOfElements());
    log.info("{}", noticeList.isFirst());
    log.info("{}", noticeList.isLast());
    log.info("{}", noticeList.getSort());
    log.info("{}", noticeList.getNumber());


    PagingButton paging = Pagenation.getPagingButtonInfo(noticeList);

    model.addAttribute("noticeList", noticeList);
    model.addAttribute("paging", paging);

    return "notice/notice-list_ver1"; //목록으로
  }

  // 공지사항 상세보기
  @GetMapping("/notice-view/{noticeCode}")
  @Transactional
  public String viewNotice(@PathVariable Integer noticeCode, Model model) {
    // noticeCode로 공지사항 조회
    NoticeDTO noticeDTO = noticeService.findNoticeByNoticeCode(noticeCode);
    Optional<Notice> prevNotice = noticeService.getPrevNotice(noticeCode);
    Optional<Notice> nextNotice = noticeService.getNextNotice(noticeCode);

    // 조회수 증가
    noticeService.increaseViewCount(noticeCode);
    
    // 조회된 공지사항을 모델에 추가
    model.addAttribute("notice", noticeDTO);
    prevNotice.ifPresent(n -> model.addAttribute("prevNotice", n)); // 이전 글 추가
    nextNotice.ifPresent(n -> model.addAttribute("nextNotice", n)); // 다음 글 추가

    return "notice/notice-view"; // 공지사항 상세보기 페이지로 이동
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
  
  // 공지사항 수정
  @GetMapping("/modify/{id}")
    public String noticeModify(@PathVariable("id") Integer noticeCode, Model model) {

    model.addAttribute("notice", noticeService.findNoticeByNoticeCode(noticeCode));

    return "notice/noticemodify";     // 글쓰기 html과 동일
    }
  
    // 공지사항 수정 진행
    @PostMapping("/update/{id}")
  public String noticeUpdate(@PathVariable("id") Integer noticeCode, NoticeDTO noticedto) {

    NoticeDTO noticeDTO1 = noticeService.findNoticeByNoticeCode(noticeCode); // 기존내용 찾기
    noticeDTO1.setNoticeTitle(noticedto.getNoticeTitle());  // 덮어씌우기
    noticeDTO1.setNoticeContent(noticedto.getNoticeContent());

    noticeService.registNotice(noticeDTO1);
    
    return "redirect:/notice/notice-list_ver1";
    }
    
    // 공지사항 삭제
    @PostMapping("/delete")
    public String deleteNotice(@RequestParam("noticeCode") Integer noticeCode) {
      noticeService.deleteNotice(noticeCode);
      return "redirect:/notice/notice-list_ver1";
    }

  }






