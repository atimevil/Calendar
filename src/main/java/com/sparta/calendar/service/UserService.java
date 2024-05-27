package com.sparta.calendar.service;

import com.sparta.calendar.dto.SignupRequestDto;
import com.sparta.calendar.entity.User;
import com.sparta.calendar.entity.UserRoleEnum;
import com.sparta.calendar.repository.UserRepositroy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepositroy userRepositroy;

    private final String ADMIN_TOKEN = "ADMIN_TOKEN";

    public void signup(SignupRequestDto requestDto) {
        String nickname = requestDto.getNickname();
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        Optional<User> checkNickname = userRepositroy.findByNickname(nickname);
        if (checkNickname.isPresent()) {
            throw new IllegalArgumentException("Nickname already exists");
        }

        UserRoleEnum role = UserRoleEnum.USER;
        if (requestDto.isAdmin()) {
            if(!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
                throw new IllegalArgumentException("Admin token does not match");
            }

            role = UserRoleEnum.ADMIN;
        }

        User user = new User(nickname, username, password, role);
        userRepositroy.save(user);
    }
}
