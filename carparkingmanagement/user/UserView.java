package com.carparkingmanagement.user;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.carparkingmanagement.dto.Receipt;
import com.carparkingmanagement.dto.User;
import com.carparkingmanagement.login.LoginView;
import com.carparkingmanagement.parking.ParkingView;

public class UserView extends UserViewCallback {
	Scanner scanner = new Scanner(System.in);
	private UserControllerCallback controllerCallback;

	public UserView() {
		this.controllerCallback = new UserController(this);
	}

	public void addUser(boolean isOwner) {
		System.out.println("Enter Name :");
		String name = scanner.next();
		String carNumber = null;
		if (!isOwner) {
			System.out.println("Enter your Car Number");
			carNumber = scanner.next();
		}
		long mobileNumber = 0;
		try {
			System.out.println("Enter Mobile Number:");
			mobileNumber = scanner.nextLong();
		} catch (InputMismatchException e) {
			System.out.println("Enter valid input");
			addUser(isOwner);
		}
		System.out.println("Set password");
		String password = scanner.next();
		controllerCallback.addUser(name, mobileNumber, carNumber, password, isOwner);

	}

	public void menu(User user) {
		if (user.isOwner())
			ownersMenu(user);
		else
			userMenu(user);
	}

	private void userMenu(User user) {
		System.out.println("1.Get Parkings\n2.Get My Bookings\n3.Close booked parking \n4.Logout");
		String option = scanner.next();
		switch (option) {
		case "1":
			getParkings(user);
			break;
		case "2":
			getBookings(user, false);
			break;
		case "3":
			getBookings(user, true);
		case "4":
			LoginView loginView = new LoginView();
			loginView.create();
			break;
		default: {
			System.out.println("Invalid Input");
			userMenu(user);
		}
			break;
		}
	}

	private void getBookings(User user, boolean forClosing) {
		controllerCallback.getMybooking(user, forClosing);

	}

	private void getParkings(User user) {
		ParkingView parkingView = new ParkingView();
		parkingView.getNearlyParkings(user);
	}

	private void ownersMenu(User user) {
		System.out.println("1.Add new ParkingLot\n2.View Parking\n3.Book Parking space\n4.Logout");
		String option = scanner.next();
		ParkingView parkingView = new ParkingView();
		switch (option) {
		case "1":
			parkingView.addParking(user);
			break;
		case "2": {
			parkingView.viewParkings(user);
		}
			break;
		case "3": {
			userMenu(user);
		}
			break;
		case "4":
			LoginView loginView = new LoginView();
			loginView.create();
			break;
		default: {
			System.out.println("Invalid Input");
			menu(user);
		}
			break;
		}
	}

	@Override
	protected void userAddFailed(String message) {
		System.out.println(message);
		LoginView loginView = new LoginView();
		loginView.create();
	}

	@Override
	protected void userAddSuccess(User user) {
		System.out.println("Registration Success..");
		menu(user);
	}

	@Override
	protected void noBookings(User user) {
		System.out.println("No booking Found....");
		menu(user);
	}

	@Override
	protected void viewReceipts(ArrayList<Receipt> receipts, User user, boolean forClosing) {
		ParkingView parkingView = new ParkingView();
		int count = 1;
		for (Receipt receipt : receipts) {

			System.out.println("Receipt No:" + count++);
			parkingView.printReceipt(receipt);
		}
		System.out.println("----------------------------------------------------------------");
		if (forClosing) {
			System.out.println("Select Receipt Number to close");
			System.out.println("Enter " + (receipts.size() + 1) + ".Menu");
			int index = 0;
			try {
				index = scanner.nextInt();
				if (index > receipts.size()) {
					menu(user);
				}
				controllerCallback.closeBooking(user, receipts.get(index - 1));
			} catch (InputMismatchException | ArrayIndexOutOfBoundsException e) {
				viewReceipts(receipts, user, forClosing);
			}
		}
		menu(user);
	}

	@Override
	protected boolean isPaymentDone(int price) {
		System.out.println("Total amount : " + price);
		System.out.println("1.proceed payment.\n 2.Cancel");
		String option = scanner.next();
		return option.equals("1") ? true : false;
	}

	@Override
	protected void closingFailed(String message, User user) {
		System.out.println(message);
		menu(user);
	}

	@Override
	void closingSucess(Receipt receipt, User user) {
		System.out.println("Booking Closing success...");
		ParkingView parkingView = new ParkingView();
		parkingView.printReceipt(receipt);
		menu(user);
	}

}
