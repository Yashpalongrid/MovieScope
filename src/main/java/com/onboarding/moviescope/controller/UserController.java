package com.onboarding.moviescope.controller;

import com.onboarding.moviescope.filter.CustomAuthorizationFilter;
import com.onboarding.moviescope.model.entity.User;
import com.onboarding.moviescope.model.request.ChangePasswordRequest;
import com.onboarding.moviescope.model.request.ForgetPasswordRequest;
import com.onboarding.moviescope.model.request.RegisterRequest;
import com.onboarding.moviescope.model.request.UserVerifyRequest;
import com.onboarding.moviescope.model.response.Response;
import com.onboarding.moviescope.model.response.ReviewHistoryResponse;
import com.onboarding.moviescope.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping
    public Response<User> registration(@Valid @RequestBody RegisterRequest registerRequest){
        User user =userService.registerUser(registerRequest);
        return new Response<User>(200,"success");
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

    @PutMapping("/changepassword")
    public Response changePassword(@RequestBody ChangePasswordRequest changePasswordRequest){
        return new Response();
    }






}
