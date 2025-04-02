package com.bbmore.member.controller;

import com.bbmore.member.dto.MemberDTO;
import com.bbmore.member.dto.MemberUpdateDTO;
import com.bbmore.member.entity.Animal;
import com.bbmore.member.entity.Member;
import com.bbmore.member.repository.AnimalRepository;
import com.bbmore.member.service.AnimalService;
import com.bbmore.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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

//    // 회원 정보 수정 메서드
//    @PutMapping("/update/{userId}")
//    public ResponseEntity<MemberDTO> updateMember(
//            @PathVariable String userId,
//            @RequestBody MemberUpdateDTO memberUpdateDTO,
//            Model model) {
//
//        // 수정된 Member 정보를 반환하도록 수정
//        Member updatedMember = memberService.updateMember(userId, memberUpdateDTO);
//
//        System.out.println("Received DTO: " + memberUpdateDTO);
//
//
//        // 수정된 정보를 모델에 추가하여 뷰로 전달
//        List<Animal> animal = animalService.getAllAnimal(); // 전체 동물 목록 조회
//        List<String> animalTypes = animalService.getAllAnimalTypes(); // animalType 목록
//        List<String> animalBreeds = animalService.getAllAnimalBreeds(); // animalBreed 목록
//
//        model.addAttribute("member", new MemberDTO(updatedMember)); // 수정된 회원 정보
//        model.addAttribute("animal", animal);
//        model.addAttribute("animalTypes", animalTypes);
//        model.addAttribute("animalBreeds", animalBreeds);
//
//
//        return ResponseEntity.ok(new MemberDTO(updatedMember));  // 수정된 MemberDTO 반환
//    }





    // 지우지마!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//    @PutMapping("/update/{userId}")
//    public String updateMember(
//            @PathVariable String userId,
//            @ModelAttribute MemberUpdateDTO memberUpdateDTO,  // 폼 데이터 바인딩
//            Model model) {
//
//        // 수정된 Member 정보를 반환하도록 수정
//        Member updatedMember = memberService.updateMember(userId, memberUpdateDTO);
//
//        // 수정된 정보를 모델에 추가하여 뷰로 전달
//        model.addAttribute("member", new MemberDTO(updatedMember));
//
//        log.info("animalCode: {}", memberUpdateDTO.getAnimalCode());
//
//        // 수정 후, 다시 회원 정보 수정 화면으로 이동
//        return "user_profile_edit"; // 수정된 데이터를 포함한 user_profile_edit.html로 이동
//    }




//      // 버전 1 => animal 몰라 이것도 누락 animalType???? 누락됨
//    @PutMapping("/update/{userId}")
//    public String updateMember(
//            @PathVariable String userId,
//            @ModelAttribute MemberUpdateDTO memberUpdateDTO,  // 폼 데이터 바인딩
//            Model model) {
//
//        // 수정된 Member 정보를 반환하도록 수정
//        Member updatedMember = memberService.updateMember(userId, memberUpdateDTO);
//
//        // 수정된 정보를 모델에 추가하여 뷰로 전달
//        model.addAttribute("member", new MemberDTO(updatedMember));
//
//        log.info("animalCode: {}", memberUpdateDTO.getAnimalCode());
//
//        // 수정 후, 다시 회원 정보 수정 화면으로 이동
//        return "user_profile_edit"; // 수정된 데이터를 포함한 user_profile_edit.html로 이동
//    }
//
//
//
//
////
//      // 버전 2 => animalCode 누락됨. 다 누락
//    @PutMapping("/update/{userId}")
//    public ResponseEntity<?> updateMember(
//            @PathVariable String userId,
//            @RequestBody MemberUpdateDTO memberUpdateDTO) { // MemberUpdateDTO로 변경
//        try {
//            Member updatedMember = memberService.updateMember(userId, memberUpdateDTO);
//            return ResponseEntity.ok().body(new MemberDTO(updatedMember)); // 업데이트된 정보를 반환
//        } catch (Exception e) {
//            log.error("회원 정보 수정 실패: {}", e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원 정보 수정 실패");
//        }
//
//    }

    @PutMapping("/member/update/{userId}")
    public ResponseEntity<?> updateMember(
            @PathVariable String userId,
            @RequestBody MemberUpdateDTO memberUpdateDTO) {
        try {
            Member updatedMember = memberService.updateMember(userId, memberUpdateDTO);
            return ResponseEntity.ok().body("회원 정보 수정 성공");
        } catch (Exception e) {
            log.error("회원 정보 수정 실패: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원 정보 수정 실패");
        }
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





