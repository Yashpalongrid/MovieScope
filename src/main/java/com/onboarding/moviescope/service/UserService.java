package com.onboarding.moviescope.service;

import com.onboarding.moviescope.model.entity.User;
import com.onboarding.moviescope.model.request.RegisterRequest;

public interface UserService {

    User registerUser(RegisterRequest registerRequest);

    public User getUser(String username);
}

//    User saveUser(User user);
//    public User findUser(long id);
