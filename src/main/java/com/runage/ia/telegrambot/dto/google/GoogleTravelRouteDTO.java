package com.runage.ia.telegrambot.dto.google;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class GoogleTravelRouteDTO {

	@JsonProperty("routes")
	private GoogleDirectionsDistanceDTO[] routes;
	
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private final String BREAK_ROW = "\n";
	
	public String returnResponse() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("De acordo com os cálculos, sua rota é: ").append(BREAK_ROW)
		.append("Origem: ").append(this.routes[0].getLegs()[0].getStartAddress()).append(BREAK_ROW)
		.append("Destino: ").append(this.routes[0].getLegs()[0].getEndAddress()).append(BREAK_ROW)
		.append("Distância: ").append(this.routes[0].getLegs()[0].getDistance().getText()).append(BREAK_ROW)
		.append("Tempo de Chegada: ").append(this.routes[0].getLegs()[0].getDuration().getText()).append(BREAK_ROW).append(BREAK_ROW)
		.append("Construído com a ajuda de Google Maps - ").append(this.routes[0].getCopyrights());
		
		return sb.toString();
	}
}
