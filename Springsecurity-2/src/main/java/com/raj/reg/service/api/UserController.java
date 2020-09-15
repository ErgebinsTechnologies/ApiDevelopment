package com.raj.reg.service.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

	

	@GetMapping("/dmin")
	
	public String secure() {
		return "<h1>hello user</h1>";
	
	}

	@GetMapping("/user")
	
	public String secur() {
		return "<h1>hell user 2</h1>";
	
	}
}




