package com.onboarding.moviescope.controller;

import com.onboarding.moviescope.model.request.AddToWatchlistRequest;
import com.onboarding.moviescope.model.request.GiveReviewRequest;
import com.onboarding.moviescope.model.response.MovieIconResponse;
import com.onboarding.moviescope.model.response.MoviePageResponse;
import com.onboarding.moviescope.model.response.MoviePageReviewResponse;
import com.onboarding.moviescope.model.response.Response;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @GetMapping
    public MovieIconResponse getALlMovies(@RequestParam("pageNo") int pageNo,@RequestParam("pageSize") int pageSize,@RequestParam("name") String name,@RequestParam("genre") String genre,@RequestParam("language") String language,@RequestParam("year") int year){
        return new MovieIconResponse();
    }

    @GetMapping("/{id}")
    public MoviePageResponse getMovie(@PathVariable long id){
        return new MoviePageResponse();
    }

    @GetMapping("/{id}/review")
    public MoviePageReviewResponse getMovieReviews(@RequestParam("pageNo") int pageNo,@RequestParam("pageSize") int pageSize,@PathVariable long id){
        return new MoviePageReviewResponse();
    }

    @PostMapping("/{id}/review")
    public Response giveMovieReview(@PathVariable long id, @RequestBody GiveReviewRequest giveReviewRequest){
        return new Response();
    }

    public Response addToWatchlist(@RequestBody AddToWatchlistRequest addToWatchlistRequest){
        return new Response();
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
