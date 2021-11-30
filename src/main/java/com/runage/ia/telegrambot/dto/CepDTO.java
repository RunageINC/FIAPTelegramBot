package com.runage.ia.telegrambot.dto;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class CepDTO {

	@JsonValue
	private String uf;
	
	@JsonValue
	private String cidade;
	
	@JsonValue
	private String bairro;
	
	@JsonValue
	private String tipo_logradouro;
	
	@JsonValue
	private String logradouro;
	
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private final String BREAK_ROW = "\n";
	
	public String getFormattedCEP() {
		StringBuilder sb = new StringBuilder();
		
		if (this != null) {
			sb.append("Estado: ").append(this.uf);
			sb.append(BREAK_ROW);
			sb.append("Cidade: ").append(this.cidade);
			sb.append(BREAK_ROW);
			sb.append("Bairro: ").append(this.bairro);
			sb.append(BREAK_ROW);
			sb.append("Logradouro: ").append(this.tipo_logradouro).append(" ").append(this.logradouro);
			sb.append(BREAK_ROW);
		}
		
		return sb.toString();
	}
}
