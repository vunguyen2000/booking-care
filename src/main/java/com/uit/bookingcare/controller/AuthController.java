package com.uit.bookingcare.controller;

import com.uit.bookingcare.dto.auth.UserLoginDto;
import com.uit.bookingcare.service.auth.JwtUserDetailsService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Api(value = "Auth APIs")
@RequiredArgsConstructor
public class AuthController {
    private final JwtUserDetailsService userDetailsService;
    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody UserLoginDto Request){

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(Request.getEmail());

        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
    }


}

