package com.onboarding.moviescope.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Response {
    private int code;
    private String message;
    Object data;

    public Response(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
