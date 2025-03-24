package com.bbmore.admin.anotice.controller;


import com.bbmore.admin.anotice.common.Pagenation;
import com.bbmore.admin.anotice.common.PagingButton;
import com.bbmore.admin.anotice.dto.NoticeDTO;
import com.bbmore.admin.anotice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/notice")
@RequiredArgsConstructor
public class AdminNoticeController {

  private final NoticeService noticeService;

  // @PageableDefault Pageable pageable :
  @GetMapping("/notice-list_ver1")
  public String findNoticeList(Model model, @PageableDefault Pageable pageable){

    /* 페이징 처리 이전 */
//        List<MenuDTO> menuList = menuService.findMenuList();
//        model.addAttribute("menuList", menuList);

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

    return "notice/notice-list_ver1";
  }

  //    @GetMapping("/querymethod")
//    public void querymethoddPage(){}

//    @GetMapping("/search")
//    public String findByMenuPrice(@RequestParam Integer menuPrice, Model model){
//
//        List<MenuDTO> menuList = menuService.findByMenuPrice(menuPrice);
//
//        model.addAttribute("menuList", menuList);
//
//        return "menu/searchResult";
//    }

  // 요청 url 이 뷰가 되도록 void 로 작성
    @GetMapping("/notice-write_ver1")
    public void registPage(){}

//    @GetMapping("/category")
//    @ResponseBody
//    public List <MenuDTO> findCategoryList(){
//        return menuService.findAllCategory();
//    }

    @PostMapping("/notice-write_ver1")
    public String registNotice(@ModelAttribute NoticeDTO noticeDTO){
        noticeService.registNotice(noticeDTO);
        return "redirect:/notice/notice-write_ver1";
    }




  @GetMapping("/modify")
  public void modifyPage(){}

  @PostMapping("/modify")
  public String modifyNotice(@ModelAttribute NoticeDTO noticeDTO){
    noticeService.modifyNotice(noticeDTO);
    return "redirect:notice/notice-list_ver1" + noticeDTO.getNoticeCode();
  }

  @GetMapping("/delete")
  public void deletePage(){}

  @PostMapping("/delete")
  public String deleteNotice(@RequestParam Integer noticeCode){
    noticeService.deleteNotice(noticeCode);
    return "redirect:notice/notice-list_ver1";       // 삭제 후 메뉴 리스트를 보여줌
  }

}
