package com.sparta.calendar.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {

    @NotBlank(message = "필수 입력 항목입니다.")
    private String username;

    @NotBlank(message = "필수 입력 항목입니다.")
    @Pattern(regexp = "^[a-z0-9]{4,10}$", message = "nickname은 4자 이상 10자 이하의 알파벳 소문자(a~z)와 숫자(0~9)로 구성되어야 합니다.")
    private String nickname;

    @NotBlank(message = "필수 입력 항목입니다.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d).{8,15}$", message = "password는 8자 이상 15자 이하의 알파벳 대소문자(a~z)와 숫자(0~9)로 구성되어야 합니다.")
    private String password;

    private boolean isAdmin;
    private String adminToken;
}
