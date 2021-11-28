package com.fiap.telegrambot.util.handler;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.telegram.telegrambots.api.objects.Update;

import com.fiap.telegrambot.core.TelegramBotCore;
import com.fiap.telegrambot.util.ParsedCommand;
import com.vdurmont.emoji.Emoji;
import com.vdurmont.emoji.EmojiManager;
import com.vdurmont.emoji.EmojiParser;

public class EmojiHandler extends AbstractHandler {

	private static final Logger log = Logger.getLogger(EmojiHandler.class);
	
	public EmojiHandler(TelegramBotCore bot) {
		super(bot);
	}
	
	 @Override
	    public String operate(String chatId, ParsedCommand parsedCommand, Update update) {
	        String text = parsedCommand.getText();
	        StringBuilder result = new StringBuilder();
	        Set<String> emojisInTextUnique = new HashSet<>(EmojiParser.extractEmojis(text));
	        if (emojisInTextUnique.size() > 0) result.append("Parsed emojies from message:").append("\n");
	        for (String emojiUnicode : emojisInTextUnique) {
	            Emoji byUnicode = EmojiManager.getByUnicode(emojiUnicode);
	            log.debug(byUnicode.toString());
	            String emoji = byUnicode.getUnicode() + " " +
	                    byUnicode.getAliases() +
	                    " " + byUnicode.getDescription();
	            result.append(emoji).append("\n");
	        }
	        return result.toString();
	    }
}
