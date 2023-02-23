package com.onboarding.moviescope.controller;

import com.onboarding.moviescope.model.request.AdminAddOrEditMovieRequest;
import com.onboarding.moviescope.model.response.Response;
import com.onboarding.moviescope.service.MovieService;
import com.onboarding.moviescope.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private MovieService movieService;


    @PostMapping("/movie")
    public Response<Object> addMovieToDatabase(@Valid @RequestBody AdminAddOrEditMovieRequest adminAddOrEditMovieRequest){
        movieService.addMovie(adminAddOrEditMovieRequest);
        return new Response<>(HttpStatus.OK.value(), AppConstants.SUCCESS_MESSAGE,null);
    }

    @PutMapping("/movie/{id}")
    public Response<Object> updateMovie(@Valid @RequestBody AdminAddOrEditMovieRequest adminAddOrEditMovieRequest,@PathVariable long id){
        movieService.updateMovie(adminAddOrEditMovieRequest,id);
        return new Response<>(HttpStatus.OK.value(), AppConstants.SUCCESS_MESSAGE,null);
    }

}
