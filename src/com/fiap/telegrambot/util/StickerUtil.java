package com.fiap.telegrambot.util;

public enum StickerUtil {

	FUNNY_JIM_CARREY("CAADBQADiQMAAukKyAPZH7wCI2BwFxYE");
	
	String stickerId;
	
	StickerUtil(String stickerId) {
		this.stickerId = stickerId;
	}
	
//	public SendSticker getSendSticker(String chatId) {
//		if ("".equals(chatId)) throw new IllegalArgumentException("ChatID cannot be null");
//		SendSticker sendSticker = getSendSticker();
//		sendSticker.setChatId(chatId);
//		return sendSticker;
//	}
//	
//	public SendSticker getSendSticker() {
//		SendSticker sendSticker = new SendSticker();
//		sendSticker.setSticker(stickerId);
//		return sendSticker;
//	}
}
