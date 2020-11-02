package com.qa.choonz.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.qa.choonz.exception.TokenNotFoundException;

public class AuthUtils {
	private final int tokenLength = 10;
	private static Map<String, Long> userTokens;
	private static Map<String, Long> adminTokens; // Future-proofing, used to lock-off API calls

	public AuthUtils() {
		this.userTokens = new HashMap<>();
	}
	
	//--[ Login Methods ]--
	public String newToken(Long userId) {
		String token = generateToken();
		userTokens.put(token, userId);
		return token;
	}
	
	public Long getTokenOwner(String token) throws TokenNotFoundException {
		if (userTokens.containsKey(token)) {
			return userTokens.get(token);
		} else {
			throw new TokenNotFoundException();
		}
	}
	
	public void deleteToken(String token) {
		if (userTokens.containsKey(token)) {
			userTokens.remove(token);
		}
	}
	
	private String generateToken() {
		// Use random-number generated values, cast to chars.
		int lowerLimit = 48;	// Set bounds for character values, lowest is '0', highest is 'z'
		int upperLimit = 122;
		Random rng = new Random();
		// Thanks to Baeldung for this code
		return rng.ints(lowerLimit, upperLimit + 1)
	    	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))	// Filter-method to avoid going out of range
	    	      .limit(this.tokenLength)
	    	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	    	      .toString();
	}
}
