package com.example.otp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.otp.reposottries.Userrepo;
@Service
public class Userdetailservice implements UserDetailsService{
	@Autowired
	Userrepo userrepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
	User user = userrepo.findByUserName(username);
	
		return user;
	}

}
