package org.securebank.backend.controller;

import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.securebank.backend.dto.LoginDTO;
import org.securebank.backend.service.JwtService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final JwtService service;

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO dto) {
        return service.createToken(dto.username());
    }

    @GetMapping
    public String getUser(@RequestAttribute String username) {
        return username;
    }

}
