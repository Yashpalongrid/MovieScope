package com.onboarding.moviescope.model.response;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private int expiryTime;
}
