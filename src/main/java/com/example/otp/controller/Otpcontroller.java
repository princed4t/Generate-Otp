package com.example.otp.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoProperties.Storage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.otp.service.Emailsenderservice;
import com.example.otp.serviceimpl.Serviceimplementation;
import com.example.otp.user.Otpdataverification;
import com.example.otp.user.User;
import com.example.otp.user.Userotpemailinfo;

@RestController
@RequestMapping(value = "/otp")
@CrossOrigin(origins = { "http://localhost:3000" })
public class Otpcontroller {
	@Autowired
	Serviceimplementation serviceimplementation;
	@Autowired
	Emailsenderservice ems;

	private String emaile;

	private String otp;

	Map<String, Otpdataverification> map = new HashMap<>();

	LocalDateTime expirationtime = LocalDateTime.now().plusMinutes(2);

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value = "/getallusers")
	public ResponseEntity<List<User>> getalluser() {

		List<User> getalluser = serviceimplementation.getalluser();
		return new ResponseEntity<List<User>>(getalluser, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value = "/finduserbyid/{id}")
	public ResponseEntity<User> finduserByid(@PathVariable int id) {
		User user = serviceimplementation.finduserByid(id);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "/adduser")
	public ResponseEntity<User> adduser(@RequestBody User user) {
		serviceimplementation.adduser(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(value = "/deleteuse/{id}")
	public void deleteuser(@PathVariable int id) {
		serviceimplementation.deleteuser(id);
	}

	@PreAuthorize("hasRole('NORMAL')")
	@GetMapping(value = "/generateotp")
	public String generateotp(@RequestBody Userotpemailinfo user) {
		emaile = user.getEmail();
		int generateotp = serviceimplementation.generateotp();
		otp = String.valueOf(generateotp);
		map.put(emaile, new Otpdataverification(otp, expirationtime));
		ems.sendmessage(emaile, "your-otp", otp);

		System.out.println(otp);
		System.out.println(map);
		return "otpgenerate" + generateotp;
	}

	@PostMapping(value = "/verifyotp/{email}/{userOtp}")
	@PreAuthorize("hasRole('NORMAL')")
	public ResponseEntity<String> validateotp(@PathVariable String email, @PathVariable String userOtp) {

		String otp3 = map.get(email).getOtp();

		Otpdataverification otpdataverification = map.get(email);
		LocalDateTime expiration = otpdataverification.getExpirationTime();

		if (otpdataverification.isexpired()) {
			return ResponseEntity.badRequest().body("ur-otp is expired");
		}

		if (userOtp.equals(otp3)) {
			map.remove(email);
			return ResponseEntity.ok().body(" ur otp is verified-otp");

		}

		System.out.println(map);

		return ResponseEntity.badRequest().body("ur-otp time is incorrect");

	}
}