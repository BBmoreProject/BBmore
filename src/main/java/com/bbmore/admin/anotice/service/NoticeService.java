package com.bbmore.admin.anotice.service;


import com.bbmore.admin.anotice.dto.NoticeDTO;
import com.bbmore.admin.anotice.entity.Notice;
import com.bbmore.admin.anotice.repository.AdminNoticeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {


    private final AdminNoticeRepository adminNoticeRepository;
    private final ModelMapper modelMapper;


    /* findById */
//    public NoticeDTO findNoticeByNoticeCode(int noticeCode) {
//
//        Notice foundNotice = adminNoticeRepository.findById(noticeCode).orElseThrow(IllegalArgumentException::new);
//
//        // modelMapper라는 라이브러리가 map 메서드를 이용해서 foundMenu 엔티티에 담긴 값들을 MenuDTO 타입으로
//        return modelMapper.map(foundNotice, NoticeDTO.class);
//    }

    /* findAll : sort 사용 */
    public List<NoticeDTO> findNoticeList() {
        List<Notice> noticeList = adminNoticeRepository.findAll(Sort.by("noticeCreatedDate").descending());
        // 리스트 -> 스트림 -> 모델맵퍼의 map 이용해서 DTO 로 -> 다시 리스트로
        return noticeList.stream()
                .map(notice -> modelMapper.map(notice, NoticeDTO.class))
                .toList();
    }

    /* findAll : Pageable*/
    // Pageable 객체 : 스프링 data domain 에서 제공
    // PageRequest.of : PageNumber, PageSize, sort 3가지 전달 필요
    // PageNumber 는 0부터
    public Page<NoticeDTO> findNoticeList(Pageable pageable) {
        pageable = PageRequest.of(
                pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
                pageable.getPageSize(),
                Sort.by("noticeCreatedDate").descending()
        );
        Page<Notice> noticeList = adminNoticeRepository.findAll(pageable);
        // map 함수로 menu를 하나 하나 ㅏ다 꺼내서 modelMapper.. DTO 타입으로
        return noticeList.map(notice -> modelMapper.map(notice, NoticeDTO.class));
    }

    /* Query Method */
//    public List<AdminNoticeDTO> findByMenuPrice(Integer menuPrice) {
//
////        List<Menu> menuList = menuRepository.findByMenuPriceGreaterThan(menuPrice);
////        List<Menu> menuList = menuRepository.findByMenuPriceGreaterThanOrderByMenuPrice(menuPrice);
//        List<AdminNotice> noticeList = adminNoticeRepository.findByMenuPriceGreaterThan(
//                menuPrice,
//                Sort.by("menuPrice").descending()
//        );
//
//        return menuList.stream()
//                .map(menu -> modelMapper.map(menu, MenuDTO.class))
//                .toList();
//    }

/* JPQL or Native Query */
//    public List<MenuDTO> findAllCategory() {
//        List<Menu> categoryList = menuRepository.findAllMenu();
//
//        return categoryList.stream()
//                .map(category -> modelMapper.map(category, MenuDTO.class))
//                .toList();
//    }



    /* save */
    @Transactional
    public void registNotice(NoticeDTO noticeDTO) {
        adminNoticeRepository.save(modelMapper.map(noticeDTO, Notice.class));  // DTO를 Entity로 가공
    }

    /* 수정(엔티티 객체의 필드 값 변경) */
    @Transactional
    public void modifyNotice(NoticeDTO noticeDTO) {
       Notice foundNotice = adminNoticeRepository.findById(noticeDTO.getNoticeCode()).orElseThrow(IllegalArgumentException::new);

        /* setter 사용 지양 , 기능에 맞는 메소드를 정의해서 사용 */
        foundNotice.modifyNoticeTitle(noticeDTO.getNoticeTitle());
        foundNotice.modifyNoticeContent(noticeDTO.getNoticeContent());


    }

    /* deleteById */
    @Transactional
    public void deleteNotice(Integer noticeCode) {
        adminNoticeRepository.deleteById(noticeCode);
    }


}
