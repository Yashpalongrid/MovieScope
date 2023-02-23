package com.onboarding.moviescope.service;

import com.onboarding.moviescope.model.constant.Genre;
import com.onboarding.moviescope.model.constant.Language;
import com.onboarding.moviescope.model.entity.Movie;
import com.onboarding.moviescope.model.request.AdminAddOrEditMovieRequest;
import com.onboarding.moviescope.model.response.MovieIconResponse;
import com.onboarding.moviescope.model.response.MoviePageResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MovieService {

    MoviePageResponse getMovieDetails(long id);
    void addMovie(AdminAddOrEditMovieRequest adminAddOrEditMovieRequest);


//    Page<Movie> getMovies(String name, String genre,String language,int year, Pageable pageable);
    List<MovieIconResponse> getMovies(String name, Genre genre, Language language, int releaseYear, boolean isDeleted, Pageable pageable);

    void updateMovie(AdminAddOrEditMovieRequest adminAddOrEditMovieRequest,long id);
    void addToWatchlist( String username,long id);
    public Movie getMovieObject(long id);
}
