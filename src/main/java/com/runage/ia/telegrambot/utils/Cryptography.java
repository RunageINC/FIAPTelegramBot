package com.runage.ia.telegrambot.utils;

import java.util.Base64;

public class Cryptography {

	public static String decodeBase64(String encodedString) {
		byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
		
		return new String(decodedBytes);
	}
}
