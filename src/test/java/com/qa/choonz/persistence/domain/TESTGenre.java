package com.qa.choonz.persistence.domain;

//--[ Imports ]--
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.persistence.domain.Track;

//===[ Testing Code ]===
public class TESTGenre {
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
		assertThat(newGenre instanceof Genre);
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
		assertThat(newGenre instanceof Genre);
	}
	
	@Test
	void testGetId() {
		assertThat(this.testGenre.getId() == this.id);
	}
	
	@Test
	void testSetId() {
		Long newId = 2l;
		this.testGenre.setId(newId);
		
		assertThat(this.testGenre.getId() == newId);
	}
	
	@Test
	void testGetName() {
		assertThat(this.testGenre.getName() == this.name);
	}
	
	@Test
	void testSetName() {
		String newName = "R&B";
		this.testGenre.setName(newName);
		
		assertThat(this.testGenre.getName() == newName);
	}
	
	@Test
	void testGetDescription() {
		assertThat(this.testGenre.getDescription() == this.description);
	}
	
	@Test
	void testSetDescription() {
		String newDescription = "Deep music";
		this.testGenre.setDescription(newDescription);
		
		assertThat(this.testGenre.getDescription() == newDescription);
	}
	
	@Test
	void testGetAlbums() {
		assertThat(this.testGenre.getAlbums() == this.albums);
	}
	
	@Test
	void testSetAlbums() {
		Album newAlbum = new Album();
		List<Album> newAlbums = new ArrayList<Album>();
		newAlbums.add(newAlbum);
		testGenre.setAlbums(newAlbums);
		
		assertThat(this.testGenre.getAlbums() == newAlbums);
	}
	
	@Test
	void testToString() {
		assertThat(this.testGenre.toString()
				.equals("Genre [id=1, name=Psychadelic Rock, description=Unusual rock-music.,"
						+ "albums=null]"));
	}
	
	@Test
	void testHashCode() {
		assertThat(this.testGenre.hashCode() == -1259434520);
	}
	
	@AfterEach
	void teardown() {
		this.testGenre = null;
	}
}
