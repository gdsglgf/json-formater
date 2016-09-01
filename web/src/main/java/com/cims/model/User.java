package com.cims.model;

import java.util.Date;

public class User {
    public User() {}
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public User(String username, String password, String email) {
		this(username, password);
		this.email = email;
	}

	public User(String username, String password, String email,
			Date registerTime) {
		this(username, password, email);
		this.registerTime = registerTime;
	}

	public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return String.format("User[%d, %s]", uid, username);
    }
    
    private long uid;
    private String username;
    private String password;
    private String email;
    private Date registerTime;
}
