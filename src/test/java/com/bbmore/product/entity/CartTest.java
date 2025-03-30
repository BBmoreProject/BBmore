package com.bbmore.product.entity;

import com.bbmore.product.dto.MemberFormDTO;
import com.bbmore.product.repository.CartRepository;
import com.bbmore.product.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.yml")
class CartTest {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * EntityManager는 JPA의 핵심 인터페이스로,
     * 엔티티의 저장, 수정, 삭제, 조회 등 엔티티와 관련된 모든 데이터베이스 작업을 처리합니다.
     */

    @PersistenceContext
    private EntityManager em;

    public Member createMember() {
        MemberFormDTO memberFormDTO = new MemberFormDTO();
        memberFormDTO.setEmail("test@test.com");
        memberFormDTO.setName("Alice");
        memberFormDTO.setAddress("New York");
        memberFormDTO.setPassword("1234");
        return Member.createMember(memberFormDTO, passwordEncoder);
    }

    @Test
    @DisplayName("Cart Member Entity Mapping Test")
    public void findCartAndMemberTest(){

        Member member = createMember();
        memberRepository.save(member);

        Cart cart = new Cart();
        cart.setMember(member);
        cartRepository.save(cart);

        em.flush();
        em.clear();
///
        /**
         * 이 코드는 Spring Data JPA를 사용하여 특정 ID를 가진 장바구니(Cart) 엔티티를
         * 데이터베이스에서 조회하는 코드입니다. 코드의 동작을 자세히 설명하면 다음과 같습니다:
         * cartRepository.findById(cart.getId()):
         *
         * cartRepository의 findById 메소드를 호출하여 cart.getId()로 얻은 ID 값을
         * 가진 장바구니를 데이터베이스에서 조회합니다.
         * 이 메소드는 Optional<Cart> 타입을 반환합니다.
         *
         * .orElseThrow(EntityNotFoundException::new):
         * 조회 결과가 존재하면(Optional이 비어있지 않으면) 해당 Cart 객체를 반환합니다.
         * 조회 결과가 없으면(Optional이 비어있으면) EntityNotFoundException을 발생시킵니다.
         * 이는 장바구니가 존재하지 않을 경우 예외 처리를 통해 안전하게 오류를 처리하는 패턴입니다.
         */
        Cart savedCart = cartRepository.findById(cart.getId()).
                orElseThrow(EntityNotFoundException::new);
        assertEquals(savedCart.getMember().getId(), member.getId());
    }


}