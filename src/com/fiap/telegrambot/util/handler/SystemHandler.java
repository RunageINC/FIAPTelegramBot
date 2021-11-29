package com.fiap.telegrambot.util.handler;

import java.io.IOException;
import java.util.Set;

import org.apache.log4j.Logger;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;

import com.fiap.telegrambot.core.TelegramBotCore;
import com.fiap.telegrambot.util.Command;
import com.fiap.telegrambot.util.MessagesEnum;
import com.fiap.telegrambot.util.MessagesUtil;
import com.fiap.telegrambot.util.ParsedCommand;

public class SystemHandler extends AbstractHandler {

	private static final Logger log = Logger.getLogger(SystemHandler.class);
	private final String END_LINE = "\n";
	
	public SystemHandler(TelegramBotCore bot) {
		super(bot);
	}

	 @Override
	    public String operate(String chatId, ParsedCommand parsedCommand, Update update) {
	        Command command = parsedCommand.getCommand();

	        switch (command) {
	            case START:
	                bot.sendQueue.add(getMessageStart(chatId));
	                break;
	            case HELP:
	                bot.sendQueue.add(getMessageHelp(chatId));
	                break;
	            case ID:
	                return MessagesEnum.TELEGRAM_ID_MESSAGE.getValue() + update.getMessage().getFrom().getId();
	            case STICKER:
	                return "StickerID: " + parsedCommand.getText();
	        }
	        return "";
	    }

	    private SendMessage getMessageHelp(String chatID) {
	        SendMessage sendMessage = new SendMessage();
	        sendMessage.setChatId(chatID);
	        sendMessage.enableMarkdown(true);
	        
	        try {
				Set<String> messages = MessagesUtil.getMessages();
				
				StringBuilder text = new StringBuilder();
		        text.append(MessagesEnum.ENTRY_MESSAGE.getValue()).append(END_LINE).append(END_LINE);
		        
		        for (String command : messages) {
		        	text.append(command).append(END_LINE);
		        }

		        sendMessage.setText(text.toString());
		        return sendMessage;

			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
	    }

	    private SendMessage getMessageStart(String chatID) {
	        SendMessage sendMessage = new SendMessage();
	        sendMessage.setChatId(chatID);
	        sendMessage.enableMarkdown(true);
	        StringBuilder text = new StringBuilder();
	        text.append("Ola. Eu sou  *").append(bot.getBotName()).append("*").append(END_LINE);
	        text.append("Eu fui criado pelo time 1 da FIAP").append(END_LINE);
	        text.append("Tudo que eu posso fazer você pode descobrir digitando o comando [/help](/help)");
	        sendMessage.setText(text.toString());
	        return sendMessage;
	    }

}
