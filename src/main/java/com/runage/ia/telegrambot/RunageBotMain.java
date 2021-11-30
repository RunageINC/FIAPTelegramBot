package com.runage.ia.telegrambot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import com.runage.ia.telegrambot.core.RunageBot;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RunageBotMain {

	public static void main(String[] args) {
		try {
			TelegramBotsApi botApi = new TelegramBotsApi(DefaultBotSession.class);
			botApi.registerBot(new RunageBot());
		} catch (TelegramApiException e) {
			log.error("Something went wrong when creating pooling", e);
		}
	}
}