package com.qa.choonz.rest.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.choonz.exception.TrackNotFoundException;
import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.persistence.domain.User;
import com.qa.choonz.persistence.repository.PlaylistRepository;
import com.qa.choonz.persistence.repository.TrackRepository;
import com.qa.choonz.rest.dto.PlaylistDTO;
import com.qa.choonz.rest.dto.TrackDTO;
import com.qa.choonz.service.TrackService;
import com.qa.choonz.utils.AuthUtils;

@SpringBootTest
@AutoConfigureMockMvc
class TestPlaylistControllerIntegration {
	
    @Autowired
    private MockMvc mock;
    
    @Autowired
    private PlaylistRepository repo;
    
    @Autowired
    private TrackRepository trackRepo;
    
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ObjectMapper objectMapper;
    
    private PlaylistDTO mapToDTO(Playlist playlist) {
    	return this.modelMapper.map(playlist, PlaylistDTO.class);
    }
    
    private Playlist testPlaylist;
    private Playlist testPlaylistWithId;
    private PlaylistDTO playlistDTO;
    
    private Long id;
    private final String name = "Tunes";
    private final String description = "Bangers only";
    private final String artwork = "artwork";
    private List<Track> tracks;
    private final User testUser = null;
    
	private String token;
	private HttpHeaders headers;
    
	@BeforeAll
	static void setup() {
		AuthUtils auth = new AuthUtils();
	}
    
    @BeforeEach
    void init() {
    	this.repo.deleteAll();
    	
    	this.tracks = new ArrayList<>();
    	this.testPlaylist = new Playlist();
    	this.testPlaylist.setName(this.name);
    	this.testPlaylist.setDescription(this.description);
    	this.testPlaylist.setArtwork(this.artwork);
    	this.testPlaylist.setTracks(this.tracks);
    	this.testPlaylist.setUser(this.testUser);
    	this.testPlaylistWithId = this.repo.save(this.testPlaylist);
    	this.playlistDTO = this.mapToDTO(this.testPlaylistWithId);
    	
    	this.id = this.testPlaylistWithId.getId();
    	this.token = AuthUtils.newToken(this.id);
    	this.headers = new HttpHeaders();
    	headers.add("token", token);
    }
    
    @Test
    void testCreate() throws Exception {
        this.mock
        	.perform(request(HttpMethod.POST, "/playlists/create").header("token", token).contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(testPlaylist))
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(content().json(this.objectMapper.writeValueAsString(playlistDTO)));
    }
    
    @Test
    void testReadOne() throws Exception{
    	this.mock
    			.perform(request(HttpMethod.GET,"/playlists/read/" + this.id).accept(MediaType.APPLICATION_JSON))
    			.andExpect(status().isOk())
    			.andExpect(content().json(this.objectMapper.writeValueAsString(this.playlistDTO)));
    }
    
    @Test
    void testReadAll() throws Exception{
    	List<PlaylistDTO> playlistList = new ArrayList<>();
    	playlistList.add(this.playlistDTO);
    	
    	String content = this.mock
    			.perform(request(HttpMethod.GET,"/playlists/read").accept(MediaType.APPLICATION_JSON))
    			.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
    	
    	assertEquals(this.objectMapper.writeValueAsString(playlistList),content);
    }
    
    @Test
    void testUpdate() throws Exception{
    	Playlist newPlaylist = new Playlist();
    	newPlaylist.setId(this.id);
    	newPlaylist.setName("Big tunes");
    	newPlaylist.setDescription("Big bangers");
    	newPlaylist.setArtwork("Art");
    	newPlaylist.setUser(null);
    	newPlaylist.setTracks(new ArrayList<>());
    	Playlist updatedPlaylist = new Playlist();
    	updatedPlaylist.setId(this.id);
    	updatedPlaylist.setName(newPlaylist.getName());
    	updatedPlaylist.setDescription(newPlaylist.getDescription());
    	updatedPlaylist.setArtwork(newPlaylist.getArtwork());
    	updatedPlaylist.setUser(null);
    	updatedPlaylist.setTracks(new ArrayList<>());
    	
        String output = this.mock
                .perform(request(HttpMethod.POST, "/playlists/update/" + this.id).header("token", token).accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(newPlaylist)))
                .andExpect(status().isAccepted()).andReturn().getResponse().getContentAsString();
    	
    	assertEquals(this.objectMapper.writeValueAsString(this.mapToDTO(updatedPlaylist)),output);
    }
    
    
    @Test
    void testDelete() throws Exception {
        this.mock.perform(request(HttpMethod.DELETE, "/playlists/delete/" + this.id).header("token", token)).andExpect(status().isNoContent());
    }
    
    @Test
    void testAddTrack() throws Exception{
    	Track track = this.trackRepo.findById(2L).orElseThrow(TrackNotFoundException::new);
    	List<Track> trackList = new ArrayList<>();
    	List<Playlist> playlistList = new ArrayList<>();
    	playlistList.add(testPlaylist);
    	track.setPlaylists(playlistList);
    	trackList.add(track);
    	testPlaylist.setTracks(trackList);
    	
        String output = this.mock
                .perform(request(HttpMethod.POST, "/playlists/add/" + this.id + "/" + 2L).header("token", token).accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(testPlaylist)))
                .andExpect(status().isAccepted()).andReturn().getResponse().getContentAsString();
    	
    	assertEquals(this.objectMapper.writeValueAsString(this.mapToDTO(testPlaylist)),output);
    }
    
    @Test
    void testDeleteTrack() throws Exception{
    	Track track = this.trackRepo.findById(2L).orElseThrow(TrackNotFoundException::new);
    	
        String output = this.mock
                .perform(request(HttpMethod.POST, "/playlists/remove/" + this.id + "/" + 2L).header("token", token).accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(testPlaylist)))
                .andExpect(status().isAccepted()).andReturn().getResponse().getContentAsString();
    	
    	assertEquals(this.objectMapper.writeValueAsString(this.mapToDTO(testPlaylist)),output);
    }
    
}
