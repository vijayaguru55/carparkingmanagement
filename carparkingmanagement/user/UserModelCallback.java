package com.carparkingmanagement.user;

import com.carparkingmanagement.dto.Receipt;
import com.carparkingmanagement.dto.User;

public abstract class UserModelCallback {

	protected abstract void addUser(User user, String password);

	protected abstract void getMyBookings(User user, boolean forClosing);

	protected abstract void closBooking(User user, Receipt receipt);

}
