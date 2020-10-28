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
import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.repository.ArtistRepository;
import com.qa.choonz.rest.dto.ArtistDTO;

@SpringBootTest
@AutoConfigureMockMvc
public class TESTArtistControllerIntegration {

    @Autowired
    private MockMvc mock;
    
    @Autowired
    private ArtistRepository repo;
    
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ObjectMapper objectMapper;
    
    private ArtistDTO mapToDTO(Artist artist) {
    	return this.modelMapper.map(artist, ArtistDTO.class);
    }
    
    private Artist testArtist;
    private Artist testArtistWithId;
    private ArtistDTO artistDTO;
    
    private Long id;
    private final String name ="Drake";
    private List<Album> testAlbums;
    
    @BeforeEach
    void init() {
    	this.repo.deleteAll();
    	
    	this.testAlbums= new ArrayList<>();
    	this.testArtist = new Artist();
    	this.testArtist.setName(this.name);
    	this.testArtist.setAlbums(this.testAlbums);
    	this.testArtistWithId = this.repo.save(this.testArtist);
    	this.artistDTO = this.mapToDTO(this.testArtistWithId);
    	
    	this.id = this.testArtistWithId.getId();
    }
    
    @Test
    void testCreate() throws Exception {
        this.mock
        	.perform(request(HttpMethod.POST, "/artists/create").contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(testArtist))
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(content().json(this.objectMapper.writeValueAsString(artistDTO)));
    }
    
    @Test
    void testReadOne() throws Exception{
    	this.mock
    			.perform(request(HttpMethod.GET,"/artists/read/" + this.id).accept(MediaType.APPLICATION_JSON))
    			.andExpect(status().isOk())
    			.andExpect(content().json(this.objectMapper.writeValueAsString(this.artistDTO)));
    }
    
    @Test
    void testReadAll() throws Exception{
    	List<ArtistDTO> artistList = new ArrayList<>();
    	artistList.add(this.artistDTO);
    	
    	String content = this.mock
    			.perform(request(HttpMethod.GET,"/artists/read").accept(MediaType.APPLICATION_JSON))
    			.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
    	
    	assertEquals(this.objectMapper.writeValueAsString(artistList),content);
    }
    
    @Test
    void testUpdate() throws Exception{
    	ArtistDTO newArtist = new ArtistDTO();
    	newArtist.setId(this.id);
    	newArtist.setName("Tyler the creator");
    	newArtist.setAlbums(null);
    	Artist updatedArtist = new Artist();
    	updatedArtist.setName(newArtist.getName());
    	updatedArtist.setAlbums(null);
    	updatedArtist.setId(this.id);
    	
        String output = this.mock
                .perform(request(HttpMethod.PUT, "/artists/update/" + this.id).accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(newArtist)))
                .andExpect(status().isAccepted()).andReturn().getResponse().getContentAsString();
    	
    	assertEquals(this.objectMapper.writeValueAsString(this.mapToDTO(updatedArtist)),output);
    }
    
    
    @Test
    void testDelete() throws Exception {
        this.mock.perform(request(HttpMethod.DELETE, "/artists/delete/" + this.id)).andExpect(status().isNoContent());
    }
}
