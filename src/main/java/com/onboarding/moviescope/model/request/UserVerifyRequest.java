package com.onboarding.moviescope.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserVerifyRequest {
    private String email;
    private int code;
    private String newPassword;
}
