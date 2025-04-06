package com.bbmore.product.service;

import com.bbmore.product.entity.Member;
import com.bbmore.product.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    // Method to register a new member
    public Member saveMember (Member member) {
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findByUserEmail(member.getUserEmail());
        if(findMember != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail)
            throws UsernameNotFoundException {

        Member member = memberRepository.findByUserEmail(userEmail);

        if (member == null) {
            throw new UsernameNotFoundException(userEmail);
        }

        return User.builder()
                .username(member.getUserEmail())
                .password(member.getUserPassword())
                .roles(member.getRole().toString())
                .build();
    }
}
