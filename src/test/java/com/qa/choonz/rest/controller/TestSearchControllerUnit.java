package com.qa.choonz.rest.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qa.choonz.rest.dto.AlbumDTO;
import com.qa.choonz.rest.dto.ArtistDTO;
import com.qa.choonz.rest.dto.GenreDTO;
import com.qa.choonz.rest.dto.PlaylistDTO;
import com.qa.choonz.rest.dto.TrackDTO;
import com.qa.choonz.service.AlbumService;
import com.qa.choonz.service.ArtistService;
import com.qa.choonz.service.GenreService;
import com.qa.choonz.service.PlaylistService;
import com.qa.choonz.service.TrackService;

@SpringBootTest
@AutoConfigureMockMvc
class TestSearchControllerUnit {
	
	@Autowired
	private SearchController controller;
	
	@Autowired
	 private ModelMapper modelMapper;
	
	@MockBean
	private AlbumService albumService;
	
	@MockBean
	private ArtistService artistService;
	
	@MockBean
	private GenreService genreService;
	
	@MockBean
	private PlaylistService playlistService;
	
	@MockBean
	private TrackService trackService;
	
	@Test
	void searchAlbumsTest() {
		List<AlbumDTO> albums = new ArrayList<>();
		
		when(this.albumService.read()).thenReturn(albums);
		
		assertThat(new ResponseEntity<List<AlbumDTO>>(albums,HttpStatus.FOUND))
		.isEqualTo(this.controller.searchAlbums("Album"));
		
		verify(this.albumService,times(1)).read();
	}
	
	@Test
	void searchArtistsTest() {
		List<ArtistDTO> artists = new ArrayList<>();
		
		when(this.artistService.read()).thenReturn(artists);
		
		assertThat(new ResponseEntity<List<ArtistDTO>>(artists,HttpStatus.FOUND))
		.isEqualTo(this.controller.searchAlbums("Artist"));
	}
	
	@Test
	void searchGenresTest() {
		List<GenreDTO> genres = new ArrayList<>();
		
		when(this.genreService.read()).thenReturn(genres);
		
		assertThat(new ResponseEntity<List<GenreDTO>>(genres,HttpStatus.FOUND))
		.isEqualTo(this.controller.searchAlbums("Genre"));
	}
	
	@Test
	void searchPlaylistsTest() {
		List<PlaylistDTO> playlists = new ArrayList<>();
		
		when(this.playlistService.read()).thenReturn(playlists);
		
		assertThat(new ResponseEntity<List<PlaylistDTO>>(playlists,HttpStatus.FOUND))
		.isEqualTo(this.controller.searchAlbums(""));
	}
	
	@Test
	void searchTracksTest() {
		List<TrackDTO> tracks = new ArrayList<>();
		
		when(this.trackService.read()).thenReturn(tracks);
		
		assertThat(new ResponseEntity<List<TrackDTO>>(tracks,HttpStatus.FOUND))
		.isEqualTo(this.controller.searchAlbums(""));
	}

}
