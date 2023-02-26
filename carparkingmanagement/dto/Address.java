package com.carparkingmanagement.dto;

public class Address {
	private int No;
	private String street, area, city;
	private long pincode;

	public Address(int no, String street, String area, String city, long pincode) {
		this.No = no;
		this.area = area;
		this.street = street;
		this.pincode = pincode;
		this.city = city;
	}

	public int getNo() {
		return No;
	}

	public void setNo(int no) {
		No = no;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public long getPincode() {
		return pincode;
	}

	public void setPincode(long pincode) {
		this.pincode = pincode;
	}

}
