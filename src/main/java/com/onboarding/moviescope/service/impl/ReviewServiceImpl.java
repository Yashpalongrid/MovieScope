package com.onboarding.moviescope.service.impl;

import com.onboarding.moviescope.dao.ReviewRepository;
import com.onboarding.moviescope.model.entity.Review;
import com.onboarding.moviescope.model.request.GiveOrEditReviewRequest;
import com.onboarding.moviescope.model.response.MoviePageReviewResponse;
import com.onboarding.moviescope.model.response.ReviewHistoryResponse;
import com.onboarding.moviescope.service.MovieService;
import com.onboarding.moviescope.service.ReviewService;
import com.onboarding.moviescope.service.UserService;
import com.onboarding.moviescope.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private MovieService movieService;

    @Override
    public List<ReviewHistoryResponse> getReviewHistory(String username,Pageable pageable) {
        Page<Review> reviewList=reviewRepository.findByUserId(userService.getUser(username).getId(),pageable);
        List<ReviewHistoryResponse> reviewHistoryResponseList=new ArrayList<>();
        for (Review review:reviewList){
            ReviewHistoryResponse reviewHistoryResponse=new ReviewHistoryResponse();
            reviewHistoryResponse.setReviewId(review.getId());
            reviewHistoryResponse.setMovieName(review.getMovie().getName());
            reviewHistoryResponse.setMovieId(review.getMovie().getId());
            reviewHistoryResponse.setRating(review.getRating());
            reviewHistoryResponse.setFeedback(review.getFeedback());
            reviewHistoryResponseList.add(reviewHistoryResponse);
        }
        return reviewHistoryResponseList;
    }

    @Override
    public List<MoviePageReviewResponse> getMovieReviews(long id, Pageable pageable) {
          Page<Review> reviewsList=reviewRepository.findByMovieId(id,pageable);
        List<MoviePageReviewResponse> moviePageReviewResponseList=new ArrayList<>();
        for( Review review : reviewsList){
            MoviePageReviewResponse moviePageReviewResponse=new MoviePageReviewResponse();
            moviePageReviewResponse.setRating(review.getRating());
            moviePageReviewResponse.setFeedback(review.getFeedback());
            moviePageReviewResponse.setUsername(review.getUser().getUsername());
            moviePageReviewResponseList.add(moviePageReviewResponse);
        }
        return moviePageReviewResponseList;
    }

    @Override
    public Review addReview(long movie_id, GiveOrEditReviewRequest giveOrEditReviewRequest, String username) {
        Review review=new Review();
        review.setUser(userService.getUser(username));
        review.setMovie(movieService.getMovieObject(movie_id));
        review.setRating(giveOrEditReviewRequest.getRating());
        review.setFeedback(giveOrEditReviewRequest.getFeedback());
        reviewRepository.save(review);
        return review;
    }

    @Override
    public void deleteReview(long id) throws IllegalArgumentException{
        Review review=reviewRepository.findById(id).orElse(null);
        if(review==null){
            throw new IllegalArgumentException(AppConstants.REVIEW_NOT_FOUND);
        }
        review.setDeleted(true);
        reviewRepository.save(review);
    }

    @Override
    public void editReview(long id, GiveOrEditReviewRequest giveOrEditReviewRequest) throws IllegalArgumentException {
        try{
            Review review=reviewRepository.findById(id).orElse(null);
            review.setFeedback(giveOrEditReviewRequest.getFeedback());
            review.setRating(giveOrEditReviewRequest.getRating());
            reviewRepository.save(review);
        }catch (Exception e){
            throw new IllegalArgumentException(AppConstants.FIELD_EMPTY);
        }

    }
}
