package com.onboarding.moviescope.model.response;

import lombok.Data;

@Data
public class MoviePageReviewResponse {
    private String username;
    private String feedback;
    private float rating;
}
