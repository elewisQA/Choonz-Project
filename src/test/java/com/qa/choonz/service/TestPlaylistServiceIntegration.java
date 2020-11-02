package com.qa.choonz.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.persistence.domain.User;
import com.qa.choonz.persistence.repository.PlaylistRepository;
import com.qa.choonz.rest.dto.PlaylistDTO;

//===[ Testing Code ]===
@SpringBootTest
class TestPlaylistServiceIntegration {
	//--[ Set-up Test Integrations ]--
	@Autowired
	private PlaylistService service;
	
	@Autowired
	private PlaylistRepository repo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	//--[ Define Mapping Method ]--
	private PlaylistDTO mapToDTO(Playlist playlist) {
		return this.modelMapper.map(playlist, PlaylistDTO.class);
	}
	
	//--[ Set-up Test Variables ]--
	private Long id;
	private final String NAME = "Playlist Tree";
	private final String DESC = "This is a test playlist.";
	private final String ART = "../";
	private List<Track> tracks;
	private User testUser;
	private Playlist testPlaylist;
	private Playlist testPlaylistWithId;
	private PlaylistDTO playlistDTOWithId;
	
	//===[ All Test Code ]===
	//--[ Test Setup ]--
	@BeforeEach
	void init() {
		this.repo.deleteAll();
		tracks = new ArrayList<Track>();
		// Instantiate the test-playlist
		testPlaylist = new Playlist();
		testPlaylist.setName(NAME);
		testPlaylist.setDescription(DESC);
		testPlaylist.setTracks(tracks);
		testPlaylist.setArtwork(ART);
		testPlaylist.setUser(testUser);
		this.testPlaylistWithId = this.repo.save(testPlaylist);
		this.id = this.testPlaylistWithId.getId();
		
		// Instantiate DTOs
		this.playlistDTOWithId = this.mapToDTO(this.testPlaylistWithId);
	}
	
	//--[ Test Cases ]--
	@Test
	void testCreate() throws Exception {
		assertThat(this.playlistDTOWithId.getName())
		.isEqualTo(this.service
				.create(this.testPlaylist).getName());
		assertThat(this.playlistDTOWithId.getId())
		.isEqualTo(this.service
				.create(this.testPlaylist).getId());
	}
	
	@Test
	void testReadOne() {
		assertThat(this.playlistDTOWithId).isEqualTo(this.service.read(this.id));
	}
	
	@Test
	void testRead() {
		assertThat(Stream.of(this.playlistDTOWithId)
				.collect(Collectors.toList()))
		.isEqualTo(this.service.read());
	}
	
	@Test
	void testUpdate() throws Exception {
		assertThat(this.playlistDTOWithId.getName())
		.isEqualTo(this.service
				.update(this.testPlaylist, this.id).getName());
		assertThat(this.playlistDTOWithId.getId())
		.isEqualTo(this.service
				.update(this.testPlaylist, this.id).getId());
	}
	
	@Test
	void testDelete() throws Exception {
		assertThat(this.service.delete(this.id)).isTrue();
	}
}
