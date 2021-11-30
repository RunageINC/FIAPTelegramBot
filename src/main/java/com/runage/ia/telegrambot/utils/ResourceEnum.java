package com.runage.ia.telegrambot.utils;

public enum ResourceEnum {

	RESOURCE_PATH("src/main/java/com/runage/ia/telegrambot/files/"),
	GREET_FILE("greet.txt");
	
	private String value;
	
	public String getValue() {
		return this.value;
	}
	
	private ResourceEnum(String value) {
		this.value = value;
	}
}
