package com.onboarding.moviescope.controller;

import com.onboarding.moviescope.model.request.*;
import com.onboarding.moviescope.model.response.LoginResponse;
import com.onboarding.moviescope.model.response.Response;
import com.onboarding.moviescope.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping
    public Response registration(@RequestBody RegisterRequest registerRequest){
        Response response=userService.
        return response;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest){

        LoginResponse loginResponse=new LoginResponse();
        return loginResponse;
    }

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
