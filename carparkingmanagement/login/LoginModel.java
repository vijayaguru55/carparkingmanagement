package com.carparkingmanagement.login;

import com.carparkingmanagement.dto.User;
import com.carparkingmanagement.repository.CarParkingDatabase;

public class LoginModel extends LoginModelCallback {
	private LoginModelControllerCallback controllerCallback;

	public LoginModel(LoginModelControllerCallback callback) {
		this.controllerCallback = callback;
	}

	interface LoginModelControllerCallback {

		void loginFailed(String string);

		void loginSuccess(User user);

	}

	@Override
	protected void checkCredential(String userName, String password) {
		CarParkingDatabase database = CarParkingDatabase.getInstance();
		User user = database.checkCredentials(userName, password);
		if (user != null) {
			controllerCallback.loginSuccess(user);
		} else {
			controllerCallback.loginFailed("Invalid Credentials");
		}

	}
}
