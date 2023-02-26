package com.carparkingmanagement.parking;

import java.util.ArrayList;

import com.carparkingmanagement.dto.ParkingLot;
import com.carparkingmanagement.dto.User;

public abstract class ParkingModelCallback {

	protected abstract ArrayList<String> getCitys();

	protected abstract ArrayList<ParkingLot> getParkings(String city);

	protected abstract void getParkingSpace(ParkingLot choosedParking, User user);

	protected abstract void addParking(ParkingLot parking, User user);

	protected abstract ArrayList<ParkingLot> getmyParkings(User user);

}
