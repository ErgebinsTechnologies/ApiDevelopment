package com.raj.reg.service.api.resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloResource {
	
	@GetMapping("/all")
	public String hello() {
		return "<h1>hello all</h1>";
	}
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/admin")
	public String hell() {
		return "<h1>hello all are secured</h1>";
	}
	


}
