package com.javainuse.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class DAOUser 
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
	private String username;
	
	@Column
	@JsonIgnore
	private String password;
	
	@Column
	private String fname;
	
	@Column
	private String lname;
	
	@Column
	private String city;
	
	

	public String getUsername() 
	{
		return username;
	}

	public void setUsername(String username) 
	{
		this.username = username;
	}

	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) 
	{
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() 
	{
		return "DAOUser [fname=" + fname + ", lname=" + lname + ", city=" + city + ", username=" + username
				+ ", password=" + password + "]";
	}
	
	
	
}