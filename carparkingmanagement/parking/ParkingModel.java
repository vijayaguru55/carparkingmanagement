package com.carparkingmanagement.parking;

import java.util.ArrayList;

import com.carparkingmanagement.dto.ParkingLot;
import com.carparkingmanagement.dto.Receipt;
import com.carparkingmanagement.dto.User;
import com.carparkingmanagement.repository.CarParkingDatabase;

public class ParkingModel extends ParkingModelCallback {
	private ParkingModelControllerCallback controllerCallback;

	public ParkingModel(ParkingModelControllerCallback controllerCallback) {
		this.controllerCallback = controllerCallback;
	}

	interface ParkingModelControllerCallback {

		void bookingSuccess(User user, Receipt receipt);

		void parkingAddedSuccess(User user);

	}

	@Override
	protected ArrayList<String> getCitys() {
		CarParkingDatabase database = CarParkingDatabase.getInstance();
		return database.getCitys();
	}

	@Override
	protected ArrayList<ParkingLot> getParkings(String city) {

		CarParkingDatabase database = CarParkingDatabase.getInstance();

		return database.getParkings(city);
	}

	@Override
	protected void getParkingSpace(ParkingLot choosedParking, User user) {
		CarParkingDatabase database = CarParkingDatabase.getInstance();
		Receipt receipt = allotSpcae(choosedParking, user);
		receipt.setPrice(choosedParking.getPricePerHour());
		receipt.setParkingId(choosedParking.getParkingId());
		database.updateBooking(receipt, user);
		choosedParking.setAvailableCapacity(choosedParking.getAvailableCapacity() - 1);
		controllerCallback.bookingSuccess(user, receipt);
	}

	private Receipt allotSpcae(ParkingLot parking, User user) {
		String spaceNo = null;
		int spNo = parking.getAvailableCapacity() % parking.getFloorCapacity() + 1;
		int fNo = parking.getFloors() - parking.getAvailableCapacity() / parking.getFloorCapacity();
		if (fNo == 0)
			fNo = 1;
		int spPosition = getChar(parking.getFloorCapacity() / 10, spNo / 10);
		char character = (char) spPosition;
		spaceNo = fNo + Character.toString(character) + spNo;
		return new Receipt(parking.getParkingName(), user.getUserName(), user.getCarNumber(), fNo, spaceNo,
				parking.getAddress().getCity());

	}

	private int getChar(int position, int spNo) {
		int val = 65;
		if (spNo == 0)
			spNo = 1;
		int index = 1;
		while (index <= position) {
			if (spNo == index++)
				return val;
			val++;
		}
		return 65;
	}

	@Override
	protected void addParking(ParkingLot parking, User user) {
		CarParkingDatabase database = CarParkingDatabase.getInstance();
		database.updateParking(parking);
		controllerCallback.parkingAddedSuccess(user);
	}

	@Override
	protected ArrayList<ParkingLot> getmyParkings(User user) {
		CarParkingDatabase database = CarParkingDatabase.getInstance();
		return database.getParkings(user);
	}
}
