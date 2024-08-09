package com.jatis.showcase.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.jatis.showcase.config.JwtConfigProperties;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.lang.Function;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtServiceImpl implements JwtService {
	@Autowired
	private JwtConfigProperties jwtConfigProps;

	@Override
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	@Override
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	@Override
	public String generateToken(UserDetails userDetails) {
		return generateToken(new HashMap<>(), userDetails);
	}

	@Override
	public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
		return buildToken(extraClaims, userDetails, jwtConfigProps.getExpirationTime());
	}

	@Override
	public long getExpirationTime() {
		return jwtConfigProps.getExpirationTime();
	}

	private String buildToken(Map<String, Object> extraClaims, UserDetails userDetails, long expiration) {
		Date currentDate = new Date();
		return Jwts.builder().claims(extraClaims).subject(userDetails.getUsername()).issuedAt(currentDate)
				.expiration(new Date(currentDate.getTime() + expiration)).signWith(getSigningKey()).compact();
	}

	@Override
	public boolean isTokenValid(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
	}

	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload();
	}

	private SecretKey getSigningKey() {
		byte[] keyBytes = this.jwtConfigProps.getSecretKey().getBytes();
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
