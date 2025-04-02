package com.bbmore.member.service;

import com.bbmore.member.dto.MemberDTO;
import com.bbmore.member.dto.MemberUpdateDTO;
import com.bbmore.member.entity.Animal;
import com.bbmore.member.entity.Member;
import com.bbmore.member.repository.AnimalRepository;
import com.bbmore.member.repository.MemberRepository;
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

    // 특정 회원 정보 조회 (!!!!!!!!!건들지마!!!!!!!!!!!)
    public MemberDTO getMemberByUserId(String userId) {
        Member member = memberRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다: " + userId));

        return new MemberDTO(member); // Member → MemberDTO 변환
    }


    // 회원 정보 수정 (Setter 없이 수정)
//    @Transactional // JPA 변경 감지(Dirty Checking) 활성화
//    public Member updateMember(String userId, MemberUpdateDTO memberUpdateDTO) {
//        Member member = memberRepository.findByUserId(userId)
//                .orElseThrow(() -> new RuntimeException("Member not found"));
//
//        // 기존 Animal 정보 가져오기
//        Animal existingAnimal = member.getAnimal();
//
//        // Animal 정보가 변경되었을 경우 기존 객체 수정
//        if (!existingAnimal.getAnimalType().equals(memberUpdateDTO.getAnimalType()) ||
//                !existingAnimal.getAnimalBreed().equals(memberUpdateDTO.getAnimalBreed())) {
//            existingAnimal.updateAnimalInfo(memberUpdateDTO.getAnimalType(), memberUpdateDTO.getAnimalBreed());
//            animalRepository.save(existingAnimal); // 변경 감지를 위해 저장 (필수)
//        }
//
//        // 기존 Member 객체 수정 (Setter 없이)
//        member.updateMemberInfo(
//                memberUpdateDTO.getUserPassword(),
//                memberUpdateDTO.getUserName(),
//                memberUpdateDTO.getUserAddress(),
//                memberUpdateDTO.getUserPhoneNumber(),
//                memberUpdateDTO.getUserEmail(),
//                memberUpdateDTO.getUserPetName(),
//                memberUpdateDTO.getUserPetAge(),
//                memberUpdateDTO.getUserPetWeight(),
//                memberUpdateDTO.getUserPetMedicalHistory(),
//                existingAnimal // 변경된 Animal 객체 적용
//        );
//
////        // 명시적으로 저장하기 (변경된 데이터를 확실히 DB에 반영하기 위해)
////        memberRepository.save(member); // 수정된 Member 객체 저장
//
//        return member; // @Transactional이 있기 때문a에 변경된 값이 자동 반영됨
//    }

//@Transactional // JPA 변경 감지(Dirty Checking) 활성화
//public Member updateMember(String userId, MemberUpdateDTO memberUpdateDTO) {
//    Member member = memberRepository.findByUserId(userId)
//            .orElseThrow(() -> new RuntimeException("Member not found"));
//
//    // 동물 코드가 null인 경우 처리 로직
//    if (memberUpdateDTO.getAnimalCode() == null) {
//        throw new RuntimeException("동물 코드가 누락되었습니다.");
//    }
//
//    // Animal 정보 조회 (findAnimalByCode 메서드 사용)
//    Animal updatedAnimal = findAnimalByCode(memberUpdateDTO.getAnimalCode());
//
//    // 기존 Animal과 다르면 변경
//    if (!member.getAnimal().equals(updatedAnimal)) {
//        member.updateMemberInfo(
//                memberUpdateDTO.getUserPassword(),
//                memberUpdateDTO.getUserName(),
//                memberUpdateDTO.getUserAddress(),
//                memberUpdateDTO.getUserPhoneNumber(),
//                memberUpdateDTO.getUserEmail(),
//                memberUpdateDTO.getUserPetName(),
//                memberUpdateDTO.getUserPetAge(),
//                memberUpdateDTO.getUserPetWeight(),
//                memberUpdateDTO.getUserPetMedicalHistory(),
//                updatedAnimal // 새로운 Animal 객체 적용
//        );
//    }




            // 안되면 다 지우고 바로 아래꺼 살리기!!!!!!!!!!!!!!!!!!!!!!
