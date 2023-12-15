package com.mypractice.services;

import com.mypractice.dto.JWTAuthenticationResponse;
import com.mypractice.dto.RefreshTokenRequest;
import com.mypractice.dto.SignInRequest;
import com.mypractice.dto.SignUpRequest;
import com.mypractice.entities.User;

public interface AuthenticationService {
	User SignUp(SignUpRequest signUpRequest);
	
	JWTAuthenticationResponse signIn(SignInRequest signInRequest);
	
	JWTAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
