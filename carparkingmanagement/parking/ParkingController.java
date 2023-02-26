package com.carparkingmanagement.parking;

import java.util.ArrayList;

import com.carparkingmanagement.dto.Address;
import com.carparkingmanagement.dto.ParkingLot;
import com.carparkingmanagement.dto.Receipt;
import com.carparkingmanagement.dto.User;
import com.carparkingmanagement.parking.ParkingModel.ParkingModelControllerCallback;

public class ParkingController extends ParkingControllerCallback implements ParkingModelControllerCallback {
	private ParkingViewCallback viewCallback;
	private ParkingModelCallback modelCallback;

	public ParkingController(ParkingView parkingView) {
		this.viewCallback = parkingView;
		this.modelCallback = new ParkingModel(this);
	}

	@Override
	protected ArrayList<String> getCitys() {

		return modelCallback.getCitys();
	}

	@Override
	protected ArrayList<ParkingLot> getParkings(String city) {

		return modelCallback.getParkings(city);
	}

	@Override
	protected void bookParkingSpace(ParkingLot choosedParking, User user) {
		modelCallback.getParkingSpace(choosedParking, user);
	}

	@Override
	public void bookingSuccess(User user, Receipt receipt) {
		viewCallback.bookingSuccess(user, receipt);
	}

	@Override
	protected void addParking(User user, String parkingName, Address address, int floors, int floorCapacity,
			int price) {
		ParkingLot parking = new ParkingLot(parkingName, address, floors, floorCapacity, price, user.getUserId());
		modelCallback.addParking(parking, user);
	}

	@Override
	public void parkingAddedSuccess(User user) {
		viewCallback.parkingAddedSuccess(user);
	}

	@Override
	protected ArrayList<ParkingLot> getMyParkings(User user) {

		return modelCallback.getmyParkings(user);
	}
}
