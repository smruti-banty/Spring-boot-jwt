package org.securebank.backend.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.securebank.backend.service.JwtService;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtService service;
    private final ObjectMapper obj;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String path = request.getRequestURI();

        if (path.contains("/login") || path.contains("/signin")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String token = request.getHeader("token");
            String username = service.validToken(token);

            request.setAttribute("username", username);
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            var problemDetails = ProblemDetail.forStatus(401);
            problemDetails.setTitle("Unauthorized");
            problemDetails.setDetail(e.getMessage());

            response.setContentType("application/json");
            response.setStatus(401);
            response.getWriter().println(obj.writeValueAsString(problemDetails));
        }
    }
}

