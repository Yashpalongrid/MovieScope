package com.onboarding.moviescope.service.impl;

import com.onboarding.moviescope.dao.WatchlistRepository;
import com.onboarding.moviescope.model.entity.Watchlist;
import com.onboarding.moviescope.model.response.WatchlistResponse;
import com.onboarding.moviescope.service.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WatchlistServiceImpl implements WatchlistService {

    @Autowired
    private WatchlistRepository watchlistRepository;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private MovieServiceImpl movieService;



    @Override
    public List<WatchlistResponse> getUserWatchlistedMovies(String username) {
        List<Watchlist> watchlistMovies=watchlistRepository.findMovieByUserId(userService.getUser(username).getId());
        List<WatchlistResponse> watchlistResponseList=new ArrayList<>();
        for( Watchlist watchlist : watchlistMovies){
            WatchlistResponse watchlistResponse=new WatchlistResponse();
            watchlistResponse.setName(watchlist.getMovie().getName());
            watchlistResponse.setStoryline(watchlist.getMovie().getStoryline());
            watchlistResponse.setRating(watchlist.getMovie().getRating());
            watchlistResponse.setReleaseYear(watchlist.getMovie().getReleaseYear());
            watchlistResponse.setImgSource(watchlist.getMovie().getImgSource());
            watchlistResponse.setGenres(watchlist.getMovie().getGenres());
            watchlistResponse.setCast(watchlist.getMovie().getCast());
            watchlistResponse.setLanguages(watchlist.getMovie().getLanguage());
            watchlistResponse.setStreamingPlatforms(watchlist.getMovie().getStreamingPlatforms());
            watchlistResponseList.add(watchlistResponse);
        }
        return watchlistResponseList;
    }
}
