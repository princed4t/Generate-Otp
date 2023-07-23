package com.example.otp.jwttoken;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.otp.user.Userdetailservice;
@Service
public class SecurityFillter extends OncePerRequestFilter {
	@Autowired
	private TokenHelper tokenhelper;
	@Autowired
	private UserDetailsService userdetails;
	

	
	
	
	

	@SuppressWarnings("unused")
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String requesttoken = request.getHeader("Authorization");
		System.out.println(requesttoken);
		String username = null;
		String token = null;
		if (requesttoken != null && requesttoken.startsWith("Bearer")) {

			token = requesttoken.substring(7);
			System.out.println(token);
			try {
				username = tokenhelper.extractUsername(token);
			
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = userdetails.loadUserByUsername(username);
             
			if (tokenhelper.validateToken(token, userDetails)) {
			
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

			}

		}
		filterChain.doFilter(request, response);

	}

}
