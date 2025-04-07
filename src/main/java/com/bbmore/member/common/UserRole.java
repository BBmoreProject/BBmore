package com.bbmore.member.common;

public enum UserRole {  // 사용자의 역할(권한(을 정의하는 열거형(Enum)

    /*
    * UserRole 은 사용자 역할을 나타내는 Enum 으로
    * 주로 USER, ADMIN 같은 고정된 역할 값을 관리할때 편함.
    * */

    USER("USER"),   // 일반 사용자
    ADMIN("ADMIN"); // 관리자

    // 괄호 안의 "USER", "ADMIN" 같은 생성자(UserRole(String role) 로 전달돼

    private String role;    // 역할 저장 함수
    /*
    * "USER", "ADMIN" 같은 역할 문자열을 저장
    * private 접근제어자 사용 => 외부에서 직접 접근 X
    * */

    UserRole(String role) {
        this.role = role;
    }
    /*
    * - `UserRole` 생성자는 `"USER"`, `"ADMIN"` 값을 받아서 `role` 변수에 저장
    * - Enum 의 생성자는 **객체 생성 시 한 번만 실행** 됨
    * */

    public String getRole() { // 호출하면 "USER", "ADMIN" 값을 반환해
        return role;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "role='" + role + '\'' +
                '}';
    }
}