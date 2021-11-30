package com.runage.ia.telegrambot.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileManagingUtils {

	public static boolean hasFile(String chatId) {
		String path = ResourceEnum.RESOURCE_PATH.getValue() + chatId;
		return new File(path).exists();
	}
	
	public static String[] readGreets() {
		String path = ResourceEnum.RESOURCE_PATH.getValue() + ResourceEnum.GREET_FILE.getValue();
		BufferedReader br;
		
		try {
			br = new BufferedReader(new FileReader(path));
			List<String> greets = new ArrayList<>();
			
			while (br.ready()) {
				String line = br.readLine();
				
				greets.add(line);
			}
			
			String[] array = greets.toArray(new String[0]);
			
			br.close();
			
			return array;
		} catch (IOException e) {
			log.error("Something went wrong when trying to read greet.txt {}", e);
			return null;
		}
	}
}
