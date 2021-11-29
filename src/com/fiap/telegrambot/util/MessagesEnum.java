package com.fiap.telegrambot.util;

public enum MessagesEnum {
	
	TELEGRAM_ID_MESSAGE("Seu Telegram ID: "),
	NOTIFY_ERROR_MESSAGE("Você precisa especificar o tempo de espera, como por exemplo: /notify 30"),
	CEP_ERROR_MESSAGE("Você precisa especificar o CEP no seguinte formato: /cep 05629040"),
	EMOJI_MESSAGE("Traduzindo emoji para mensagens: "),
	ENTRY_MESSAGE("Abaixo tenho uma lista de comandos que posso executar: ");
	
	private String value;
	
	public String getValue() {
		return this.value;
	}
	
	private MessagesEnum(String value) {
		this.value = value;
	}
}
