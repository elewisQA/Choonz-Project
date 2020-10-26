package com.qa.choonz.rest.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.qa.choonz.persistence.domain.Album;

public class TESTArtistDTO {

	ArtistDTO testArtist;
	final Long id = 1l;
	final String name = "Pink Floyd";
	List<Album> testAlbums;
	
	@BeforeEach
	void init() {
		this.testAlbums = new ArrayList<Album>();
		this.testArtist = new ArtistDTO(
				this.id, 
				this.name, 
				this.testAlbums);
	}
	
	@Test
	void testZeroArgsConstructor() {
		ArtistDTO newTestArtist = new ArtistDTO();

		assertThat(newTestArtist instanceof ArtistDTO);
	}
	
	@Test
	void testAllArgsConstructor() {
		Long newId = this.id + 1;
		String newName = "R.E.M";
		List<Album> newTestAlbums = new ArrayList<Album>();
		ArtistDTO newTestArtist = new ArtistDTO(
				newId, 
				newName,
				newTestAlbums);	
	
		assertThat(newTestArtist instanceof ArtistDTO);
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
				this.testAlbums);
		
		assertThat(!this.testArtist.equals(emptyArtist));
		assertThat(this.testArtist.equals(fullArtist));
	}
	
	@Test
	void testHashCode() {
		assertThat(this.testArtist.hashCode() == -1259434520);
	}
	
	@Test
	void testToString() {
		assertThat(this.testArtist.toString()
				.equals("Artist [id=1, name=Pink Floyd, albums=[Album "
						+ "[id=0, name=null, tracks=null, artist=null, "
						+ "genre=null, cover=null]]]"));
	}
	
	@AfterEach
	void teardown() {
		this.testArtist = null;
	}
	
}
