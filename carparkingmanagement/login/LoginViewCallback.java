package com.carparkingmanagement.login;

import com.carparkingmanagement.dto.User;

public abstract class LoginViewCallback {

	protected abstract void loginFailed(String message);

	protected abstract void loginSuccess(User user);

}
