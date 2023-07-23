package com.example.otp.controller;
	import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
	import lombok.NoArgsConstructor;
	import lombok.Setter;
	import lombok.ToString;

  @Data
  @ToString
  @AllArgsConstructor
  @Getter
  @Setter
	public class Jwtrequest {
	     @NotEmpty
	 	@Min(value =6,message="must be equal 6 char")
		private String username;
		private String password;

	}


