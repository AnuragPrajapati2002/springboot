package com.javainuse.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javainuse.model.DAOUser;

@Repository

public interface UserDao extends JpaRepository<DAOUser, Integer> 
{
	
	DAOUser findByUsername(String username);
	
}