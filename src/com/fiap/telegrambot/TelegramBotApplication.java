package com.fiap.telegrambot;

import com.fiap.telegrambot.core.TelegramActionControl;

public class TelegramBotApplication {

	public static void main(String[] args) {
		TelegramActionControl tac = new TelegramActionControl("2122859953:AAEv7qQqxlvjmhZW4dlyj4dHlV_Dy89ATmQ");
		
		tac.run();
	}
}
