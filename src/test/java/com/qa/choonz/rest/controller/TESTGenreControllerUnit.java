package com.qa.choonz.rest.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.rest.dto.GenreDTO;
import com.qa.choonz.service.GenreService;

@SpringBootTest
public class TESTGenreControllerUnit {

	@Autowired
	private GenreController controller;
	
	@MockBean
	private GenreService service;
	
	@Autowired
	private ModelMapper modelMapper;
	
	private GenreDTO mapToDTO(Genre genre) {
		return this.modelMapper.map(genre, GenreDTO.class);
	}
	
	private GenreDTO genreDTO;
	private Genre testGenre;
	private Genre testGenreWithId;
	private List<Album> testAlbums;
	private List<Genre> genreList;
	
	final Long id = 1L;
	final String name = "R&B";
	final String description = "Meaningful music";
	final String picture = "Picture";
	
	@BeforeEach
	void init() {
		this.testAlbums = new ArrayList<Album>();
		this.genreList = new ArrayList<Genre>();
		this.testGenre = new Genre(this.id, this.name,this.picture, this.description, this.testAlbums);
		genreList.add(testGenre);
	}
	
    @Test
    void createTest() {
        when(this.service.create(testGenre)).thenReturn(this.genreDTO);
        
        assertThat(new ResponseEntity<GenreDTO>(this.genreDTO, HttpStatus.CREATED))
                .isEqualTo(this.controller.create(testGenre));
        
        verify(this.service, times(1)).create(this.testGenre);
    }
    
    @Test
    void readTest() {
    	when(this.service.read(this.id)).thenReturn(this.genreDTO);
    	
    	assertThat(new ResponseEntity<GenreDTO>(this.genreDTO,HttpStatus.OK))
    		.isEqualTo(this.controller.read(this.id));
    	
    	verify(this.service,times(1)).read(this.id);
    }
    
    @Test
    void readAllTest() {
    	when(this.service.read())
    		.thenReturn(this.genreList
    				.stream()
    				.map(this::mapToDTO)
    				.collect(Collectors.toList()));
    	
    	assertThat(this.controller.read().getBody().isEmpty()).isFalse();
    	
    	verify(this.service, times(1)).read();
    }
    
    @Test
    void updateTest() {
    	Genre newGenre = new Genre(this.id,this.name,this.picture,this.description,this.testAlbums);
    	
    	GenreDTO updatedGenre = new GenreDTO(this.id,newGenre.getName(),newGenre.getPicture()
    			,newGenre.getDescription()
    			,newGenre.getAlbums());
    	
    	when(service.update(newGenre, id)).thenReturn(updatedGenre);
    	
    	assertThat(new ResponseEntity<GenreDTO>(updatedGenre,HttpStatus.ACCEPTED))
		.isEqualTo(this.controller.update(newGenre,this.id));
    }
    
    @Test
    void deleteTest() {
    	this.controller.delete(this.id);
    	
    	verify(this.service, times(1)).delete(id);
    }
}
