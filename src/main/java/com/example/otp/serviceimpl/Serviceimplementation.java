package com.example.otp.serviceimpl;

import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.otp.reposottries.Userrepo;
import com.example.otp.service.Userservice;
import com.example.otp.user.Registeration;
import com.example.otp.user.User;
import com.example.otp.zecptionhandler.ResourceNotFoundException;

@Transactional
@Service
public class Serviceimplementation implements Userservice{

	@Autowired
	Userrepo userrepo;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	User u=new User();
	
	@Override
	public List<User> getalluser() {
	return userrepo.findAll();

	}

	
	@Override
	public User finduserByid(int id) {
	return	userrepo.findById(id).orElseThrow(()-> new ResourceNotFoundException());
	
	}
	@Override
	public void adduser(User user) {
		        
		        u.setUserName(user.getUsername());
		        u.setPassword(passwordEncoder.encode(user.getPassword()));
		        u.setEmail(user.getEmail()); 
		        u.setRole(user.getRole());
		        
		userrepo.save(u);
		
	}

	@Override
	public void deleteuser(int id) {
		userrepo.deleteById(id);
		
	}
	
	public int generateotp() {
		Random random=new Random();
		int otp=100000+random.nextInt(900000);
		return otp;
	}
	
	public String userregisteration(Registeration registeruser) {
		  u.setUserName(registeruser.getUsername());
		  u.setPassword( passwordEncoder.encode(registeruser.getPassword())  );
		  u.setEmail(registeruser.getEmail());
		  u.setRole("ROLE_NORMAL");
		  userrepo.save(u);
		  return "added-successfully";
		
		
	}

}
