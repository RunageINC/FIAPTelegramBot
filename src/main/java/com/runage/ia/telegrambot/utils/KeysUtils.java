package com.runage.ia.telegrambot.utils;

public enum KeysUtils {

	TELEGRAM_BOTNAME("Runage Java Bot"),
	TELEGRAM_APIKEY("2122859953:AAEv7qQqxlvjmhZW4dlyj4dHlV_Dy89ATmQ"),
//	WEATHER_APIKEY("490d5f77"),
//	GOOGLE_DIRECTIONS_APIKEY("AIzaSyDl0uHQ0MJs9BtumF7QGXA4iFTBfjYDcxU");
	
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
