package com.mypractice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mypractice.dto.JWTAuthenticationResponse;
import com.mypractice.dto.RefreshTokenRequest;
import com.mypractice.dto.SignInRequest;
import com.mypractice.dto.SignUpRequest;
import com.mypractice.entities.User;
import com.mypractice.services.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
	
	private final AuthenticationService authenticationService;
	
	@PostMapping("/signup")
	public ResponseEntity<User> signUp(@RequestBody SignUpRequest signUpRequest){
		return ResponseEntity.ok(authenticationService.SignUp(signUpRequest));
	}
	
	@PostMapping("/signin")
	public ResponseEntity<JWTAuthenticationResponse> signIn(@RequestBody SignInRequest signInRequest){
		
		return ResponseEntity.ok(authenticationService.signIn(signInRequest));
	}
	
	@PostMapping("/refresh")
	public ResponseEntity<JWTAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest){
		
		return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
	}
}
