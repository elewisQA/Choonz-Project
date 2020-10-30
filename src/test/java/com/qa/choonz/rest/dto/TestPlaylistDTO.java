package com.qa.choonz.rest.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.Track;

public class TestPlaylistDTO {
	
	PlaylistDTO testPlaylist;
	final long id = 1L;
	final String name = "Tunes";
	final String description = "Bangers only";
	final String artwork = "Arty";
	List<Track> testTracks;
	
	@BeforeEach
	void init() {
		this.testTracks = new ArrayList<Track>();
		this.testPlaylist = new PlaylistDTO(
				this.id,
				this.name,
				this.description,
				this.artwork,
				this.testTracks);
	}
	
	@Test
	public void noArgsconstructorTest() {
		Playlist emptyPlaylist = new Playlist();
		
		assertTrue(emptyPlaylist instanceof Playlist);
	}
	
	@Test
	public void allArgsconstructorTest() {
		Playlist playlist = new Playlist(1L,"Choonz","Bangers","Picture",null);
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
	public void getSetDescriptionTest() {
		String newDesc = "Fresh tunes";
		this.testPlaylist.setDescription(newDesc);
		
		assertEquals(newDesc,this.testPlaylist.getDescription());
	}
	
	@Test
	public void getSetArtworkTest() {
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
	public void toStringTests() {		
		assertTrue(this.testPlaylist.toString()
				.equals("PlaylistDTO [id=1, name=Choonz, description=Bangers, artwork=Picture, tracks=null]"));
	}
	
	@Test
	public void hashCodeTest() {		
		PlaylistDTO playlist1 = new PlaylistDTO(1L,"Choonz","Bangers","Picture",null);
		PlaylistDTO playlist2 = new PlaylistDTO(1L,"Choonz","Bangers","Picture",null);
		
		assertTrue(playlist1.hashCode() == playlist2.hashCode());
	}
	
	@Test
	void testEquals() {
		PlaylistDTO emptyPlaylist = new PlaylistDTO();
		PlaylistDTO fullPlaylist = new PlaylistDTO(
				this.id, 
				this.name, 
				this.description,
				this.artwork,
				this.testTracks);
		
		assertTrue(!this.testPlaylist.equals(emptyPlaylist));
		assertTrue(this.testPlaylist.equals(fullPlaylist));
	}
	
	@AfterEach
	void teardown() {
		this.testPlaylist = null;
	}
}