//    @Transactional // JPA 변경 감지(Dirty Checking) 활성화
//    public Member updateMember(String userId, MemberUpdateDTO memberUpdateDTO) {
//        Member member = memberRepository.findByUserId(userId)
//                .orElseThrow(() -> new RuntimeException("Member not found"));
//
//        // 동물 코드가 null인 경우 예외 처리
//        if (memberUpdateDTO.getAnimalCode() == null) {
//            throw new RuntimeException("동물 코드가 누락되었습니다.");
//        }
//
//        // Animal 정보 조회
//        Animal updatedAnimal = findAnimalByCode(memberUpdateDTO.getAnimalCode());
//
//        if (!updatedAnimal.getAnimalType().equals(memberUpdateDTO.getAnimalType()) ||
//                !updatedAnimal.getAnimalBreed().equals(memberUpdateDTO.getAnimalBreed())) {
//            updatedAnimal.updateAnimalInfo(memberUpdateDTO.getAnimalType(), memberUpdateDTO.getAnimalBreed());
//            animalRepository.save(updatedAnimal); // 변경 감지를 위해 저장 (필수)
//        }
//
//        // 기존 Animal과 다르면 변경
//        if (!member.getAnimal().equals(updatedAnimal)) {
//            member.updateMemberInfo(
//                    memberUpdateDTO.getUserPassword(),
//                    memberUpdateDTO.getUserName(),
//                    memberUpdateDTO.getUserAddress(),
//                    memberUpdateDTO.getUserPhoneNumber(),
//                    memberUpdateDTO.getUserEmail(),
//                    memberUpdateDTO.getUserPetName(),
//                    memberUpdateDTO.getUserPetAge(),
//                    memberUpdateDTO.getUserPetWeight(),
//                    memberUpdateDTO.getUserPetMedicalHistory(),
//                    updatedAnimal // 새로운 Animal 객체 적용
//            );
//        }
//
//    return member; // @Transactional 덕분에 변경된 값이 자동 반영됨
//    }



@Transactional // JPA 변경 감지(Dirty Checking) 활성화
public Member updateMember(String userId, MemberUpdateDTO memberUpdateDTO) {
    Member member = memberRepository.findByUserId(userId)
            .orElseThrow(() -> new RuntimeException("Member not found"));

    // 동물 코드가 null인 경우 예외 처리
    if (memberUpdateDTO.getAnimalCode() == null) {
        throw new RuntimeException("동물 코드가 누락되었습니다.");
    }

    // Animal 정보 조회
    Animal updatedAnimal = findAnimalByCode(memberUpdateDTO.getAnimalCode());

    // animalBreed가 null이면 기존 값 유지
    String newAnimalBreed = (memberUpdateDTO.getAnimalBreed() != null && !memberUpdateDTO.getAnimalBreed().isEmpty())
            ? memberUpdateDTO.getAnimalBreed()
            : updatedAnimal.getAnimalBreed(); // 기존 값 유지

    // 기존 값과 다르면 업데이트
//    if (!updatedAnimal.getAnimalType().equals(memberUpdateDTO.getAnimalType()) ||
//            !updatedAnimal.getAnimalBreed().equals(newAnimalBreed)) {
//        updatedAnimal.updateAnimalInfo(memberUpdateDTO.getAnimalType(), newAnimalBreed);
//        animalRepository.save(updatedAnimal); // 변경 감지를 위해 저장
//    }



    // 기존 Animal과 다르면 변경
    if (!member.getAnimal().equals(updatedAnimal)) {
        member.updateMemberInfo(
                memberUpdateDTO.getUserPassword(),
                memberUpdateDTO.getUserName(),
                memberUpdateDTO.getUserAddress(),
                memberUpdateDTO.getUserPhoneNumber(),
                memberUpdateDTO.getUserEmail(),
                memberUpdateDTO.getUserPetName(),
                memberUpdateDTO.getUserPetAge(),
                memberUpdateDTO.getUserPetWeight(),
                memberUpdateDTO.getUserPetMedicalHistory(),
                updatedAnimal // 새로운 Animal 객체 적용
        );
    }

    log.info("받은 요청 데이터 - animalCode: {}", memberUpdateDTO.getAnimalCode());
    log.info("받은 요청 데이터 - animalType: {}", memberUpdateDTO.getAnimalType());
    log.info("받은 요청 데이터 - animalBreed: {}", memberUpdateDTO.getAnimalBreed());


    return member; // @Transactional 덕분에 변경된 값이 자동 반영됨
}







    public Animal findAnimalByCode(Integer animalCode) {
        return animalRepository.findByAnimalCode(animalCode)
                .orElseThrow(() -> new RuntimeException("유효하지 않은 Animal 정보입니다: " + animalCode));
    }





}





