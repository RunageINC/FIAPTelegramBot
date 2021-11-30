package com.runage.ia.telegrambot.dto.google;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GoogleDirectionsDistanceDTO {

	@JsonProperty
	private GoogleDirectionsLegsDTO[] legs;
	
	@JsonProperty
	private String copyrights;
}
