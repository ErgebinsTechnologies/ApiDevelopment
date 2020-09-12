package com.raj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.raj.model.User;
import com.raj.service.RegistrationService;

@RestController

public class RegistrationController {
	@Autowired
	private RegistrationService registrationService;
	
	@PostMapping("/registeruser")
	
	public User registerUser(@RequestBody User user) throws Exception  {
	String tempemailId=user.getEmailId();
		if(tempemailId !=null && ! "".equals(tempemailId)) {
			User userobj=registrationService.fetchUserByEmailId(tempemailId);
			if(userobj != null) {
				throw new Exception("user with "+tempemailId+ "is already Exist");
			}
		}
		User userobj=null;
		userobj=registrationService.saveuser(user);
		return userobj;
	}
	

	@PostMapping("/login")
	@CrossOrigin(origins="http://localhost:4200")
	public String loginUser(@RequestBody User user ) throws Exception {
		String templemailId=user.getEmailId();
		String tempPass=user.getPassword();
		User userobj=null;
		if(templemailId !=null && tempPass !=null ) {
			userobj=registrationService.fetchUserByEmailIdAndPassword(templemailId, tempPass);
		
		}
		if(userobj==null) {
			throw new Exception ("Bad Crediantial");
		}
		return "Valid User";
	}
	

}
