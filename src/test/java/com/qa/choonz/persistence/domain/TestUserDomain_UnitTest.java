package com.qa.choonz.persistence.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//===[ Testing Code ]===
public class TestUserDomain_UnitTest {
	//--[ Test Variables ]--
	User testUser;
	final Long id = 1L;
	final String username = "testName";
	final String pass = "password";
	List<Playlist> playlists;
	
	//--[ Test Setup ]--
	@BeforeEach
	void init() {
		this.playlists = new ArrayList<>();
		this.testUser = new User(
				this.id,
				this.username,
				this.pass,
				this.playlists);
	}
	
	//--[ Test Methods ]--
	@Test
	void testZeroArgsConstructor() {
		// test setup
		User newUser = new User();
		
		// test assertion
		assertTrue(newUser instanceof User);
	}
	
	@Test
	void testAllArgsConstructor() {
		// test setup
		User newUser = new User(
				this.id,
				this.username,
				this.pass,
				this.playlists);
		
		// test assertion
		assertTrue(newUser instanceof User);
	}
	
	@Test
	void testGetId() {
		assertEquals(this.id, this.testUser.getId());
	}
	
	@Test
	void testSetId() {
		// test setup
		Long newId = 2L;
		this.testUser.setId(newId);

		// test assertion
		assertEquals(newId, this.testUser.getId());
	}
	
	@Test
	void testGetUsername() {
		assertEquals(this.username, this.testUser.getUsername());
	}
	
	@Test
	void testSetUsername() {
		// test setup
		String newUsername = "new username";
		this.testUser.setUsername(newUsername);
		
		// test assertion
		assertEquals(newUsername, this.testUser.getUsername());
	}
	
	@Test
	void testGetPassword() {
		assertEquals(this.username, this.testUser.getUsername());
	}
	
	@Test
	void testSetPassword() {
		// test setup
		String newPassword = "new password";
		this.testUser.setPassword(newPassword);
		
		// test assertion
		assertEquals(newPassword, this.testUser.getPassword());
	}
	
	@Test
	void testSetGetPlaylists() { 
		// test setup
		List<Playlist> newPlaylists = new ArrayList<>();		
		this.testUser.setPlaylists(newPlaylists);
		
		// test assertion
		assertEquals(newPlaylists, this.testUser.getPlaylists());
	}
	
	@Test
	void testEquals() {
		User emptyUser = new User();
		User fullUser = new User(
				this.id,
				this.username,
				this.pass,
				this.playlists);
		assertFalse(this.testUser.equals(emptyUser));
		assertEquals(fullUser, this.testUser);
	}
	
	@Test
	void testToString() {
		assertEquals("User [id=1, username=testName, password=password, playlists=[]]",
				this.testUser.toString());
	}
	
	@Test
	void testHashCode() {
		assertEquals(639706019,
				this.testUser.hashCode());
	}
	
	//--[ Test Tear-Down ]--
	@AfterEach
	void teardown() {
		this.testUser = null;
	}
}
