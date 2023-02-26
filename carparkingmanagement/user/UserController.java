package com.carparkingmanagement.user;

import java.util.ArrayList;

import com.carparkingmanagement.dto.Receipt;
import com.carparkingmanagement.dto.User;
import com.carparkingmanagement.user.UserModel.UserModelControllerCallback;

public class UserController extends UserControllerCallback implements UserModelControllerCallback {
	private UserViewCallback viewCallback;
	private UserModelCallback modelCallback;

	public UserController(UserView userView) {
		this.viewCallback = userView;
		this.modelCallback = new UserModel(this);
	}

	@Override
	protected void addUser(String name, long mobileNumber, String carNumber, String password, boolean isOwner) {
		User user = new User(name, carNumber, mobileNumber);
		user.setOwner(isOwner);
		modelCallback.addUser(user, password);
	}

	@Override
	public void userAddFailed(String message) {
		viewCallback.userAddFailed(message);
	}

	@Override
	public void userAddSuccess(User user) {
		viewCallback.userAddSuccess(user);
	}

	@Override
	protected void getMybooking(User user, boolean forClosing) {
		modelCallback.getMyBookings(user, forClosing);
	}

	@Override
	public void noBookings(User user) {
		viewCallback.noBookings(user);
	}

	@Override
	public void viewBookings(ArrayList<Receipt> receipts, User user, boolean forClosing) {
		viewCallback.viewReceipts(receipts, user, forClosing);
	}

	@Override
	protected void closeBooking(User user, Receipt receipt) {
		modelCallback.closBooking(user, receipt);
	}

	@Override
	public boolean isPaymentDone(int price) {

		return viewCallback.isPaymentDone(price);
	}

	@Override
	public void closingSuccess(Receipt receipt, User user) {
		viewCallback.closingSucess(receipt, user);
	}

	@Override
	public void closingFailed(String message, User user) {
		// TODO Auto-generated method stub
		viewCallback.closingFailed(message, user);
	}
}
