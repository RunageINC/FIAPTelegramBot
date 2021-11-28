package com.example.telegrambot.handler;

import com.example.telegrambot.bot.Bot;
import com.example.telegrambot.command.Command;
import com.example.telegrambot.command.ParsedCommand;
import org.apache.log4j.Logger;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;

public class SystemHandler extends AbstractHandler {
    private static final Logger log = Logger.getLogger(SystemHandler.class);
    private final String END_LINE = "\n";

    public SystemHandler(Bot bot) {
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
                return "Seu telegramID: " + update.getMessage().getFrom().getId();
            case STICKER:
                return "StickerID: " + parsedCommand.getText();
        }
        return "";
    }

    private SendMessage getMessageHelp(String chatID) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatID);
        sendMessage.enableMarkdown(true);

        StringBuilder text = new StringBuilder();
        text.append("*This is help message*").append(END_LINE).append(END_LINE);
        text.append("[/start](/start) - Mostra mensagem de início").append(END_LINE);
        text.append("[/help](/help) - Mostra mensagem de Ajuda").append(END_LINE);
        text.append("[/id](/id) - Retorna seu ID do telegram ").append(END_LINE);
        text.append("/*notify* _time-in-sec_  - Recebe uma notificação minha após o tempo específico informado").append(END_LINE);
        text.append("/*cep* _codigo-postal-string_  - consulta informações sobre o CEP informado").append(END_LINE);

        sendMessage.setText(text.toString());
        return sendMessage;
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
