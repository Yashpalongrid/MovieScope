package com.onboarding.moviescope.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class GiveOrEditReviewRequest {

    @NotNull
    private float rating;

    @Size(max = 300)
    @NotBlank
    private String feedback;

}
