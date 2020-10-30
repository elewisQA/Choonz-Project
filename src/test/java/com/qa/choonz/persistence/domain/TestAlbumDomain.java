package com.qa.choonz.persistence.domain;

//---[ Imports ]---
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//===[ Testing Code ]===
public class TestAlbumDomain {
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
		assertEquals(this.testAlbum.getId(), this.id);
	}
	
	@Test
	void testSetId() {
		// test setup
		Long newId = 2l;
		this.testAlbum.setId(newId);
		
		// test assertion
		assertEquals(this.testAlbum.getId(), newId);
	}
	
	@Test
	void testGetName() {
		assertEquals(this.testAlbum.getName(), this.name);
	}
	
	@Test
	void testSetName() {
		// test setup
		String newName = "The Wall";
		this.testAlbum.setName(newName);
		
		// test assertion
		assertEquals(this.testAlbum.getName(), newName);
	}
	
	@Test
	void testGetTracks() {
		assertEquals(this.testAlbum.getTracks(), this.tracks);
	}
	
	@Test
	void testSetTracks() {
		// test setup
		List<Track> newTracks = new ArrayList<Track>();
		this.testAlbum.setTracks(newTracks);
		
		// test assertion
		assertEquals(this.testAlbum.getTracks(), newTracks);
	}
	
	@Test
	void testGetArtist() {
		assertEquals(this.testAlbum.getArtist(), null);
	}
	
	@Test
	void testSetArtist() {
		// test setup
		this.testAlbum.setArtist(null);
		
		// test assertion
		assertEquals(this.testAlbum.getArtist(), null);
	}
	
	@Test
	void testGetGenre() {
		assertEquals(this.testAlbum.getGenre(), null);
	}
	
	@Test
	void testSetGenre() {
		// test setup
		this.testAlbum.setGenre(null);
		
		// test assertion
		assertEquals(this.testAlbum.getGenre(), null);
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
		assertFalse(this.testAlbum.equals(emptyAlbum));
		assertEquals(this.testAlbum, fullAlbum);
	}
	
	@Test
	void testToString() {
		//TODO fill this method in
		assertEquals(this.testAlbum.toString(),
				"Album [id=1, name=Dark Side of the Moon, "
						+ "tracks=[], artist=null, genre=null, "
						+ "cover=not-a-cover]");
	}
	
	@Test
	void testHashCode() {
		// TODO check this method is correct / if better test method exists
		assertEquals(this.testAlbum.hashCode(), -1870013350);
	}
	
	//--[ Test Tear-Down ]--
	@AfterEach
	void teardown() {
		// Nullify object as precaution (will be built anew)
		this.testAlbum = null;
	}
}
