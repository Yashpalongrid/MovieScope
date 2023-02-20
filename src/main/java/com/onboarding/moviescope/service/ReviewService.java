package com.onboarding.moviescope.service;

import com.onboarding.moviescope.model.entity.Review;
import com.onboarding.moviescope.model.request.GiveReviewRequest;

import java.util.List;

public interface ReviewService {
    List<Review> getMovieReviews(long id);

    Review addReview(long movie_id, GiveReviewRequest giveReviewRequest,String username);

    void editReview(long id,GiveReviewRequest giveReviewRequest);

    void deleteReview(long id);

    List<Review> getReviewHistory(String username);

}
