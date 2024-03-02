package dev.smruti.jwtsecurit.exception;

import io.jsonwebtoken.JwtException;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandleException {
    @ExceptionHandler({JwtException.class, IllegalArgumentException.class})
    ProblemDetail handleException(Exception e) {
        var problemDetail = ProblemDetail.forStatus(403);
        problemDetail.setDetail(e.getMessage());
        return problemDetail;
    }
}
