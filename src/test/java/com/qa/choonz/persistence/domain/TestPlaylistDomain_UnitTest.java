package com.qa.choonz.persistence.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestPlaylistDomain_UnitTest {
	// Test Variables 
	Playlist testPlaylist;
	
	@BeforeEach
	void init() {
		this.testPlaylist = new Playlist();
	}
	
	@Test
	void testZeroArgsConstructor() {
		Playlist newPlaylist = new Playlist();
		
		assertTrue(newPlaylist instanceof Playlist);
	}
	
	@Test
	void testAllArgsConstructor() {
		Playlist newPlaylist = new Playlist(1L, "Choonz", "Bangers", "Picture", null);
		assertTrue(newPlaylist instanceof Playlist);
	}
	
	@Test
	void getSetIdTest() {	
		this.testPlaylist.setId(1L);
		
		assertEquals(1L, this.testPlaylist.getId());
	}
	
	@Test
	void getSetNameTest() {
		this.testPlaylist.setName("Song");
		
		assertEquals("Song", this.testPlaylist.getName());
	}
	
	@Test
	void getSetDescriptionTest() {
		this.testPlaylist.setDescription("Bangers");
		
		assertEquals("Bangers", this.testPlaylist.getDescription());
	}
	
	@Test
	void getSetArtworkTest() {
		this.testPlaylist.setArtwork("Picture");
		
		assertEquals("Picture", this.testPlaylist.getArtwork());
	}
	
	@Test
	void getSetTracksTest() {
		Track track = new Track();
		List<Track> tracks = new ArrayList<>();
		tracks.add(track);
		
		this.testPlaylist.setTracks(tracks);
		
		assertEquals(tracks, this.testPlaylist.getTracks());
	}
	
	@Test
	void toStringTests() {		
		Playlist newPlaylist = new Playlist(1L,"Choonz","Bangers","Picture",null);
		
		assertEquals("Playlist [id=1, name=Choonz, description=Bangers, artwork=Picture, tracks=null]"
				,newPlaylist.toString());
	}
	
	@Test
	void hashCodeTest() {		
		Playlist playlist1 = new Playlist(1L,"Choonz","Bangers","Picture",null);
		Playlist playlist2 = new Playlist(1L,"Choonz","Bangers","Picture",null);
		
		assertEquals(playlist1.hashCode(), playlist2.hashCode());
	}
	
	@Test
	void equalsTest() {
		Playlist newPlaylist = new Playlist();
		
		assertEquals(newPlaylist, this.testPlaylist);
		assertFalse(newPlaylist.equals(null));
	}
	
	@AfterEach
	void teardown() {
		testPlaylist = null;
	}
}
