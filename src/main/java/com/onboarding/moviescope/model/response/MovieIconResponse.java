package com.onboarding.moviescope.model.response;

import lombok.Data;

@Data
public class MovieIconResponse {
    private long movieId;
    private String movieName;
    private String imgSrc;
    private float rating;
}
