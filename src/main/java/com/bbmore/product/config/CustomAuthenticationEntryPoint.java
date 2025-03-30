package com.bbmore.product.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

/**
 * 인증이 필요한 페이지에 로그인하지 않은 사용자가 접근할 때 어떻게 응답할지 커스텀하게 정의하는 클래스입니다.
 * 기본적으로 Spring Security는 로그인 페이지로 리다이렉트하지만, 이 클래스를 통해 다른 방식(예: 특정 오류 페이지로 이동, JSON 응답 반환 등)으로 응답할 수 있습니다.
 * AJAX는 필수가 아닙니다. AJAX는 단지 하나의 예시일 뿐이며, 어떤 방식으로든 응답을 커스터마이징할 수 있습니다
 */
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {


    /**

     *
     * commence 메서드: 이 메서드는 AuthenticationEntryPoint 인터페이스에서 정의된 메서드로, 인증되지 않은 사용자가 보호된 리소스에 접근할 때 호출됩니다.
     *
     *      HttpServletRequest request: 클라이언트의 HTTP 요청 정보
     *      HttpServletResponse response: 서버가 클라이언트에게 보낼 응답 정보
     *      AuthenticationException authException: 인증 과정에서 발생한 예외
     *
     *      AJAX 요청과 일반 페이지 요청을 구분

         * 사용자가 로그인하지 않은 상태에서:
         * 일반 웹 페이지 접근: 로그인 페이지로 바로 이동시킴 (전체 페이지가 변경됨)
         * AJAX 요청: 오류 코드(401)만 보냄
         * AJAX 요청에 오류 코드를 보내는 이유는, AJAX는 보통 페이지의 일부분만 변경하는 데 사용되기 때문입니다.
         * 전체 페이지를 로그인 페이지로 바꾸는 대신, 자바스크립트가 이 오류 코드를 받아서 "로그인이 필요합니다"라는 작은 알림창을 띄우거나 다른 적절한 대응을 할 수 있습니다.
     *

     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        } else {
            response.sendRedirect("/members/login");
        }


    }
}
