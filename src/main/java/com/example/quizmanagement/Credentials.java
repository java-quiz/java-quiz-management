package com.example.quizmanagement;

public class Credentials {
	private static boolean isLoggedIn = false;
	private static String username;

	public static boolean isLoggedIn() {
		return isLoggedIn;
	}

	public static void setLoggedIn(boolean data) {
		isLoggedIn = data;
	}

	public static String getUsername() {
		return username;
	}

	public static void setUsername(String name) {
		username = name;
	}
}
