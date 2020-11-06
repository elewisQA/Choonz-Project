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
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.persistence.repository.AlbumRepository;
import com.qa.choonz.rest.dto.AlbumDTO;

@SpringBootTest
class TestAlbumServiceIntegration {

	// because we're testing the service layer, we can't use a MockMvc
    // because MockMvc only models a controller (in mockito format)
	
	@Autowired
	private AlbumService service;
	
	@Autowired
	private AlbumRepository repo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	private AlbumDTO mapToDTO(Album album) {
		return this.modelMapper.map(album, AlbumDTO.class);
	}
	
	// there's no objectMapper this time
    // because we don't need to convert any returned objects to JSON
    // that's a controller job, and we're not testing the controller!
	
	private Long id = 1L;
	private final String name = "Beauty Behind The Madness";
	private final String artist = "The Weeknd";
	private final String cover = "Test Cover";
	private Genre testGenre;
	private Artist testArtist;
	private List<Track> testTracks;
	private List<Album> testAlbums;
	//private List<Album> testAlbums;
	private Album testAlbum;
	private Album testAlbumWithId;
	private AlbumDTO albumDTOWithId;
	
	
	
	@BeforeEach
	void init() {
		this.repo.deleteAll();
	
		testAlbums = new ArrayList<>();
		
		// Setup Artist
		this.testArtist = new Artist();
		this.testArtist.setId(3);
		this.testArtist.setName("Gorrilaz");
		this.testArtist.setAlbums(testAlbums);
		
		
		// Setup Genre
		this.testGenre = new Genre();
		this.testGenre.setId(2);
		this.testGenre.setName("Psychedelic Rock");
		
		// Instantiate 
		this.testTracks = new ArrayList<Track>();
		
		this.testAlbum = new Album();
		this.testAlbum.setName(this.name);
		this.testAlbum.setTracks(this.testTracks);
		//this.testAlbum.setArtist(this.testArtist);
		this.testAlbum.setArtist(null);
		//this.testAlbum.setGenre(this.testGenre);
		this.testAlbum.setGenre(null);
		this.testAlbum.setCover(this.cover);
		this.testAlbumWithId = this.repo.save(testAlbum);
		this.id = this.testAlbumWithId.getId();
		
		this.albumDTOWithId = this.mapToDTO(this.testAlbumWithId);		
	}
	
	@Test
	void testCreate() {
		assertThat(this.albumDTOWithId.getName())
		.isEqualTo(this.service
				.create(this.testAlbum).getName());
		assertThat(this.albumDTOWithId.getId())
		.isEqualTo(this.service
				.create(this.testAlbum).getId());
	}
	
	@Test
	void testRead() {
		assertThat(this.albumDTOWithId)
			.isEqualTo(this.service.read(this.id));
	}
	
	@Test
	void testReadAll() throws Exception {
		assertThat(Stream.of(this.albumDTOWithId).collect(Collectors.toList()))
		.isEqualTo(this.service.read());
	}
	
	@Test
	void testUpdate() {		
		assertThat(this.albumDTOWithId.getName())
		.isEqualTo(this.service
				.update(this.testAlbum, this.id).getName());
		assertThat(this.albumDTOWithId.getId())
		.isEqualTo(this.service
				.update(this.testAlbum, this.id).getId());
	}
	
	@Test
	void testDelete() {
		assertThat(this.service.delete(this.id)).isTrue();
		
	}
}
