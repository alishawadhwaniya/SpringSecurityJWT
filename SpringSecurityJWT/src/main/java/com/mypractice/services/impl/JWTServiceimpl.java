package com.mypractice.services.impl;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.mypractice.services.JWTService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTServiceimpl implements JWTService{
	public String generateToken(UserDetails userDetails) {
		return Jwts.builder().setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
				.signWith(getSiginKey(),SignatureAlgorithm.HS256)
				.compact();
	}
	public String generateRefreshToken(Map<String, Object> extraClaims,UserDetails userDetails) {
		return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+604800000))
				.signWith(getSiginKey(),SignatureAlgorithm.HS256)
				.compact();
	}
	public String extractUserName(String token) {
		return extractClaim(token,Claims::getSubject);
	}
	private <T> T extractClaim(String token,Function<Claims,T> claimsResolvers) {
		final Claims claims=extractAllClaims(token);
		return claimsResolvers.apply(claims);
	}
	private Key getSiginKey() {
		byte[] key=Decoders.BASE64.decode("8Zz5tw0Ionm3XPZZfN0NOml3z9FMfmpgXwovR9fp6ryDIoGRM8EPHAB6iHsc0fb");
		return Keys.hmacShaKeyFor(key);
	}
	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSiginKey()).build().parseClaimsJws(token).getBody();
	}
	
	public Boolean isTokenValid(String token,UserDetails userDetails) {
		final String username=extractUserName(token);
		return(username.equals(userDetails.getUsername())&& !isTokenExpired(token));
	}
	
	private Boolean isTokenExpired(String token) {
		return extractClaim(token,Claims::getExpiration).before(new Date());
	}
}
