package com.runage.ia.telegrambot.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExternalConnections {

	private String path;
	
	public ExternalConnections(String path) {
		this.path = path;
	}
	
	public <T> T getHttps(Class<T> model) {
		try {
			log.info("Start connection to " + path);
			final URL url = new URL(path);
			final HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
			
			connection.setRequestProperty("accept", "application/json");
			connection.setRequestMethod("GET");
			
			
			if (connection.getResponseCode() == 200) {
				InputStream response = connection.getInputStream();
				ObjectMapper mapper = new ObjectMapper() {
					private static final long serialVersionUID = -174113593500315394L;
					{
						configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
						setSerializationInclusion(JsonInclude.Include.NON_NULL);
					}
				};

				log.info("API for " + model.getName() + " - 200 OK");
				
				return mapper.readValue(response, model);
			} else {
				log.info(connection.getResponseMessage() + " - " + connection.getResponseMessage() + connection.getInputStream().toString());
				return null;
			}
			
			
		} catch (IOException e) {
			return null;
		}
	}
	
	public <T> T get(Class<T> model) {
		try {
			log.info("Start connection to " + path);
			final URL url = new URL(path);
			final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			connection.setRequestProperty("accept", "application/json");
			connection.setRequestMethod("GET");
			
			
			if (connection.getResponseCode() == 200) {
				InputStream response = connection.getInputStream();
				ObjectMapper mapper = new ObjectMapper() {
					private static final long serialVersionUID = -174113593500315394L;
					{
						configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
						setSerializationInclusion(JsonInclude.Include.NON_NULL);
					}
				};

				log.info("API for " + model.getName() + " - 200 OK");
				
				return mapper.readValue(response, model);
			} else {
				log.info(connection.getResponseMessage() + " - " + connection.getResponseMessage() + connection.getInputStream().toString());
				return null;
			}
			
			
		} catch (IOException e) {
			return null;
		}
	}
}
