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
        List<Animal> animal = animalService.getAllAnimal(); // 전체 동물 목록 조회
        List<String> animalTypes = animalService.getAllAnimalTypes(); // animalType 목록 가져와서 추가
        List<String> animalBreeds = animalService.getAllAnimalBreeds(); // animalBreed 목록 가져와서 추가

        model.addAttribute("member", member);
        model.addAttribute("animal", animal);
        model.addAttribute("animalTypes", animalTypes);
        model.addAttribute("animalBreeds", animalBreeds);

        return "user_profile_edit"; // user_profile_edit.html로 이동
    }




    @GetMapping("/modify")
    public String modifyPage(){
        return "user_profile_edit";
    }

    @PostMapping("/modify")
    public String modify(@ModelAttribute MemberUpdateDTO memberUpdateDTO, Model model) {
        System.out.println("modify" + memberUpdateDTO.toString());
        System.out.println("Animal Code: " + memberUpdateDTO.getAnimalCode());

        // 여기서 animalBreed 값을 확인
        System.out.println("Animal Breed: " + memberUpdateDTO.getAnimalBreed());

        // animalCode로 품종 리스트 조회
        List<String> animalBreeds = animalService.getBreedsByAnimalCode(memberUpdateDTO.getAnimalCode());
        model.addAttribute("animalBreeds", animalBreeds);

        // 기존 회원 수정 로직
        memberService.modifyMember(memberUpdateDTO);

        return "user_profile_edit";
    }

    // 수정 메서드 설명 (?)
    // => 조회한 animalBreed 를 model 에 담아서 타임리프에서 사용하도록 설정










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





