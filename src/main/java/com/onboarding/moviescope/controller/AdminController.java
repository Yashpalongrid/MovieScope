package com.onboarding.moviescope.controller;

import com.onboarding.moviescope.model.entity.Movie;
import com.onboarding.moviescope.model.request.AdminAddMovieRequest;
import com.onboarding.moviescope.model.request.DeleteMovie;
import com.onboarding.moviescope.model.response.Response;
import com.onboarding.moviescope.service.impl.MovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private MovieServiceImpl movieService;


    @PostMapping("/movie")
    public Response<Movie> addMovieToDatabase(@RequestBody AdminAddMovieRequest adminAddMovieRequest){
        movieService.addMovie(adminAddMovieRequest);
        return new Response<Movie>(200,"success");
    }

    @PutMapping("/movie")
    public Response<Movie> deleteMovie(@RequestBody DeleteMovie deleteMovie){
        movieService.deleteMovie(deleteMovie);
        return new Response<Movie>(200,"success");
    }
}
