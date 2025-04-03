//package com.bbmore.product.service;
//
//import com.bbmore.product.dto.MemberFormDTO;
//import com.bbmore.product.entity.Member;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//@SpringBootTest
//@Transactional
//@TestPropertySource(locations="classpath:application-test.yml")
//class MemberServiceTest {
//
//    @Autowired
//    MemberService memberService;
//
//    @Autowired
//    PasswordEncoder passwordEncoder;
//
//    public Member createMember(){
//        MemberFormDTO memberFormDTO = new MemberFormDTO();
//        memberFormDTO.setEmail("test@test.com");
//        memberFormDTO.setName("Alice");
//        memberFormDTO.setAddress("Seoul");
//        memberFormDTO.setPassword("1234");
//        return Member.createMember(memberFormDTO, passwordEncoder);
//    }
//
//
//    /**
//     * 1. createMember 을 통해 테스트 Member 객체 생성
//     * 2. savedMember에 값 저장
//     * 3. 저장 과정에서 데이터가 변형되지 않았는지 확인
//     *
//     */
//    @Test
//    @DisplayName("Register Test")
//    public void saveMemberTest(){
//        Member member = createMember(); /// Member 타입의 member 변수는 = createdMember(선언 시 타입지정)
//        Member savedMember = memberService.saveMember(member);
//
//        assertEquals(member.getEmail(), savedMember.getEmail());
//        assertEquals(member.getName(), savedMember.getName());
//        assertEquals(member.getAddress(), savedMember.getAddress());
//        assertEquals(member.getPassword(), savedMember.getPassword());
//        assertEquals(member.getRole(), savedMember.getRole());
//    }
//
//    @Test
//    @DisplayName("Duplicate Register Test")
//    public void saveDuplicateMemberTest() {
//        Member member1 = createMember();
//        Member member2 = createMember();
//        memberService.saveMember(member1);
//
//        /// "불법 상태 예외" - 기대되는 예외 유형을 지정
//        Throwable e = assertThrows(IllegalStateException.class, () ->{
//            memberService.saveMember(member2);
//        });
//        assertEquals("이미 가입된 회원입니다.", e.getMessage());
//        ///  발생한 예외 객체를 e 변수에 저장,  validateDuplicateMember 메소드에서 던진 예외 메시지와 일치
//    }
//}