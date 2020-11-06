package com.qa.choonz.rest.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
import com.qa.choonz.persistence.repository.UserRepository;
import com.qa.choonz.rest.dto.PlaylistDTO;
import com.qa.choonz.utils.AuthUtils;

@SpringBootTest
@AutoConfigureMockMvc
class TestPlaylistControllerIntegration {
	
    @Autowired
    private MockMvc mock;
    
    @Autowired
    private PlaylistRepository repo;
    
    @Autowired
    private UserRepository userRepo;
    
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
    private Long uid;
    private final String name = "Tunes";
    private final String description = "Bangers only";
    private final String artwork = "artwork";
    private List<Track> tracks;
    private User testUser = null;
    
	private String token;
	private HttpHeaders headers;
    
	@BeforeAll
	static void setup() {	
		AuthUtils.initialize();
	}
    
    @BeforeEach
    void init() {    	
    	this.repo.deleteAll();
    	// Initialize User
    	// RANDOM NAME GENERATION
    	// for reasons
    	this.testUser = new User();
    	Random rng = new Random();
		String name = rng.ints(48, 122 + 1)
	    	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))	// Filter-method to avoid going out of range
	    	      .limit(5)
	    	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	    	      .toString();
    	this.testUser.setUsername(name);
    	this.testUser.setPassword("pass");
    	this.testUser.setPlaylists(new ArrayList<Playlist>());	
    	this.testUser = this.userRepo.save(testUser);
    	this.uid = this.testUser.getId();
    	
    	// Initialize Playlist
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
    	this.token = AuthUtils.newToken(this.uid);
    	
    	// Header Setup
    	this.headers = new HttpHeaders();
    	this.headers.add("Content-Type", "application/json");
    	this.headers.add("token", token);
    	this.headers.add("uid", this.uid.toString());
    }
    
    @Test
    void testCreate() throws Exception {
        this.mock
        	.perform(request(HttpMethod.POST, "/playlists/create").headers(this.headers)
                .content(this.objectMapper.writeValueAsString(this.testPlaylist))
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());
        //.andExpect(content().json(this.objectMapper.writeValueAsString(playlistDTO)));
    }
    
    @Test
    void testReadOne() throws Exception{
    	this.mock.perform(request(HttpMethod.GET,"/playlists/read/" + this.id).accept(MediaType.APPLICATION_JSON))
    	.andExpect(status().isOk())
    	.andExpect(jsonPath("$.user.username").value(this.testUser.getUsername()))
    	.andExpect(jsonPath("$.id").value(this.id));
    }
    
    /*
    @Test
    void testReadAll() throws Exception{ 	
    	List<PlaylistDTO> playlistList = new ArrayList<>();
    	playlistList.add(this.playlistDTO);
    	
    	String content = this.mock
    			.perform(request(HttpMethod.GET,"/playlists/read").accept(MediaType.APPLICATION_JSON))
    			.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
    	
    	assertEquals(this.objectMapper.writeValueAsString(playlistList),content);
    }
    */
    
    @Test
    void testUpdate() throws Exception{
    	String newName = "Big Tunes";
    	Playlist newPlaylist = new Playlist();
    	newPlaylist.setId(this.id);
    	newPlaylist.setName(newName);
    	newPlaylist.setDescription("Big bangers");
    	newPlaylist.setArtwork("Art");
    	newPlaylist.setUser(null);
    	newPlaylist.setTracks(new ArrayList<>());
    	/*
    	Playlist updatedPlaylist = new Playlist();
    	updatedPlaylist.setId(this.id);
    	updatedPlaylist.setName(newPlaylist.getName());
    	updatedPlaylist.setDescription(newPlaylist.getDescription());
    	updatedPlaylist.setArtwork(newPlaylist.getArtwork());
    	updatedPlaylist.setUser(null);
    	updatedPlaylist.setTracks(new ArrayList<>());
    	*/
    	
        this.mock.perform(request(HttpMethod.POST, "/playlists/update/" + this.id).header("token", token).accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(newPlaylist)))
                .andExpect(jsonPath("$.name").value(newName))
            	.andExpect(jsonPath("$.id").value(this.id))
                .andExpect(status().isAccepted());
    	
    }
    
    
    @Test
    void testDelete() throws Exception {
        this.mock.perform(request(HttpMethod.DELETE, "/playlists/delete/" + this.id).headers(this.headers)).andExpect(status().isNoContent());
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
                .perform(request(HttpMethod.POST, "/playlists/add/" + this.id + "/" + 2L).headers(this.headers).accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(testPlaylist)))
                .andExpect(jsonPath("$.user.username").value(this.testUser.getUsername()))
            	.andExpect(jsonPath("$.id").value(this.id))
                .andExpect(status().isAccepted()).andReturn().getResponse().getContentAsString();
        // TODO update JSON Expect's to relate to the test     	
    }
    
    @Test
    void testDeleteTrack() throws Exception{
    	Track track = this.trackRepo.findById(2L).orElseThrow(TrackNotFoundException::new);
    	
        String output = this.mock
                .perform(request(HttpMethod.POST, "/playlists/remove/" + this.id + "/" + 2L).headers(this.headers).accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(testPlaylist)))
                .andExpect(jsonPath("$.user.username").value(this.testUser.getUsername()))
            	.andExpect(jsonPath("$.id").value(this.id))
                .andExpect(status().isAccepted()).andReturn().getResponse().getContentAsString();
    	// TODO update JSON Expect's to relate to the test
    }
}
