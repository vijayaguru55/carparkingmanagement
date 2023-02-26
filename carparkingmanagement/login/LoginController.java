package com.carparkingmanagement.login;

import com.carparkingmanagement.dto.User;
import com.carparkingmanagement.login.LoginModel.LoginModelControllerCallback;

public class LoginController extends LoginControllerCallback implements LoginModelControllerCallback {
	private LoginViewCallback viewCallback;
	private LoginModelCallback modelCallback;

	public LoginController(LoginView loginView) {
		this.viewCallback = loginView;
		this.modelCallback = new LoginModel(this);
	}

	@Override
	protected void chechCredential(String userName, String password) {
		modelCallback.checkCredential(userName, password);
	}

	@Override
	public void loginFailed(String message) {
		viewCallback.loginFailed(message);
	}

	@Override
	public void loginSuccess(User user) {
		viewCallback.loginSuccess(user);
	}
}
