package com.runage.ia.telegrambot.dto.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ResultsDTO {

	@JsonProperty
	private int temp;
	
	@JsonProperty
	private String date;
	
	@JsonProperty
	private String time;
	
	@JsonProperty
	private String description;
	
	@JsonProperty
	private String currently;
	
	@JsonProperty
	private String city;
	
	@JsonProperty
	private int humidity;
	
	@JsonProperty("wind_speedy")
	private String windSpeed;
	
	@JsonProperty
	private ForecastDTO[] forecast;
}
