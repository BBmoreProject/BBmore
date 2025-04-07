package com.bbmore.product.controller;

import com.bbmore.product.dto.ReviewListDTO;
import com.bbmore.product.dto.ReviewWriteDTO;
import com.bbmore.product.dto.ReviewWritingDTO;
import com.bbmore.product.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/write")
    public String getUserPurchaseList(Model model) {    // 구매한 목록 조회 (user_review_write)
        Integer userCode = 3; // 로그인 사용자로 교체 예정

        // 리뷰 작성이 가능한 상품만 필터링
        List<ReviewWriteDTO> purchaseList = reviewService.getUserPurchaseList(userCode);

        model.addAttribute("purchaseList", purchaseList);
        return "user_review_write";
    }

    @GetMapping("/writing/{orderDetailCode}")
    public String writingReview(@PathVariable Integer orderDetailCode, Model model) {
        // 특정 상품을 선택해 리뷰 작성하는 페이지로 이동
        Integer userCode = 3;

        ReviewWritingDTO reviewInfo = reviewService.getReviewInfo(userCode, orderDetailCode);

        // 📌 reviewInfo가 null일 경우 처리
        if (reviewInfo == null) {
            reviewInfo = new ReviewWritingDTO(); // 빈 객체 생성
            reviewInfo.setOrderDetailCode(orderDetailCode); // orderDetailCode는 넣어줌
            // 혹시 필요한 상품 정보가 있으면 따로 채워넣을 수도 있음
        }


        model.addAttribute("formAction", "/review/update");
        model.addAttribute("reviewInfo", reviewInfo);

        return "user_review_writing";
    }

    @PostMapping("/update")   // 리뷰 등록
    public String updateReview(@ModelAttribute ReviewWritingDTO reviewDTO) {
        // ✅ 여기에 확인 코드 추가!
        System.out.println("✅ userCode: " + reviewDTO.getUserCode());
        System.out.println("✅ orderDetailCode: " + reviewDTO.getOrderDetailCode());
        System.out.println("✅ reviewCode: " + reviewDTO.getReviewCode());
        System.out.println("✅ reviewRating: " + reviewDTO.getReviewRating());
        System.out.println("✅ reviewContent: " + reviewDTO.getReviewContent());

        reviewService.saveReview(reviewDTO);
        return "redirect:/review/list";
    }

//    @PostMapping("/update") // 리뷰 등록
//    public String updateReview(@Valid @ModelAttribute ReviewWritingDTO reviewDTO,
//                               BindingResult bindingResult,
//                               Model model) {
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("formAction", "/review/update");
//            model.addAttribute("reviewInfo", reviewDTO); // 사용자가 입력한 값을 다시 보여줌
//            return "user_review_writing"; // 검증 실패 시 다시 작성 페이지로
//        }
//
//        reviewService.saveReview(reviewDTO);
//        return "redirect:/review/list";
//    }

    @GetMapping("/modify/{orderDetailCode}")
    public String modifyReviewForm(@PathVariable Integer orderDetailCode, Model model) {
        Integer userCode = 3;

        ReviewWritingDTO reviewInfo = reviewService.getReviewInfo(userCode, orderDetailCode);
        if (reviewInfo == null) {
            return "redirect:/review/list"; // 수정할 리뷰가 없으면 목록으로
        }

        model.addAttribute("formAction", "/review/modify");
        model.addAttribute("reviewInfo", reviewInfo);
        return "user_review_writing";
    }



    @PostMapping("/modify")   // 리뷰 수정
    public String modifyReview(@ModelAttribute ReviewWritingDTO reviewDTO) {

        reviewService.updateReview(reviewDTO);
        return "redirect:/review/list";
    }

    @GetMapping("/list")
    public String selectReviewList(Model model) {   // 작성된 리뷰 페이지 (user_review_list)
        Integer userCode = 3;

        List<ReviewListDTO> reviewList = reviewService.getUserReviewList(userCode);

        reviewList.forEach(review -> System.out.println("🔎 리뷰: " + review));

        model.addAttribute("reviewList", reviewList);
        return "user_review_list";
    }

    @PostMapping("/delete/{reviewCode}")    // 리뷰 삭제
    public String deleteReview(@PathVariable("reviewCode") Integer reviewCode) {

        reviewService.deleteReview(reviewCode);

        return "redirect:/review/list";
    }


    // 로그인 기능을 추가했을때의 리뷰 삭제 메서드
//    @PostMapping("/delete/{reviewCode}")
//    public String deleteReview(@PathVariable("reviewCode") Integer reviewCode,
//                               Principal principal) {
//        String loggedInUserId = principal.getName(); // 또는 Authentication에서 꺼냄
//        reviewService.deleteReviewForUser(reviewCode, loggedInUserId);
//        return "redirect:/review/list";
//    }

}
