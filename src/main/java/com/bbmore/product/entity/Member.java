package com.bbmore.product.entity;

import com.bbmore.product.constant.Role;
import com.bbmore.product.dto.MemberFormDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table(name="member")
@Getter
@Setter
@ToString
/**
 * 회원 정보를 정리하는 MemberEntity
 * 관리할 회원 정보는 이름, 이메일, 비밀번호, 주소, 역할
 */
public class Member {

    @Id
    @Column(name="member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true) /// 이메일은 고유값이어야 하므로 unique 속성을 true로 설정
    private String email;

    private String password;

    private String address;

    /// Enum 어노테이션을 사용하는 이유는 Enum 타입을 데이터베이스 저장할 때 타입을 지정하기 위함
    /// Enum.ORDINAL 을 사용하면 ENUM 값의 순서가 인덱스로 저장
    ///
    @Enumerated(EnumType.STRING)
    private Role role;

    /**
     *
     * @param memberFormDTO: 회원가입 화면으로부터 넘어오는 가입정보를 담은 dto
     * @param passwordEncoder: Security에서 제공하는 비밀번호를 안전하게 저장하기 위한 인터페이스
     *                       인터페이스는 비밀번호를 해싱(Hashing)하고 검증하는 기능을 제공
     * 1. 비밀번호 인코딩(해싱): encode() 메서드를 통해 평문 비밀번호를 해쉬된 형태로 변환
     * 2. 비밀번호 검증: matches()메서드를 통해 사용자가 입력한 비밀번호와 저장된 해시가 일치하는지 확인
     *
     *                       입력한 비밀번호와 저장된 해시 비밀번호 비교(BCrypt)알고리즘 이용
     *
     * @return
     *
     * createMember = MemberDTO, PasswordEncoder 매개변수로 받아 Member 엔티티 생성
     *
     * 회원가입 폼에서 입력한 데이터를 dto로 받음 (날 것의 데이터)
     * 웹 -> 서비스 -> 데이터 베이스는 서로 분리해야함
     * 데이터베이스로 직접 매핑이라 웹 레이어에서 다루는 건 좋지 않음.
     *
     * 3.유효성 검사.
     * @NotEmpty, @Email 같은 유효성 검사 어노테이션으로 입력값 검증(엔티티에는 검증 로직 불필요)
     *
     * 4.데이터노출
     * 필요한 필드만 포함시켜 데이터 노출 방지
     *
     * 계좌 신청서 -> 실제 계좌와의 관계(추가적인 정보ㅖ*
     * // 컨트롤러: 신청서(DTO) 접수
     * 검증과 데이터 가공


     *
     */
    /// 팩토리 메서드  패턴: 도메인 객체가 자신의 생명주기를 관리, 여러 서비스에서 중복해서 작성 안해도 됨
    public static Member createMember(MemberFormDTO memberFormDTO,
                                      PasswordEncoder passwordEncoder) {

        Member member = new Member(); /// 멤버 객체 생성
        member.setName(memberFormDTO.getName());
        member.setEmail(memberFormDTO.getEmail());
        member.setAddress(memberFormDTO.getAddress());
        String password = passwordEncoder.encode(memberFormDTO.getPassword()); /// 예외 처리 로직 필요성
        member.setPassword(password);
        member.setRole(Role.ADMIN); /// 상품관리 접근을 위해 ADMIN 으로 세팅
        return member;
    }
}
