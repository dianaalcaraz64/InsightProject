package com.insight.automation.data;

public class User {
	
	private String email;
    private String password;
    private String expectedUserName;
    

    public User(String email, String password, String expectedUserName) {
     this.email = email;
     this.password = password;
     this.expectedUserName = expectedUserName;
    }
    

    public String getEmail() { 
     return email; 
    }
    
    public String getPassword() {
     return password; 
    }
	
	public String getExpectedUserName() {
		return expectedUserName;
	}
	
	public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setExpectedUserName(String expectedUserName) { 
        this.expectedUserName = expectedUserName;
    }

}
