package com.example.otp.reposottries;

import org.hibernate.type.TrueFalseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.otp.user.User;

public interface Userrepo extends JpaRepository<User,Integer>{
	
	public User findByUserName(String username);
    
    
	
}
