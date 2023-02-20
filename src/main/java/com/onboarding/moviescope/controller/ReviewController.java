package com.onboarding.moviescope.controller;

import com.onboarding.moviescope.filter.CustomAuthorizationFilter;
import com.onboarding.moviescope.model.entity.Review;
import com.onboarding.moviescope.model.request.GiveReviewRequest;
import com.onboarding.moviescope.model.response.Response;
import com.onboarding.moviescope.model.response.ReviewHistoryResponse;
import com.onboarding.moviescope.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;
    @PutMapping("/{id}")
    public Response<Review> editReview(@PathVariable long id, @RequestBody GiveReviewRequest giveReviewRequest){
        reviewService.editReview(id,giveReviewRequest);
        return new Response<Review>(200,"success");
    }

    @PutMapping("/{id}/delete")
    public Response<Review> deleteReview(@PathVariable long id){
    reviewService.deleteReview(id);
        return new Response<Review>(200,"success");
    }
    @GetMapping
    public Response<ReviewHistoryResponse> getAllReviewHistory(){
        String userName= CustomAuthorizationFilter.userName;
        List<Review> reviewList=reviewService.getReviewHistory(userName);
        List<ReviewHistoryResponse> reviewHistoryResponseList=new ArrayList<>();
        for (Review review:reviewList){
            ReviewHistoryResponse reviewHistoryResponse=new ReviewHistoryResponse();
            reviewHistoryResponse.setReviewId(review.getId());
            reviewHistoryResponse.setRating(review.getRating());
            reviewHistoryResponse.setFeedback(review.getFeedback());
            reviewHistoryResponse.setMovieName(review.getMovie().getName());
            reviewHistoryResponse.setMovieId(review.getMovie().getId());
            reviewHistoryResponseList.add(reviewHistoryResponse);
        }
        return new Response<ReviewHistoryResponse>(200,"success",reviewHistoryResponseList);
    }
}
