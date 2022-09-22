package com.example.quizmanagement;

public class Credentials {
	private static boolean isLoggedIn = false;

	public static boolean isLoggedIn() {
		return isLoggedIn;
	}

	public static void setLoggedIn(boolean data) {
		isLoggedIn = data;
	}
}
