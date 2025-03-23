package com.bbmore.admin.anotice.controller;


import com.bbmore.admin.anotice.common.Pagenation;
import com.bbmore.admin.anotice.common.PagingButton;
import com.bbmore.admin.anotice.dto.AdminNoticeDTO;
import com.bbmore.admin.anotice.service.AdminNoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/notice")
@RequiredArgsConstructor
public class AdminNoticeController {

  private final AdminNoticeService adminNoticeService;

  // @PageableDefault Pageable pageable :
  @GetMapping("/notice-list_ver1")
  public String findAdminNoticeList(Model model, @PageableDefault Pageable pageable){

    /* 페이징 처리 이전 */
//        List<MenuDTO> menuList = menuService.findMenuList();
//        model.addAttribute("menuList", menuList);

    /* 페이징 처리 이후 */
    // {}: 위치홀더라고 생각할 것
    log.info("pageable: {}", pageable);

    Page<AdminNoticeDTO> adminNoticeList = adminNoticeService.findAdminNoticeList(pageable);

    log.info("{}", adminNoticeList.getContent());
    log.info("{}", adminNoticeList.getTotalPages());
    log.info("{}", adminNoticeList.getTotalElements());
    log.info("{}", adminNoticeList.getSize());
    log.info("{}", adminNoticeList.getNumberOfElements());
    log.info("{}", adminNoticeList.isFirst());
    log.info("{}", adminNoticeList.isLast());
    log.info("{}", adminNoticeList.getSort());
    log.info("{}", adminNoticeList.getNumber());


    PagingButton paging = Pagenation.getPagingButtonInfo(adminNoticeList);

    model.addAttribute("adminNoticeList", adminNoticeList);
    model.addAttribute("paging", paging);

    return "notice/notice-list_ver1";
  }

}
