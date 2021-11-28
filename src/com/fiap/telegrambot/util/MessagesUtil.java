package com.fiap.telegrambot.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MessagesUtil {

	
	
	public void readMessages() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(ResourcesUtil.RESOURCES_PATH.getValue() + ResourcesUtil.TEXT_FILENAME.getValue()));
		
		
		
	}
}
