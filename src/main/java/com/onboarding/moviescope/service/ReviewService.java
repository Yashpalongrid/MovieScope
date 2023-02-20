package com.onboarding.moviescope.service;

import com.onboarding.moviescope.model.entity.Review;
import com.onboarding.moviescope.model.request.GiveReviewRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewService {
    Page<Review> getMovieReviews(long id, Pageable pageable);

    Review addReview(long movie_id, GiveReviewRequest giveReviewRequest,String username);

    void editReview(long id,GiveReviewRequest giveReviewRequest);

    void deleteReview(long id);

    List<Review> getReviewHistory(String username);

}
