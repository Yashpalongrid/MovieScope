package com.onboarding.moviescope.service.impl;

import com.onboarding.moviescope.dao.MovieRepository;
import com.onboarding.moviescope.model.entity.Movie;
import com.onboarding.moviescope.model.request.AdminAddMovieRequest;
import com.onboarding.moviescope.model.request.DeleteMovie;
import com.onboarding.moviescope.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Movie getMovieDetails(long id) {  //write logic when isdeletd is true
        return movieRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Movie> getMovies(String name, String genre, String language, int year, Pageable pageable) {
        return movieRepository.findByFilters(name,genre,language,year,pageable);
    }

    @Override
    public void addMovie(AdminAddMovieRequest adminAddMovieRequest){
        Movie movie=new Movie();
        movie.setName(adminAddMovieRequest.getName());
        movie.setStoryline(adminAddMovieRequest.getStoryline());
        movie.setAverageRating(adminAddMovieRequest.getAverageRating());
        movie.setReleaseYear(adminAddMovieRequest.getReleaseYear());
        movie.setImgSource(adminAddMovieRequest.getImgSource());
        movie.setGenres(adminAddMovieRequest.getGenres());
        movie.setCast(adminAddMovieRequest.getCast());
        movie.setLanguages(adminAddMovieRequest.getLanguages());
        movie.setStreamingPlatforms(adminAddMovieRequest.getStreamingPlatforms());
        movieRepository.save(movie);
    }

    @Override
    public void deleteMovie(DeleteMovie deleteMovie) {
        Movie movie=movieRepository.findById(deleteMovie.getMovieId()).orElse(null);
        movie.setDeleted(true);
        movieRepository.save(movie);
    }
}
