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


  // PageRequest.of : PageNumber, PageSize, sort 3가지 전달 필요
  // PageNumber 는 0부터
  // 1-1 게시글타입 - 공지사항 타입 조회해오기 (수정금지)
  public Page<NoticeTypeDTO> findNoticeList(Pageable pageable) {
    pageable = PageRequest.of(
        pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
        pageable.getPageSize(),
        Sort.by("noticeCreatedDate").descending()
    );
    Page<Notice> noticeList = adminNoticeRepository.findByNoticeType("공지사항", pageable);
    // map 함수로 notice를 하나 하나 ㅏ다 꺼내서 modelMapper.. DTO 타입으로
    return noticeList.map(notice -> modelMapper.map(notice, NoticeTypeDTO.class));
  }


  // 1-2 게시글타입 - 자주묻는질문 타입 조회해오기 (수정금지)
  public Page<NoticeTypeDTO> findFaqList(Pageable pageable) {
    pageable = PageRequest.of(
        pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
        pageable.getPageSize(),
        Sort.by("noticeCreatedDate").descending()
    );
    Page<Notice> faqList = adminNoticeRepository.findByNoticeType("자주묻는질문", pageable);
    // map 함수로 notice를 하나 하나 ㅏ다 꺼내서 modelMapper.. DTO 타입으로
    return faqList.map(faq -> modelMapper.map(faq, NoticeTypeDTO.class));
  }
//--------------------------------------------------------------------------------------------------------------




//--------------------------------------------------------------------------------------------------------------

  /* 공지사항 등록 save */
  @Transactional
  public void registNotice(NoticeDTO noticeDTO) {
    adminNoticeRepository.save(modelMapper.map(noticeDTO, Notice.class));  // DTO를 Entity로 가공

  }

  /* 자주묻는질문 등록 save */
  @Transactional
  public void registFaq(NoticeDTO noticeDTO) {
    adminNoticeRepository.save(modelMapper.map(noticeDTO, Notice.class));  // DTO를 Entity로 가공

  }



  // noticeCode로 공지사항 조회
  public NoticeDTO findNoticeByNoticeCode(Integer noticeCode) {
    Notice foundNotice = adminNoticeRepository.findById(noticeCode)
        .orElseThrow(() -> new IllegalArgumentException("공지사항을 찾을 수 없습니다."));

    return modelMapper.map(foundNotice, NoticeDTO.class);
  }

  //------------------------------------------------------------------------------------

  // 공지사항/자주묻는질문 수정
  @Transactional
  public void updateNotice(Integer noticeCode, NoticeDTO noticedto, String noticeType) {
    Notice notice = adminNoticeRepository.findById(noticeCode)
        .orElseThrow(() -> new IllegalArgumentException("해당" + noticeType + "을 찾을 수 없습니다."));

    // Builder 패턴을 사용하여 수정된 값으로 새로운 객체 생성
    Notice updatedNotice = notice.toBuilder()  // 기존 객체에서 Builder로 변경
        .noticeTitle(noticedto.getNoticeTitle())  // 수정된 제목
        .noticeContent(noticedto.getNoticeContent())  // 수정된 내용
        .noticeType(noticeType)                      // 수정된 noticeType을 반영
        .build();  // 새로운 객체 생성

    // DB에 저장
    adminNoticeRepository.save(updatedNotice);

  }


  //------------------------------------------------------------------------------------

  // 검색 기능: 검색어로 제목을 포함하는 공지사항 조회- test  15:15
  public Page<NoticeTypeDTO> noticeSearchList(String searchKeyword, String noticeType , Pageable pageable) {
    pageable = PageRequest.of(
            pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
            pageable.getPageSize(),
            Sort.by("noticeCreatedDate").descending()
    );

    // 검색어가 제목에 포함된 게시글만 필터링
    Page<Notice> noticeList = adminNoticeRepository.findByNoticeTypeAndNoticeTitleContaining(noticeType, searchKeyword, pageable);

    // notice를 DTO로 변환
    return noticeList.map(notice -> modelMapper.map(notice, NoticeTypeDTO.class));
  }



  //------------------------------------------------------------------------------------

  // 조회수 증가 (가능)
  @Transactional
  public void increaseViewCount(Integer noticeCode) {
    Notice notice = adminNoticeRepository.findById(noticeCode)
        .orElseThrow(() -> new RuntimeException("공지사항을 찾을 수 없습니다."));

    notice.setNoticeView(notice.getNoticeView() + 1); // Getter를 활용한 조회수 증가
  }

  //------------------------------------------------------------------------------------

  // 이전 공지사항 조회 (타입별로)
  public Optional<Notice> getPrevNotice(Integer noticeCode, String noticeType) {
    return adminNoticeRepository.findFirstByNoticeCodeLessThanAndNoticeTypeOrderByNoticeCodeDesc(noticeCode, noticeType);
  }

  // 다음 공지사항 조회 (타입별로)
  public Optional<Notice> getNextNotice(Integer noticeCode, String noticeType) {
    return adminNoticeRepository.findFirstByNoticeCodeGreaterThanAndNoticeTypeOrderByNoticeCodeAsc(noticeCode, noticeType);
  }


  //------------------------------------------------------------------------------------


  /* 특정게시글 삭제 */
  @Transactional
  public void deleteNotice(Integer noticeCode) {

    adminNoticeRepository.deleteById(noticeCode);
  }



}