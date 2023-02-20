package com.onboarding.moviescope.advice;

import com.onboarding.moviescope.model.response.Response;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Level;

@RestControllerAdvice
@Log
public class ExceptionHandllerAdvice {
    @ExceptionHandler(value = {IllegalArgumentException.class})


    public @ResponseBody ResponseEntity<Response> badRequestErrorHandler(HttpServletRequest req, Exception e) throws Exception {

        // If the exception is annotated with @ResponseStatus rethrow it and let

        // the framework handle it - like the OrderNotFoundException example

        // at the start of this post.

        // AnnotationUtils is a Spring Framework utility class.

        log.log(Level.SEVERE, e.getMessage(), e);


        Response<Object> errorResponse = new Response(HttpStatus.BAD_REQUEST.value(), e.getMessage(),null);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);

    }
}
