package com.runage.ia.telegrambot.dto.google;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GoogleDirectionsLegsDTO {

	@JsonProperty
	private GoogleLegsPropertyDTO distance;
	
	@JsonProperty
	private GoogleLegsPropertyDTO duration;
	
	@JsonProperty("end_address")
	private String endAddress;
	
	@JsonProperty("start_address")
	private String startAddress;
}
