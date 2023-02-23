package com.onboarding.moviescope.service;

import com.onboarding.moviescope.model.entity.Review;
import com.onboarding.moviescope.model.request.GiveOrEditReviewRequest;
import com.onboarding.moviescope.model.response.MoviePageReviewResponse;
import com.onboarding.moviescope.model.response.ReviewHistoryResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewService {
    List<MoviePageReviewResponse> getMovieReviews(long id, Pageable pageable);

    Review addReview(long movie_id, GiveOrEditReviewRequest giveOrEditReviewRequest, String username);

    void editReview(long id, GiveOrEditReviewRequest giveOrEditReviewRequest);

    void deleteReview(long id);

    List<ReviewHistoryResponse> getReviewHistory(String username,Pageable pageable);

}
