package com.onboarding.moviescope.service;


import com.onboarding.moviescope.model.entity.Watchlist;
import com.onboarding.moviescope.model.request.AddToWatchlistRequest;

import java.util.List;

public interface WatchlistService {

    void addToWatchlist(AddToWatchlistRequest addToWatchlistRequest,String username);
    List<Watchlist> getUserWatchlistedMovies(String username);
}
