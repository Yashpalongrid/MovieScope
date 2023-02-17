package com.onboarding.moviescope.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ForgetPasswordRequest {
    private String email;
}
