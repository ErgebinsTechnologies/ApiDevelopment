package com.raj.reg.service.api.service;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.raj.reg.service.api.model.CustomUserDetails;
import com.raj.reg.service.api.model.Users;
import com.raj.reg.service.api.repository.UsersRepository;

@Service

public class CustomUserDetailsService implements UserDetailsService {
	@Autowired 
	private UsersRepository usersRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		Optional<Users> optionalUsers=usersRepository.findByName(username);
		
		optionalUsers
        .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
         return optionalUsers
        .map(CustomUserDetails::new).get();
}

		
	

}
