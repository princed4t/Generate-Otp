package com.example.otp.service;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

import com.example.otp.user.User;

public interface Userservice {
	
	public List<User> getalluser();
	public User finduserByid(int id);
	public void adduser(User user);
	public void deleteuser(int id);
		
	
	

}
