package com.carparkingmanagement.user;

import com.carparkingmanagement.dto.Receipt;
import com.carparkingmanagement.dto.User;

public abstract class UserControllerCallback {

	protected abstract void addUser(String name, long mobileNumber, String carNumber, String password, boolean isOwner);

	protected abstract void getMybooking(User user, boolean forClosing);

	protected abstract void closeBooking(User user, Receipt receipt);

}
