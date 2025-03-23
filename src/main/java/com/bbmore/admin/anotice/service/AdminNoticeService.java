package com.bbmore.admin.anotice.service;

import com.bbmore.admin.anotice.dto.AdminNoticeDTO;
import com.bbmore.admin.anotice.entity.AdminNotice;
import com.bbmore.admin.anotice.repository.NoticeAdminRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminNoticeService {

  private final NoticeAdminRepository noticeAdminRepository;
  private final ModelMapper modelMapper;


  /* findById */
  public AdminNoticeDTO findById (int notice_code) {

    AdminNotice foundNotice = noticeAdminRepository.findById(notice_code)
    .orElseThrow(() -> new IllegalArgumentException("공지사항을 찾을 수 없습니다."));

    // modelMapper라는 라이브러리가 map 메서드를 이용해서 foundMenu 엔티티에 담긴 값들을 MenuDTO 타입으로
    return modelMapper.map(foundNotice, AdminNoticeDTO.class);
  }


  /* findAll : sort 사용 */
  public List<AdminNoticeDTO> findNoticeList() {
    List<AdminNotice> adminNoticeList = noticeAdminRepository.findAll(Sort.by(Sort.Direction.DESC, "notice_code"));
    // 리스트 -> 스트림 -> 모델맵퍼의 map 이용해서 DTO 로 -> 다시 리스트로
    return adminNoticeList.stream()
        .map(adminNotice -> modelMapper.map(adminNotice, AdminNoticeDTO.class))
        .toList();
  }


  /* findAll : Pageable*/
  // Pageable 객체 : 스프링 data domain 에서 제공
  // PageRequest.of : PageNumber, PageSize, sort 3가지 전달 필요
  // PageNumber 는 0부터
  public Page<AdminNoticeDTO> findAdminNoticeList(Pageable pageable) {
    pageable = PageRequest.of(
        pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
        pageable.getPageSize(),
        Sort.by("notice_code").descending()
    );
    Page<AdminNotice> adminNoticeList = noticeAdminRepository.findAll(pageable);
    // map 함수로 menu를 하나 하나 ㅏ다 꺼내서 modelMapper.. DTO 타입으로
    return adminNoticeList.map(adminNotice -> modelMapper.map(adminNotice, AdminNoticeDTO.class));
  }


  /* JPQL or Native Query */
  public List<AdminNoticeDTO> findAllAdminNotice() {
    List<AdminNotice> adminNoticeList = noticeAdminRepository.findAll();

    return adminNoticeList.stream()
        .map(adminNotice -> modelMapper.map(adminNotice, AdminNoticeDTO.class))
        .toList();
  }


}
