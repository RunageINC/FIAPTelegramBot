package com.runage.ia.telegrambot.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import com.runage.ia.telegrambot.dto.CepDTO;
import com.runage.ia.telegrambot.dto.google.GoogleTravelRouteDTO;
import com.runage.ia.telegrambot.dto.weather.WeatherDTO;
import com.runage.ia.telegrambot.utils.BotOptions;
import com.runage.ia.telegrambot.utils.Cryptography;
import com.runage.ia.telegrambot.utils.ExternalConnections;
import com.runage.ia.telegrambot.utils.FileManagingUtils;
import com.runage.ia.telegrambot.utils.KeysUtils;
import com.runage.ia.telegrambot.utils.States;
import com.runage.ia.telegrambot.utils.URLUtils;

public class RunageBotActionControl {

	private States state = States.EMPTY;
	
	private String origin = ""; 
	private String destination = "";

	public SendMessage decide(Update update) {
		long chatId = update.getMessage().getChatId();
		String userText = update.getMessage().getText();

		SendMessage message = SendMessage.builder().chatId(Long.toString(chatId)).text(interpret(userText, chatId))
				.build();

		ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();

		List<KeyboardRow> keyboard = new ArrayList<>();

		KeyboardRow row = new KeyboardRow();

		row.add(BotOptions.CUMPLIMENT.getOption());
		row.add(BotOptions.CEP.getOption());
		row.add(BotOptions.HOW_ARE_YOU.getOption());

		keyboard.add(row);

		row = new KeyboardRow();

		row.add(BotOptions.WEATHER.getOption());
		row.add(BotOptions.HELP.getOption());
		row.add(BotOptions.DIRECTIONS.getOption());

		keyboard.add(row);

		keyboardMarkup.setKeyboard(keyboard);

		message.setReplyMarkup(keyboardMarkup);

		return message;
	}

	public String cepInteraction(String message) {
		message = message.replace("-", "");

		if (message.length() > 8 || message.length() < 8)
			return BotOptions.SMALL_CEP.getOption();
		if (message.contains("^[a-Z]"))
			return BotOptions.LETTER_CEP.getOption();

		ExternalConnections connections = new ExternalConnections(String.format(URLUtils.CEP.getPath(), message));

		CepDTO cepDTO = connections.get(CepDTO.class);

		state = States.ENTRY;

		return "Aqui estão as informações sobre seu endereço: \n" + cepDTO.getFormattedCEP()
				+ "\nComo posso continuar ajudando?";
	}

	public String weatherInteraction() {
		ExternalConnections connections = new ExternalConnections(
				String.format(URLUtils.WEATHER_TODAY.getPath(), Cryptography.decodeBase64(KeysUtils.WEATHER_APIKEY.getValue())));

		WeatherDTO weatherDTO = connections.getHttps(WeatherDTO.class);

		return weatherDTO.returnResults();
	}

	public String getDirections(String origin, String destination) {
		System.out.println(origin + " " + destination);
		ExternalConnections connections = new ExternalConnections(String.format(URLUtils.GOOGLE_DIRECTIONS.getPath(),
				origin.replaceAll(" ", "+"), destination.replaceAll(" ", "+"), Cryptography.decodeBase64(KeysUtils.GOOGLE_DIRECTIONS_APIKEY.getValue())));
		
		GoogleTravelRouteDTO routes = connections.getHttps(GoogleTravelRouteDTO.class);
		
		cleanOriginAndDestination();
		
		state = States.ENTRY;
		
		return routes.returnResponse();
	}
	
	private void cleanOriginAndDestination() {
		this.origin = "";
		this.destination = "";
	}

	public String help() {
		StringBuilder sb = new StringBuilder();

		sb.append("Você pode executar os meus comandos através do teclado do Telegram ou digitando os comandos abaixo:")
				.append("\n\n").append("/greet - Envio uma mensagem de oi em uma língua diferente.").append("\n")
				.append("/cep - Busco informações sobre seu endereço.").append("\n")
				.append("/howareyou - Mostro uma pequena mensagem de como está meu dia.").append("\n")
				.append("/weather - Digo como está o clima hoje.").append("\n")
				.append("/directions - Digo quanto tempo você irá demorar para ir de um ponto ao outro, levando em conta o trânsito.");

		return sb.toString();
	}

	public String interpret(String message, long chatId) {
		if (state == States.EMPTY) {
			if (message.toLowerCase().contains("adeus") || message.toLowerCase().contains("até logo")
					|| message.toLowerCase().contains("tchau") || message.toLowerCase().contains("fui"))
				return "Mal começamos a nossa interação e você já quer se despedir? Hahaha :)";

			state = States.ENTRY;

			if (!FileManagingUtils.hasFile(Long.toString(chatId))) {
				return BotOptions.WELCOME.getOption();
			} else {
				return BotOptions.WELCOME_BACK.getOption();
			}
		} else if (state == States.ENTRY) {
			if (message.toLowerCase().equals("oi") || message.toLowerCase().contains("ola")
					|| message.toLowerCase().equals("/start") || message.toLowerCase().contains("opa")) {
				return BotOptions.FORCE_OPTION.getOption();
			} else if (message.equals(BotOptions.CUMPLIMENT.getOption()) || message.equals("/greet")) {
				String[] greet = FileManagingUtils.readGreets();
				int randomGreet = new Random().nextInt(greet.length);

				return greet[randomGreet];
			} else if (message.equals(BotOptions.CEP.getOption()) || message.toLowerCase().equals("/cep")) {
				state = States.CEP;

				return BotOptions.CEP_INSTRUCTION.getOption();
			} else if (message.toLowerCase().contains("adeus") || message.toLowerCase().contains("até logo")
					|| message.toLowerCase().contains("tchau") || message.toLowerCase().contains("fui")) {
				state = States.EMPTY;
				return BotOptions.GOODBYE.getOption();
			} else if (message.equals(BotOptions.HOW_ARE_YOU.getOption())
					|| message.toLowerCase().equals("/howareyou")) {
				return BotOptions.GOOD_DAY.getOption();
			} else if (message.equals(BotOptions.WEATHER.getOption()) || message.toLowerCase().equals("/weather")) {
				return weatherInteraction();
			} else if (message.equals(BotOptions.HELP.getOption()) || message.toLowerCase().equals("/help")) {
				return help();
			} else if (message.equals(BotOptions.DIRECTIONS.getOption())
					|| message.toLowerCase().equals("/directions")) {
				state = States.ORIGIN;
				return BotOptions.ORIGINS_INSTRUCTION.getOption();
			}
		} else if (state == States.CEP) {
			return cepInteraction(message);
		} else if (state == States.ORIGIN) {
			state = States.DESTINATION;
			origin = message;

			return BotOptions.DESTINATION_INSTRUCTION.getOption();
		} else if (state == States.DESTINATION) {
			destination = message;

			return getDirections(origin, destination);
		}

		return "Desculpe, não entendi... Poderia repetir?";
	}
}
