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
import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.persistence.repository.TrackRepository;
import com.qa.choonz.rest.dto.ArtistDTO;
import com.qa.choonz.rest.dto.PlaylistDTO;
import com.qa.choonz.rest.dto.TrackDTO;

@SpringBootTest
@AutoConfigureMockMvc
public class TESTTrackControllerIntegration {

    @Autowired
    private MockMvc mock;
    
    @Autowired
    private TrackRepository repo;
    
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ObjectMapper objectMapper;
    
    private TrackDTO mapToDTO(Track track) {
    	return this.modelMapper.map(track, TrackDTO.class);
    }
    
    private Track testTrack;
    private Track testTrackWithId;
    private TrackDTO trackDTO;
    
	private Long id;
	private final String name = "Song";
	private final Album album = null;
	private List<Playlist> playlist = null;
	private final int duration = 180;
	private final String lyrics = "Lyrics";
	
    @BeforeEach
    void init() {
    	this.repo.deleteAll();
    	
    	this.playlist = new ArrayList<Playlist>();
    	
    	this.testTrack = new Track();
    	this.testTrack.setName(this.name);
    	this.testTrack.setAlbum(this.album);
    	this.testTrack.setPlaylists(this.playlist);
    	this.testTrack.setDuration(this.duration);
    	this.testTrack.setLyrics(this.lyrics);
    	this.testTrackWithId = this.repo.save(this.testTrack);
    	this.trackDTO = this.mapToDTO(this.testTrackWithId);
    	
    	this.id = this.testTrackWithId.getId();
    }
    
    @Test
    void testCreate() throws Exception {
        this.mock
        	.perform(request(HttpMethod.POST, "/tracks/create").contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(testTrack))
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(content().json(this.objectMapper.writeValueAsString(trackDTO)));
    }
    
    @Test
    void testReadOne() throws Exception{
    	this.mock
    			.perform(request(HttpMethod.GET,"/tracks/read/" + this.id).accept(MediaType.APPLICATION_JSON))
    			.andExpect(status().isOk())
    			.andExpect(content().json(this.objectMapper.writeValueAsString(this.trackDTO)));
    }
    
    @Test
    void testReadAll() throws Exception{
    	List<TrackDTO> trackList = new ArrayList<>();
    	trackList.add(this.trackDTO);
    	
    	String content = this.mock
    			.perform(request(HttpMethod.GET,"/tracks/read").accept(MediaType.APPLICATION_JSON))
    			.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
    	
    	assertEquals(this.objectMapper.writeValueAsString(trackList),content);
    }
    
    @Test
    void testUpdate() throws Exception{
    	Track newTrack = new Track();
    	newTrack.setId(this.id);
    	newTrack.setName("Big tune");
    	newTrack.setAlbum(this.album);
    	newTrack.setPlaylists(this.playlist);
    	newTrack.setDuration(240);
    	newTrack.setLyrics("New lyrics");
    	Track updatedTrack = new Track();
    	updatedTrack.setId(this.id);
    	updatedTrack.setName(newTrack.getName());
    	updatedTrack.setAlbum(newTrack.getAlbum());
    	updatedTrack.setPlaylists(newTrack.getPlaylists());
    	updatedTrack.setDuration(newTrack.getDuration());
    	updatedTrack.setLyrics(newTrack.getLyrics());
    	
        String output = this.mock
                .perform(request(HttpMethod.POST, "/tracks/update/" + this.id).accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(newTrack)))
                .andExpect(status().isAccepted()).andReturn().getResponse().getContentAsString();
    	
    	assertEquals(this.objectMapper.writeValueAsString(this.mapToDTO(updatedTrack)),output);
    }
    
    @Test
    void testDelete() throws Exception {
        this.mock.perform(request(HttpMethod.DELETE, "/tracks/delete/" + this.id)).andExpect(status().isNoContent());
    }
}
