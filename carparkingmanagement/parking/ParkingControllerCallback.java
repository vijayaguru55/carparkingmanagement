package com.carparkingmanagement.parking;

import java.util.ArrayList;

import com.carparkingmanagement.dto.Address;
import com.carparkingmanagement.dto.ParkingLot;
import com.carparkingmanagement.dto.User;

public abstract class ParkingControllerCallback {

	protected abstract ArrayList<String> getCitys();

	protected abstract ArrayList<ParkingLot> getParkings(String string);

	protected abstract void bookParkingSpace(ParkingLot choosedParking, User user);

	protected abstract void addParking(User user, String parkingName, Address address, int floors, int floorCapacity,
			int price);

	protected abstract ArrayList<ParkingLot> getMyParkings(User user);

}
