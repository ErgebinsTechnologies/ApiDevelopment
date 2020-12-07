package com.raj.reg.service.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raj.reg.service.api.model.Users;

public interface UsersRepository extends JpaRepository<Users,Integer> {
	Optional<Users> findByName(String username);

}
