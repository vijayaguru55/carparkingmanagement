package com.carparkingmanagement.dto;

public class Credential {
	private String userName, password, userID;

	public Credential(String name, String password, String userId) {
		this.userName = name;
		this.userID = userId;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

}
