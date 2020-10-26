package com.qa.choonz.rest.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Playlist;

public class TESTTrackDTO {

	TrackDTO testTrack;
    final long id=1L;
    final String name="Song";
    final Album album=null;
    final Playlist playlist=null;
    final int duration=180;
    final String lyrics="Lyrics";
    
	@BeforeEach
	void init() {
		this.testTrack = new TrackDTO(
				this.id,
				this.name,
				this.album,
				this.playlist,
				this.duration,
				this.lyrics);
	}
    
	@Test
	void testZeroArgsConstructor() {
		TrackDTO newTestTrack = new TrackDTO();

		assertThat(newTestTrack instanceof TrackDTO);
	}
	
	@Test
	void testAllArgsConstructor() {
		Long newId = this.id + 1;
		String newName = "New song";
		Album newAlbum = null;
		Playlist newPlaylist = null;
		int newDuration = 240;
		String newLyrics = "New lyrics";
		TrackDTO newTestTrack = new TrackDTO(
				newId, 
				newName,
				newAlbum,
				newPlaylist,
				newDuration,
				newLyrics);	
	
		assertThat(newTestTrack instanceof TrackDTO);
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
		
		assertEquals(newAlbum,this.testTrack.getAlbum());
	}
	
	@Test
	void getSetPlaylistTest() {
		Playlist newPlaylist = new Playlist();
		this.testTrack.setPlaylist(newPlaylist);
		
		assertEquals(newPlaylist,this.testTrack.getPlaylist());
	}
	
	@Test
	void getSetDurationTest() {
		int newDuration = 240;
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
		assertThat(this.testTrack.toString()
				.equals("TrackDTO [id=1, name=Song, album=null, playlist=null, duration=180, lyrics=Lyrics]"));
	}
	
	@Test
	public void hashCodeTest() {		
		TrackDTO track1 = new TrackDTO(1L,"Pop",null,null,180,"Lyrics");
		TrackDTO track2 = new TrackDTO(1L,"Pop",null,null,180,"Lyrics");
		
		assertTrue(track1.hashCode() == track2.hashCode());
	}
	
	@Test
	void testEquals() {
		TrackDTO emptyTrack = new TrackDTO();
		TrackDTO fullTrack = new TrackDTO(
				this.id, 
				this.name,
				this.album,
				this.playlist,
				this.duration,
				this.lyrics);
		
		assertThat(!this.testTrack.equals(emptyTrack));
		assertThat(this.testTrack.equals(fullTrack));
	}
	
	@AfterEach
	void teardown() {
		this.testTrack = null;
	}
}
