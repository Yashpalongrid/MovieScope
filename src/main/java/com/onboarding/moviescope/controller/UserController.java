package com.onboarding.moviescope.controller;

import com.onboarding.moviescope.model.entity.User;
import com.onboarding.moviescope.model.request.ForgetPasswordRequest;
import com.onboarding.moviescope.model.request.RegisterRequest;
import com.onboarding.moviescope.model.request.UserVerifyRequest;
import com.onboarding.moviescope.model.response.Response;
import com.onboarding.moviescope.model.response.ReviewHistoryResponse;
import com.onboarding.moviescope.model.response.WatchlistResponse;
import com.onboarding.moviescope.service.ReviewService;
import com.onboarding.moviescope.service.UserService;
import com.onboarding.moviescope.service.WatchlistService;
import com.onboarding.moviescope.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private WatchlistService watchlistService;

    @PostMapping
    public Response<Object> registration(@Valid @RequestBody RegisterRequest registerRequest){
        User user =userService.registerUser(registerRequest);
        return new Response<>(HttpStatus.OK.value(), AppConstants.SUCCESS_MESSAGE,null);
    }



    @PostMapping("/logout")
    public Response logout(){
        return new Response();
    }

    @PostMapping("/forgetpassword")
    public Response forgetPassword(@Valid @RequestBody ForgetPasswordRequest forgetPasswordRequest){
        return new Response();
    }

    @PostMapping("/verify")
    public Response verifyUser(@RequestBody UserVerifyRequest userVerifyRequest){
        return new Response();
    }
    @GetMapping("/review")
    public Response<List<ReviewHistoryResponse>> getAllReviewHistory(@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int pageNo,
                                                                     @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int pageSize, Principal principal){
        Pageable pageable= PageRequest.of(pageNo,pageSize);
        String userName= principal.getName();
        List<ReviewHistoryResponse> reviewHistoryResponseList=reviewService.getReviewHistory(userName,pageable);
        return new Response<>(HttpStatus.OK.value(), AppConstants.SUCCESS_MESSAGE,reviewHistoryResponseList);
    }

    @GetMapping("/watchlist")
    public Response<List<WatchlistResponse>> getWatchListedMovies(Principal principal){
        String username= principal.getName();
        List<WatchlistResponse> watchlistResponseList=watchlistService.getUserWatchlistedMovies(username);
        return new Response<>(HttpStatus.OK.value(), AppConstants.SUCCESS_MESSAGE,watchlistResponseList);

    }








}
