package com.onboarding.moviescope.model.request;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class GiveReviewRequest {

    private float rating;

    @Size(max = 300)
    private String feedback;

}
