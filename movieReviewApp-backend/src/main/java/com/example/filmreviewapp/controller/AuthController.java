package com.example.filmreviewapp.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.filmreviewapp.dto.LoginDTO;
import com.example.filmreviewapp.dto.RegistrationDTO;
import com.example.filmreviewapp.entity.AppUser;
import com.example.filmreviewapp.entity.Role;
import com.example.filmreviewapp.service.AppUserManager;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

import static com.example.filmreviewapp.config.SecurityConstants.*;

@AllArgsConstructor
@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping(path = AUTH_PREFIX)
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final AppUserManager userManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginRequest) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(), loginRequest.getPassword()
                    )
            ).getPrincipal();


            final UserDetails customer = userManager.loadUserByUsername(loginRequest.getEmail());
            AppUser user = (AppUser) userManager.loadUserByUsername(loginRequest.getEmail());
            String token = JWT.create()
                    .withSubject((customer.getUsername()))
                    .withClaim("userID", user.getId())
                    .withClaim("name", customer.getUsername())
                    .withClaim("isAdmin", (customer.getAuthorities()).toString().equals("[ADMIN]") ? "true" : "false")
                    .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .sign(Algorithm.HMAC512(SECRET.getBytes()));
            return ResponseEntity.ok(token);
        } catch (BadCredentialsException badCredentialsException) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistrationDTO request) {
        if (request.getName() == null || request.getSurname() == null || request.getEmail() == null ||
                                         request.getPassword() == null)
        {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
        // email validation
        return userManager.registerUser(new AppUser(request.getName(), request.getSurname(), request.getEmail(), request.getPassword(), Role.USER));
    }
}
