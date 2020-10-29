package com.qa.choonz.persistence.domain;

//--[ Imports ]--
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Artist;

//===[ Testing Code ]===
public class TESTArtist {
	//--[ Test Variables ]--
	Artist testArtist;
	final Long id = 1l;
	final String name = "Pink Floyd";
	List<Album> testAlbums;

	//--[ Test Setup ]--
	@BeforeEach
	void init() {
		// Initialize testing vars
		this.testAlbums = new ArrayList<Album>();
		this.testArtist = new Artist(this.id, this.name, this.testAlbums);
	}
	
	//--[ Test Methods ]--
	@Test
	void testZeroArgsConstructor() {
		// test setup
		Artist newTestArtist = new Artist();
		
		// test assertion
		assertThat(newTestArtist instanceof Artist);
	}
	
	@Test
	void testAllArgsConstructor() {
		// test setup
		Long newId = this.id + 1;
		String newName = "R.E.M";
		List<Album> newTestAlbums = new ArrayList<Album>();
		Artist newTestArtist = new Artist(
				newId, 
				newName,
				newTestAlbums);	
	
		// test assertion
		assertThat(newTestArtist instanceof Artist);
	}
	
	
	@Test
	void testGetId() {
		assertThat(this.testArtist.getId() == this.id);
	}
	
	@Test
	void testSetId() {
		// test setup
		Long newId = 2l;
		this.testArtist.setId(newId);
		
		// test assertion
		assertThat(this.testArtist.getId() == newId);
	}
	
	@Test
	void testGetName() {
		assertThat(this.testArtist.getName() == this.name);
	}
	
	@Test
	void testSetName() {
		// test setup
		String newName = "Orange Floyd";
		this.testArtist.setName(newName);
		
		// test assertion
		assertThat(this.testArtist.getName().equals(newName));
	}
	
	@Test
	void testGetAlbums() {
		assertThat(this.testArtist.getAlbums() == this.testAlbums);
	}
	
	@Test
	void testSetAlbums() {
		// test setup
		Album newAlbum = new Album();
		List<Album> newAlbums = new ArrayList<Album>();
		newAlbums.add(newAlbum);
		this.testArtist.setAlbums(newAlbums);
		
		// test assertion
		assertThat(this.testArtist.getAlbums() == newAlbums);
	}
	
	
	@Test
	void testEquals() {
		Artist emptyArtist = new Artist();
		Artist fullArtist = new Artist(
				this.id, 
				this.name, 
				this.testAlbums);
		
		assertThat(!this.testArtist.equals(emptyArtist));
		assertThat(this.testArtist.equals(fullArtist));
	}
	
	@Test
	void testHashCode() {
		// TODO check this method is correct / if better test method exists
		assertThat(this.testArtist.hashCode() == -1259434520);
	}
	
	@Test
	void testToString() {
		assertThat(this.testArtist.toString()
				.equals("Artist [id=1, name=Pink Floyd, albums=[Album "
						+ "[id=0, name=null, tracks=null, artist=null, "
						+ "genre=null, cover=null]]]"));
	}
	
	//--[ Test Tear-Down ]--
	@AfterEach
	void teardown() {
		// Nullify object as precaution (will be built anew)
		this.testArtist = null;
	}
}
