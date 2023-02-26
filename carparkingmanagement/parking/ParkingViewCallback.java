package com.carparkingmanagement.parking;

import com.carparkingmanagement.dto.Receipt;
import com.carparkingmanagement.dto.User;

public abstract class ParkingViewCallback {

	protected abstract void bookingSuccess(User user, Receipt receipt);

	protected abstract void parkingAddedSuccess(User user);

}
