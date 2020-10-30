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
import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.persistence.repository.AlbumRepository;
import com.qa.choonz.rest.dto.AlbumDTO;

@SpringBootTest
@AutoConfigureMockMvc
class TestAlbumControllerIntegration {

    @Autowired
    private MockMvc mock;
    
    @Autowired
    private AlbumRepository repo;
    
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ObjectMapper objectMapper;
    
    private AlbumDTO mapToDTO(Album album) {
    	return this.modelMapper.map(album, AlbumDTO.class);
    }
    
    private Album testAlbum;
    private Album testAlbumWithId;
    private AlbumDTO albumDTO;
    
    private Long id;
    private final String name = "Igor";
    private final String cover = "image";
    private List<Track> testTracks;
    
    @BeforeEach
    void init() {
    	this.repo.deleteAll();
    	
    	this.testTracks = new ArrayList<>();
    	this.testAlbum = new Album();
    	this.testAlbum.setName(this.name);
    	this.testAlbum.setCover(this.cover);
    	this.testAlbum.setTracks(this.testTracks);
    	this.testAlbumWithId = this.repo.save(this.testAlbum);
    	this.albumDTO = this.mapToDTO(this.testAlbumWithId);
    	
    	this.id = this.testAlbumWithId.getId();
    	}
    
    @Test
    void testCreate() throws Exception {
        this.mock
        	.perform(request(HttpMethod.POST, "/albums/create").contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(testAlbum))
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(content().json(this.objectMapper.writeValueAsString(albumDTO)));
    }
    
    @Test
    void testReadOne() throws Exception{
    	this.mock
    			.perform(request(HttpMethod.GET,"/albums/read/" + this.id).accept(MediaType.APPLICATION_JSON))
    			.andExpect(status().isOk())
    			.andExpect(content().json(this.objectMapper.writeValueAsString(this.albumDTO)));
    }
    
    @Test
    void testReadAll() throws Exception{
    	List<AlbumDTO> albumList = new ArrayList<>();
    	albumList.add(this.albumDTO);
    	
    	String content = this.mock
    			.perform(request(HttpMethod.GET,"/albums/read").accept(MediaType.APPLICATION_JSON))
    			.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
    	
    	assertEquals(this.objectMapper.writeValueAsString(albumList),content);
    }
    
    @Test
    void testUpdate() throws Exception{
    	Album newAlbum = new Album();
    	newAlbum.setId(this.id);
    	newAlbum.setName("Flower Boy");
    	newAlbum.setCover("new image");
    	newAlbum.setGenre(null);
    	newAlbum.setTracks(null);
    	Album updatedAlbum = new Album();
    	updatedAlbum.setName(newAlbum.getName());
    	updatedAlbum.setCover(newAlbum.getCover());
    	updatedAlbum.setGenre(null);
    	updatedAlbum.setTracks(null);
    	updatedAlbum.setId(this.id);
    	
        String output = this.mock
                .perform(request(HttpMethod.POST, "/albums/update/" + this.id).accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(newAlbum)))
                .andExpect(status().isAccepted()).andReturn().getResponse().getContentAsString();
    	
    	assertEquals(this.objectMapper.writeValueAsString(this.mapToDTO(updatedAlbum)),output);
    }
    
    @Test
    void testDelete() throws Exception {
        this.mock.perform(request(HttpMethod.DELETE, "/albums/delete/" + this.id)).andExpect(status().isNoContent());
    }
}
