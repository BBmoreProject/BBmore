package com.bbmore.notice.service;


import com.bbmore.admin.anotice.entity.Notice;
import com.bbmore.notice.repository.UserNoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class UserNoticeService {

  private final UserNoticeRepository userNoticeRepository;

  @Autowired
  public UserNoticeService(UserNoticeRepository userNoticeRepository) {
    this.userNoticeRepository = userNoticeRepository;
  }

  public Page<Notice> getNotices(String noticeType, int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    return userNoticeRepository.findByNoticeType(noticeType, pageable);
  }

  public Notice getNoticeDetail(Integer noticeCode, String noticeType) {
    return userNoticeRepository.findById(noticeCode)
        .orElseThrow(() -> new IllegalArgumentException("Notice not found"));
  }





  }








