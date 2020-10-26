package com.qa.choonz.rest.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.qa.choonz.persistence.domain.Track;

public class TESTAlbumDTO {
	
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
	public void noArgsConstructorTest() {
		AlbumDTO newAlbum = new AlbumDTO();
		
		assertThat(newAlbum instanceof AlbumDTO);
	}
	
	@Test
	public void allArgsConstructorTest() {
		AlbumDTO newAlbum = new AlbumDTO(
				this.id,
				this.name,
				this.tracks,
				null,
				null,
				this.cover);
		
		assertThat(newAlbum instanceof AlbumDTO);
	}
	
	@Test
	public void getSetIdTest() {
		Long newId = 2L;
		this.testAlbumDTO.setId(newId);
		
		assertEquals(newId,testAlbumDTO.getId());
	}
	
	@Test
	public void getSetNameTest() {
		String newName = "The wall";
		this.testAlbumDTO.setName(newName);
		
		assertEquals(newName,testAlbumDTO.getName());
	}
	
	@Test
	public void getSetTracksTest() {
		List<Track> newTracks = new ArrayList<Track>();
		this.testAlbumDTO.setTracks(newTracks);
		
		assertEquals(newTracks,this.testAlbumDTO.getTracks());
	}
	
	@Test
	public void getSetArtistTest() {
		this.testAlbumDTO.setArtist(null);
		
		assertNull(this.testAlbumDTO.getArtist());
	}
	
	@Test
	public void getSetGenreTest() {
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
		
		assertThat(!this.testAlbumDTO.equals(emptyAlbum));
		assertThat(this.testAlbumDTO.equals(fullAlbum));
	}
	
	@Test
	void testToString() {
		assertThat(this.testAlbumDTO.toString()
				.equals("AlbumDTO [id=1, name=Dark Side of the Moon, "
						+ "tracks=[], artist=null, genre=null, "
						+ "cover=not-a-cover]"));
	}
	
	@Test
	void testHashCode() {
		assertThat(this.testAlbumDTO.hashCode() == -1870013350);
	}
	
	@AfterEach
	void teardown() {
		this.testAlbumDTO = null;
	}
}
