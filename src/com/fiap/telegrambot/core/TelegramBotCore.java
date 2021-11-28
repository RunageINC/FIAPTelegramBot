package com.fiap.telegrambot.core;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

import org.apache.log4j.Logger;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class TelegramBotCore extends TelegramLongPollingBot {

	private static final Logger log = Logger.getLogger(TelegramBotCore.class);
	private final int RECONNECT_PAUSE = 10000;

	@Setter @Getter
	private String botName;
	
	@Setter
	private String botToken;
	
	public final Queue<Object> sendQueue = new ConcurrentLinkedDeque<>();
	public final Queue<Object> receiveQueue = new ConcurrentLinkedDeque<>();
	
	public TelegramBotCore(String botName, String botToken) {
		this.botName = botName;
		this.botToken = botToken;
	}
	
	public void onUpdateReceived(Update update) {
		log.debug("New update - " + update.getUpdateId());
		receiveQueue.add(update);
	}

	@Override
	public String getBotUsername() {
		log.debug("Bot name: " + botName);
		return null;
	}

	@Override
	public String getBotToken() {
		log.debug("Bot token: " + botToken);
		return null;
	}
	
	public void connect() {
		final TelegramBotsApi api;
		
		try {
			api = new TelegramBotsApi();
			api.registerBot(this);
			log.info("Started Telegram API. Bot Connected: " + this);
		} catch (TelegramApiException e) {
			log.error("Error while connecting. Pause of : " + RECONNECT_PAUSE / 1000 + " second and then trying. Error: " + e.getMessage());
			
			try {
				Thread.sleep(RECONNECT_PAUSE);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
				return;
			}
			
			connect();
		}
	}
}
