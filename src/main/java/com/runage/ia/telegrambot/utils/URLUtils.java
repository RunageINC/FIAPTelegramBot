package com.runage.ia.telegrambot.utils;

public enum URLUtils {

	CEP("http://cep.republicavirtual.com.br/web_cep.php?cep=%s&formato=json"),
	WEATHER_TODAY("https://api.hgbrasil.com/weather?key=%s&city_name=São Paulo,SP"),
	GOOGLE_DIRECTIONS("https://maps.googleapis.com/maps/api/directions/json?origin=%s&destination=%s&key=%s");
	
	private String path;
	
	public String getPath() {
		return this.path;
	}
	
	private URLUtils(String path) {
		this.path = path;
	}
}
