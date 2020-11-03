package com.qa.choonz.rest.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.qa.choonz.persistence.domain.Album;

class TestArtistDTO {

	ArtistDTO testArtist;
	final Long id = 1l;
	final String name = "Pink Floyd";
	final String picture = "../";
	List<Album> testAlbums;
	
	@BeforeEach
	void init() {
		this.testAlbums = new ArrayList<Album>();
		this.testArtist = new ArtistDTO(
				this.id, 
				this.name, 
				this.picture,
				this.testAlbums);
	}
	
	@Test
	void testZeroArgsConstructor() {
		ArtistDTO newTestArtist = new ArtistDTO();

		assertTrue(newTestArtist instanceof ArtistDTO);
	}
	
	@Test
	void testAllArgsConstructor() {
		Long newId = this.id + 1;
		String newName = "R.E.M";
		String newPicture = "../";
		List<Album> newTestAlbums = new ArrayList<Album>();
		ArtistDTO newTestArtist = new ArtistDTO(
				newId, 
				newName,
				newPicture,
				newTestAlbums);	
	
		assertTrue(newTestArtist instanceof ArtistDTO);
	}
	
	@Test
	void getSetIdTest() {
		Long newId = 2l;
		this.testArtist.setId(newId);

		assertEquals(newId,this.testArtist.getId());
	}
	
	@Test
	void getSetNameTest() {
		String newName = "Orange Floyd";
		this.testArtist.setName(newName);

		assertEquals(newName,this.testArtist.getName());
	}
	
	@Test
	void getSetAlbumsTest() {
		Album newAlbum = new Album();
		List<Album> newAlbums = new ArrayList<Album>();
		newAlbums.add(newAlbum);
		this.testArtist.setAlbums(newAlbums);
		
		assertEquals(newAlbums,this.testArtist.getAlbums());
	}
	
	@Test
	void testEquals() {
		ArtistDTO emptyArtist = new ArtistDTO();
		ArtistDTO fullArtist = new ArtistDTO(
				this.id, 
				this.name, 
				this.picture,
				this.testAlbums);
		
		assertNotEquals(this.testArtist, emptyArtist);
		assertEquals(this.testArtist, fullArtist);
	}
	
	@Test
	void testHashCode() {
		ArtistDTO newArtist = new ArtistDTO(	
				this.id, 
				this.name, 
				this.picture,
				this.testAlbums);
		assertEquals(this.testArtist.hashCode(), newArtist.hashCode());
	}
	
	@Test
	void testToString() {
		System.out.println(this.testArtist.toString());
		assertEquals("ArtistDTO [id=1, name=Pink Floyd, picture=../, albums=[]]",
				this.testArtist.toString());
	}
	
	@AfterEach
	void teardown() {
		this.testArtist = null;
	}
	
}
