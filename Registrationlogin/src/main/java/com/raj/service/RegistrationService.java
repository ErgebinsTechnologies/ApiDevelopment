package com.raj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raj.model.User;
import com.raj.repository.RegistrationRepository;

@Service
public class RegistrationService {
	@Autowired
	RegistrationRepository registrationRepository;
	public User saveuser(User user) {
		return registrationRepository.save(user);
	}
	public User fetchUserByEmailId(String email) {
		return registrationRepository.findByEmailId(email);
	
	}
	public User fetchUserByEmailIdAndPassword(String email,String password) {
		return registrationRepository.findByEmailIdAndPassword(email, password);
	}

}
