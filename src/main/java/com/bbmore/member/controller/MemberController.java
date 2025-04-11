package com.bbmore.member.controller;

import com.bbmore.member.dto.MemberDTO;
import com.bbmore.member.dto.MemberUpdateDTO;
import com.bbmore.member.entity.Animal;
import com.bbmore.member.repository.AnimalRepository;
import com.bbmore.member.service.AnimalService;
import com.bbmore.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/member")
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final AnimalService animalService;

    private final AnimalRepository animalRepository;


    // 로그인 기능을 연결하지 않았으므로 특정 회원 ID를 하드코딩하여 사용
    private static final String DEFAULT_USER_ID = "id_03";


    // 회원 정보 조회 (GET)
    @GetMapping("/edit")
    public String getMember(Model model) {
        MemberDTO member = memberService.getMemberByUserId(DEFAULT_USER_ID);

        // userPetMedicalHistory 가 null 이거나 비어있으면 "해당없음" 설정
        if (member.getUserPetMedicalHistory() == null || member.getUserPetMedicalHistory().trim().isEmpty()) {
            member.setUserPetMedicalHistory("해당없음");
            log.info("조회한 회원의 의료기록이 없어 '해당없음'으로 세팅했습니다.");
        }

        List<Animal> animal = animalService.getAllAnimal(); // 전체 동물 목록 조회
        List<String> animalTypes = animalService.getAllAnimalTypes(); // animalType 목록 가져와서 추가
        List<String> animalBreeds = animalService.getAllAnimalBreeds(); // animalBreed 목록 가져와서 추가

        model.addAttribute("member", member);
        model.addAttribute("animal", animal);
        model.addAttribute("animalTypes", animalTypes);
        model.addAttribute("animalBreeds", animalBreeds);

        return "mypage/user_profile_edit"; // user_profile_edit.html로 이동
    }

    @GetMapping("/modify")
    public String modifyPage(){
        return "user_profile_edit";
    }

    @PostMapping("/modify")
    public String modify(@ModelAttribute MemberUpdateDTO memberUpdateDTO, Model model) {

        log.info("회원 수정 요청: {}", memberUpdateDTO);


        // userPetMedicalHistory 가 null 이거나 비어있으면 "해당없음"으로 설정
        if (memberUpdateDTO.getUserPetMedicalHistory() == null || memberUpdateDTO.getUserPetMedicalHistory().trim().isEmpty()) {
            memberUpdateDTO.setUserPetMedicalHistory("해당없음");
            log.info("입력된 반려동물 병력이 없어 '해당없음'으로 설정했습니다.");
        }


        if (memberUpdateDTO.getAnimalDTO() != null) {
            String selectedType = memberUpdateDTO.getAnimalDTO().getAnimalType();
            String selectedBreed = memberUpdateDTO.getAnimalDTO().getAnimalBreed();

            log.info("선택한 Type: {}", selectedType);
            log.info("선택한 Breed: {}", selectedBreed);

            // animalType + animalBreed 기준으로 animalCode 직접 조회해서 DTO에 설정
            animalRepository.findByAnimalTypeAndAnimalBreed(selectedType, selectedBreed)
                    .ifPresent(animal -> {
                        memberUpdateDTO.setAnimalCode(animal.getAnimalCode());
                        log.info("변경할 animalCode: {}", animal.getAnimalCode());
                    });

        } else {
            log.warn("AnimalDTO가 null입니다!");
        }

        // 회원 정보 수정
        memberService.modifyMember(memberUpdateDTO);

        // 수정 후 최신 회원 정보 다시 조회
        MemberDTO updatedMember = memberService.getMemberByUserId(DEFAULT_USER_ID);
        model.addAttribute("member", updatedMember); // 수정 후 회원 정보 갱신

        // 전체 동물 목록/타입/품종 다시 모델에 담기 (수정 후에도 선택 가능하게!)
        List<Animal> animalList = animalService.getAllAnimal();
        List<String> animalTypes = animalService.getAllAnimalTypes();
        List<String> animalBreeds = animalService.getAllAnimalBreeds();

        model.addAttribute("animal", animalList);            // 전체 Animal 리스트
        model.addAttribute("animalTypes", animalTypes);      // 전체 Type 리스트
        model.addAttribute("animalBreeds", animalBreeds);    // 전체 Breed 리스트

        return "mypage/user_profile_edit";
    }

    // AnimalController 에 animalCode 조회용 API 추가해보기
    @GetMapping("/code")
    public ResponseEntity<Integer> getAnimalCode(
            @RequestParam String animalType,
            @RequestParam String animalBreed) {

        return animalRepository.findByAnimalTypeAndAnimalBreed(animalType, animalBreed)
                .map(animal -> ResponseEntity.ok(animal.getAnimalCode()))
                .orElse(ResponseEntity.notFound().build());
    }

}





