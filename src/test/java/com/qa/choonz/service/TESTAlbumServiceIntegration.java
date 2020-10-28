package com.qa.choonz.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.persistence.repository.AlbumRepository;
import com.qa.choonz.rest.dto.AlbumDTO;

@SpringBootTest
public class TESTAlbumServiceIntegration {

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
	
	private Album testAlbum;
	private Album testAlbumWithId;
	private AlbumDTO testAlbumDTO;
	private Artist testArtist;
	private Genre testGenre;
	
	private final String name = "Beauty Behind The Madness";
	private final String artistName = "The Weeknd";
	private final String genreName = "R&B";
	private final String cover = "Test Cover";
	private List<Track> testTracks;
	private List<Album> testAlbums;
	private Long id = 1L;
	
	
	@BeforeEach
	void init() {
		this.repo.deleteAll();
		this.testAlbum = new Album(id, name, testTracks, 
				testArtist, testGenre, cover);
		this.testAlbumWithId = this.repo.save(this.testAlbum);
		this.testAlbumDTO = this.mapToDTO(testAlbumWithId);
		this.id = this.testAlbumWithId.getId();
	}
	
	@Test
	void testCreate() {
		assertThat(this.testAlbumDTO)
		.isEqualTo(this.service.create(testAlbum));
	}
	
	@Test
	void testRead() {
		assertThat(this.testAlbumDTO)
			.isEqualTo(this.service.read(this.id));
	}
	
	@Test
	void testReadAll() {
		assertThat(this.service.read())
		.isEqualTo(Stream.of(this.testAlbumDTO)
				.collect(Collectors.toList()));
	}
	
	@Test
	void testUpdate() {
		AlbumDTO newAlbum = new AlbumDTO(id, "Starboy", testTracks, testArtist, testGenre, cover);
		AlbumDTO updatedAlbum =
				new AlbumDTO(this.id, newAlbum.getName(), newAlbum.getTracks(),
						newAlbum.getArtistName(), newAlbum.getGenre(), newAlbum.getCover());
		
		assertThat(updatedAlbum).isEqualTo(this.service.update(newAlbum, this.id));
	}
	
	@Test
	void testDelete() {
		assertThat(this.service.delete(this.id)).isTrue();
		
	}
}
