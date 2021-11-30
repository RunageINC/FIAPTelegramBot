package com.runage.ia.telegrambot.dto.google;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GoogleLegsPropertyDTO {

	@JsonProperty
	private String text;
	
	@JsonProperty
	private int value;
}
