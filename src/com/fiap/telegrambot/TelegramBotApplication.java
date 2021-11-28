package com.fiap.telegrambot;

import org.apache.log4j.Logger;
import org.telegram.telegrambots.api.methods.send.SendMessage;

import com.fiap.telegrambot.core.TelegramBotCore;
import com.fiap.telegrambot.service.MessageReceiver;
import com.fiap.telegrambot.service.MessageSender;

public class TelegramBotApplication {

	private static final Logger log = Logger.getLogger(TelegramBotApplication.class);
	private static final int PRIORITY_FOR_SENDER = 1;
	private static final int PRIORITY_FOR_RECEIVER = 3;
	private static final String BOT_ADMIN = "321644283";

	public static void main(String[] args) {

		TelegramBotCore core = new TelegramBotCore("R2D2", "2117476942:AAG6Tg1IbPqbwzPoY9m85_QZrdjvpA0hteg");

		MessageReceiver messageReceiver = new MessageReceiver(core);
		MessageSender messageSender = new MessageSender(core);

		core.connect();

		Thread receiver = new Thread(messageReceiver);
		receiver.setDaemon(true);
		receiver.setName("TelegramBotMessageReceiver");
		receiver.setPriority(PRIORITY_FOR_RECEIVER);
		receiver.start();

		Thread sender = new Thread(messageSender);
		sender.setDaemon(true);
		sender.setName("TelegramBotMessageSender");
		sender.setPriority(PRIORITY_FOR_SENDER);
		sender.start();
		
		sendStartReport(core);
	}
	
	private static void sendStartReport(TelegramBotCore core) {
		SendMessage message = new SendMessage();
		message.setChatId(BOT_ADMIN);
		message.setText("Bot Started");
		core.sendQueue.add(message);
	}
}
