package com.runage.ia.telegrambot.utils;

public enum KeysUtils {

	TELEGRAM_BOTNAME("<name>"),
	TELEGRAM_APIKEY("<token>"),
	WEATHER_APIKEY("NDkwZDVmNzc="),
	GOOGLE_DIRECTIONS_APIKEY("QUl6YVN5RGwwdUhRME1KczlCdHVtRjdRR1hBNGlGVEJmallEY3hV");
	
	private String value;
	
	public String getValue() {
		return this.value;
	}
	
	private KeysUtils(String value) {
		this.value = value;
	}
}
