package com.bbmore.admin.anotice.common;

public class PagingButton {
    private int currentPage;
    private int startPage;
    private int endPage;
    private String searchKeyword;  // 검색어 추가


    public PagingButton() {
    }

    public PagingButton(int currentPage, int startPage, int endPage, String searchKeyword) {
        this.currentPage = currentPage;
        this.startPage = startPage;
        this.endPage = endPage;
        this.searchKeyword = searchKeyword;
    }


    // Getter methods
    public int getCurrentPage() {
        return currentPage;
    }

    public int getStartPage() {
        return startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }
}


