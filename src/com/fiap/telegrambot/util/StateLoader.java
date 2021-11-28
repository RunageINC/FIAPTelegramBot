package com.fiap.telegrambot.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

public class StateLoader {

	public static Set<State> statesList = new HashSet<State>();
	
	public static void loadStates() {
		BufferedReader br = new BufferedReader(new FileReader("res/states/states.txt")) 
	}
}
