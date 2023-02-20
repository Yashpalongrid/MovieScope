package com.onboarding.moviescope.service.impl;

import com.onboarding.moviescope.dao.WatchlistRepository;
import com.onboarding.moviescope.model.entity.Watchlist;
import com.onboarding.moviescope.model.request.AddToWatchlistRequest;
import com.onboarding.moviescope.service.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void addToWatchlist(AddToWatchlistRequest addToWatchlistRequest,String username) {
        Watchlist watchlist=new Watchlist();
        watchlist.setUser(userService.getUser(username));
        watchlist.setMovie(movieService.getMovieDetails(addToWatchlistRequest.getMovieId()));
        watchlistRepository.save(watchlist);
    }

    @Override
    public List<Long> getUserWatchlistedMovies() {
        List<Long> watchlistMovies=watchlistRepository.findMovieByUserId(4L);
        return watchlistMovies;
    }
}
