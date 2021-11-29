package com.fiap.telegrambot.util.handler;

import org.apache.log4j.Logger;
import org.telegram.telegrambots.api.objects.Update;

import com.fiap.telegrambot.core.Notify;
import com.fiap.telegrambot.core.TelegramBotCore;
import com.fiap.telegrambot.util.MessagesEnum;
import com.fiap.telegrambot.util.ParsedCommand;

public class NotifyHandler extends AbstractHandler {

	private static final Logger log = Logger.getLogger(NotifyHandler.class);
	private final int MILLISEC_IN_SEC = 1000;

	public NotifyHandler(TelegramBotCore bot) {
		super(bot);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String operate(String chatId, ParsedCommand command, Update update) {
		String text = command.getText();
		if ("".equals(text))
			return MessagesEnum.NOTIFY_ERROR_MESSAGE.getValue();
		long timeInSec;
		try {
			timeInSec = Long.parseLong(text.trim());
		} catch (NumberFormatException e) {
			return MessagesEnum.NOTIFY_ERROR_MESSAGE.getValue();
		}
		if (timeInSec > 0) {
			Thread thread = new Thread(new Notify(bot, chatId, timeInSec * MILLISEC_IN_SEC));
			thread.start();
		} else
			return MessagesEnum.NOTIFY_ERROR_MESSAGE.getValue();
		return "";
	}
}
