package com.onboarding.moviescope.service;

import com.onboarding.moviescope.model.entity.Movie;
import com.onboarding.moviescope.model.request.AdminAddMovieRequest;
import com.onboarding.moviescope.model.request.DeleteMovie;

public interface MovieService {

    Movie getMovieDetails(long id);
    void addMovie(AdminAddMovieRequest adminAddMovieRequest);

    void deleteMovie(DeleteMovie deleteMovie);
}
