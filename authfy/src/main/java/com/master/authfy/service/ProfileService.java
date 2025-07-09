package com.master.authfy.service;

import com.master.authfy.io.ProfileRequest;
import com.master.authfy.io.ProfileResponse;

public interface ProfileService {

    ProfileResponse createProfile( ProfileRequest request );

    ProfileResponse getProfile( String email );
}
