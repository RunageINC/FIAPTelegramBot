package com.fiap.telegrambot;

import com.fiap.telegrambot.core.TelegramActionControl;
import com.fiap.telegrambot.util.EnvironmentUtil;

public class TelegramBotApplication {

	public static void main(String[] args) {
		TelegramActionControl tac = new TelegramActionControl(EnvironmentUtil.getToken());
		
		tac.run();
	}
}
