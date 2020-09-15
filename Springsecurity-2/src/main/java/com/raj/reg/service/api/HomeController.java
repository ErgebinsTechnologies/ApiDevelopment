package com.raj.reg.service.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/use")
public class HomeController {
@GetMapping("/a")
	
	public String secure() {
		return "<h1>hello Admin</h1>";
	
	}
	

}
