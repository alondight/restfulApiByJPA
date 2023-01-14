package com.tradlinx.backendtest.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.tradlinx.backendtest.exception.CustomdException;
import com.tradlinx.backendtest.service.UserService;
import com.tradlinx.backendtest.util.JwtTokenUtil;

import io.jsonwebtoken.Claims;

@Component
public class JwtAuthenticationFilter  extends OncePerRequestFilter {

    private JwtTokenUtil jwtTokenUtil;
	private UserService userService;

	public JwtAuthenticationFilter(JwtTokenUtil jwtTokenUtil2, UserService userService2) {
		// TODO Auto-generated constructor stub
		this.jwtTokenUtil = jwtTokenUtil2;
		this.userService  = userService2;
	}



	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
		    String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		    Claims claims = jwtTokenUtil.parseJwtToken(authorizationHeader);
		    if(null != claims ) {
			    long seq = Long.valueOf((String)claims.get("seq"));
			    UserDetails userDetails = userService.loadUserBySeq(seq);
			    UsernamePasswordAuthenticationToken authentication = 
			    new UsernamePasswordAuthenticationToken(userDetails,
			                                              null,
			                                              userDetails.getAuthorities());
			  
			    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			
			    SecurityContextHolder.getContext().setAuthentication(authentication);
		    }
		} catch (Exception e) {
			throw new CustomdException("No Authorized", 403);
		}
	    filterChain.doFilter(request, response);
		
	}
}
