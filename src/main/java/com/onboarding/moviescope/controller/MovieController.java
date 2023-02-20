package com.onboarding.moviescope.controller;

import com.onboarding.moviescope.filter.CustomAuthorizationFilter;
import com.onboarding.moviescope.model.constant.Genre;
import com.onboarding.moviescope.model.constant.StreamingPlatform;
import com.onboarding.moviescope.model.entity.Movie;
import com.onboarding.moviescope.model.entity.Review;
import com.onboarding.moviescope.model.request.GiveReviewRequest;
import com.onboarding.moviescope.model.response.MovieIconResponse;
import com.onboarding.moviescope.model.response.MoviePageResponse;
import com.onboarding.moviescope.model.response.MoviePageReviewResponse;
import com.onboarding.moviescope.model.response.Response;
import com.onboarding.moviescope.service.impl.MovieServiceImpl;
import com.onboarding.moviescope.service.impl.ReviewServiceImpl;
import com.onboarding.moviescope.service.impl.UserServiceImpl;
import com.onboarding.moviescope.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieServiceImpl movieService;

    @Autowired
    private ReviewServiceImpl reviewService;

    @Autowired
    private UserServiceImpl userService;

    @GetMapping
    public Response<List<MovieIconResponse>> getALlMovies(@RequestParam("pageNo") int pageNo,@RequestParam("pageSize") int pageSize,@RequestParam("name") String name,@RequestParam("genre") String genre,@RequestParam("language") String language,@RequestParam("year") int year){
        Pageable pageable=PageRequest.of(pageNo,pageSize);
        Page<Movie> movies=movieService.getMovies(name,genre,language,year,pageable);
        List<MovieIconResponse> movieIconResponseList=new ArrayList<>();
        for(Movie movie:movies ){
            MovieIconResponse movieIconResponse=new MovieIconResponse();
            movieIconResponse.setMovieName(movie.getName());
            movieIconResponse.setMovieId(movie.getId());
            movieIconResponse.setAverageRating(movie.getAverageRating());
            movieIconResponse.setImgSrc(movie.getImgSource());
            movieIconResponseList.add(movieIconResponse);
        }

        return new Response<>(HttpStatus.OK.value(), "success",movieIconResponseList);
    }

    @GetMapping("/{id}")
    public Response<MoviePageResponse> getMovie(@PathVariable long id){
        Movie movie=movieService.getMovieDetails(id);
        MoviePageResponse moviePageResponse=new MoviePageResponse();
        moviePageResponse.setName(movie.getName());
        moviePageResponse.setStoryline(movie.getStoryline());
        moviePageResponse.setAverageRating(movie.getAverageRating());
        moviePageResponse.setReleaseYear(movie.getReleaseYear());
        moviePageResponse.setImgSource(movie.getImgSource());
        Set<Genre> genres=movie.getGenres();
        moviePageResponse.setGenres(genres);
        Set<String> cast=movie.getCast();
        moviePageResponse.setCast(cast);
        moviePageResponse.setLanguages(movie.getLanguages());
        Set<StreamingPlatform> streamingPlatforms=movie.getStreamingPlatforms();
        moviePageResponse.setStreamingPlatforms(streamingPlatforms);
        return new Response<>(HttpStatus.OK.value(), "success",moviePageResponse);

    }

    @GetMapping("/{id}/review")
    public Response<List<MoviePageReviewResponse>> getMovieReviews(@RequestParam(value = "pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int pageNo, @RequestParam(value = "pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int pageSize, @PathVariable long id){
        Pageable pageable= PageRequest.of(pageNo,pageSize);
        Page<Review> reviewList=reviewService.getMovieReviews(id,pageable);
        List<MoviePageReviewResponse> moviePageReviewResponseList=new ArrayList<>();
        for( Review review : reviewList){
            MoviePageReviewResponse moviePageReviewResponse=new MoviePageReviewResponse();
            moviePageReviewResponse.setRating(review.getRating());
            moviePageReviewResponse.setFeedback(review.getFeedback());
            moviePageReviewResponse.setUsername(review.getUser().getUsername());
            moviePageReviewResponseList.add(moviePageReviewResponse);
        }
        return new Response<>(HttpStatus.OK.value(), "success",moviePageReviewResponseList);
    }

    @PostMapping("/{id}/review")
    public Response<Object> giveMovieReview(@PathVariable long id, @RequestBody GiveReviewRequest giveReviewRequest){
        String username=CustomAuthorizationFilter.userName;
        reviewService.addReview(id,giveReviewRequest,username);
        return new Response<Object>(HttpStatus.CREATED.value(), "success",null);
    }





//    @GetMapping
//    public BaseResponseObject homePage(){
//        List<MovieResponseObject> responseObjectList=new ArrayList<>();
//        Map<String,Object> map=new HashMap<>();
//        map.put("movies",responseObjectList);
//        BaseResponseObject baseResponseObject=new BaseResponseObject(200,"success",map);
//        return baseResponseObject;
//
//    }
}
