package com.fiap.telegrambot.util;

public class EnvironmentUtil {

	public static String getToken() {
		return System.getenv("TELEGRAM_TOKEN");
	}
}
