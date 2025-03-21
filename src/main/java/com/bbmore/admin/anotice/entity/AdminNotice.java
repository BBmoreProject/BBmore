package com.bbmore.admin.anotice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Date;


@NoArgsConstructor  /*생성자가 없어도 인스턴스 생성 가능 */
@Getter
@Entity
@Table(name = "tbl_notice")
public class AdminNotice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int notice_code;

    @Column(name="notice_type", nullable = false, length = 255)
    private String notice_type;

    @Column(name="notice_title", nullable = false, length = 255)
    private String notice_title;

    @Column(name = "notice_created_date", nullable = false)
    private Date notice_created_date;

//    조회수
    @Column
    @NotNull
    private int notice_view;

    @Column(length = 255)
    @NotNull
    private String notice_content;

}
