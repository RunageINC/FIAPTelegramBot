package com.fiap.telegrambot.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class MessagesUtil {

	public static Set<String> getMessages() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(ResourcesUtil.RESOURCES_PATH.getValue() + ResourcesUtil.TEXT_FILENAME.getValue()));
		Set<String> messagesSet = new HashSet<String>();
		
		while (br.ready()) {
			String line = br.readLine();
			
			messagesSet.add(line);
		}
		
		return messagesSet;
	}
}
