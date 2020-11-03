package com.qa.choonz.utils;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestAuthUtilsUnit {
	private AuthUtils utils;
	private final Long ID = 1L;
	
	@BeforeEach
	void init() {
		this.utils = new AuthUtils();
	}
	
	@Test
	void testNewToken() {
		String token = AuthUtils.newToken(this.ID);
		assertTrue(token instanceof String);
	}
	
	@Test
	void testSetGetToken() {
		String token = AuthUtils.newToken(this.ID);
		
		assertEquals(this.ID, AuthUtils.getTokenOwner(token));
	}
}
