package com.sparta.calendar.controller;

import com.sparta.calendar.dto.SignupRequestDto;
import com.sparta.calendar.repository.UserRepositroy;
import com.sparta.calendar.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final UserRepositroy userRepositroy;

    @PostMapping("/user/signup")
    public String signup(@Valid SignupRequestDto requestDto, BindingResult bindingResult) {
        List<FieldError> errors = bindingResult.getFieldErrors();
        bindingResult.getFieldErrors();
        if(errors.size() > 0) {
            for(FieldError error : bindingResult.getFieldErrors()) {
                log.error(error.getField() + " 필드 : " + error.getDefaultMessage());
            }
            return "redirect:/api/user/signup";
        }
        userService.signup(requestDto);

        return "redirect:/api/user/login-page";
    }

}
