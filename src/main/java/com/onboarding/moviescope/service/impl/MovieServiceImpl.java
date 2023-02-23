package com.onboarding.moviescope.service.impl;

import com.onboarding.moviescope.dao.MovieRepository;
import com.onboarding.moviescope.dao.WatchlistRepository;
import com.onboarding.moviescope.model.constant.Genre;
import com.onboarding.moviescope.model.constant.Language;
import com.onboarding.moviescope.model.constant.StreamingPlatform;
import com.onboarding.moviescope.model.entity.Movie;
import com.onboarding.moviescope.model.entity.Watchlist;
import com.onboarding.moviescope.model.request.AdminAddOrEditMovieRequest;
import com.onboarding.moviescope.model.response.MovieIconResponse;
import com.onboarding.moviescope.model.response.MoviePageResponse;
import com.onboarding.moviescope.service.MovieService;
import com.onboarding.moviescope.service.UserService;
import com.onboarding.moviescope.utils.AppConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private WatchlistRepository watchlistRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private MovieService movieService;

    @Override
    public MoviePageResponse getMovieDetails(long id) throws IllegalArgumentException {
        Movie movie=movieRepository.findById(id).orElse(null);
        if(movie!=null){
            MoviePageResponse moviePageResponse=new MoviePageResponse();
            moviePageResponse.setName(movie.getName());
            moviePageResponse.setStoryline(movie.getStoryline());
            moviePageResponse.setRating(movie.getRating());
            moviePageResponse.setReleaseYear(movie.getReleaseYear());
            moviePageResponse.setImgSource(movie.getImgSource());
            Set<Genre> genres=movie.getGenres();
            moviePageResponse.setGenres(genres);
            Set<String> cast=movie.getCast();
            moviePageResponse.setCast(cast);
            moviePageResponse.setLanguages(movie.getLanguage());
            Set<StreamingPlatform> streamingPlatforms=movie.getStreamingPlatforms();
            moviePageResponse.setStreamingPlatforms(streamingPlatforms);
            return moviePageResponse;
        }else{
            throw new IllegalArgumentException(AppConstants.MOVIE_NOT_FOUND);
        }

    }

    @Override
    public List<MovieIconResponse> getMovies(String name, Genre genre, Language language, int releaseYear, boolean isDeleted, Pageable pageable) {
        Page<Movie> movies;
        if( (name==null || name.length()==0) && genre==null && language==null && releaseYear==0){
             movies=movieRepository.findAll(pageable);
        }else{
             movies=movieRepository.findByNameContainingIgnoreCaseOrGenresOrLanguageOrReleaseYearAndIsDeleted(name,genre,language,releaseYear, false,pageable);
        }

        List<MovieIconResponse> movieIconResponseList=new ArrayList<>();
        for(Movie movie:movies ){
            MovieIconResponse movieIconResponse=new MovieIconResponse();
            movieIconResponse.setMovieName(movie.getName());
            movieIconResponse.setMovieId(movie.getId());
            movieIconResponse.setRating(movie.getRating());
            movieIconResponse.setImgSrc(movie.getImgSource());
            movieIconResponseList.add(movieIconResponse);
        }
        return movieIconResponseList;
    }

    @Override
    public void addMovie(AdminAddOrEditMovieRequest adminAddOrEditMovieRequest) throws IllegalArgumentException{
        try {
            Movie movie = new Movie();
            movie.setName(adminAddOrEditMovieRequest.getName());
            movie.setStoryline(adminAddOrEditMovieRequest.getStoryline());
            movie.setRating(adminAddOrEditMovieRequest.getRating());
            movie.setReleaseYear(adminAddOrEditMovieRequest.getReleaseYear());
            movie.setImgSource(adminAddOrEditMovieRequest.getImgSource());
            movie.setGenres(adminAddOrEditMovieRequest.getGenres());
            movie.setCast(adminAddOrEditMovieRequest.getCast());
            movie.setLanguage(adminAddOrEditMovieRequest.getLanguage());
            movie.setStreamingPlatforms(adminAddOrEditMovieRequest.getStreamingPlatforms());
            movieRepository.save(movie);
        }catch (Exception e){
            throw new IllegalArgumentException(AppConstants.FIELD_EMPTY);
        }
    }

    @Override
    public void updateMovie(AdminAddOrEditMovieRequest adminAddOrEditMovieRequest,long id) throws IllegalArgumentException {
        Movie movie=movieRepository.findById(id).orElse(null);
        if (movie!=null){
            movie.setName(adminAddOrEditMovieRequest.getName());
            movie.setStoryline(adminAddOrEditMovieRequest.getStoryline());
            movie.setRating(adminAddOrEditMovieRequest.getRating());
            movie.setReleaseYear(adminAddOrEditMovieRequest.getReleaseYear());
            movie.setImgSource(adminAddOrEditMovieRequest.getImgSource());
            movie.setGenres(adminAddOrEditMovieRequest.getGenres());
            movie.setCast(adminAddOrEditMovieRequest.getCast());
            movie.setLanguage(adminAddOrEditMovieRequest.getLanguage());
            movie.setStreamingPlatforms(adminAddOrEditMovieRequest.getStreamingPlatforms());
            movieRepository.save(movie);

        }else {
            throw new IllegalArgumentException(AppConstants.MOVIE_NOT_FOUND);
        }

    }
    @Override
    public void addToWatchlist(String username, long id) throws IllegalArgumentException{
        Watchlist watchlist=new Watchlist();
        watchlist.setUser(userService.getUser(username));
        Movie movie=movieRepository.findById(id).orElse(null);
        if(movie==null){
            throw new IllegalArgumentException(AppConstants.MOVIE_NOT_FOUND);
        }
        watchlist.setMovie(movie);
        watchlistRepository.save(watchlist);
    }
    public Movie getMovieObject(long id) throws IllegalArgumentException{
        Movie movie=movieRepository.findById(id).orElse(null);
        if(movie==null){
            throw new IllegalArgumentException(AppConstants.MOVIE_NOT_FOUND);
        }
        return movie;
    }
}
