package com.onboarding.moviescope.controller;

import com.onboarding.moviescope.filter.CustomAuthorizationFilter;
import com.onboarding.moviescope.model.constant.Genre;
import com.onboarding.moviescope.model.constant.StreamingPlatform;
import com.onboarding.moviescope.model.entity.Movie;
import com.onboarding.moviescope.model.entity.Watchlist;
import com.onboarding.moviescope.model.request.AddToWatchlistRequest;
import com.onboarding.moviescope.model.response.Response;
import com.onboarding.moviescope.model.response.WatchlistResponse;
import com.onboarding.moviescope.service.impl.MovieServiceImpl;
import com.onboarding.moviescope.service.impl.WatchlistServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/watchlist")
public class WatchlistController {

    @Autowired
    private WatchlistServiceImpl watchlistService;

    @Autowired
    private MovieServiceImpl movieService;



    @PostMapping
    public Response<Watchlist> addToWatchlist(@RequestBody AddToWatchlistRequest addToWatchlistRequest){
        String username=CustomAuthorizationFilter.userName;
        watchlistService.addToWatchlist(addToWatchlistRequest,username);
        return new Response<Watchlist>(200,"success");
    }

    @GetMapping
    public Response<Watchlist> getWatchListedMovies(){
        List<Long> watchlistMovies=watchlistService.getUserWatchlistedMovies();
        List<WatchlistResponse> watchlistResponseList=new ArrayList<>();
        for( Long movieId : watchlistMovies){
            WatchlistResponse watchlistResponse=new WatchlistResponse();
            Movie movie=movieService.getMovieDetails(movieId);
            watchlistResponse.setName(movie.getName());
            watchlistResponse.setStoryline(movie.getStoryline());
            watchlistResponse.setAverageRating(movie.getAverageRating());
            watchlistResponse.setReleaseYear(movie.getReleaseYear());
            watchlistResponse.setImgSource(movie.getImgSource());
            Set<Genre> genres=movie.getGenres();
            watchlistResponse.setGenres(genres);
            Set<String> cast=movie.getCast();
            watchlistResponse.setCast(cast);
            watchlistResponse.setLanguages(movie.getLanguages());
            Set<StreamingPlatform> streamingPlatforms=movie.getStreamingPlatforms();
            watchlistResponse.setStreamingPlatforms(streamingPlatforms);
            watchlistResponseList.add((watchlistResponse));
        }

        return new Response<Watchlist>(200,"success",watchlistMovies);

    }
}
