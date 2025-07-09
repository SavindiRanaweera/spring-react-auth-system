package com.master.authfy.controller;

import com.master.authfy.io.ProfileRequest;
import com.master.authfy.io.ProfileResponse;
import com.master.authfy.service.ProfileService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ProfileResponse register(@Valid @RequestBody ProfileRequest request) {
        ProfileResponse response = profileService.createProfile ( request );
        //ToDo: send welcome email
        return response;
    }

    @GetMapping("/profile")
    public ProfileResponse getProfile( @CurrentSecurityContext(expression = "authentication?.name") String email ) {
        return profileService.getProfile ( email );
    }
}
