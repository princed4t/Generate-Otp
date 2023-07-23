package com.example.otp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.otp.jwttoken.JwtAuthResponse;
import com.example.otp.jwttoken.TokenHelper;
import com.example.otp.serviceimpl.Serviceimplementation;
import com.example.otp.user.Registeration;
import com.example.otp.user.User;
import com.example.otp.user.Userdetailservice;
import com.example.otp.zecptionhandler.ResourceNotFoundException;

@RestController
@RequestMapping(value="/security")
@CrossOrigin(origins = {"http://localhost:3000"})
public class Securitycontroller {
	@Autowired
	TokenHelper tokenhelper;
	@Autowired
	private Userdetailservice userdetailservices;

	@Autowired
	private AuthenticationManager authenticationManager;
    
	@Autowired
	Serviceimplementation serviceimplementation;
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> m1(@RequestBody Jwtrequest request) {

		this.authenticate(request.getUsername(), request.getPassword());
		
		UserDetails user = this.userdetailservices.loadUserByUsername(request.getUsername());
		System.out.println(user+"hi");
		String token = this.tokenhelper.generateToken(user);
		JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
		jwtAuthResponse.setToken(token);
		jwtAuthResponse.setUser((User)user);
		return new ResponseEntity<JwtAuthResponse>(jwtAuthResponse, HttpStatus.OK);

	}

	private void authenticate(String username, String password) {  try {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				username, password);
		this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
	}
	catch(Exception e) {
		throw new ResourceNotFoundException();
	}
		
		
	}
	@PostMapping(value="/registeruser")
	public String registeruser(@RequestBody Registeration registeration) {
		
		return serviceimplementation.userregisteration(registeration);
		
		
		
		
	}
	
	

}
