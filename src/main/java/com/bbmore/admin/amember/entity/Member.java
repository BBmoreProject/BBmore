package com.bbmore.admin.amember.entity;

import com.bbmore.admin.amember.dto.MemberDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_member")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userCode;

    private String userName;
    private String userAddress;
    private String userMembershipLevel;
    private String userPhoneNumber;

    @Column(name = "animal_breed")
    private String animalBreed;

    public MemberDTO toDTO() {
        return MemberDTO.builder()
                .userCode(this.userCode)
                .userName(this.userName)
                .userAddress(this.userAddress)
                .userMembershipLevel(this.userMembershipLevel)
                .userPhoneNumber(this.userPhoneNumber)
                .animalBreed(this.animalBreed)
                .build();
    }
}