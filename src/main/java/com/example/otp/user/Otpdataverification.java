package com.example.otp.user;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data
@Getter
@Setter
@NoArgsConstructor
public class Otpdataverification {
	 private String otp;
	 private LocalDateTime expirationTime;
	public Otpdataverification(String otp, LocalDateTime expirationTime) {
		super();
		this.otp = otp;
		this.expirationTime = expirationTime;
	}
	 
public boolean isexpired() {
	return LocalDateTime.now().isAfter(expirationTime);
	
	
}
	 
	
	

}
