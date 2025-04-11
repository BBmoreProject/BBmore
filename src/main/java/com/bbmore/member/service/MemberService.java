package com.bbmore.member.service;

import com.bbmore.member.dto.MemberDTO;
import com.bbmore.member.dto.MemberUpdateDTO;
import com.bbmore.member.entity.Animal;
import com.bbmore.member.entity.Member;
import com.bbmore.member.repository.AnimalRepository;
import com.bbmore.member.repository.MemberRepository;
import com.bbmore.member.repository.MemberUpRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j  // 로그 추가
public class MemberService {

    private final MemberRepository memberRepository;
    private final AnimalRepository animalRepository;
    private final AnimalService animalService;
    private final MemberUpRepository memberUpRepository;

    // 특정 회원 정보 조회
    public MemberDTO getMemberByUserId(String userId) {
        Member member = memberRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다: " + userId));

        return new MemberDTO(member); // Member → MemberDTO 변환
    }



    @Transactional
    public void modifyMember(MemberUpdateDTO memberUpdateDTO) {
        Member member = memberUpRepository.findByUserId(memberUpdateDTO.getUserId());

        if (member == null) {
            throw new IllegalArgumentException("해당 사용자가 존재하지 않습니다.");
        }

        System.out.println("기존 animalCode: " + (member.getAnimal() != null ? member.getAnimal().getAnimalCode() : "없음"));

        Animal animal = animalRepository.findById(memberUpdateDTO.getAnimalCode())
                .orElseThrow(() -> new IllegalArgumentException("해당 animalCode 에 해당하는 Animal 정보가 없습니다."));


        if (animal == null) {
            throw new IllegalArgumentException("해당 animalBreed 에 해당하는 Animal 정보가 없습니다.");
        }

        System.out.println("변경할 animalCode: " + animal.getAnimalCode());

        // animal 업데이트
        member.updateAnimal(animal);

        // 변경된 회원 정보 업데이트
        member.updateMemberInfo(
                memberUpdateDTO.getUserId(),
                memberUpdateDTO.getUserPassword(),
                memberUpdateDTO.getUserName(),
                memberUpdateDTO.getUserAddress(),
                memberUpdateDTO.getUserPhoneNumber(),
                memberUpdateDTO.getUserEmail(),
                memberUpdateDTO.getUserPetName(),
                memberUpdateDTO.getUserPetAge(),
                memberUpdateDTO.getUserPetWeight(),
                memberUpdateDTO.getUserPetMedicalHistory(),
                animal
        );

        // 변경 사항 저장
        memberRepository.save(member);

        System.out.println("업데이트 후 animalCode: " + member.getAnimal().getAnimalCode());
    }




}
