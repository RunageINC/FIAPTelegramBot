package com.runage.ia.telegrambot.core;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.runage.ia.telegrambot.utils.KeysUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RunageBot extends TelegramLongPollingBot {

	final RunageBotActionControl brain = new RunageBotActionControl();
	
	@Override
	public void onUpdateReceived(Update update) {
		if (update.hasMessage() && update.getMessage().hasText()) {
			SendMessage message = brain.decide(update);
			
			try {
				execute(message);
			} catch (TelegramApiException e) {
				log.error("Error on Telegram Bot: {}", e);
			}
		}
	}
	
	@Override
	public String getBotUsername() {
		return KeysUtils.TELEGRAM_BOTNAME.getValue();
	}
	
	@Override
	public String getBotToken() {
		return KeysUtils.TELEGRAM_APIKEY.getValue();
	}
}
