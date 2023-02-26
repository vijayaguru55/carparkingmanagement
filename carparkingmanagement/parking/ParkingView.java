package com.carparkingmanagement.parking;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.carparkingmanagement.dto.Address;
import com.carparkingmanagement.dto.ParkingLot;
import com.carparkingmanagement.dto.Receipt;
import com.carparkingmanagement.dto.User;
import com.carparkingmanagement.user.UserView;

public class ParkingView extends ParkingViewCallback {
	Scanner scanner = new Scanner(System.in);
	private ParkingControllerCallback controllerCallback;

	public ParkingView() {
		this.controllerCallback = new ParkingController(this);
	}

	public void getNearlyParkings(User user) {
		if (user.isOwner() && user.getCarNumber().equals("null")) {
			System.out.println("Enter your car Number to Book Parking");
			String carNumber = scanner.next();
			user.setCarNumber(carNumber);
		}
		ArrayList<ParkingLot> availableParkings = getParkings(user);
		if (availableParkings == null || availableParkings.size() == 0) {
			noService("No Parkings are avail in this city...", user);
		} else {
			viewParkings(availableParkings);
			System.out.println("Enter parking number to Book parking Space");
			System.out.println("Enter " + (availableParkings.size() + 1) + ".Menu");
			int index = 0;
			try {
				index = scanner.nextInt();
				if (index > availableParkings.size()) {
					UserView userView = new UserView();
					userView.menu(user);
				}
			} catch (InputMismatchException e) {
				System.out.println("invalid input");
				viewParkings(availableParkings);
			}
			ParkingLot choosedParking = availableParkings.get(index - 1);
			if (choosedParking.getAvailableCapacity() == 0) {
				noService("No space avail in this Parking", user);
			} else {
				controllerCallback.bookParkingSpace(choosedParking, user);
			}

		}
	}

	private void viewParkings(ArrayList<ParkingLot> availableParkings) {
		int count = 1;
		for (ParkingLot parkingLot : availableParkings) {

			System.out.println("-------------------------------------------------------");
			System.out.printf("\t%d.%s\n", count++, parkingLot.getParkingName());
			System.out.printf("\t  %s\n", parkingLot.getAddress().getCity());
			System.out.printf("%-23s:%d\n", "Available space", parkingLot.getAvailableCapacity());
			System.out.printf("%-23s:%d/hr\n", "Price", parkingLot.getPricePerHour());
		}
		System.out.println("-------------------------------------------------------");
	}

	private ArrayList<ParkingLot> getParkings(User user) {
		ArrayList<String> citys = controllerCallback.getCitys();
		if (citys == null || citys.size() == 0) {
			noService("No service......", user);
		} else {
			int index = 1;
			for (String city : citys) {
				System.out.println(index++ + "." + city);
			}
			System.out.println("Enter City number to choose");
			int option = scanner.nextInt();
			if (option > citys.size()) {
				System.out.println("Invalid input");
				UserView userView = new UserView();
				userView.menu(user);
			}
			return controllerCallback.getParkings(citys.get(option - 1));
		}
		return null;
	}

	private void noService(String message, User user) {
		System.out.println(message);
		UserView userView = new UserView();
		userView.menu(user);
	}

	@Override
	protected void bookingSuccess(User user, Receipt receipt) {
		System.out.println("Parking Space Booking Confirmed....");
		printReceipt(receipt);
		System.out.println("------------------------------------------------------------------");
		UserView userView = new UserView();
		userView.menu(user);
	}

