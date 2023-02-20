package com.onboarding.moviescope.service;

import com.onboarding.moviescope.model.entity.Movie;
import com.onboarding.moviescope.model.request.AdminAddMovieRequest;
import com.onboarding.moviescope.model.request.DeleteMovie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MovieService {

    Movie getMovieDetails(long id);
    void addMovie(AdminAddMovieRequest adminAddMovieRequest);

    void deleteMovie(DeleteMovie deleteMovie);

    Page<Movie> getMovies(String name, String genre,String language,int year, Pageable pageable);
}
