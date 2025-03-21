package com.bbmore.admin.anotice.dto;

import java.util.Date;

public class AdminNoticeDTO {

    private int notice_code;
    private String notice_type;
    private Date notice_created_date;
    private int notice_view;
    private String notice_content;

    public AdminNoticeDTO() {
    }

    public AdminNoticeDTO(int notice_code, String notice_type, Date notice_created_date, int notice_view, String notice_content) {
        this.notice_code = notice_code;
        this.notice_type = notice_type;
        this.notice_created_date = notice_created_date;
        this.notice_view = notice_view;
        this.notice_content = notice_content;
    }

    public int getNotice_code() {
        return notice_code;
    }

    public void setNotice_code(int notice_code) {
        this.notice_code = notice_code;
    }

    public String getNotice_type() {
        return notice_type;
    }

    public void setNotice_type(String notice_type) {
        this.notice_type = notice_type;
    }

    public Date getNotice_created_date() {
        return notice_created_date;
    }

    public void setNotice_created_date(Date notice_created_date) {
        this.notice_created_date = notice_created_date;
    }

    public int getNotice_view() {
        return notice_view;
    }

    public void setNotice_view(int notice_view) {
        this.notice_view = notice_view;
    }

    public String getNotice_content() {
        return notice_content;
    }

    public void setNotice_content(String notice_content) {
        this.notice_content = notice_content;
    }

    @Override
    public String toString() {
        return "AdminNoticeDTO{" +
                "notice_code=" + notice_code +
                ", notice_type='" + notice_type + '\'' +
                ", notice_created_date=" + notice_created_date +
                ", notice_view=" + notice_view +
                ", notice_content='" + notice_content + '\'' +
                '}';
    }
}
