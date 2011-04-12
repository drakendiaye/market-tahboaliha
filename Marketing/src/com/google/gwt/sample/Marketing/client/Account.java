package com.google.gwt.sample.Marketing.client;

import java.io.Serializable;

public class Account implements Serializable{
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private String email;
	private String phonenumber;
	private String address;
	private String city;
	private String zip;
	private boolean isadmin;
	
	public Account(String username, String password){
		this.username = username;
		this.password = password;
	}
	
	public String getUsername(){
		return this.username;
	}
	
	public String getPassword(){
		return this.password;
	}
	
	public boolean getIsAdmin(){
		return isadmin;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public void setIsAdmin(boolean isAdmin){
		this.isadmin = isAdmin;
	}
}
