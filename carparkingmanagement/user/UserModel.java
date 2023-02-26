package com.carparkingmanagement.user;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.carparkingmanagement.dto.Credential;
import com.carparkingmanagement.dto.Receipt;
import com.carparkingmanagement.dto.User;
import com.carparkingmanagement.repository.CarParkingDatabase;

public class UserModel extends UserModelCallback {
	private UserModelControllerCallback controllerCallback;

	public UserModel(UserModelControllerCallback controllerCallback) {
		this.controllerCallback = controllerCallback;
	}

	interface UserModelControllerCallback {

		void userAddFailed(String string);

		void userAddSuccess(User user);

		void noBookings(User user);

		void viewBookings(ArrayList<Receipt> receipts, User user, boolean forClosing);

		boolean isPaymentDone(int price);

		void closingSuccess(Receipt receipt, User user);

		void closingFailed(String string, User user);

	}

	@Override
	protected void addUser(User user, String password) {
		CarParkingDatabase database = CarParkingDatabase.getInstance();
		if (database.isExistiongUser(user.getUserName(), password)) {
			controllerCallback.userAddFailed("User already Exist");
		} else {
			Credential credential = new Credential(user.getUserName(), password, user.getUserId());
			database.updateUser(user, credential);
			if(user.isOwner()) {
				user.setCarNumber("null");
			}
			controllerCallback.userAddSuccess(user);
		}
	}

	@Override
	protected void getMyBookings(User user, boolean forClosing) {

		CarParkingDatabase database = CarParkingDatabase.getInstance();
		if (!database.isbookedUser(user)) {
			controllerCallback.noBookings(user);
		}
		ArrayList<Receipt> receipts = database.getBookings(user, forClosing);
		if (receipts == null || receipts.size() == 0) {
			controllerCallback.noBookings(user);
		} else {
			controllerCallback.viewBookings(receipts, user, forClosing);
		}
	}

	@Override
	protected void closBooking(User user, Receipt receipt) {
		CarParkingDatabase database = CarParkingDatabase.getInstance();
		int price = getPriceAndCloseBooking(receipt);
		if (controllerCallback.isPaymentDone(price)) {
			database.updateClosing(receipt);
			String closeTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm").format(LocalDateTime.now());
			receipt.setEndDate(closeTime.substring(0, 11));
			receipt.setEndTime(closeTime.substring(11));
			controllerCallback.closingSuccess(receipt, user);
		} else {
			controllerCallback.closingFailed("Payment Failed...", user);
		}
	}

	private int getPriceAndCloseBooking(Receipt receipt) {
		long hourdf = 0;
		long daysdf = 0;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		try {
			Date date1 = dateFormat.parse(receipt.getStartdate() + receipt.getStarttime()), date2 = dateFormat
					.parse(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm").format(LocalDateTime.now()));
			long time = date2.getTime() - date1.getTime();
			hourdf = TimeUnit.MILLISECONDS.toHours(time) % 24;
			daysdf = TimeUnit.MICROSECONDS.toDays(time) % 365;

		} catch (ParseException e) {

			e.printStackTrace();
		}
		if (daysdf != 0) {
			hourdf += 24;
		} else if (hourdf == 0) {
			return ((int) hourdf + 1) * receipt.getPrice();
		}
		return ((int) hourdf) * receipt.getPrice();
	}
}
