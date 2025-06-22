package com.simzaconnections.ecommerce_webapp_backend.controller;

import com.simzaconnections.ecommerce_webapp_backend.dto.ResponseDto;
import com.simzaconnections.ecommerce_webapp_backend.dto.user.SignInDto;
import com.simzaconnections.ecommerce_webapp_backend.dto.user.SignInResponseDto;
import com.simzaconnections.ecommerce_webapp_backend.dto.user.SignupDto;
import com.simzaconnections.ecommerce_webapp_backend.service.UserService;
import org.springframework.web.bind.annotation.*;

@RequestMapping("user")
@RestController
@CrossOrigin(origins = "https://thembis-bazaar.netlify.app")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // two apis

    // signup

    @PostMapping("/signup")
    public ResponseDto signup(@RequestBody SignupDto signupDto) {
        return userService.signUp(signupDto);
    }


    // signin

    @PostMapping("/signin")
    public SignInResponseDto signIn(@RequestBody SignInDto signInDto) {
        return userService.signIn(signInDto);
    }
}
