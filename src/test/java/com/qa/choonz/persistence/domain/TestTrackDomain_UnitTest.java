package com.qa.choonz.persistence.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.Track;

public class TestTrackDomain_UnitTest {
	//--[ Test Variables ]--
	private Track testTrack;
	
	@BeforeEach
	void init() {
		this.testTrack = new Track();
	}
	
	@Test
	void testZeroArgsConstructor() {	
		Track newTrack = new Track();
		
		assertTrue(newTrack instanceof Track);
	}
	
	@Test
	void testAllArgsConstructor() {
		List<Playlist> playlists = new ArrayList<>();
		Album album = new Album();
		Track newTrack = new Track(1L, "track", album, playlists, 5F, "../");
		assertTrue(newTrack instanceof Track);
	}
	
	@Test
	void getSetIdTest() {		
		testTrack.setId(1L);
		
		assertEquals(1L,testTrack.getId());
	}
	
	@Test
	void getSetNameTest() {
		this.testTrack.setName("Song");
		
		assertEquals("Song",this.testTrack.getName());
	}
	
	@Test
	void getSetAlbumTest() {
		Album album = new Album();
		
		this.testTrack.setAlbum(album);
		
		assertEquals(album,this.testTrack.getAlbum());
	}
	
	@Test
	void getSetPlaylistTest() {
		List<Playlist> playlists = new ArrayList<Playlist>();
		
		this.testTrack.setPlaylists(playlists);
		
		assertEquals(playlists,this.testTrack.getPlaylists());
	}
	
	@Test
	void getSetDurationTest() {
		this.testTrack.setDuration(1.80F);
		
		assertEquals(1.80F, this.testTrack.getDuration());
	}
	
	@Test
	void getSetLyricsTest() {
		this.testTrack.setLyrics("La la la");
		
		assertEquals("La la la", this.testTrack.getLyrics());
	}
	
	@Test
	void toStringTests() {		
		Track track = new Track(1L,"Song",null,null,1.8F,"Lyrics");
		assertNotNull(track.toString());
		assertEquals("Track [id=1, name=Song, album=null, playlists=null, duration=1.8, lyrics=Lyrics]", 
				track.toString());
	}
	
	@Test
	void hashCodeTest() {
		Album album = new Album();
		List<Playlist> playlists = new ArrayList<Playlist>();
		
		Track track1 = new Track(1L,"Song",album,playlists,1.8F,"Lyrics");
		Track track2 = new Track(1L,"Song",album,playlists,1.8F,"Lyrics");
		
		assertEquals(track1.hashCode(), track2.hashCode());
	}
	
	@Test
	void equalsTest() {
		Album album = new Album();
		List<Playlist> playlists = new ArrayList<Playlist>();
		
		Track track = new Track(1L,"Song",album,playlists,1.8F,"Lyrics");
		
		assertEquals(track, track);
		assertFalse(track.equals(null));
	}
	
	@AfterEach
	void teardown() {
		
	}

}
