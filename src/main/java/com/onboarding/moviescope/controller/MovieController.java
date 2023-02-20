package com.onboarding.moviescope.controller;

import com.onboarding.moviescope.filter.CustomAuthorizationFilter;
import com.onboarding.moviescope.model.constant.Genre;
import com.onboarding.moviescope.model.constant.StreamingPlatform;
import com.onboarding.moviescope.model.entity.Movie;
import com.onboarding.moviescope.model.entity.Review;
import com.onboarding.moviescope.model.entity.User;
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
    public MovieIconResponse getALlMovies(@RequestParam("pageNo") int pageNo,@RequestParam("pageSize") int pageSize,@RequestParam("name") String name,@RequestParam("genre") String genre,@RequestParam("language") String language,@RequestParam("year") int year){
        return new MovieIconResponse();
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
        return new Response<MoviePageResponse>(200,"success",moviePageResponse);

    }

    @GetMapping("/{id}/review")
    public Response<MoviePageReviewResponse> getMovieReviews(@RequestParam(value = "pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int pageNo, @RequestParam(value = "pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int pageSize, @PathVariable long id){
        List<Review> reviewList=reviewService.getMovieReviews(id);
        List<MoviePageReviewResponse> moviePageReviewResponseList=new ArrayList<>();
        for( Review review : reviewList){
            MoviePageReviewResponse moviePageReviewResponse=new MoviePageReviewResponse();
            moviePageReviewResponse.setRating(review.getRating());
            moviePageReviewResponse.setFeedback(review.getFeedback());
            User user =review.getUser();
            moviePageReviewResponse.setUsername(user.getUsername());
            moviePageReviewResponseList.add(moviePageReviewResponse);
        }
        return new Response<MoviePageReviewResponse>(200,"success",moviePageReviewResponseList);
    }

    @PostMapping("/{id}/review")
    public Response<Review> giveMovieReview(@PathVariable long id, @RequestBody GiveReviewRequest giveReviewRequest){
        String username=CustomAuthorizationFilter.userName;
        reviewService.addReview(id,giveReviewRequest,username);
        return new Response<Review>(200,"success");
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
