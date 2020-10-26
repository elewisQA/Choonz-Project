package com.qa.choonz.persistence.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.Track;

public class TESTPlaylist {
	
	@Test
	public void constructorTests() {
		Playlist emptyPlaylist = new Playlist();
		
		assertTrue(emptyPlaylist instanceof Playlist);
		
		Playlist playlist = new Playlist(1L,"Choonz","Bangers","Picture",null);
		assertTrue(playlist instanceof Playlist);
	}
	
	@Test
	public void getSetIdTest() {
		Playlist emptyPlaylist = new Playlist();
		
		emptyPlaylist.setId(1L);
		
		assertEquals(1L,emptyPlaylist.getId());
	}
	
	@Test
	public void getSetNameTest() {
		Playlist emptyPlaylist = new Playlist();
		emptyPlaylist.setName("Song");
		
		assertEquals("Song",emptyPlaylist.getName());
	}
	
	@Test
	public void getSetDescriptionTest() {
		Playlist emptyPlaylist = new Playlist();
		emptyPlaylist.setDescription("Bangers");
		
		assertEquals("Bangers",emptyPlaylist.getDescription());
	}
	
	@Test
	public void getSetArtworkTest() {
		Playlist emptyPlaylist = new Playlist();
		emptyPlaylist.setArtwork("Picture");
		
		assertEquals("Picture",emptyPlaylist.getArtwork());
	}
	
	@Test
	public void getSetTracksTest() {
		Track track = new Track();
		List<Track> tracks = new ArrayList<>();
		tracks.add(track);
		
		Playlist emptyPlaylist = new Playlist();
		emptyPlaylist.setTracks(tracks);
		
		assertEquals(tracks,emptyPlaylist.getTracks());
	}
	
	@Test
	public void toStringTests() {		
		Playlist playlist = new Playlist(1L,"Choonz","Bangers","Picture",null);
		
		assertNotNull(playlist.toString());
		assertEquals("Playlist [id=1, name=Choonz, description=Bangers, artwork=Picture, tracks=null]"
				,playlist.toString());
	}
	
	@Test
	public void hashCodeTest() {		
		Playlist playlist1 = new Playlist(1L,"Choonz","Bangers","Picture",null);
		Playlist playlist2 = new Playlist(1L,"Choonz","Bangers","Picture",null);
		
		assertTrue(playlist1.hashCode() == playlist2.hashCode());
	}
	
	@Test
	public void equalsTest() {
		Playlist playlist = new Playlist(1L,"Choonz","Bangers","Picture",null);
		Track track = new Track();
		
		assertTrue(playlist.equals(playlist));
		assertFalse(playlist.equals(track));
	}
}
