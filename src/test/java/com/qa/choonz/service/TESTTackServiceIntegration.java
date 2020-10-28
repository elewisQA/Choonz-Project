package com.qa.choonz.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.persistence.repository.TrackRepository;
import com.qa.choonz.rest.dto.TrackDTO;

//===[ Testing Code ]===
@SpringBootTest
public class TESTTackServiceIntegration {
	//--[ Set-up Test Integrations ]--
	@Autowired
	private TrackService service;
	
	@Autowired
	private TrackRepository repo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	//--[ Define Mapping Method ]--
	private TrackDTO mapToDTO(Track track) {
		return this.modelMapper.map(track, TrackDTO.class);
	}
	
	//--[ Set-up Test Variables ]--
	private Long id;
	private final String NAME = "Comfortably Numb";
	private final String LYRICS = "I have become comfortably numb";
	private Album album;
	private Playlist playlist;
	private final int DURATION = 2;
	private Track testTrack;
	private Track testTrackWithId;
	private TrackDTO trackDTO;
	private TrackDTO trackDTOWithId;
	
	//===[ All Test Code ]===
	//--[ Test Setup ]--
	@BeforeEach
	void init() {
		this.repo.deleteAll();
		
		// Instantiate vars for the test-track
		album = new Album(1L, "Pink", null, null, null, "cover");
		playlist = new Playlist(1L, "Playlist", "Description", "cover", null);
		// Instantiate Album and Playlist.
		// The ID's and names for these match those found in Data.sql
		// The rest of the info here doesn't matter / isn't used by the repo
		this.album = new Album(3, "The Wall", null, null, null, null);
		this.playlist = new Playlist(1, "Playlist One", null, null, null);
		this.playlist.setId(1);
		this.playlist.setName("Playlist One");
		// Instantiate the test-track
		// Have to use setters as we don't want to include the ID 
		// And no constructor exists to set all these without an id
		this.testTrack = new Track();
		this.testTrack.setName(this.NAME);
		this.testTrack.setLyrics(this.LYRICS);
		this.testTrack.setDuration(this.DURATION);
		this.testTrack.setAlbum(album);
		this.testTrack.setPlaylist(playlist);
		this.testTrackWithId = this.repo.save(testTrack);
		this.id = this.testTrackWithId.getId();
		// instantiate track DTOs
		// DTO With ID must be set manually as you cant "get" an album from a Track DTO
		this.trackDTO = this.mapToDTO(this.testTrackWithId);
		this.trackDTOWithId = new TrackDTO();
		this.trackDTOWithId.setId(this.trackDTO.getId());
		this.trackDTOWithId.setName(this.trackDTO.getName());
		this.trackDTOWithId.setAlbumId(this.trackDTO.getAlbumId());
		this.trackDTOWithId.setAlbumName(this.trackDTO.getAlbumName());
		this.trackDTOWithId.setPlaylistName(this.trackDTO.getPlaylistName());
		this.trackDTOWithId.setPlaylistId(this.trackDTO.getPlaylistId());
		this.trackDTOWithId.setDuration(this.trackDTO.getDuration());
		this.trackDTOWithId.setLyrics(this.trackDTO.getLyrics());
	}
	
	//--[ Test Cases ]--
	@Test
	void testCreate() throws Exception {
		System.out.println(this.testTrack.toString());
		System.out.println(this.trackDTOWithId.toString());
		assertThat(this.trackDTOWithId).isEqualTo(this.service.create(this.testTrack));
	}
	
	@Test
	void testReadOne() throws Exception {
		assertThat(this.trackDTOWithId).isEqualTo(this.service.read(this.id));
	}
	
	@Test
	void testRead() throws Exception {
		assertThat(Stream.of(this.trackDTOWithId)
				.collect(Collectors.toList()))
		.isEqualTo(this.service.read());
	}
	
	@Test
	void testUpdate() throws Exception {
		assertThat(this.trackDTOWithId)
		.isEqualTo(this.service.update(this.testTrack, this.id));
	}
	
	@Test
	void testDelete() throws Exception {
		assertThat(this.service.delete(this.id)).isTrue();
	}
	
	
}
