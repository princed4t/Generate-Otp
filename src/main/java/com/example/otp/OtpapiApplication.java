package com.example.otp;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.otp.reposottries.Userrepo;
import com.example.otp.user.User;

@SpringBootApplication

public class OtpapiApplication {
	@Autowired
	Userrepo ur;
	@Autowired
	PasswordEncoder pecode;
	
	
	
	/*
	 * @PostConstruct public void initusers() {
	 * 
	 * List<User> li=new ArrayList<>(); li.add(new
	 * User("prince",pecode.encode("xyz"),"princedt4@gmail.com","ROLE_NORMAL"));
	 * li.add(new
	 * User("princess",pecode.encode("xyz"),"princedt4@gmail.com","ROLE_NORMAL"));
	 * li.add(new
	 * User("princewa",pecode.encode("xyz"),"princedt4@gmail.com","ROLE_NORMAL"));
	 * ur.saveAll(li); }
	 */
	
	 
	

	public static void main(String[] args) {
		SpringApplication.run(OtpapiApplication.class, args);
	}

}
