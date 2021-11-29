package com.fiap.telegrambot.util.handler;

import org.apache.log4j.Logger;
import org.telegram.telegrambots.api.objects.Update;

import com.fiap.telegrambot.core.TelegramBotCore;
import com.fiap.telegrambot.util.ParsedCommand;

public class DefaultHandler extends AbstractHandler {

	private static final Logger log = Logger.getLogger(DefaultHandler.class);
	
	public DefaultHandler(TelegramBotCore bot) {
		super(bot);
	}
	
	@Override
	public String operate(String chatId, ParsedCommand command, Update update) {
		return "";
	}
}
