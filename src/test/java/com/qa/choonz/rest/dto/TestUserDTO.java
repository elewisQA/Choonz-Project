package com.qa.choonz.rest.dto;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.User;

class TestUserDTO {
	//--[ Test Variables ]--
	UserDTO testUserDTO;
	final Long id = 1L;
	final String username = "testName";
	final String pass = "password";
	List<Playlist> playlists;
	
	//--[ Test Setup ]--
	@BeforeEach
	void init() {
		this.playlists = new ArrayList<>();
		this.testUserDTO = new UserDTO(
				this.id,
				this.username,
				this.pass,
				this.playlists);
	}
	
	@Test
	void testZeroArgsConstructor() {
		UserDTO newUser = new UserDTO();
		
		assertTrue(newUser instanceof UserDTO);
	}
	
	@Test
	void testAllArgsConstructor() {
		UserDTO newUser = new UserDTO(
				this.id,
				this.username,
				this.pass,
				this.playlists);
		
		assertTrue(newUser instanceof UserDTO);
	}
	
	@Test
	void testSetGetId() {
		Long newId = 2L;
		this.testUserDTO.setId(newId);
		
		assertEquals(newId, this.testUserDTO.getId());
	}
	
	@Test
	void testSetGetUsername() {
		String newUsername = "new username";
		this.testUserDTO.setUsername(newUsername);
		
		assertEquals(newUsername, this.testUserDTO.getUsername());
	}
	
	@Test
	void testSetGetPassword() {
		String newPass = "new password";
		this.testUserDTO.setPassword(newPass);
		
		assertEquals(newPass, this.testUserDTO.getPassword());
	}
	
	@Test
	void testSetGetPlaylists() {
		List<Playlist> newList = new ArrayList<>();
		this.testUserDTO.setPlaylists(newList);
		
		assertEquals(newList, this.testUserDTO.getPlaylists());
	}
	
	@Test
	void testEquals() {
		UserDTO emptyUser = new UserDTO();
		UserDTO fullUser = new UserDTO(
				this.id,
				this.username,
				this.pass,
				this.playlists);
		assertNotEquals(this.testUserDTO, emptyUser);
		assertEquals(fullUser, this.testUserDTO);
	}
	
	@Test
	void testToString() {
		assertEquals("User [id=1, username=testName, password=password, playlists=[]]",
				this.testUserDTO.toString());
	}
	
	@Test
	void testHashCode() {
		assertEquals(639706019,
				this.testUserDTO.hashCode());
	}
	
	//--[ Test Tear-Down ]--
	@AfterEach
	void teardown() {
		this.testUserDTO = null;
	}
}
