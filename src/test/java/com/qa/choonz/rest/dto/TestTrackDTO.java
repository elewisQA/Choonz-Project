package com.qa.choonz.rest.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Playlist;

public class TestTrackDTO {

	TrackDTO testTrack;
    final long id=1L;
    final String name="Song";
    final Album album=null;
    private List<Playlist> playlists;
    final Float duration = 1.80F;
    final String lyrics="Lyrics";
    
	@BeforeEach
	void init() {
		this.playlists = new ArrayList<Playlist>();
		this.testTrack = new TrackDTO(
				this.id,
				this.name,
				this.album,
				this.playlists,
				this.duration,
				this.lyrics);
	}
    
	@Test
	void testZeroArgsConstructor() {
		TrackDTO newTestTrack = new TrackDTO();

		assertTrue(newTestTrack instanceof TrackDTO);
	}
	
	@Test
	void testAllArgsConstructor() {
		Long newId = this.id + 1;
		String newName = "New song";
		Album newAlbum = null;
		List<Playlist> newPlaylists = new ArrayList<Playlist>();
		Float newDuration = 2.4F;
		String newLyrics = "New lyrics";
		TrackDTO newTestTrack = new TrackDTO(
				newId, 
				newName,
				newAlbum,
				newPlaylists,
				newDuration,
				newLyrics);	
	
		assertTrue(newTestTrack instanceof TrackDTO);
	}
	
	@Test
	void getSetIdTest() {
		Long newId = 2l;
		this.testTrack.setId(newId);

		assertEquals(newId,this.testTrack.getId());
	}
	
	@Test
	void getSetNameTest() {
		String newName = "New song";
		this.testTrack.setName(newName);

		assertEquals(newName,this.testTrack.getName());
	}
	
	@Test
	void getSetAlbumTest() {
		Album newAlbum = new Album();
		this.testTrack.setAlbum(newAlbum);
		
		assertEquals(newAlbum, this.testTrack.getAlbum());
	}
	
	@Test
	void getSetPlaylistTest() {
		List<Playlist> newPlaylists = new ArrayList<Playlist>();
		this.testTrack.setPlaylists(newPlaylists);
		
		assertEquals(newPlaylists,this.testTrack.getPlaylists());
	}
	
	@Test
	void getSetDurationTest() {
		Float newDuration = 2.4F;
		this.testTrack.setDuration(newDuration);

		assertEquals(newDuration,this.testTrack.getDuration());
	}
	
	@Test
	void getSetLyricsTest() {
		String newLyrics = "La la la";
		this.testTrack.setLyrics(newLyrics);

		assertEquals(newLyrics,this.testTrack.getLyrics());
	}
	
	@Test
	public void toStringTests() {
		assertTrue(this.testTrack.toString()
				.equals("TrackDTO [id=1, name=Song, album=null, playlist=[], duration=1.8, lyrics=Lyrics]"));
	}
	
	@Test
	public void hashCodeTest() {		
		TrackDTO track1 = new TrackDTO(1L,"Pop",null,null,1.8F,"Lyrics");
		TrackDTO track2 = new TrackDTO(1L,"Pop",null,null,1.8F,"Lyrics");
		
		assertTrue(track1.hashCode() == track2.hashCode());
	}
	
	@Test
	void testEquals() {
		TrackDTO emptyTrack = new TrackDTO();
		TrackDTO fullTrack = new TrackDTO(
				this.id, 
				this.name,
				this.album,
				this.playlists,
				this.duration,
				this.lyrics);
		
		assertTrue(!this.testTrack.equals(emptyTrack));
		assertTrue(this.testTrack.equals(fullTrack));
	}
	
	@AfterEach
	void teardown() {
		this.testTrack = null;
	}
}
