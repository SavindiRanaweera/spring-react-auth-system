package com.master.authfy.controller;

import com.master.authfy.io.ProfileRequest;
import com.master.authfy.io.ProfileResponse;
import com.master.authfy.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1.0")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ProfileResponse register(@RequestBody ProfileRequest request) {
        ProfileResponse response = profileService.createProfile ( request );
        //ToDo: send welcome email
        return response;
    }

}
