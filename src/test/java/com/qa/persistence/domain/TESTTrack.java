package com.qa.persistence.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.Track;

public class TESTTrack {
		
	@Test
	public void constructorTests() {
		Track emptyTrack = new Track();
		
		assertTrue(emptyTrack instanceof Track);
		
		Album album = new Album();
		Playlist playlist = new Playlist();
		
		Track track = new Track(1L,"Song",album,playlist,180,"Lyrics");
		
		assertTrue(track instanceof Track);
	}
	
	@Test
	public void getSetIdTest() {
		Track emptyTrack = new Track();
		
		emptyTrack.setId(1L);
		
		assertEquals(1L,emptyTrack.getId());
	}
	
	@Test
	public void getSetNameTest() {
		Track emptyTrack = new Track();
		emptyTrack.setName("Song");
		
		assertEquals("Song",emptyTrack.getName());
	}
	
	@Test
	public void getSetAlbumTest() {
		Track emptyTrack = new Track();
		Album album = new Album();
		
		emptyTrack.setAlbum(album);
		
		assertEquals(album,emptyTrack.getAlbum());
	}
	
	@Test
	public void getSetPlaylistTest() {
		Track emptyTrack = new Track();
		Playlist playlist = new Playlist();
		
		emptyTrack.setPlaylist(playlist);
		
		assertEquals(playlist,emptyTrack.getPlaylist());
	}
	
	@Test
	public void getSetDurationTest() {
		Track emptyTrack = new Track();
		
		emptyTrack.setDuration(180);
		
		assertEquals(180,emptyTrack.getDuration());
	}
	
	@Test
	public void getSetLyricsTest() {
		Track emptyTrack = new Track();
		
		emptyTrack.setLyrics("La la la");
		
		assertEquals("La la la",emptyTrack.getLyrics());
	}
	
	@Test
	public void toStringTests() {		
		Track track = new Track(1L,"Song",null,null,180,"Lyrics");
		
		assertNotNull(track.toString());
		assertEquals("Track [id=1, name=Song, album=null, playlist=null, duration=180, lyrics=Lyrics]"
				,track.toString());
	}
	
	@Test
	public void hashCodeTest() {
		Album album = new Album();
		Playlist playlist = new Playlist();
		
		Track track1 = new Track(1L,"Song",album,playlist,180,"Lyrics");
		Track track2 = new Track(1L,"Song",album,playlist,180,"Lyrics");
		
		assertTrue(track1.hashCode() == track2.hashCode());
	}
	
	@Test
	public void equalsTest() {
		Album album = new Album();
		Playlist playlist = new Playlist();
		
		Track track = new Track(1L,"Song",album,playlist,180,"Lyrics");
		
		assertTrue(track.equals(track));
		assertFalse(track.equals(album));
	}
	
	

}
