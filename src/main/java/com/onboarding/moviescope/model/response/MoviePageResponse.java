package com.onboarding.moviescope.model.response;

import com.onboarding.moviescope.model.constant.Genre;
import com.onboarding.moviescope.model.constant.Language;
import com.onboarding.moviescope.model.constant.StreamingPlatform;
import lombok.Data;

import java.util.Set;

@Data
public class MoviePageResponse {

    private String name;
    private String storyline;
    private float rating;
    private int releaseYear;
    private String imgSource;
    private Set<Genre> genres;
    private Set<String> cast;
    private Language languages;
    private Set<StreamingPlatform> streamingPlatforms;
}
