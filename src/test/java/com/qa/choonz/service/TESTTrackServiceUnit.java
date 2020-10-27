package com.qa.choonz.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.persistence.repository.TrackRepository;
import com.qa.choonz.rest.dto.TrackDTO;

@SpringBootTest
public class TESTTrackServiceUnit {

	@Autowired
	private TrackService service;
	
	@MockBean
	private TrackRepository repo;
	
	@MockBean
	private ModelMapper modelMapper;
	
    private TrackDTO mapToDTO(Track track) {
        return this.modelMapper.map(track, TrackDTO.class);
    }
    
    private List<Track> trackList;
    private Track testTrack;
    private Track testTrackWithId;
    private TrackDTO trackDTO;
    
    final Long id = 1L;
    final String name = "Song";
    final Album album = null;
    final Playlist playlist = null;
    final int duration = 180;
    final String lyrics = "Lyrics";
    
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
    	when(this.repo.save(this.testTrack)).thenReturn(this.testTrackWithId);
    	
    	when(this.modelMapper.map(this.testTrackWithId,TrackDTO.class)).thenReturn(this.trackDTO);
    	
    	TrackDTO expected = this.trackDTO;
    	TrackDTO actual = this.service.create(this.testTrack);
    	assertThat(expected).isEqualTo(actual);
    	
    	verify(this.repo, times(1)).save(this.testTrack);
    }
    
    @Test
    void readOneTest() {
		when(this.repo.findById(this.id)).thenReturn(Optional.of(this.testTrack));
		
		when(this.modelMapper.map(testTrackWithId, TrackDTO.class)).thenReturn(trackDTO);
		
		assertThat(this.trackDTO).isEqualTo(this.service.read(this.id));
		
		verify(this.repo, times(1)).findById(this.id);
    }
    
    
    @Test
    void realAllTest() {
        when(repo.findAll()).thenReturn(this.trackList);

        when(this.modelMapper.map(this.testTrackWithId, TrackDTO.class)).thenReturn(this.trackDTO);

        assertThat(this.service.read().isEmpty()).isFalse();

        verify(repo, times(1)).findAll();
    }
    
    @Test
    void updateTest(){
    	Track track = new Track(this.id,this.name,this.album,this.playlist,this.duration
    			,this.lyrics);
    	
    	TrackDTO trackDTO = new TrackDTO(this.id,this.name,this.album,this.playlist,this.duration
    			,this.lyrics);
    	
    	Track updatedTrack = new Track(this.id,trackDTO.getName(),
    			this.album,
    			this.playlist,
    			trackDTO.getDuration(),
    			trackDTO.getLyrics());
    	
    	TrackDTO updatedTrackDTO = new TrackDTO(this.id,updatedTrack.getName(),updatedTrack.getAlbum()
    			,updatedTrack.getPlaylist(),updatedTrack.getDuration(),updatedTrack.getLyrics());
    	
		when(this.repo.findById(this.id)).thenReturn(Optional.of(track));
		
		when(this.repo.save(track)).thenReturn(updatedTrack);
		
		when(this.modelMapper.map(updatedTrack, TrackDTO.class)).thenReturn(updatedTrackDTO);
		
		assertThat(updatedTrackDTO).isEqualTo(this.service.update(testTrack, this.id));
		
		verify(this.repo, times(1)).findById(1L);
		verify(this.repo, times(1)).save(updatedTrack);
    	
    }
    
	@Test
	void testDelete() {
		when(this.repo.existsById(id)).thenReturn(true, false);
		
		assertThat(this.service.delete(id)).isFalse();
		verify(this.repo, times(1)).deleteById(id);
		verify(this.repo, times(1)).existsById(id);
	}
}
