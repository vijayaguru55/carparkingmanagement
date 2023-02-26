package com.carparkingmanagement.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Receipt {
	private String parkingName;
	private String parkingCity;
	private String userName;
	private String carNumber;
	private int floorNo;
	private static int id = 1221;
	private String parkingSpaceNo;
	String Startdate;
	private String endDate;
	String Starttime;
	private String endTime;
	private String receiptId;
	private int price;
	private String parkingId;

	public Receipt(String parkingName, String userName, String carNumber, int floorNo, String spaceNo,
			String location) {
		this.parkingName = parkingName;
		this.parkingCity = location;
		this.userName = userName;
		this.carNumber = carNumber.toUpperCase();
		this.floorNo = floorNo;
		this.endDate = "null";
		this.parkingSpaceNo = spaceNo;
		String dateTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm").format(LocalDateTime.now());
		this.Startdate = dateTime.substring(0, 11);
		this.Starttime = dateTime.substring(11);
		this.receiptId = Startdate.substring(6) + parkingName.substring(0, 4).toUpperCase()
				+ userName.substring(0, 3).toUpperCase() + id++;
	}

	public String getParkingName() {
		return parkingName;
	}

	public void setParkingName(String parkingName) {
		this.parkingName = parkingName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getStartdate() {
		return Startdate;
	}

	public void setStartdate(String startdate) {
		Startdate = startdate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getStarttime() {
		return Starttime;
	}

	public void setStarttime(String starttime) {
		Starttime = starttime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getFloorNo() {
		return floorNo;
	}

	public void setFloorNo(int floorNo) {
		this.floorNo = floorNo;
	}

	public String getParkingCity() {
		return parkingCity;
	}

	public void setParkingCity(String parkingCity) {
		this.parkingCity = parkingCity;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public String getParkingSpaceNo() {
		return parkingSpaceNo;
	}

	public void setParkingSpaceNo(String parkingSpaceNo) {
		this.parkingSpaceNo = parkingSpaceNo;
	}

	public String getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(String receiptId) {
		this.receiptId = receiptId;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getParkingId() {
		return parkingId;
	}

	public void setParkingId(String parkingId) {
		this.parkingId = parkingId;
	}

}
