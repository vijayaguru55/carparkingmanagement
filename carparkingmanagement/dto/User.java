package com.carparkingmanagement.dto;

public class User {
	private String userName;
	private String carNumber;
	private long mobileNo;
	private static int id = 121;
	private String userId;
	private boolean isOwner = false;

	public User(String name, String carNum, long mobileNo) {
		this.userName = name;
		this.carNumber = carNum;
		this.mobileNo = mobileNo;
		this.userId = id + name.substring(3).toUpperCase();
		id++;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}

	public boolean isOwner() {
		return isOwner;
	}

	public void setOwner(boolean isOwner) {
		this.isOwner = isOwner;
	}

}
