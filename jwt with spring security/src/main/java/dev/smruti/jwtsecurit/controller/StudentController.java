package dev.smruti.jwtsecurit.controller;

import dev.smruti.jwtsecurit.dto.LoginDTO;
import dev.smruti.jwtsecurit.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {
    private final AuthenticationManager authentication;
    private final JwtService jwtService;
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginDTO dto) {
        var auth = new UsernamePasswordAuthenticationToken(dto.username(), dto.password());
        authentication.authenticate(auth);
        var token = jwtService.createToken(dto.username());
        return Map.of("token", token);
    }

    @GetMapping
    Map<String, String> getStudent(Principal principal) {
        return Map.of("username", principal.getName());
    }
}
