package com.carparkingmanagement.user;

import java.util.ArrayList;

import com.carparkingmanagement.dto.Receipt;
import com.carparkingmanagement.dto.User;

public abstract class UserViewCallback {

	protected abstract void userAddFailed(String message);

	protected abstract void userAddSuccess(User user);

	protected abstract void noBookings(User user);

	protected abstract void viewReceipts(ArrayList<Receipt> receipts, User user, boolean forClosing);

	protected abstract boolean isPaymentDone(int price);

	protected abstract void closingFailed(String message, User user);

	abstract void closingSucess(Receipt receipt, User user);

}
