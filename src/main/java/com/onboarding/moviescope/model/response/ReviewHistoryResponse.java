package com.onboarding.moviescope.model.response;

import lombok.Data;

@Data
public class ReviewHistoryResponse {
    private long reviewId;
    private long movieId;
    private String movieName;
    private float rating;
    private String feedback;
}
