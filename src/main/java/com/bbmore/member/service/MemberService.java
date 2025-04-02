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

    private Animal convertToRegistry(String animal) {

        if (animal == null || animal.trim().isEmpty()) {
            throw new IllegalArgumentException("animal 값이 null이거나 비어 있습니다.");
        } // null 또는 빈 문자열이 들어오면 예외를 던지게 변경

        return new Animal(Integer.parseInt(animal), animal);
        // Integer.pareInt(animal) -> 문자열 animal 을 정수(Integer)로 변환
        // 변환된 animalCode 와 animal 을 Animal 객체의 생성자 전달
    }
    /*
    * convertRegistry 메서드 동작과정 (?)
    * => animal(문자열)을 받아서 Animal 객체로 반환하는 메서드
    */


//    @Transactional      // modifyMember() 내부의 모든 DB 작업이 성공해야 최종 반영됨.
//    public void modifyMember(MemberUpdateDTO memberUpdateDTO){   // memberUpdateDTO : 사용자의 수정 정보를 담고 있는 DTO 객체
//        Member member = memberUpRepository.findByUserId(memberUpdateDTO.getUserId());   // 기존 회원 정보를 userId 로 DB 에서 조회
//
//
//        Animal animal = convertToRegistry(memberUpdateDTO.getAnimalBreed()); // 동물 정보 변환
//        // memberUpdateDTO.getAnimalBreed() -> 사용자가 선택한 동물 품종 정보(문자열)
//        // convertToRegistry() 메서드를 호출해서 Animal 객체로 변환
//
//        System.out.println("Received animalBreed: " + memberUpdateDTO.getAnimalBreed()); // 디버깅용 로그 추가
//
//        if (memberUpdateDTO.getAnimalBreed() == null || memberUpdateDTO.getAnimalBreed().trim().isEmpty()) {
//            throw new IllegalArgumentException("animalBreed 값이 null이거나 비어 있습니다.");
//        }
//
//
//        member.updateMemberInfo(    // 사용자의 수정 정보를 담고 있는 DTO 객체
//                memberUpdateDTO.getUserId(),
//                memberUpdateDTO.getUserPassword(),
//                memberUpdateDTO.getUserName(),
//                memberUpdateDTO.getUserAddress(),
//                memberUpdateDTO.getUserPhoneNumber(),
//                memberUpdateDTO.getUserEmail(),
//                memberUpdateDTO.getUserPetName(),
//                memberUpdateDTO.getUserPetAge(),
//                memberUpdateDTO.getUserPetWeight(),
//                memberUpdateDTO.getUserPetMedicalHistory(),
//                animal // 사용자의 반려동물 정보도 함께 변경
//        ); // 프론트에서 받은 데이터를 이 DTO 객체로 전달받음
//    }


    /*
    * tbl_member 를 수정하면 데이터베이스에 저장이 되지만
    * tbl_animal 수정한 값은 데이터베이스에 저장이 되지않음.
    * */
    @Transactional
    public void modifyMember(MemberUpdateDTO memberUpdateDTO) {
        Member member = memberUpRepository.findByUserId(memberUpdateDTO.getUserId());

        if (member == null) {
            throw new IllegalArgumentException("해당 사용자가 존재하지 않습니다.");
        }

        System.out.println("🐶 기존 animalCode: " + (member.getAnimal() != null ? member.getAnimal().getAnimalCode() : "없음"));

        // 🟢 animalCode를 정확히 가져오기 위해 동물 정보를 다시 조회
//
        // 🟢 동물 정보 조회 시 animalCode 우선 사용
        Animal animal = animalRepository.findById(memberUpdateDTO.getAnimalCode())
                .orElseThrow(() -> new IllegalArgumentException("해당 animalCode에 해당하는 Animal 정보가 없습니다."));

        if (memberUpdateDTO.getAnimalBreed() == null) {
            System.out.println("❌ animalBreed 값이 null입니다.");
        }


        if (animal == null) {
            throw new IllegalArgumentException("해당 animalBreed에 해당하는 Animal 정보가 없습니다.");
        }

        System.out.println("🔄 변경할 animalCode: " + animal.getAnimalCode());

        // 🟢 animal 업데이트
        member.updateAnimal(animal);

        // 🟢 변경된 회원 정보 업데이트
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

        // 🟢 변경 사항 저장
        memberRepository.save(member);

        System.out.println("✅ 업데이트 후 animalCode: " + member.getAnimal().getAnimalCode());
    }


}
