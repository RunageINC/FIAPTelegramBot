package com.fiap.telegrambot.util.handler;

import static com.fiap.telegrambot.util.CepUtils.fillCEP;

import java.net.MalformedURLException;
import java.util.logging.Level;

import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;

import com.fiap.telegrambot.core.TelegramBotCore;
import com.fiap.telegrambot.service.Webservicecep;
import com.fiap.telegrambot.util.CepUtils;
import com.fiap.telegrambot.util.MessagesEnum;
import com.fiap.telegrambot.util.ParsedCommand;

public class CepHandler extends AbstractHandler {

    private static final Logger log = Logger.getLogger(CepHandler.class);
    private String WRONG_INPUT_MESSAGE = "CEP invalido. ";

    public CepHandler(TelegramBotCore bot) {
        super(bot);
    }

    @Override
    public String operate(String chatId, ParsedCommand command, Update update) {
        String cepValue = command.getText();
        if ("".equals(cepValue)) {
            return MessagesEnum.CEP_ERROR_MESSAGE.getValue();
        }
        
        if (cepValue.contains("-"))
        	cepValue = cepValue.replace("-", ""); 

        if (cepValue.length() != 8) {
            return WRONG_INPUT_MESSAGE;
        }
        if (cepValue.contains("^[a-Z]")) {
            return WRONG_INPUT_MESSAGE;
        }

        if (cepValue != null && !cepValue.isEmpty()) {
            bot.sendQueue.add(getMessageCEP(chatId, cepValue));
        }
        return "";
    }

    private SendMessage getMessageCEP(String chatID, String cepValue) {
        try {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatID);
            sendMessage.enableMarkdown(true);

            Webservicecep result = CepUtils.getEndereco(cepValue.replace("-", ""));
            StringBuilder sb = fillCEP(result);

            sendMessage.setText(sb.toString());
            return sendMessage;
        } catch (JAXBException ex) {
            java.util.logging.Logger.getLogger(CepHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            java.util.logging.Logger.getLogger(CepHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
