package com.qa.choonz.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.persistence.repository.AlbumRepository;
import com.qa.choonz.persistence.repository.GenreRepository;
import com.qa.choonz.rest.dto.GenreDTO;

@SpringBootTest
public class TESTGenreServiceUnit {
	
	@Autowired
	private GenreService service;
	
	@MockBean
	private GenreRepository repo;
	
	@MockBean
	private ModelMapper modelMapper;
	
	private GenreDTO genreDTO;
	private Genre testGenre;
	private Genre testGenreWithId;
	private Album testAlbum;
	private List<Album> testAlbums;
	private List<Genre> testGenres;
	
	final Long id = 1L;
	final String name = "R&B";
	final String description = "Meaningful music";
	
	
	@BeforeEach
	void init() {
		this.testAlbums = new ArrayList<Album>();
		this.testGenres = new ArrayList<Genre>();
		this.testGenre = new Genre(this.id, this.name, this.description, this.testAlbums);
		testGenres.add(testGenre);
	}
	
	@Test
	void testCreateGenre() {
		when(this.repo.save(this.testGenre)).thenReturn(this.testGenreWithId);
		when(this.modelMapper.map(this.testGenreWithId, GenreDTO.class)).thenReturn(this.genreDTO);
		
		GenreDTO expected = this.genreDTO;
		GenreDTO actual = this.service.create(this.testGenre);
		assertThat(expected).isEqualTo(actual);
		
	}
	
	@Test
	void testReadGenre() {
		when(this.repo.findById(this.id)).thenReturn(Optional.of(this.testGenre));
		when(this.modelMapper.map(this.testGenreWithId, GenreDTO.class)).thenReturn(this.genreDTO);
		
		assertThat(this.genreDTO).isEqualTo(this.service.read(this.id));
		verify(this.repo, times(1)).findById(this.id);
	}
	
	@Test
	void testReadAllGenre() {
		when(this.repo.findAll()).thenReturn(this.testGenres);
		when(this.modelMapper.map(testGenreWithId, GenreDTO.class)).thenReturn(genreDTO);
		
		assertThat(this.service.read().isEmpty()).isFalse();
		verify(this.repo, times(1)).findAll();
	}
	
	@Test
	void testUpdateGenre() {
		Genre genre = new Genre(this.id, this.name, this.description, this.testAlbums);
		GenreDTO genreDTO = new GenreDTO(this.id, this.name, this.description, this.testAlbums);
		
		Genre updatedGenre = new Genre(this.id, genreDTO.getName(), genreDTO.getDescription(), genreDTO.getAlbums());
		GenreDTO updatedGenreDTO = new GenreDTO(this.id, updatedGenre.getName(), updatedGenre.getDescription(), updatedGenre.getAlbums());
		
		when(this.repo.findById(this.id)).thenReturn(Optional.of(genre));
		when(this.repo.save(genre)).thenReturn(updatedGenre);
		when(this.modelMapper.map(updatedGenre, GenreDTO.class)).thenReturn(updatedGenreDTO);
		
		assertThat(updatedGenreDTO).isEqualTo(this.service.update(testGenre, this.id));
		verify(this.repo, times(1)).findById(1L);
		verify(this.repo, times(1)).save(updatedGenre);
	}
	
	@Test
	void testDeleteGenre() {
		when(this.repo.existsById(id)).thenReturn(true, false);
		
		assertThat(this.service.delete(id)).isFalse();
		verify(this.repo, times(1)).deleteById(id);
		verify(this.repo, times(1)).existsById(id);
		
	}

}
