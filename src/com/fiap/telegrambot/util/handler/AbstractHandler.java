package com.fiap.telegrambot.util.handler;

import org.telegram.telegrambots.api.objects.Update;

import com.fiap.telegrambot.core.TelegramBotCore;
import com.fiap.telegrambot.util.ParsedCommand;

public abstract class AbstractHandler {

	TelegramBotCore bot;
	
	public AbstractHandler(TelegramBotCore bot) {
		this.bot = bot;
	}
	
	public abstract String operate(String chatId, ParsedCommand command, Update update);
}
