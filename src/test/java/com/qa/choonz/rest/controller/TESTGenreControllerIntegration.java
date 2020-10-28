package com.qa.choonz.rest.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.persistence.repository.GenreRepository;
import com.qa.choonz.rest.dto.GenreDTO;

@SpringBootTest
@AutoConfigureMockMvc
public class TESTGenreControllerIntegration {
	
    @Autowired
    private MockMvc mock;
    
    @Autowired
    private GenreRepository repo;
    
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ObjectMapper objectMapper;

    private GenreDTO mapToDTO(Genre genre) {
    	return this.modelMapper.map(genre, GenreDTO.class);
    }
    
    private Genre testGenre;
    private Genre testGenreWithId;
    private GenreDTO genreDTO;
    
	private Long id;
	final String name = "R&B";
	final String description = "Meaningful music";
	private List<Album> testAlbums;
	
    @BeforeEach
    void init() {
    	this.repo.deleteAll();
    	
    	this.testAlbums = new ArrayList<>();
    	this.testGenre = new Genre();
    	this.testGenre.setName(this.name);
    	this.testGenre.setDescription(this.description);
    	this.testGenre.setAlbums(this.testAlbums);
    	this.testGenreWithId = this.repo.save(this.testGenre);
    	this.genreDTO = this.mapToDTO(this.testGenreWithId);
    	
    	this.id = this.testGenreWithId.getId();
    }
    
    @Test
    void testCreate() throws Exception {
        this.mock
        	.perform(request(HttpMethod.POST, "/genres/create").contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(testGenre))
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(content().json(this.objectMapper.writeValueAsString(genreDTO)));
    }
    
    @Test
    void testReadOne() throws Exception{
    	this.mock
    			.perform(request(HttpMethod.GET,"/genres/read/" + this.id).accept(MediaType.APPLICATION_JSON))
    			.andExpect(status().isOk())
    			.andExpect(content().json(this.objectMapper.writeValueAsString(this.genreDTO)));
    }
    
    @Test
    void testReadAll() throws Exception{
    	List<GenreDTO> genreList = new ArrayList<>();
    	genreList.add(this.genreDTO);
    	
    	String content = this.mock
    			.perform(request(HttpMethod.GET,"/genres/read").accept(MediaType.APPLICATION_JSON))
    			.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
    	
    	assertEquals(this.objectMapper.writeValueAsString(genreList),content);
    }
    
    @Test
    void testUpdate() throws Exception{
    	Genre newGenre = new Genre();
    	newGenre.setId(this.id);
    	newGenre.setName("Rock");
    	newGenre.setDescription("Guitar smash");
    	newGenre.setAlbums(null);
    	Genre updatedGenre = new Genre();
    	updatedGenre.setId(this.id);
    	updatedGenre.setName(newGenre.getName());
    	updatedGenre.setDescription(newGenre.getDescription());
    	updatedGenre.setAlbums(null);
    	
        String output = this.mock
                .perform(request(HttpMethod.POST, "/genres/update/" + this.id).accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(newGenre)))
                .andExpect(status().isAccepted()).andReturn().getResponse().getContentAsString();
    	
    	assertEquals(this.objectMapper.writeValueAsString(this.mapToDTO(updatedGenre)),output);
    }
    
    @Test
    void testDelete() throws Exception {
        this.mock.perform(request(HttpMethod.DELETE, "/genres/delete/" + this.id)).andExpect(status().isNoContent());
    }
}
