package com.example.otp.jwttoken;

import com.example.otp.user.User;

import lombok.Data;

@Data
public class JwtAuthResponse {
	private String token;
	private User user;

}
