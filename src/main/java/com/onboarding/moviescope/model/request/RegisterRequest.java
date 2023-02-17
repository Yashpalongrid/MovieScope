package com.onboarding.moviescope.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class RegisterRequest {

    @NotBlank
    @Size(max = 20)
    private String username;
    @NotBlank
    @Size(max = 40)
    @Email
    private String email;

    @NotBlank
    @Size(max = 30)
    private String password;

    @NotBlank
    @Size(max=30)
    private String confirmPassword;
}
