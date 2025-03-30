package com.bbmore.product.service;

import com.bbmore.product.entity.Member;
import com.bbmore.product.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional /// 로직을 처리하다가 에러가 발생하면 변경된 데이터를 로직 수행 이전으로 콜백
public class MemberService implements UserDetailsService {

    /**
     * 인터페이스를 통해 데이터 접근 기능을 수행 (구현체가 아닌 인터페이스에 의존해 코드 결합도 낮춤)
     * final을 이용해 참조가 변경되지 않도록 보장
     *
     * 만약 JpaRepository 직접 주입받으면
     * 기본 CRUD 기능은 수행 가능하나
     * 추가로 정의한 커스텀 메서드를 사용할 수 없음.
     */

    public final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member saveMember(Member member) {
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member) {

        Member findMember = memberRepository.findByEmail(member.getEmail());
        if (findMember != null) { /// 가입하려는 이메일을 데이터베이스에 조회, 결과가 null이 아니면 이미 가입된 회원
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Member member = memberRepository.findByEmail(email);

        if(member == null) {
            throw new UsernameNotFoundException(email);
        }

        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();
    }
    /**
     * UserDetails 객체 생성 및 반환:
     *
     * 회원이 존재하면 스프링 시큐리티가 인증에 사용할 수 있는 UserDetails 타입의 객체를 생성합니다.
     * User.builder()는 스프링 시큐리티에서 제공하는 빌더 패턴으로, UserDetails의 구현체인 User 객체를 생성합니다.
     * 여기에 사용자의 이메일, 비밀번호, 권한 정보를 설정합니다:
     *
     * .username(member.getEmail()): 사용자 식별자로 이메일을 설정
     * .password(member.getPassword()): 데이터베이스에 저장된 암호화된 비밀번호 설정
     * .roles(member.getRole().toString()): 사용자의 권한(역할) 설정
     * .build(): 최종 User 객체 생성
     */
}
