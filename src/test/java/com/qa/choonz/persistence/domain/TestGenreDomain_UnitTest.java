package com.qa.choonz.persistence.domain;

//--[ Imports ]--
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.persistence.domain.Track;

//===[ Testing Code ]===
class TestGenreDomain_UnitTest {
	//--[ Test Variables ]--
	Genre testGenre;
	final Long id = 1l;
	final String name = "Psychadelic Rock";
	final String picture ="../";
	final String description = "Unusual rock-music.";
	List<Album> albums;
	
	//--[ Test Setup ]--
	@BeforeEach
	void init() {
		this.albums = new ArrayList<Album>();
		this.testGenre = new Genre(
				this.id, 
				this.name,
				this.picture,
				this.description, 
				this.albums);
	}
	
	//--[ Test Methods ]--
	@Test
	void testZeroArgsConstructor() {
		// test setup
		Genre newGenre = new Genre();
		
		// test assertion
		assertTrue(newGenre instanceof Genre);
	}
	
	@Test
	void testAllArgsConstructor() {
		// test setup
		Genre newGenre = new Genre(
				this.id, 
				this.name, 
				this.picture,
				this.description, 
				this.albums);
		
		// test assertion
		assertTrue(newGenre instanceof Genre);
	}
	
	@Test
	void testGetId() {
		assertEquals(this.id, this.testGenre.getId());
	}
	
	@Test
	void testSetId() {
		Long newId = 2l;
		this.testGenre.setId(newId);
		
		assertEquals(newId, this.testGenre.getId());
	}
	
	@Test
	void testGetName() {
		assertEquals(this.name, this.testGenre.getName());
	}
	
	@Test
	void testSetName() {
		String newName = "R&B";
		this.testGenre.setName(newName);
		
		assertEquals(newName, this.testGenre.getName());
	}
	
	@Test
	void testGetDescription() {
		assertEquals(this.description, this.testGenre.getDescription());
	}
	
	@Test
	void testSetDescription() {
		String newDescription = "Deep music";
		this.testGenre.setDescription(newDescription);
		
		assertEquals(newDescription, this.testGenre.getDescription());
	}
	
	@Test
	void testGetAlbums() {
		assertEquals(this.albums, this.testGenre.getAlbums());
	}
	
	@Test
	void testSetAlbums() {
		Album newAlbum = new Album();
		List<Album> newAlbums = new ArrayList<Album>();
		newAlbums.add(newAlbum);
		testGenre.setAlbums(newAlbums);
		
		assertEquals(newAlbums, this.testGenre.getAlbums());
	}
	
	@Test
	void testToString() {
		assertEquals("Genre [id=1, name=Psychadelic Rock, picture=../, description=Unusual rock-music., albums=[]]",
				this.testGenre.toString());
	}
	
	@Test
	void testHashCode() {
		assertEquals(-320901008,
				this.testGenre.hashCode());
	}
	
	@AfterEach
	void teardown() {
		this.testGenre = null;
	}
}
