package com.raj.controller;



import java.util.List;

import javax.management.relation.RelationNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raj.repository.RajRepository;
import com.raj.exceptions.ResourceNotFoundExceptions;
import com.raj.model.Raj;
@RestController
@RequestMapping("/api")
public class Control {
	 @Autowired
	private RajRepository rajRepository;
	// Getting all users
	@GetMapping("/all")
	public List<Raj> getAll(){
		return this.rajRepository.findAll();
	}
	// Add a User
	@PostMapping("/add")
	public Raj register(@RequestBody Raj raj){
		return this.rajRepository.save(raj);
	}
	// Get User With ID
	@GetMapping("/{id}")
	public Raj getUser(@PathVariable(value="id") long rajId){
		return this.rajRepository.findById(rajId)
				.orElseThrow(()-> new ResourceNotFoundExceptions("User Not Found with this :"+rajId));
	}
	// update a User
	@PutMapping("/{id}")
	public Raj updateUser( @RequestBody Raj raj, @PathVariable(value="id") long rajId){
		Raj user=this.rajRepository.findById(rajId)
				.orElseThrow(()-> new ResourceNotFoundExceptions("User Not Found with this :"+rajId));
		user.setFirstname(raj.getFirstname());
		user.setLastname(raj.getLastname());
		user.setEmail(raj.getEmail());
		
		return this.rajRepository.save(user);
	}
	// Delete a User with ID
	@DeleteMapping("/{id}")
	public ResponseEntity<Raj> deleteUser( @PathVariable(value="id") long rajId){
		Raj user=this.rajRepository.findById(rajId)
				.orElseThrow(()-> new ResourceNotFoundExceptions("User Not Found with this :"+rajId));
		this.rajRepository.delete(user);
		return ResponseEntity.ok().build();
	}
	
	
}
