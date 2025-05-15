package com.master.authfy.service;

import com.master.authfy.entity.UserEntity;
import com.master.authfy.io.ProfileRequest;
import com.master.authfy.io.ProfileResponse;
import com.master.authfy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ProfileResponse createProfile ( ProfileRequest request ) {
        UserEntity newProfile = convertToUserEntity(request);
        if (!userRepository.existsByEmail ( request.getEmail () )) {
            newProfile = userRepository.save(newProfile);
            return convertToProfileResponse(newProfile);
        }
        throw new ResponseStatusException ( HttpStatus.CONFLICT, "Email already exists");
    }

    private ProfileResponse convertToProfileResponse ( UserEntity newProfile ) {
        return ProfileResponse.builder ()
                .userId ( newProfile.getUserId () )
                .name ( newProfile.getName () )
                .email ( newProfile.getEmail () )
                .isAccountVerified ( newProfile.getIsAccountVerified () )
                .build();
    }

    private UserEntity convertToUserEntity ( ProfileRequest request ) {
        return UserEntity.builder ()
                .name ( request.getName() )
                .userId ( UUID.randomUUID ().toString () )
                .email ( request.getEmail() )
                .password (passwordEncoder.encode(request.getPassword ()))
                .isAccountVerified ( false )
                .resetOtpExpireAt ( 0L )
                .verifyOtp ( null )
                .verifyOtpExpireAt ( 0L )
                .resetOtp ( null )
                .build ();

    }


}
