package com.qa.choonz.persistence.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//===[ Testing Code ]===
class TestArtistDomain_UnitTest {
	//--[ Test Variables ]--
	Artist testArtist;
	final Long id = 1l;
	final String name = "Pink Floyd";
	final String picture = "../";
	List<Album> testAlbums;

	//--[ Test Setup ]--
	@BeforeEach
	void init() {
		// Initialize testing vars
		this.testAlbums = new ArrayList<Album>();
		this.testArtist = new Artist(this.id, this.name, this.picture, this.testAlbums);
	}
	
	//--[ Test Methods ]--
	@Test
	void testZeroArgsConstructor() {
		// test setup
		Artist newTestArtist = new Artist();
		
		// test assertion
		assertTrue(newTestArtist instanceof Artist);
	}
	
	@Test
	void testAllArgsConstructor() {
		// test setup
		Long newId = this.id + 1;
		String newName = "R.E.M";
		String newPicture = "../";
		List<Album> newTestAlbums = new ArrayList<Album>();
		Artist newTestArtist = new Artist(
				newId, 
				newName,
				newPicture,
				newTestAlbums);	
	
		// test assertion
		assertTrue(newTestArtist instanceof Artist);
	}
	
	
	@Test
	void testGetId() {
		assertEquals(this.testArtist.getId(), this.id);
	}
	
	@Test
	void testSetId() {
		// test setup
		Long newId = 2l;
		this.testArtist.setId(newId);
		
		// test assertion
		assertEquals(this.testArtist.getId(), newId);
	}
	
	@Test
	void testGetName() {
		assertEquals(this.testArtist.getName(), this.name);
	}
	
	@Test
	void testSetName() {
		// test setup
		String newName = "Orange Floyd";
		this.testArtist.setName(newName);
		
		// test assertion
		assertEquals(this.testArtist.getName(), newName);
	}
	
	@Test
	void testGetAlbums() {
		assertEquals(this.testArtist.getAlbums(), this.testAlbums);
	}
	
	@Test
	void testSetAlbums() {
		// test setup
		Album newAlbum = new Album();
		List<Album> newAlbums = new ArrayList<Album>();
		newAlbums.add(newAlbum);
		this.testArtist.setAlbums(newAlbums);
		
		// test assertion
		assertEquals(this.testArtist.getAlbums(), newAlbums);
	}
	
	
	@Test
	void testEquals() {
		Artist emptyArtist = new Artist();
		Artist fullArtist = new Artist(
				this.id, 
				this.name, 
				this.picture,
				this.testAlbums);
		
		assertTrue(!this.testArtist.equals(emptyArtist));
		assertEquals(this.testArtist, fullArtist);
	}
	
	@Test
	void testHashCode() {
		// TODO check this method is correct / if better test method exists
		assertEquals(this.testArtist.hashCode(), -192099002);
	}
	
	@Test
	void testToString() {
		assertEquals(this.testArtist.toString(),
				"Artist [id=1, name=Pink Floyd, picture=../, albums=[]]");
	}
	
	//--[ Test Tear-Down ]--
	@AfterEach
	void teardown() {
		// Nullify object as precaution (will be built anew)
		this.testArtist = null;
	}
}
