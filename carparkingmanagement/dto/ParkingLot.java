package com.carparkingmanagement.dto;

public class ParkingLot {
	private String parkingName;
	private Address address;
	private static int id = 123;
	private String parkingId;
	private int floors;
	private int floorCapacity;
	private int capacity;
	private int availableCapacity = capacity;
	private int pricePerHour;
	private String userID;

	public ParkingLot(String name, Address address, int floors, int floorCapacity, int price, String useriD) {
		this.address = address;
		this.parkingName = name + " Parking Yard";
		this.floorCapacity = floorCapacity;
		this.floors = floors;
		this.capacity = floors * floorCapacity;
		this.availableCapacity = capacity;
		this.userID = useriD;
		this.pricePerHour = price;
		this.parkingId = name.substring(2).toUpperCase() + id++;

	}

	public String getParkingName() {
		return parkingName;
	}

	public void setParkingName(String parkingName) {
		this.parkingName = parkingName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public int getFloors() {
		return floors;
	}

	public void setFloors(int floors) {
		this.floors = floors;
	}

	public int getFloorCapacity() {
		return floorCapacity;
	}

	public void setFloorCapacity(int floorCapacity) {
		this.floorCapacity = floorCapacity;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getAvailableCapacity() {
		return availableCapacity;
	}

	public void setAvailableCapacity(int availableCapacity) {
		this.availableCapacity = availableCapacity;
	}

	public String getParkingId() {
		return parkingId;
	}

	public void setParkingId(String parkingId) {
		this.parkingId = parkingId;
	}

	public int getPricePerHour() {
		return pricePerHour;
	}

	public void setPricePerHour(int pricePerHour) {
		this.pricePerHour = pricePerHour;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

}
