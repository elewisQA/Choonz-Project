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

import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.persistence.repository.GenreRepository;
import com.qa.choonz.rest.dto.GenreDTO;

@SpringBootTest
public class TESTGenreServiceIntegration {

	@Autowired
	private GenreService service;
	
	@Autowired
	private GenreRepository repo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	private GenreDTO mapToDTO(Genre genre) {
		return this.modelMapper.map(genre, GenreDTO.class);
	}
	
	private Long id = 1L;
	private final String name = "R&B";
	private final String description = "nice music";
	private List<Album> testAlbums;
	private Genre testGenre;
	private Genre testGenreWithId;
	private GenreDTO genreDTO;
	private GenreDTO genreDTOWithId;
	
	@BeforeEach
	void init() {
		this.repo.deleteAll();
		this.testGenre = new Genre();
		this.testGenre.setId(1L);
		this.testGenre.setName("R&B");
		this.testGenre.setDescription("nice music");
		
		this.testAlbums = new ArrayList<Album>();
		
		this.testGenre = new Genre();
		this.testGenre.setName(this.name);
		this.testGenre.setDescription(this.description);
		this.testGenre.setAlbums(this.testAlbums);
		this.testGenreWithId = this.repo.save(testGenre);
		this.id = this.testGenreWithId.getId();
		
		this.genreDTO = this.mapToDTO(this.testGenreWithId);
		this.genreDTOWithId = new GenreDTO();
		this.genreDTOWithId.setId(this.genreDTO.getId());
		this.genreDTOWithId.setName(this.genreDTO.getName());
		this.genreDTOWithId.setDescription(this.genreDTO.getDescription());
		this.genreDTOWithId.setAlbums(this.genreDTO.getAlbums());
		
	}
	
	@Test
	void testCreate() {
		System.out.println(this.testGenre.toString());
		System.out.println(this.genreDTOWithId.toString());
		assertThat(this.genreDTOWithId).isEqualTo(this.service.create(this.testGenre));
		
	}
	
	@Test
	void testRead() {
		assertThat(this.genreDTOWithId).isEqualTo(this.service.read(this.id));
	}
	
	@Test
	void testReadAll() {
		assertThat(Stream.of(this.genreDTOWithId).collect(Collectors.toList()))
			.isEqualTo(this.service.read());
	}
	
	@Test
	void testUpdate() {
		assertThat(this.genreDTOWithId).isEqualTo(this.service.update(this.testGenre, this.id));
	}
	
	@Test
	void testDelete() {
		assertThat(this.service.delete(this.id)).isTrue();
	}
}
