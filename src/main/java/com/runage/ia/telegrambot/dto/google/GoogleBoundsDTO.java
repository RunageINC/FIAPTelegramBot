package com.runage.ia.telegrambot.dto.google;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GoogleBoundsDTO {

	@JsonProperty
	private double lat;
	
	@JsonProperty
	private double lng;
}
