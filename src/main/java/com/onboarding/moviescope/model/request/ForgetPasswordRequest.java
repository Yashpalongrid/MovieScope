package com.onboarding.moviescope.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;

@Data
@AllArgsConstructor
public class ForgetPasswordRequest {

    @Email
    private String email;
}
