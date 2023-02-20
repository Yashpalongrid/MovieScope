package com.onboarding.moviescope.service.impl;

import com.onboarding.moviescope.dao.ReviewRepository;
import com.onboarding.moviescope.model.entity.Review;
import com.onboarding.moviescope.model.request.GiveReviewRequest;
import com.onboarding.moviescope.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private MovieServiceImpl movieService;

    @Override
    public List<Review> getReviewHistory(String username) {
        List<Review> reviewList=reviewRepository.findByUserId(userService.getUser(username).getId());
        return reviewList;
    }

    @Override
    public Page<Review> getMovieReviews(long id, Pageable pageable) {
          return reviewRepository.findByMovieId(id,pageable);
    }

    @Override
    public Review addReview(long movie_id, GiveReviewRequest giveReviewRequest,String username) {
        Review review=new Review();
        review.setUser(userService.getUser(username));
        review.setMovie(movieService.getMovieDetails(movie_id));
        review.setRating(giveReviewRequest.getRating());
        review.setFeedback(giveReviewRequest.getFeedback());
        reviewRepository.save(review);
        return review;
    }

    @Override
    public void deleteReview(long id) {
        Review review=reviewRepository.findById(id).orElse(null);
        review.setDeleted(true);
        reviewRepository.save(review);
    }

    @Override
    public void editReview(long id, GiveReviewRequest giveReviewRequest) {
        Review review=reviewRepository.findById(id).orElse(null);
        review.setFeedback(giveReviewRequest.getFeedback());
        review.setRating(giveReviewRequest.getRating());
        reviewRepository.save(review);
    }
}
