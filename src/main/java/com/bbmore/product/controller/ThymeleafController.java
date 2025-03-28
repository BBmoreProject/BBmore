package com.bbmore.product.controller;

import com.bbmore.product.dto.ProductDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

///GetMapping = url 지정  html 연동하는건 반환값
@Controller
/// MVC의 컨트롤러 ( 웹 요청을 처리하고 응답 반환 )
///  Spring 이 이 클래스를 빈으로 등록하고 관리
@RequestMapping(value="/thymeleaf")
///  이 컨트롤러가 처리할 URL경로의 기본 경로를 /thymeleaf로 지정
///  이 클래스 내의 모든 핸들러 메소드는 /thymeleaf로 시작하는 경로를 처리
///  클래스 내에 @GetMapping("/hello")가 있으면 이는 /thymeleaf/hello URL에 매핑
public class ThymeleafController {

///  요청 수신: http://localhost:8080/thymeleaf/ex01 URL로 GET 요청을 보냄
/// DispatcherServlet이 이 요청을 받아 적절한 컨트롤러를 찾음.
///
/// 핸들러 매핑:
/// URL 패턴 /thymeleaf/ex01과 HTTP 메서드 GET이 ThymeleafController의
/// thymeleafExample01 메서드와 일치.
/// 스프링은 이 메서드를 호출하기로 결정
    @GetMapping("/ex01")
    public String thymeleafExample01(Model model) {
        model.addAttribute("data", "HI");
        return "thymeleafEx/thymeleafEx01";
        /// 반환값 "thymeleafEx/thymeleafEx01"은 뷰 이름을 지정
        ///  src/main/resources/templates/뷰이름.html 형태
        /// 하위 폴더를 포함할 경우 return "폴더/파일명"

    }

    ///  이걸 안적어주면? 어떤 HTTP 요청 경로로 매핑되는지 모르니 404에러가 뜨겠지?
    @GetMapping("/ex02")

    ///  Model은 데이터 컨테이너, Controller에서 View로 데이터를 전달할 때 사용
    /**
     * 다른 방법으로 데이터를 전달하려면?
     * ModelAndView
     * @ModelAttribute
     * @ResponseBody와 객체 직접 반환 (REST API 방식)
     * String과 같은 단순 타입 반환 (뷰 이름으로 사용)
     */

    public String thymeleafExample02(Model model) {
        /// 왜 DTO를 쓰냐? 엔티티는 데이터베이스 테이블과의 매핑에 집중
        /// DTO는 API와 서비스 계층의 데이터 전송에 집중
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductDetail("상품 상세 설명");
        productDTO.setProductName("테스트 상품1");
        productDTO.setPrice(10000);
        productDTO.setRegTime(LocalDateTime.now());

        ///  addAttribute, 모델에 메서드 추가: 매개변수 두 개
        ///  (param1 = "마음대로지으셈", param2 = productDTO)
        model.addAttribute("ModelInProductDTO", productDTO);
        return "thymeleafEx/thymeleafEx02";

        ///  것은 데이터를 HTML로 직접 전달하는 것이 아니라, 데이터 전달을 '가능하게' 만드는 것이다!!!
    }

    @GetMapping(value = "/ex03")
    public String thymeleafExample03(Model model) {

        List<ProductDTO> productDTOList = new ArrayList<>();

        for (int i = 0; i <= 10; i++) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setProductDetail("상품 상세 설명" + i);
            productDTO.setProductName("테스트 상품" + i);
            productDTO.setPrice(10000 + i);
            productDTO.setRegTime(LocalDateTime.now());
            productDTOList.add(productDTO);

        }
        model.addAttribute("productDTOList", productDTOList);
        return "thymeleafEx/thymeleafEx03";
    }

    @GetMapping(value = "/ex04")
    public String thymeleafExample04(Model model) {

        List<ProductDTO> productDTOList = new ArrayList<>();

        for (int i = 0; i <= 10; i++) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setProductDetail("상품 상세 설명" + i);
            productDTO.setProductName("테스트 상품" + i);
            productDTO.setPrice(10000 + i);
            productDTO.setRegTime(LocalDateTime.now());
            productDTOList.add(productDTO);

        }
        model.addAttribute("productDTOList", productDTOList);
        return "thymeleafEx/thymeleafEx04";

    }

    @GetMapping(value = "/ex05")
    public String thymeleafExample05(Model model) {

        List<ProductDTO> productDTOList = new ArrayList<>();

        for (int i = 0; i <= 10; i++) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setProductDetail("상품 상세 설명" + i);
            productDTO.setProductName("테스트 상품" + i);
            productDTO.setPrice(10000 + i);
            productDTO.setRegTime(LocalDateTime.now());
            productDTOList.add(productDTO);

        }
        model.addAttribute("productDTOList", productDTOList);
        return "thymeleafEx/thymeleafEx05";

    }

    @GetMapping(value = "/ex06")
    public String thymeleafExample06(Model model) {
        return "thymeleafEx/thymeleafEx06";

    }

    @GetMapping(value = "/ex07")
    public String thymeleafExample07(String param1, String param2, Model model) {
        model.addAttribute("parakilometer1", param1);
        model.addAttribute("parakilometer2", param2);
        /**
         * parakilometer은 thymelaf의 키값으로 쓰이고 param1은 값으로 쓰인다.
         * /ex07?param1=값1&param2=값2와요청이 오면 값은 1,2로 할당된다
         * 서비스로직에서 전달해 줄 수도 있다는 걸 생각해!
         */



        return "thymeleafEx/thymeleafEx07";

    }

    @GetMapping(value = "/ex08")
    public String thymeleafExample08() {
        return "thymeleafEx/thymeleafEx08";

    }







}
