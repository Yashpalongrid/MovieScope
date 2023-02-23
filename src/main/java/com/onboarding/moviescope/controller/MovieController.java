package com.onboarding.moviescope.controller;

import com.onboarding.moviescope.model.constant.Genre;
import com.onboarding.moviescope.model.constant.Language;
import com.onboarding.moviescope.model.request.GiveOrEditReviewRequest;
import com.onboarding.moviescope.model.response.MovieIconResponse;
import com.onboarding.moviescope.model.response.MoviePageResponse;
import com.onboarding.moviescope.model.response.MoviePageReviewResponse;
import com.onboarding.moviescope.model.response.Response;
import com.onboarding.moviescope.service.MovieService;
import com.onboarding.moviescope.service.ReviewService;
import com.onboarding.moviescope.service.UserService;
import com.onboarding.moviescope.utils.AppConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/movie")
@Slf4j
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;


    @GetMapping
    public Response<List<MovieIconResponse>> getALlMovies(@RequestParam(value = "name",required = false) String name,
                                                          @RequestParam(value = "genre",required = false) Genre genre,
                                                          @RequestParam(value = "language",required = false) Language language,
                                                          @RequestParam(value="releaseYear",required=false,defaultValue = "0") int releaseYear,
                                                          @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int pageNo,
                                                          @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int pageSize
                                                          ){
        Pageable pageable=PageRequest.of(pageNo,pageSize);

        List<MovieIconResponse> movieIconResponseList=movieService.getMovies(name,genre,language,releaseYear,false,pageable);

        return new Response<>(HttpStatus.OK.value(), AppConstants.SUCCESS_MESSAGE,movieIconResponseList);
    }

    @GetMapping("/{id}")
    public Response<MoviePageResponse> getMovie(@PathVariable long id){
        MoviePageResponse moviePageResponse=movieService.getMovieDetails(id);

        return new Response<>(HttpStatus.OK.value(), AppConstants.SUCCESS_MESSAGE,moviePageResponse);

    }

    @GetMapping("/{id}/review")
    public Response<List<MoviePageReviewResponse>> getMovieReviews(@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int pageNo, @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int pageSize,
                                                                   @PathVariable long id){
        Pageable pageable= PageRequest.of(pageNo,pageSize);
        List<MoviePageReviewResponse> moviePageReviewResponseList=reviewService.getMovieReviews(id,pageable);
        return new Response<>(HttpStatus.OK.value(), AppConstants.SUCCESS_MESSAGE,moviePageReviewResponseList);
    }

    @PostMapping("/{id}/review")
    public Response<Object> giveMovieReview(@PathVariable long id, @Valid @RequestBody GiveOrEditReviewRequest giveOrEditReviewRequest, Principal principal){
        String username= principal.getName();
        reviewService.addReview(id, giveOrEditReviewRequest,username);
        return new Response<Object>(HttpStatus.CREATED.value(), AppConstants.SUCCESS_MESSAGE,null);
    }

    @PostMapping("/{id}/watchlist")
    public Response<Object> addToWatchlist( @PathVariable long id, Principal principal){
        String username= principal.getName();
        log.info("username: {}",username);
        movieService.addToWatchlist(username,id);
        return new Response<Object>(HttpStatus.OK.value(), AppConstants.SUCCESS_MESSAGE,null);
    }


}
