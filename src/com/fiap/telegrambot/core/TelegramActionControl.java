package com.fiap.telegrambot.core;

import java.util.List;


import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;

public class TelegramActionControl {

	
	
	private TelegramBotCore core;
	private GetUpdatesResponse updatesResponse;
	private SendResponse sendResponse;
	private BaseResponse baseResponse;
	private int offset;
	private static int messageLimit = 100;
	
	public TelegramActionControl(String apiToken) {
		core = new TelegramBotCore(apiToken);
	}
	
	public void run() {
		while(true) {
			updatesResponse = core.executeMessageGathering(messageLimit);
			
			for (Update update : getMessageList(updatesResponse)) {
				core.addToOffset(update.updateId() + 1);
				
				baseResponse = core.executeResponse(update);
				System.out.println("Response base stat ok: " + baseResponse.isOk());
				
				sendResponse = core.sendMessage(update, "Hello");
				System.out.println("Response send stat ok: " + sendResponse.isOk());
			}
		}
	}
	
	public List<Update> getMessageList(GetUpdatesResponse updatesResponse) {
		return updatesResponse.updates();
	}
}
