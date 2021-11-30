package com.runage.ia.telegrambot.utils;

public enum BotOptions {

	WELCOME("Olá, como posso ajudar?"),
	WELCOME_BACK("Bem vindo de volta! O que deseja?"),
	CUMPLIMENT("Me cumprimente em alguma língua!"),
	HELP("O que você pode fazer?"),
	CEP("Informações do meu CEP"),
	HOW_ARE_YOU("Como foi seu dia?"),
	WEATHER("Como está o tempo hoje?"),
	DIRECTIONS("Direções para meu destino"),
	GOODBYE("Foi muito legal conversar com você, até mais! :)"),
	GOOD_DAY("Meu dia é sempre muito bom, afinal sou muito bem programado pela minha equipe! \nEspero que seu dia esteja indo muito bem ^^"),
	
	SMALL_CEP("Seu CEP é menor do que o esperado. Por favor, insira um CEP válido, de 8 caractéres!"),
	LETTER_CEP("CEP não contém letras, por favor insira um CEP válido!"),
	FORCE_OPTION("Acho que já nos introduzimos né? Por favor escolha uma opção!"),
	CEP_INSTRUCTION("Digite o seu CEP no seguinte formato: 99999-999"),
	ORIGINS_INSTRUCTION("Digite sua origem. Por ex: Av. Xpto, 25."),
	DESTINATION_INSTRUCTION("Agora por favor insira seu destino, no mesmo formato.");
	
	
	private String option;
	
	public String getOption() {
		return this.option;
	}
	
	private BotOptions(String option) {
		this.option = option;
	}
}
