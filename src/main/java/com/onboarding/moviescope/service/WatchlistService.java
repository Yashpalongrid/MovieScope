package com.onboarding.moviescope.service;


import com.onboarding.moviescope.model.response.WatchlistResponse;

import java.util.List;

public interface WatchlistService {


    List<WatchlistResponse> getUserWatchlistedMovies(String username);
}