	public void printReceipt(Receipt receipt) {
		System.out.println("------------------------------------------------------------------");
		System.out.printf("\t%s\t\n", receipt.getParkingName());
		System.out.printf("\t   %s\n", receipt.getParkingCity());
		System.out.printf("%-23s:%s\n", "Receipt ID", receipt.getReceiptId());
		System.out.printf("%-23s:%-20s\n", "Name", receipt.getUserName());
		System.out.printf("%-23s:%s\n", "Car Number", receipt.getCarNumber());
		System.out.printf("%-23s:%s\n", "Parking No", receipt.getParkingSpaceNo());
		if (!receipt.getEndDate().equals("null")) {
			System.out.printf("%-23s:%s  %s\n", "Start", receipt.getStartdate(), receipt.getStarttime());
			System.out.printf("%-23s:%s  %s\n", "End", receipt.getEndDate(), receipt.getEndTime());
		} else
			System.out.printf("%-23s:%s\n%-23s:%s\n", "Date", receipt.getStartdate(), "Time", receipt.getStarttime());
	}

	public void addParking(User user) {
		System.out.println("Enter parking Name");
		String parkingName = scanner.next();
		System.out.println("Enter parking address:");
		System.out.println("Enter Street name :");
		String street = scanner.next();
		System.out.println("Enter  area name :");
		String area = scanner.next();
		System.out.println("Enter City Name :");
		String city = scanner.next();
		int no = 0, floors = 0, floorCapacity = 0, price = 0;
		long pin = 0;

		System.out.println("Enter buildingNo:");
		try {
			no = scanner.nextInt();
			System.out.println("Enter PinCode:");
			pin = scanner.nextLong();
			System.out.println("Enter Number of floors are in your Parking");
			floors = scanner.nextInt();
			System.out.println("Enter Number of cars to be parked in each floor:");
			floorCapacity = scanner.nextInt();
			System.out.println("Enter Price Per Hour:");
			price = scanner.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Invalid Input");
			addParking(user);
		}
		Address address = new Address(no, street, area, city, pin);
		controllerCallback.addParking(user, parkingName, address, floors, floorCapacity, price);
	}

	@Override
	protected void parkingAddedSuccess(User user) {
		System.out.println("Parking Addded Success");
		UserView userView = new UserView();
		userView.menu(user);
	}

	public void viewParkings(User user) {
		UserView userView = new UserView();
		ArrayList<ParkingLot> parkingLots = controllerCallback.getMyParkings(user);
		if (parkingLots == null || parkingLots.size() == 0) {
			System.out.println("No parkingLots available");

			userView.menu(user);
		}
		System.out.println("Select by Numbers :");
		System.out.println((parkingLots.size() + 1) + ".Menu");
		viewParkings(parkingLots);
		int index = scanner.nextInt();
		if (index > parkingLots.size()) {
			userView.menu(user);
		}
		viewparking(parkingLots.get(index - 1));
		userView.menu(user);
	}

	private void viewparking(ParkingLot parkingLot) {

		System.out.println("-------------------------------------------------------");
		System.out.printf("\t%20s\t\n", parkingLot.getParkingName());

		System.out.printf("%-23s:%d,%s\n", "Address", parkingLot.getAddress().getNo(),
				parkingLot.getAddress().getArea());
		System.out.printf("%-23s %s -%d\n", "", parkingLot.getAddress().getCity(),
				parkingLot.getAddress().getPincode());
		System.out.printf("%-23s:%s\n", "ParkingID", parkingLot.getParkingId());
		System.out.printf("%-23s:%d\n", "Available space", parkingLot.getAvailableCapacity());
		System.out.printf("%-23s:%d\n", "Occupied space", parkingLot.getCapacity() - parkingLot.getAvailableCapacity());
		System.out.printf("%-23s:%d\n", "Price per Hour", parkingLot.getPricePerHour());
		System.out.println("-------------------------------------------------------");
		System.out.println("1.Change Price\n2.Menu");
		int option = 0;
		try {
			option = scanner.nextInt();
			if (option == 1) {
				System.out.println("Enter new Price:");
				int price = scanner.nextInt();
				parkingLot.setPricePerHour(price);
			} else if (option == 2) {
				return;
			} else {
				throw new InputMismatchException();
			}
		} catch (InputMismatchException e) {
			System.out.println("Invalid Input..");
			viewparking(parkingLot);
		}

	}

}
