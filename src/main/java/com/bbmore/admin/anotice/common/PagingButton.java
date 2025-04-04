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

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    @Override
    public String toString() {
        return "PagingButton{" +
                "currentPage=" + currentPage +
                ", startPage=" + startPage +
                ", endPage=" + endPage +
                ", searchKeyword='" + searchKeyword + '\'' +
                '}';
    }
}
