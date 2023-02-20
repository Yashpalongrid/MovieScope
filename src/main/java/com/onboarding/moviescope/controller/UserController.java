package com.onboarding.moviescope.controller;

import com.onboarding.moviescope.model.entity.User;
import com.onboarding.moviescope.model.request.ForgetPasswordRequest;
import com.onboarding.moviescope.model.request.RegisterRequest;
import com.onboarding.moviescope.model.request.UserVerifyRequest;
import com.onboarding.moviescope.model.response.Response;
import com.onboarding.moviescope.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping
    public Response<Object> registration(@Valid @RequestBody RegisterRequest registerRequest){
        User user =userService.registerUser(registerRequest);
        return new Response<>(HttpStatus.OK.value(), "success",null);
    }

//    @PostMapping("/login")
//    public LoginResponse login(@RequestBody LoginRequest loginRequest){
//
//        LoginResponse loginResponse=new LoginResponse();
//        return loginResponse;
//    }

    @PostMapping("/logout")
    public Response logout(){
        return new Response();
    }

    @PostMapping("/forgetpassword")
    public Response forgetPassword(@RequestBody ForgetPasswordRequest forgetPasswordRequest){
        return new Response();
    }

    @PostMapping("/verify")
    public Response verifyUser(@RequestBody UserVerifyRequest userVerifyRequest){
        return new Response();
    }








}
