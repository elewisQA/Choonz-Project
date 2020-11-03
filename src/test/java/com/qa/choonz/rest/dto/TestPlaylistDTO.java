package com.qa.choonz.rest.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.persistence.domain.User;

class TestPlaylistDTO {
	
	PlaylistDTO testPlaylist;
	final long id = 1L;
	final String name = "Tunes";
	final String description = "Bangers only";
	final String artwork = "Arty";
	List<Track> testTracks;
	User testUser;
	
	@BeforeEach
	void init() {
		this.testTracks = new ArrayList<Track>();
		this.testUser = new User();
		this.testPlaylist = new PlaylistDTO(
				this.id,
				this.name,
				this.description,
				this.artwork,
				this.testTracks,
				this.testUser);
	}
	
	@Test
	void noArgsconstructorTest() {
		Playlist emptyPlaylist = new Playlist();
		
		assertTrue(emptyPlaylist instanceof Playlist);
	}
	
	@Test
	void allArgsconstructorTest() {
		Playlist playlist = new Playlist(1L,"Choonz","Bangers","Picture",null, null);
		assertTrue(playlist instanceof Playlist);
	}
	
	@Test
	void getSetIdTest() {
		Long newId = 2l;
		this.testPlaylist.setId(newId);

		assertEquals(newId,this.testPlaylist.getId());
	}
	
	@Test
	void getSetNameTest() {
		String newName = "Refresh";
		this.testPlaylist.setName(newName);

		assertEquals(newName,this.testPlaylist.getName());
	}
	
	@Test
	void getSetDescriptionTest() {
		String newDesc = "Fresh tunes";
		this.testPlaylist.setDescription(newDesc);
		
		assertEquals(newDesc,this.testPlaylist.getDescription());
	}
	
	@Test
	void getSetArtworkTest() {
		String newArt = "Picture";
		this.testPlaylist.setArtwork(newArt);
		
		assertEquals(newArt,this.testPlaylist.getArtwork());
	}
	
	@Test
	void getSetTracksTest() {
		Track newTrack = new Track();
		List<Track> newTracks = new ArrayList<Track>();
		newTracks.add(newTrack);
		this.testPlaylist.setTracks(newTracks);
		
		assertEquals(newTracks,this.testPlaylist.getTracks());
	}
	// TODO Album getting / setting not yet implemented in PlaylistDTO
	
	@Test
	void toStringTests() {
		assertEquals(this.testPlaylist.toString(),
				"PlaylistDTO [id=1, name=Tunes, description=Bangers only, artwork=Arty, "
						+ "tracks=[], user=User [id=null, username=null, password=null, playlists=[]]]");
	}
	
	@Test
	void hashCodeTest() {		
		PlaylistDTO playlist1 = new PlaylistDTO(1L,"Choonz","Bangers","Picture",null, null);
		PlaylistDTO playlist2 = new PlaylistDTO(1L,"Choonz","Bangers","Picture",null, null);
		
		assertEquals(playlist1.hashCode(), playlist2.hashCode());
	}
	
	@Test
	void testEquals() {
		PlaylistDTO emptyPlaylist = new PlaylistDTO();
		PlaylistDTO fullPlaylist = new PlaylistDTO(
				this.id, 
				this.name, 
				this.description,
				this.artwork,
				this.testTracks,
				this.testUser);
		
		assertNotEquals(this.testPlaylist, emptyPlaylist);
		assertEquals(this.testPlaylist, fullPlaylist);
	}
	
	@AfterEach
	void teardown() {
		this.testPlaylist = null;
	}
}
