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

    @GetMapping("/{noticeCode}")
    public String findNoticeByNoticeCode(@PathVariable int noticeCode, Model model) {

        // 메뉴 서비스에서 DTO 값으로 변환되서 담긴 값을 resultMenu 에 담는다
        NoticeDTO resultNotice = noticeService.findNoticeByNoticeCode(noticeCode);
        model.addAttribute("menu", resultNotice);

        return "notice/notice-list_ver1";    // 뷰를 반환
    }

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

    @GetMapping("/regist")
    public void registPage(){}


    @PostMapping("/regist")
    public String registNotice(@ModelAttribute NoticeDTO noticeDTO){
        noticeService.registNotice(noticeDTO);
        return "redirect:/menu/notice-list_ver1";
    }

    @GetMapping("/modify")
    public void modifyPage(){}

    @PostMapping("/modify")
    public String modifyNotice(@ModelAttribute NoticeDTO noticeDTO){
        noticeService.modifyNotice(noticeDTO);
        return "redirect:/notice/" + noticeDTO.getNoticeCode();
    }

    @GetMapping("/delete")
    public void deletePage(){}

    @PostMapping("/delete")
    public String deleteNotice(@RequestParam Integer noticeCode){
        noticeService.deleteNotice(noticeCode);
        return "redirect:/notice/notice-list_ver1";       // 삭제 후 메뉴 리스트를 보여줌
    }







}
