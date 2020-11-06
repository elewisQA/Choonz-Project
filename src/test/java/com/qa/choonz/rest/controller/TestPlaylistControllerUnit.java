package com.qa.choonz.rest.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.persistence.domain.User;
import com.qa.choonz.rest.dto.PlaylistDTO;
import com.qa.choonz.service.PlaylistService;
import com.qa.choonz.utils.AuthUtils;

@SpringBootTest
class TestPlaylistControllerUnit {

	@Autowired
	private PlaylistController controller;
	
	@MockBean
	private PlaylistService service;
	
	@Autowired
	private ModelMapper modelMapper;
	
	private PlaylistDTO mapToDTO(Playlist playlist) {
		return this.modelMapper.map(playlist, PlaylistDTO.class);
	}
	
    private List<Playlist> playlistList;
    private Playlist testPlaylist;
    private PlaylistDTO playlistDTO;
    
    private final Long id = 1L;
    private final String name = "Tunes";
    private final String description = "Bangers only";
    private final String artwork = "artwork";
    private List<Track> tracks;
    private User testUser = null;
    
    private String token;
    
	@BeforeAll
	static void setup() {
		AuthUtils.initialize();
	}
    
    @BeforeEach
    void init() {
    	// Initialize User
    	this.testUser = new User();
    	this.testUser.setId(this.id);
    	this.testUser.setUsername("user");
    	this.testUser.setPassword("pass");
    	this.testUser.setPlaylists(new ArrayList<Playlist>());   	
    	
    	// Initialize Playlist
    	this.playlistList = new ArrayList<>();
    	this.tracks = new ArrayList<>();
    	this.testPlaylist = new Playlist(this.id,this.name,this.description
    			,this.artwork,this.tracks,this.testUser);
    	this.playlistList.add(testPlaylist);
    	this.playlistDTO = this.mapToDTO(testPlaylist);
    	
    	this.token = AuthUtils.newToken(this.id);
    }
    
    @Test
    void createTest() {
        when(this.service.create(testPlaylist)).thenReturn(this.playlistDTO);
        
        HttpHeaders headers = new HttpHeaders();
    	headers.add("token", token);
        
        assertThat(new ResponseEntity<PlaylistDTO>(this.playlistDTO, headers, HttpStatus.CREATED).getBody())
                .isEqualTo(this.controller.create(testPlaylist, this.token).getBody());
        
        verify(this.service, times(1)).create(this.testPlaylist);
    }
    
    @Test
    void readTest() {
    	when(this.service.read(this.id)).thenReturn(this.playlistDTO);
    	
    	assertThat(new ResponseEntity<PlaylistDTO>(this.playlistDTO,HttpStatus.OK))
    		.isEqualTo(this.controller.read(this.id));
    	
    	verify(this.service,times(1)).read(this.id);
    }
    
    @Test
    void readAllTest() {
    	when(this.service.read())
    		.thenReturn(this.playlistList
    				.stream()
    				.map(this::mapToDTO)
    				.collect(Collectors.toList()));
    	
    	assertThat(this.controller.read().getBody().isEmpty()).isFalse();
    	
    	verify(this.service, times(1)).read();
    }
    
    @Test
    void updateTest() {
    	Playlist newPlaylist = new Playlist(this.id,this.name,this.description,this.artwork
    			,this.tracks,this.testUser);
    	
    	PlaylistDTO updatedPlaylist = new PlaylistDTO(this.id,
    			newPlaylist.getName(),
    			newPlaylist.getDescription(),
    			newPlaylist.getArtwork(),
    			newPlaylist.getTracks(),
    			newPlaylist.getUser());
    	
    	when(service.update(newPlaylist, id)).thenReturn(updatedPlaylist);
    	
    	assertThat(new ResponseEntity<PlaylistDTO>(updatedPlaylist,HttpStatus.ACCEPTED))
		.isEqualTo(this.controller.update(newPlaylist,this.id, this.token));
    }
    
    @Test
    void deleteTest() {
    	when(service.read(this.id)).thenReturn(this.playlistDTO);
    	
    	this.controller.delete(this.testUser.getId().toString(), this.id, this.token);
    	
    	verify(this.service, times(1)).delete(id);
    }
    
    @Test
    void addTrackTest() {
    	when(service.addTrack(this.testPlaylist.getId(),2L)).thenReturn(playlistDTO);
    	when(service.read(this.id)).thenReturn(this.playlistDTO);
    	
    	assertThat(new ResponseEntity<PlaylistDTO>(this.playlistDTO,HttpStatus.ACCEPTED))
    	.isEqualTo(this.controller.add(this.testUser.getId().toString(), 2L, this.testPlaylist.getId(), this.token));
    	
    	verify(this.service,times(1)).addTrack(this.testPlaylist.getId(), 2L);
    }
    
    @Test
    void removeTrackTest() {
    	when(service.removeTrack(this.testPlaylist.getId(),1L)).thenReturn(playlistDTO);
    	when(service.read(this.id)).thenReturn(this.playlistDTO);
    	
    	assertThat(new ResponseEntity<PlaylistDTO>(this.playlistDTO,HttpStatus.ACCEPTED))
    	.isEqualTo(this.controller.remove(this.testUser.getId().toString(), this.testPlaylist.getId(), 1L,this.token));
    	
    	verify(this.service,times(1)).removeTrack(this.testPlaylist.getId(), 1L);
    }
}
