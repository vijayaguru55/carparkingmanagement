package com.carparkingmanagement.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.carparkingmanagement.dto.Credential;
import com.carparkingmanagement.dto.ParkingLot;
import com.carparkingmanagement.dto.Receipt;
import com.carparkingmanagement.dto.User;

public class CarParkingDatabase {
	private static CarParkingDatabase carParkingDatabase;
	private ArrayList<User> userList = new ArrayList<User>();
	private Map<User, ArrayList<Receipt>> parkingsOfUser = new HashMap<User, ArrayList<Receipt>>();
	private ArrayList<Credential> credential = new ArrayList<Credential>();
	private ArrayList<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
	private ArrayList<String> serviceCitys = new ArrayList<String>();

	public static CarParkingDatabase getInstance() {
		if (carParkingDatabase == null) {
			carParkingDatabase = new CarParkingDatabase();
			return carParkingDatabase;
		}

		return carParkingDatabase;
	}

	public User checkCredentials(String userName, String password) {
		for (Credential credential : credential) {
			if (credential.getUserName().equals(userName) && credential.getPassword().equals(password))
				return getUser(credential.getUserID());
		}
		return null;
	}

	private User getUser(String userID) {
		for (User user : userList) {
			if (user.getUserId().equals(userID))
				return user;
		}
		return null;
	}

	public void updateUser(User user, Credential credential) {
		this.userList.add(user);
		this.credential.add(credential);
	}

	public boolean isExistiongUser(String userName, String password) {
		for (Credential credential : credential) {
			if (credential.getUserName().equals(userName) && credential.getPassword().equals(password))
				return true;
		}
		return false;
	}

	public ArrayList<String> getCitys() {

		return this.serviceCitys;
	}

	public ArrayList<ParkingLot> getParkings(String city) {
		ArrayList<ParkingLot> parkings = new ArrayList<ParkingLot>();
		for (ParkingLot parkingLot : this.parkingLots) {
			if (parkingLot.getAddress().getCity().equals(city))
				parkings.add(parkingLot);
		}
		return parkings;
	}

	public void updateBooking(Receipt receipt, User user) {
		ArrayList<Receipt> receipts = null;
		if (this.parkingsOfUser.containsKey(user)) {
			receipts = this.parkingsOfUser.get(user);
		} else {
			receipts = new ArrayList<Receipt>();
		}
		receipts.add(receipt);
		this.parkingsOfUser.put(user, receipts);
	}

	public void updateParking(ParkingLot parking) {
		this.parkingLots.add(parking);
		if (!this.serviceCitys.contains(parking.getAddress().getCity())) {
			this.serviceCitys.add(parking.getAddress().getCity());
		}
	}

	public ArrayList<ParkingLot> getParkings(User user) {
		ArrayList<ParkingLot> parkings = new ArrayList<ParkingLot>();
		for (ParkingLot parking : parkingLots) {
			if (user.getUserId().equals(parking.getUserID()))
				parkings.add(parking);
		}
		return parkings;
	}

	public ArrayList<Receipt> getBookings(User user, boolean forClosing) {
		ArrayList<Receipt> receipts = new ArrayList<Receipt>();
		if (forClosing) {
			for (Receipt receipt : this.parkingsOfUser.get(user)) {
				if (receipt.getEndDate().equals("null"))
					receipts.add(receipt);
			}
		} else {
			receipts = this.parkingsOfUser.get(user);
		}
		return receipts;
	}

	public void updateClosing(Receipt receipt) {
		for (ParkingLot parking : parkingLots) {
			if (parking.getParkingId().equals(receipt.getParkingId())) {
				parking.setCapacity(parking.getCapacity() + 1);
			}
		}
	}

	public boolean isbookedUser(User user) {

		return this.parkingsOfUser.containsKey(user);
	}
}
