package com.bbmore.admin.anotice.common;

import org.springframework.data.domain.Page;
// 원본
public class Pagenation {
    public static PagingButton getPagingButtonInfo(Page page, String searchKeyword) {
        int currentPage = page.getNumber() + 1; // 현재 페이지 (0부터 시작하므로 1을 더함)
        int defaultButtonCount = 10;            // 한 페이지에 보여줄 버튼의 개수
        int startPage
                = (int) (Math.ceil((double) currentPage / defaultButtonCount) - 1)
                * defaultButtonCount + 1;
        int endPage = startPage + defaultButtonCount - 1;

        // 전체 페이지가 endPage보다 적을 경우 처리
        if (page.getTotalPages() < endPage) endPage = page.getTotalPages();
        if (page.getTotalPages() == 0 && endPage == 0) endPage = startPage;

        // 검색어와 페이지 정보를 포함한 페이징 버튼 생성
        return new PagingButton(currentPage, startPage, endPage, searchKeyword);
    }
}

