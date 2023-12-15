package com.mypractice.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;

import com.mypractice.entities.User;

public interface JWTService {
	String extractUserName(String token);
	String generateToken(UserDetails userDetails);
		
	
	Boolean isTokenValid(String token,UserDetails userDetails);
	String generateRefreshToken(Map<String, Object> extraClaims,UserDetails userDetails);
}
