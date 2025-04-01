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
@Builder(toBuilder = true)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userCode;
    private String userName;
    private String userAddress;
    private String userMembershipLevel;
    private String userPhoneNumber;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "animal_code", referencedColumnName = "animalCode")
    private Animal animal;

    public MemberDTO toDTO() {
        return MemberDTO.builder()
                .userCode(this.userCode)
                .userName(this.userName)
                .userAddress(this.userAddress)
                .userMembershipLevel(this.userMembershipLevel)
                .userPhoneNumber(this.userPhoneNumber)
                .animalBreed(this.animal != null ? this.animal.getAnimalBreed() : null)
                .build();
    }
}
