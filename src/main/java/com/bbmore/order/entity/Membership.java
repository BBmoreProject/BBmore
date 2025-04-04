package com.bbmore.order.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tbl_membership")
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "membership_code")
    private int membershipCode;

    @Column(name = "membership_name")
    private String membershipName;

    @OneToMany(mappedBy = "membership")
    private List<Member> members; // Member 테이블과 관계 설정

    // Getters and setters


}
