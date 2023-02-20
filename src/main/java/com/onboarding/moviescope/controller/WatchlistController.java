package com.onboarding.moviescope.controller;

import com.onboarding.moviescope.filter.CustomAuthorizationFilter;
import com.onboarding.moviescope.model.entity.Watchlist;
import com.onboarding.moviescope.model.request.AddToWatchlistRequest;
import com.onboarding.moviescope.model.response.Response;
import com.onboarding.moviescope.model.response.WatchlistResponse;
import com.onboarding.moviescope.service.impl.MovieServiceImpl;
import com.onboarding.moviescope.service.impl.WatchlistServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/watchlist")
public class WatchlistController {

    @Autowired
    private WatchlistServiceImpl watchlistService;

    @Autowired
    private MovieServiceImpl movieService;



    @PostMapping
    public Response<Object> addToWatchlist(@RequestBody AddToWatchlistRequest addToWatchlistRequest){
        String username=CustomAuthorizationFilter.userName;
        watchlistService.addToWatchlist(addToWatchlistRequest,username);
        return new Response<Object>(HttpStatus.OK.value(), "success",null);
    }

    @GetMapping
    public Response<List<WatchlistResponse>> getWatchListedMovies(){
        String username=CustomAuthorizationFilter.userName;
        List<Watchlist> watchlistMovies=watchlistService.getUserWatchlistedMovies(username);
        List<WatchlistResponse> watchlistResponseList=new ArrayList<>();
        for( Watchlist watchlist : watchlistMovies){
            WatchlistResponse watchlistResponse=new WatchlistResponse();
            watchlistResponse.setName(watchlist.getMovie().getName());
            watchlistResponse.setStoryline(watchlist.getMovie().getStoryline());
            watchlistResponse.setAverageRating(watchlist.getMovie().getAverageRating());
            watchlistResponse.setReleaseYear(watchlist.getMovie().getReleaseYear());
            watchlistResponse.setImgSource(watchlist.getMovie().getImgSource());
            watchlistResponse.setGenres(watchlist.getMovie().getGenres());
            watchlistResponse.setCast(watchlist.getMovie().getCast());
            watchlistResponse.setLanguages(watchlist.getMovie().getLanguages());
            watchlistResponse.setStreamingPlatforms(watchlist.getMovie().getStreamingPlatforms());
            watchlistResponseList.add(watchlistResponse);
        }

        return new Response<>(HttpStatus.OK.value(), "success",watchlistResponseList);

    }
}
