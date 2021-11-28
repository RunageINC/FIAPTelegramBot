package com.fiap.telegrambot.util;

public enum ResourcesUtil {

	RESOURCES_PATH("res/"),
	TEXT_FILENAME("messages.txt"),
	TOKEN("2117476942:AAG6Tg1IbPqbwzPoY9m85_QZrdjvpA0hteg");
	
	private String value;
	
	public String getValue() {
		return this.value;
	}
	
	private ResourcesUtil(String value) {
		this.value = value;
	}
}
