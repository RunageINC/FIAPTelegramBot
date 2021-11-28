package com.fiap.telegrambot.core;

import org.apache.log4j.Logger;
import org.telegram.telegrambots.api.methods.send.SendMessage;

import lombok.ToString;

@ToString
public class Notify implements Runnable {
	
	private static final Logger log = Logger.getLogger(Notify.class);
	private static final int MILLISEC_IN_SEC = 1000;
	
	TelegramBotCore bot;
	long delayInMillisec;
	String chatId;
	
	public Notify(TelegramBotCore bot, String chatId, long delayInMillisec) {
		this.bot = bot;
		this.chatId = chatId;
		this.delayInMillisec = delayInMillisec;
		
		log.debug("CREATED: " + toString());
	}
	
	@Override
	public void run() {
		log.info("Running: " + toString());
		
		bot.sendQueue.add(getFirstMessage());
		try {
			Thread.sleep(delayInMillisec);
		} catch (InterruptedException e) {
			log.error(e.getMessage(), e);
		}
		
		log.info("Finished - " + toString());
	}
	
	private SendMessage getFirstMessage() {
		return new SendMessage(chatId, "Notify after " + delayInMillisec / MILLISEC_IN_SEC + " seconds.");
	}
	
	private SendMessage getSecondMessage() {
		return new SendMessage(chatId, "This is notify message. Thanks for using :)");
	}
}
