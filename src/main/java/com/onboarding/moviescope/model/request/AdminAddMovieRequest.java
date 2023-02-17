package com.onboarding.moviescope.model.request;

import com.onboarding.moviescope.model.constant.Genre;
import com.onboarding.moviescope.model.constant.Language;
import com.onboarding.moviescope.model.constant.StreamingPlatform;
import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class AdminAddMovieRequest {

    @NotBlank
    @Size(max = 30)
    private String name;

    @NotBlank
    @Size(max = 300)
    private String storyline;

    @Digits(integer = 10,fraction = 1)
    private float averageRating;

    @Digits(integer = 1000,fraction = 0)
    private int releaseYear;

    @NotBlank
    private String imgSource;


    private Set<Genre> genres;


    private Set<String> cast;


    private Language languages;


    private Set<StreamingPlatform> streamingPlatforms;
}
