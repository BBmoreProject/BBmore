package com.bbmore.admin.anotice.service;


import com.bbmore.admin.anotice.dto.NoticeDTO;
import com.bbmore.admin.anotice.dto.NoticeTypeDTO;
import com.bbmore.admin.anotice.entity.Notice;
import com.bbmore.admin.anotice.repository.AdminNoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeService {


  private final AdminNoticeRepository adminNoticeRepository;
  private final ModelMapper modelMapper;


  /* findAll : Pageable 사용*/
  // Pageable 객체 : 스프링 data domain 에서 제공
  // PageRequest.of : PageNumber, PageSize, sort 3가지 전달 필요
  // PageNumber 는 0부터
  // 공지사항 조회가능
  public Page<NoticeTypeDTO> findNoticeList(Pageable pageable) {
    pageable = PageRequest.of(
        pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
        pageable.getPageSize(),
        Sort.by("noticeCreatedDate").descending()
    );
    Page<Notice> noticeList = adminNoticeRepository.findByNoticeType("공지사항",pageable);
    // map 함수로 notice를 하나 하나 ㅏ다 꺼내서 modelMapper.. DTO 타입으로
    return noticeList.map(notice -> modelMapper.map(notice, NoticeTypeDTO.class));
  }


  /* 공지사항 등록 save */
  @Transactional
  public void registNotice(NoticeDTO noticeDTO) {
    adminNoticeRepository.save(modelMapper.map(noticeDTO, Notice.class));  // DTO를 Entity로 가공

  }


  // noticeCode로 공지사항 조회
  public NoticeDTO findNoticeByNoticeCode(Integer noticeCode) {
    Notice foundNotice = adminNoticeRepository.findById(noticeCode)
        .orElseThrow(() -> new IllegalArgumentException("공지사항을 찾을 수 없습니다."));

    return modelMapper.map(foundNotice, NoticeDTO.class);
  }

  // 검색 기능: 검색어로 제목을 포함하는 공지사항 조회
  public Page<NoticeTypeDTO> noticeSearchList(String searchKeyword, Pageable pageable) {
    Page<Notice> notices = adminNoticeRepository.findByNoticeTitleContaining(searchKeyword, pageable);

    // Notice 엔티티를 NoticeDTO로 변환
    return notices.map(this::convertToDTO);
  }

  // Notice -> NoticeDTO 변환 메서드
  private NoticeTypeDTO convertToDTO(Notice notice) {
    return new NoticeTypeDTO(
        notice.getNoticeCode(),
        notice.getNoticeType(),
        notice.getNoticeTitle(),
        notice.getNoticeCreatedDate(),
        notice.getNoticeView(),
        notice.getNoticeContent()
    );
  }


  // 조회수 증가
  @Transactional
  public void increaseViewCount(Integer noticeCode) {
    Notice notice = adminNoticeRepository.findById(noticeCode)
        .orElseThrow(() -> new RuntimeException("공지사항을 찾을 수 없습니다."));

    notice.setNoticeView(notice.getNoticeView() + 1); // Getter를 활용한 조회수 증가
  }


  // 이전글
  public Optional<Notice> getPrevNotice(Integer noticeCode) {
    return adminNoticeRepository.findTopByNoticeCodeLessThanOrderByNoticeCodeDesc(noticeCode);
  }

  // 다음글
  public Optional<Notice> getNextNotice(Integer noticeCode) {
    return adminNoticeRepository.findTopByNoticeCodeGreaterThanOrderByNoticeCodeAsc(noticeCode);
  }


  /* 수정(엔티티 객체의 필드 값 변경) */
//    @Transactional
//    public void modifyNotice(NoticeDTO noticeDTO) {
//        Notice foundNotice = adminNoticeRepository.findById(noticeDTO.getNoticeCode()).orElseThrow(IllegalArgumentException::new);
//
//        /* setter 사용 지양 , 기능에 맞는 메소드를 정의해서 사용할 것 */
//        foundNotice.modifyNoticeTitle(noticeDTO.getNoticeTitle());
//        foundNotice.modifyNoticeContent(noticeDTO.getNoticeContent());
//    }

  /* 특정게시글 삭제 */
  @Transactional
  public void deleteNotice(Integer noticeCode) {

    adminNoticeRepository.deleteById(noticeCode);
  }


}