package com.qa.choonz.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.persistence.repository.PlaylistRepository;
import com.qa.choonz.rest.dto.PlaylistDTO;

@SpringBootTest
public class TESTPlaylistServiceUnit {

	@Autowired
	private PlaylistService service;
	
	@MockBean
	private PlaylistRepository repo;
	
	@MockBean
	private ModelMapper modelMapper;
	
    private PlaylistDTO mapToDTO(Playlist playlist) {
        return this.modelMapper.map(playlist, PlaylistDTO.class);
    }
    
    Playlist testPlaylist;
    Playlist testPlaylistWithId;
    private PlaylistDTO playlistDTO;
    
    final Long id = 1L;
    final String name = "Tunes";
    final String description = "Bangers only";
    final String artwork = "artwork";
    List<Track> tracks;
    
    @BeforeEach
    void init() {
    	this.tracks = new ArrayList<>();
    	this.testPlaylist = new Playlist(this.id,this.name,this.description
    			,this.artwork,this.tracks);
    	this.playlistDTO = this.mapToDTO(testPlaylist);
    }
    
    @Test
    void createTest() {
    	when(this.repo.save(this.testPlaylist)).thenReturn(this.testPlaylistWithId);
    	
    	when(this.modelMapper.map(this.testPlaylistWithId,PlaylistDTO.class)).thenReturn(this.playlistDTO);
    	
    	PlaylistDTO expected = this.playlistDTO;
    	PlaylistDTO actual = this.service.create(this.testPlaylist);
    	assertThat(expected).isEqualTo(actual);
    	
    	verify(this.repo, times(1)).save(this.testPlaylist);
    }
    
    
}
