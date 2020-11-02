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
		if (AuthUtils.userTokens != null) {
			AuthUtils.userTokens = new HashMap<>();
		}
	}
	
	//--[ Login Methods ]--
	public static String newToken(Long userId) {
		String token = generateToken();
		AuthUtils.userTokens.put(token, userId);
		return token;
	}
	
	public static Long getTokenOwner(String token) throws TokenNotFoundException {
		if (AuthUtils.userTokens.containsKey(token)) {
			return AuthUtils.userTokens.get(token);
		} else {
			throw new TokenNotFoundException();
		}
	}
	
	public static void deleteToken(String token) {
		if (AuthUtils.userTokens.containsKey(token)) {
			AuthUtils.userTokens.remove(token);
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
	    	      .limit(AuthUtils.tokenLength)
	    	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	    	      .toString();
	}
}
