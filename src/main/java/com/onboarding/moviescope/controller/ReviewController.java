package com.onboarding.moviescope.controller;

import com.onboarding.moviescope.model.request.GiveOrEditReviewRequest;
import com.onboarding.moviescope.model.response.Response;
import com.onboarding.moviescope.service.ReviewService;
import com.onboarding.moviescope.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;


    @PutMapping("/{id}")
    public Response<Object> editReview(@PathVariable long id, @Valid @RequestBody GiveOrEditReviewRequest giveOrEditReviewRequest) throws IllegalArgumentException{
        reviewService.editReview(id, giveOrEditReviewRequest);
        return new Response<>(HttpStatus.OK.value(), AppConstants.SUCCESS_MESSAGE,null);
    }

    @DeleteMapping("/{id}")
    public Response<Object> deleteReview(@PathVariable long id){
    reviewService.deleteReview(id);
        return new Response<>(HttpStatus.OK.value(), AppConstants.SUCCESS_MESSAGE,null);
    }

}
