package com.javainuse.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.javainuse.dao.UserDao;
import com.javainuse.model.DAOUser;
import com.javainuse.model.UserDTO;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{

		DAOUser user = userDao.findByUsername(username);
		if (username == null || username.isEmpty() || username.length()==0) 
		{
		    throw new UsernameNotFoundException("Username is null or empty.");
		}
		else
		{
			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
		}
	}
	
	public DAOUser save(UserDTO userDto)
	{
		DAOUser newUser = new DAOUser();
		newUser.setFname(userDto.getFname());
		newUser.setLname(userDto.getLname());
		newUser.setCity(userDto.getCity());
		newUser.setUsername(userDto.getUsername());
		newUser.setPassword(bcryptEncoder.encode(userDto.getPassword()));
		return userDao.save(newUser);
		
	}
		
	public DAOUser getDetail(String username)
	{
		DAOUser user = userDao.findByUsername(username);
		return user;	
	}
	
	public DAOUser updateDetails(DAOUser user)
	{
		DAOUser userUpdated = userDao.save(user);
		return userUpdated;
	}

	
}