package com.bbmore.product.entity;

import com.bbmore.product.constant.Role;
import com.bbmore.product.dto.MemberFormDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table(name="tbl_member")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor  // 빌더와 함께 필요
public class Member {

    @Id
    @Column(name= "user_code")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userCode;

    private String userName;

    private String userPassword;

    private String userAddress;

    @Column(unique = true)
    private String userEmail;

    @Enumerated(EnumType.STRING)
    private Role role;


///  Setter 을 빌더 방식으로 바꾸니까 매개변수 있는 생성자가 필요함.. 왜?
    public static Member createMember(
            MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
        return Member.builder()
                .userName(memberFormDto.getUserName())
                .userEmail(memberFormDto.getUserEmail())
                .userAddress(memberFormDto.getUserAddress())
                .userPassword(passwordEncoder.encode(memberFormDto.getUserPassword()))
                .role(Role.ADMIN)
                .build();
    }
}
