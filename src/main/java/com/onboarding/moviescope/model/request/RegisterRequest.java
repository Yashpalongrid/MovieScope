package com.onboarding.moviescope.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotBlank
    @Column(unique = true)
    @Size(max = 20)
    private String username;

    @NotBlank
    @Column(unique = true)
    @Size(max = 40)
    @Email
    private String email;

    @NotBlank
    @Size(max = 200)
    private String password;

    @NotBlank
    @Size
    private String confirmPassword;
}
