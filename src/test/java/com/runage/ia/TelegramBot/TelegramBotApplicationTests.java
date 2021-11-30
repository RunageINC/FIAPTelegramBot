package com.runage.ia.TelegramBot;

import com.runage.ia.telegrambot.dto.CepDTO;
import com.runage.ia.telegrambot.dto.google.GoogleTravelRouteDTO;
import com.runage.ia.telegrambot.dto.weather.WeatherDTO;
import com.runage.ia.telegrambot.utils.Cryptography;
import com.runage.ia.telegrambot.utils.ExternalConnections;
import com.runage.ia.telegrambot.utils.KeysUtils;
import com.runage.ia.telegrambot.utils.URLUtils;

class TelegramBotApplicationTests {

//	public static void main(String[] args) {
//		System.out.println(getCEP());
//		System.out.println(getWeather());
//		System.out.println(getGoogleAPI());
//	}
//	
//	private static String getCEP() {
//		String path = "http://cep.republicavirtual.com.br/web_cep.php?cep=03694000&formato=json";
//		ExternalConnections con = new ExternalConnections(path);
//		CepDTO dto = con.get(CepDTO.class);
//		
//		return dto.getFormattedCEP();
//	}
//	
//	private static String getWeather() {
//		String path2 = String.format(URLUtils.WEATHER_TODAY.getPath(), Cryptography.decodeBase64(KeysUtils.WEATHER_APIKEY.getValue()));
//		ExternalConnections con = new ExternalConnections(path2);
//		con = new ExternalConnections(path2);
//		WeatherDTO res = con.getHttps(WeatherDTO.class);
//		
//		return res.toString();
//	}
//	
//	private static String getGoogleAPI() {
//		String origin = "Av. Aguia de Haia, 2255";
//		String destination = "Av. Paulista, 1106";
//		ExternalConnections connections = new ExternalConnections(String.format(URLUtils.GOOGLE_DIRECTIONS.getPath(),
//				origin.replaceAll(" ", "+"), destination.replaceAll(" ", "+"), Cryptography.decodeBase64(KeysUtils.GOOGLE_DIRECTIONS_APIKEY.getValue())));
//		
//		GoogleTravelRouteDTO routes = connections.getHttps(GoogleTravelRouteDTO.class);
//		
//		return routes.returnResponse();
//	}

}
