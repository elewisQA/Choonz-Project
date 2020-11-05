package com.qa.choonz.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.qa.choonz.exception.TokenNotFoundException;

public class AuthUtils {
	private static final int tokenLength = 10;
	private static Map<String, Long> userTokens;
	private static Map<String, Long> adminTokens; // Future-proofing, used to lock-off API calls

	public AuthUtils() {
		if (userTokens == null) {
			userTokens = new HashMap<>();
		}
	}
	
	//--[ Login Methods ]--
	public static String newToken(Long userId) {
		String token = generateToken();
		userTokens.put(token, userId);
		return token;
	}
	
	public static Boolean validToken(String token) {
		return userTokens.containsKey(token);
	}
	
	public static Long getTokenOwner(String token) {
		if (userTokens.containsKey(token)) {
			return userTokens.get(token);
		} else {
			return null;
		}
	}
	
	public static void deleteToken(String token) {
		if (userTokens.containsKey(token)) {
			userTokens.remove(token);
		}
	}
	
	private static String generateToken() {
		// Use random-number generated values, cast to chars.
		int lowerLimit = 48;	// Set bounds for character values, lowest is '0', highest is 'z'
		int upperLimit = 122;
		Random rng = new Random();
		// Thanks to Baeldung for this code
		return rng.ints(lowerLimit, upperLimit + 1)
	    	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))	// Filter-method to avoid going out of range
	    	      .limit(tokenLength)
	    	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	    	      .toString();
	}
}
