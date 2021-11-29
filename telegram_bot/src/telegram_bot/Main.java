package telegram_bot;

import cep.utils.CepUtils;
import static cep.utils.CepUtils.fillCEP;
import cep.webservice.Webservicecep;
import java.util.List;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;

public class Main {

    private static final String BREAK_ROW = "\r\n";

    public static void main(String[] args) {
        // Criacao do objeto bot com as informacoes de acesso.
        TelegramBot bot = new TelegramBot("2117476942:AAG6Tg1IbPqbwzPoY9m85_QZrdjvpA0hteg");
//        TelegramBot bot = new TelegramBot("2141629636:AAH_sWwEjT83jSlpFH5O2_5HAym0h7PMchc"); //Velho

        // Objeto responsavel por receber as mensagens.
        GetUpdatesResponse updatesResponse;

        // Objeto responsavel por gerenciar o envio de respostas.
        SendResponse sendResponse;

        // Objeto responsavel por gerenciar o envio de acoes do chat.
        BaseResponse baseResponse;

        // Controle de off-set, isto e, a partir deste ID sera lido as mensagens
        // pendentes na fila.
        int m = 0;

        // Loop infinito pode ser alterado por algum timer de intervalo curto.
        while (true) {
            // Executa comando no Telegram para obter as mensagens pendentes a partir de um
            // off-set (limite inicial).
            updatesResponse = bot.execute(new GetUpdates().limit(100).offset(m));

            // Lista de mensagens.
            List<Update> updates = updatesResponse.updates();

            // Analise de cada acao da mensagem.
            for (Update update : updates) {

                // Atualizacao do off-set.
                m = update.updateId() + 1;

                System.out.println("Recebendo mensagem: " + update.message().text());

                // Envio de "Escrevendo" antes de enviar a resposta.
                baseResponse = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));

                // Verificacao de acao de chat foi enviada com sucesso.
                System.out.println("Resposta de Chat Action Enviada? " + baseResponse.isOk());

                // Envio da mensagem de resposta.
                //sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "N�o entendi..."));
                // Verificacao de mensagem enviada com sucesso
                //System.out.println("Mensagem Enviada? " + sendResponse.isOk());
                switch (update.message().text()) {
                    case "/busca_cep":
                        sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "Por favor informe o CEP"));
                        break;
                }
                if (update.message().text() != null) {
//                    if (update.message().text().equals("/busca_cep")) {
                    try {
                        Webservicecep result = CepUtils.getEndereco(update.message().text().replace("-", ""));
                        StringBuilder sb = fillCEP(result);
                        sendResponse = bot.execute(new SendMessage(update.message().chat().id(), sb.toString()));

                        // Verificacao de mensagem enviada com sucesso.
                        System.out.println("Mensagem Enviada? " + sendResponse.isOk());
                        sb = null;
                    } catch (JAXBException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (MalformedURLException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
//                    }
                }

                // -Rastreio de encomendas
//-Clima/Tempo
//-Piada (Talvez)
//-Cotação de moeda e crypto
//-Músicas
//-Memes
//-Noticias
//-Busca de Endereço por CEP
            }
        }
    }

}
