package com.carparkingmanagement.login;

import java.util.Scanner;

import com.carparkingmanagement.dto.User;
import com.carparkingmanagement.user.UserView;

public class LoginView extends LoginViewCallback {
	Scanner scanner = new Scanner(System.in);
	private LoginControllerCallback controllerCallback;

	public LoginView() {
		this.controllerCallback = new LoginController(this);
	}

	public static void main(String[] args) {
		LoginView loginView = new LoginView();
		loginView.create();
	}

	public void create() {
		menu();
	}

	private void menu() {
		System.out.println("1.Login\n2.SignUp\n3.Exit");
		String option = scanner.next();
		switch (option) {
		case "1":
			login();
			break;
		case "2":
			signUp();
			break;
		case "3":
			System.out.println("\tThank You....\t");
			System.exit(0);
			break;
		default: {
			System.out.println("Invalid Input");
			menu();
		}
			break;
		}
	}

	private void signUp() {
		System.out.println("1.Register as Parkinguser\n2.Register as ParkingOwner");
		String option = scanner.next();
		if (option.equals("1")) {
			UserView userView = new UserView();
			userView.addUser(false);
		} else if (option.equals("2")) {
			UserView userView = new UserView();
			userView.addUser(true);
		} else {
			System.out.println("Invalid input");
			signUp();
		}

	}

	private void login() {
		System.out.println("Enter User Name:");
		String userName = scanner.next();
		System.out.println("Enter password:");
		String password = scanner.next();
		controllerCallback.chechCredential(userName, password);
	}

	@Override
	protected void loginFailed(String message) {
		System.out.println(message);
		menu();
	}

	@Override
	protected void loginSuccess(User user) {
		System.out.println("Welcome <--" + user.getUserName() + "-->");
		UserView userView = new UserView();
		userView.menu(user);
	}
}
