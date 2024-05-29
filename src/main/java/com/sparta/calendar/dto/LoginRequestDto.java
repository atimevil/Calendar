package com.sparta.calendar.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {
    @NotBlank(message = "필수 입력 항목입니다.")
    private String nickname;

    @NotBlank(message = "필수 입력 항목입니다.")
    private String password;
}
