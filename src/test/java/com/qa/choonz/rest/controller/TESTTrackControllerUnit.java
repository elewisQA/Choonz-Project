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
import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.rest.dto.TrackDTO;
import com.qa.choonz.service.TrackService;

@SpringBootTest
public class TESTTrackControllerUnit {
	
	@Autowired
	private TrackController controller;
	
	@MockBean
	private TrackService service;
	
	@Autowired
	private ModelMapper modelMapper;
	
	private TrackDTO mapToDTO(Track track) {
		return this.modelMapper.map(track, TrackDTO.class);
	}
	
	private List<Track> trackList;
	private Track testTrack;
	private TrackDTO trackDTO;
	private final Long id = 1L;
	private final String name = "Song";
	private final Album album = null;
	private final List<Playlist> playlist = null;
	private final int duration = 180;
	private final String lyrics = "Lyrics";

    @BeforeEach
    void init() {
    	this.trackList = new ArrayList<>();
    	this.testTrack = new Track(this.id,this.name,this.album,this.playlist,this.duration
    			,this.lyrics);
    	this.trackList.add(testTrack);
    	this.trackDTO = this.mapToDTO(testTrack);
    }
    
    @Test
    void createTest() {
        when(this.service.create(testTrack)).thenReturn(this.trackDTO);
        
        assertThat(new ResponseEntity<TrackDTO>(this.trackDTO, HttpStatus.CREATED))
                .isEqualTo(this.controller.create(testTrack));
        
        verify(this.service, times(1)).create(this.testTrack);
    }
    
    @Test
    void readTest() {
    	when(this.service.read(this.id)).thenReturn(this.trackDTO);
    	
    	assertThat(new ResponseEntity<TrackDTO>(this.trackDTO,HttpStatus.OK))
    		.isEqualTo(this.controller.read(this.id));
    	
    	verify(this.service,times(1)).read(this.id);
    }
    
    @Test
    void readAllTest() {
    	when(this.service.read())
    		.thenReturn(this.trackList
    				.stream()
    				.map(this::mapToDTO)
    				.collect(Collectors.toList()));
    	
    	assertThat(this.controller.read().getBody().isEmpty()).isFalse();
    	
    	verify(this.service, times(1)).read();
    }
    
    @Test
    void updateTest() {
    	Track newTrack = new Track(this.id,this.name,this.album,this.playlist,this.duration
    			,this.lyrics);
    	TrackDTO updatedTrack = new TrackDTO(this.id,newTrack.getName(),newTrack.getAlbum()
    			,newTrack.getPlaylists(),newTrack.getDuration(),newTrack.getLyrics());
    	
    	when(service.update(newTrack, id)).thenReturn(updatedTrack);
    	
    	assertThat(new ResponseEntity<TrackDTO>(updatedTrack,HttpStatus.ACCEPTED))
		.isEqualTo(this.controller.update(newTrack,this.id));
    }
    
    @Test
    void deleteTest() {
    	this.controller.delete(this.id);
    	
    	verify(this.service, times(1)).delete(id);
    }

}
