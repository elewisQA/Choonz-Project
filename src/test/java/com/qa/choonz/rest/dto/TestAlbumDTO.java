package com.qa.choonz.rest.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.qa.choonz.persistence.domain.Track;

class TestAlbumDTO {
	
	AlbumDTO testAlbumDTO;
	final Long id = 1L;
	final String name = "Dark Side of the Moon";
	final String cover = "not-a-cover";
	List<Track> tracks;

	@BeforeEach
	void init() {
		this.tracks = new ArrayList<Track>();
		this.testAlbumDTO = new AlbumDTO(
				this.id, 
				this.name, 
				this.tracks, 
				null, 
				null, 
				this.cover);
	}
	
	@Test
	void noArgsConstructorTest() {
		AlbumDTO newAlbum = new AlbumDTO();
		
		assertTrue(newAlbum instanceof AlbumDTO);
	}
	
	@Test
	void allArgsConstructorTest() {
		AlbumDTO newAlbum = new AlbumDTO(
				this.id,
				this.name,
				this.tracks,
				null,
				null,
				this.cover);
		
		assertTrue(newAlbum instanceof AlbumDTO);
	}
	
	@Test
	void getSetIdTest() {
		Long newId = 2L;
		this.testAlbumDTO.setId(newId);
		
		assertEquals(newId,testAlbumDTO.getId());
	}
	
	@Test
	void getSetNameTest() {
		String newName = "The wall";
		this.testAlbumDTO.setName(newName);
		
		assertEquals(newName,testAlbumDTO.getName());
	}
	
	@Test
	void getSetTracksTest() {
		List<Track> newTracks = new ArrayList<Track>();
		this.testAlbumDTO.setTracks(newTracks);
		
		assertEquals(newTracks,this.testAlbumDTO.getTracks());
	}
	
	@Test
	void getSetArtistTest() {
		this.testAlbumDTO.setArtist(null);
		
		assertNull(this.testAlbumDTO.getArtist());
	}
	
	@Test
	void getSetGenreTest() {
		this.testAlbumDTO.setGenre(null);
		
		assertNull(this.testAlbumDTO.getGenre());
	}
	
	@Test
	void testEquals() {
		AlbumDTO emptyAlbum = new AlbumDTO();
		AlbumDTO fullAlbum = new AlbumDTO(
				this.id,
				this.name,
				this.tracks,
				null,
				null,
				this.cover);
		
		assertNotEquals(this.testAlbumDTO, emptyAlbum);
		assertEquals(this.testAlbumDTO, fullAlbum);
	}
	
	@Test
	void testToString() {
		assertEquals(this.testAlbumDTO.toString(), 
						"AlbumDTO [id=1, name=Dark Side of the Moon, "
						+ "tracks=[], artist=null, genre=null, "
						+ "cover=not-a-cover]");
	}
	
	@Test
	void testHashCode() {
		assertEquals(this.testAlbumDTO.hashCode(), -1870013350);
	}
	
	@AfterEach
	void teardown() {
		this.testAlbumDTO = null;
	}
}
