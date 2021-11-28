/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.telegrambot.handler;

import com.example.telegrambot.bot.Bot;
import com.example.telegrambot.cep.CepUtils;
import static com.example.telegrambot.cep.CepUtils.fillCEP;
import com.example.telegrambot.cep.Webservicecep;
import com.example.telegrambot.command.ParsedCommand;
import java.net.MalformedURLException;
import java.util.logging.Level;
import javax.xml.bind.JAXBException;
import org.apache.log4j.Logger;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.methods.send.SendMessage;

/**
 *
 * @author lugamvazori
 */
public class CEPHandler extends AbstractHandler {

    private static final Logger log = Logger.getLogger(CEPHandler.class);
    private String WRONG_INPUT_MESSAGE = "CEP invalido. ";

    public CEPHandler(Bot bot) {
        super(bot);
    }

    @Override
    public String operate(String chatId, ParsedCommand parsedCommand, Update update) {
        String text = parsedCommand.getText();
        if ("".equals(text)) {
            return "You must specify the delay time. Like this:\n"
                    + "/cep 05629040";
        }
//        long timeInSec;
        String cepValue;

        if (text.length() > 8 || text.length() < 8) {
            return WRONG_INPUT_MESSAGE;
        }
        if (text.contains("^[a-Z]")) {
            return WRONG_INPUT_MESSAGE;
        }
        

        cepValue = text.trim();
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
            java.util.logging.Logger.getLogger(CEPHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            java.util.logging.Logger.getLogger(CEPHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
