package com.raj.reg.service.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")

public class Resource {
	

	@GetMapping("/ra")
	
	public String secure() {
		return "<h1>hello admmin 1</h1>";
	
	}

	@GetMapping("/raaj")
	
	public String secur() {
		return "<h1>hello admin 2</h1>";
	
	}


}
