package com.runage.ia.telegrambot.dto.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class WeatherDTO {

	@JsonProperty
	private ResultsDTO results;

	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private final String BREAK_ROW = "\n";

	public String returnResults() {
		StringBuilder sb = new StringBuilder();

		sb.append("A previsão para São Paulo hoje é: ").append(BREAK_ROW)
		.append("Temperatura: ").append(results.getTemp())
		.append("°C").append(BREAK_ROW)
		.append("Data da Previsão: ").append(results.getTime()).append(" ").append(results.getDate()).append(BREAK_ROW)
		.append("Clima: ").append(results.getDescription()).append(BREAK_ROW)
		.append("Velocidade do Vento: ").append(results.getWindSpeed()).append(BREAK_ROW)
		.append("Humidade: ").append(results.getHumidity())	.append("%").append(BREAK_ROW)
		.append("Para os próximos dias: ").append(BREAK_ROW).append(BREAK_ROW);

		for (int i = 0; i < results.getForecast().length - 3; i++) {
			sb.append(results.getForecast()[i].getWeekday()).append(" ")
			.append(results.getForecast()[i].getDate()).append(BREAK_ROW).append("Mínima: ")
			.append(results.getForecast()[i].getMin()).append("°C").append(BREAK_ROW).append("Máxima: ")
			.append(results.getForecast()[i].getMax()).append("°C").append(BREAK_ROW).append("Clima: ")
			.append(results.getForecast()[i].getDescription()).append(BREAK_ROW).append("----------------")
			.append(BREAK_ROW);
		}

		return sb.toString();
	}
}
