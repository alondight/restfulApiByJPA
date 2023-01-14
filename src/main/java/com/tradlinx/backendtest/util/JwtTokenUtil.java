package com.tradlinx.backendtest.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil{

	public static final long JWT_TOKEN_VALIDITY = 5*60*60*10000;

	@Value("${jwt.secret}")
	private String secret;

	public Claims parseJwtToken(String authorizationHeader) throws Exception{
		if(authorizationHeader != null ) {
		    validationAuthorizationHeader(authorizationHeader); // (1)
		    String token = extractToken(authorizationHeader); // (2) 
	
		    return Jwts.parser()
		        .setSigningKey(secret) // (3)
		        .parseClaimsJws(token) // (4)
		        .getBody();
		} else {
			return null;
		}
		
	}


	private void validationAuthorizationHeader(String header) {
	    if (header == null || !header.startsWith("Bearer ")) {
	      throw new IllegalArgumentException();
	    }
	}

	private String extractToken(String authorizationHeader) {
	    return authorizationHeader.substring("Bearer ".length());
	}
	
	public String makeJwtToken(String seq , String username) {
	    return Jwts.builder()
	        .setHeaderParam(Header.TYPE, Header.JWT_TYPE) // (1)
	        .setIssuer("fresh") // (2)
	        .setIssuedAt(new Date()) // (3)
	        .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY)) // (4)
	        .claim("seq", seq) // (5)
	        .claim("username", username)
	        .signWith(SignatureAlgorithm.HS256, secret) // (6)
	        .compact();
	  }
}
