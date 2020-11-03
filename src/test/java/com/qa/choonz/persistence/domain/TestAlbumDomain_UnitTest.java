package com.qa.choonz.persistence.domain;

//---[ Imports ]---
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//===[ Testing Code ]===
class TestAlbumDomain_UnitTest {
	//--[ Test Variables ]--
	Album testAlbum;
	final Long id = 1l;
	final String name = "Dark Side of the Moon";
	final String cover = "not-a-cover";
	List<Track> tracks;

	//--[ Test Setup ]--
	@BeforeEach
	void init() {
		this.tracks = new ArrayList<Track>();
		this.testAlbum = new Album(
				this.id, 
				this.name, 
				this.tracks, 
				null, 
				null, 
				this.cover);
	}
	
	//--[ Test Methods ]--
	@Test
	void testZeroArgsConstructor() {
		// test setup
		Album newAlbum = new Album();
		
		// test assertion
		assertTrue(newAlbum instanceof Album);
	}
	
	@Test
	void testAllArgsConstructor() {
		// test setup
		Album newAlbum = new Album(
				this.id,
				this.name,
				this.tracks,
				null,
				null,
				this.cover);
		
		// test assertion
		assertTrue(newAlbum instanceof Album);
	}
	
	@Test
	void testGetId() {
		assertEquals(this.id, this.testAlbum.getId());
	}
	
	@Test
	void testSetId() {
		// test setup
		Long newId = 2l;
		this.testAlbum.setId(newId);
		
		// test assertion
		assertEquals(newId, this.testAlbum.getId());
	}
	
	@Test
	void testGetName() {
		assertEquals(this.name, this.testAlbum.getName());
	}
	
	@Test
	void testSetName() {
		// test setup
		String newName = "The Wall";
		this.testAlbum.setName(newName);
		
		// test assertion
		assertEquals(newName, this.testAlbum.getName());
	}
	
	@Test
	void testGetTracks() {
		assertEquals(this.tracks, this.testAlbum.getTracks());
	}
	
	@Test
	void testSetTracks() {
		// test setup
		List<Track> newTracks = new ArrayList<Track>();
		this.testAlbum.setTracks(newTracks);
		
		// test assertion
		assertEquals(newTracks, this.testAlbum.getTracks());
	}
	
	@Test
	void testGetArtist() {
		assertEquals(null, this.testAlbum.getArtist());
	}
	
	@Test
	void testSetArtist() {
		// test setup
		this.testAlbum.setArtist(null);
		
		// test assertion
		assertEquals(null, this.testAlbum.getArtist());
	}
	
	@Test
	void testGetGenre() {
		assertEquals(null, this.testAlbum.getGenre());
	}
	
	@Test
	void testSetGenre() {
		// test setup
		this.testAlbum.setGenre(null);
		
		// test assertion
		assertEquals(null, this.testAlbum.getGenre());
	}
	
	@Test
	void testEquals() {
		// test setup
		Album emptyAlbum = new Album();
		Album fullAlbum = new Album(
				this.id,
				this.name,
				this.tracks,
				null,
				null,
				this.cover);
		
		// test assertion
		assertNotEquals(this.testAlbum, emptyAlbum);
		assertEquals(fullAlbum, this.testAlbum);
	}
	
	@Test
	void testToString() {
		//TODO fill this method in
		assertEquals("Album [id=1, name=Dark Side of the Moon, "
						+ "tracks=[], artist=null, genre=null, "
						+ "cover=not-a-cover]",
						this.testAlbum.toString());
	}
	
	@Test
	void testHashCode() {
		// TODO check this method is correct / if better test method exists
		assertEquals(-1870013350, this.testAlbum.hashCode());
	}
	
	//--[ Test Tear-Down ]--
	@AfterEach
	void teardown() {
		// Nullify object as precaution (will be built anew)
		this.testAlbum = null;
	}
}
