package com.javainuse.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javainuse.model.DAOUser;
import com.javainuse.model.UserDTO;
import com.javainuse.service.JwtUserDetailsService;



@RestController
public class HelloWorldController 
{
	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	@RequestMapping({ "/hello" })
	public String firstPage() 
	{
		return "Hello World";
	}
	 
	@GetMapping("/profile")
	public ResponseEntity<?> getProfile() 
	{
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String username = authentication.getName();
	    
	    DAOUser user = jwtUserDetailsService.getDetail(username);
	    return ResponseEntity.ok(user);

	}
	
//	
	@PostMapping("/update")
	public ResponseEntity<?> updateProfile(@RequestBody UserDTO userDto)
	{	
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String username = authentication.getName();
		
	    DAOUser user = jwtUserDetailsService.getDetail(username);
	    if (user == null) 
	    {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
	    }
	    
	    if (userDto.getFname() != null)
	    {
	        user.setFname(userDto.getFname());
	    }

	    if (userDto.getLname() != null) 
	    {
	        user.setLname(userDto.getLname());
	    }

	    if (userDto.getCity() != null) 
	    {
	        user.setCity(userDto.getCity());
	    }
	    DAOUser userUpdated =  jwtUserDetailsService.updateDetails(user);
	    return ResponseEntity.ok(userUpdated);
		
		
	}

}