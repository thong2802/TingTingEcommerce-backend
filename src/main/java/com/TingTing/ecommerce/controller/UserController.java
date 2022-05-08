package com.TingTing.ecommerce.controller;

import com.TingTing.ecommerce.dto.ResponseDto;
import com.TingTing.ecommerce.dto.user.SignInDto;
import com.TingTing.ecommerce.dto.user.SignInResponseDto;
import com.TingTing.ecommerce.dto.user.SignUpDto;
import com.TingTing.ecommerce.repository.UserRepository;
import com.TingTing.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserController {
    // create two apis
    @Autowired
    private UserService userService;

    // signUp
    @PostMapping("/signup")
    public ResponseDto signup(@RequestBody SignUpDto signUpDto) {
        return userService.signUp(signUpDto);
    }
    // signIn
    @PostMapping("/signin")
    public SignInResponseDto signIn(@RequestBody SignInDto signInDto) {
        return userService.signIn(signInDto);
    }
}
