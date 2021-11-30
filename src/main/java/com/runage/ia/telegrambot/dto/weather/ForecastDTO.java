package com.runage.ia.telegrambot.dto.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ForecastDTO {

	@JsonProperty
	private String date;
	
	@JsonProperty
	private String weekday;
	
	@JsonProperty
	private int max;
	
	@JsonProperty
	private int min;
	
	@JsonProperty
	private String description;
	
	@JsonProperty
	private String condition;
}
