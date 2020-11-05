package com.qa.choonz.rest.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
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
import com.qa.choonz.rest.dto.AlbumDTO;
import com.qa.choonz.rest.dto.ArtistDTO;
import com.qa.choonz.rest.dto.GenreDTO;
import com.qa.choonz.rest.dto.PlaylistDTO;
import com.qa.choonz.rest.dto.TrackDTO;
import com.qa.choonz.service.ArtistService;
import com.qa.choonz.persistence.repository.ArtistRepository;
import com.qa.choonz.rest.controller.SearchController;
@SpringBootTest
@AutoConfigureMockMvc
class TestSearchControllerIntegration {
	
	@Autowired
	private MockMvc mock;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private ArtistRepository artistRepo;
	
	private ArtistDTO artistDTO;
	private AlbumDTO albumDTO;
	private GenreDTO genreDTO;
	private TrackDTO trackDTO;
	private PlaylistDTO playlistDTO;
	private ArtistService artistService;
	
	private final String qArtist = "week";
	private final String qAlbum = "kiss";
	private final String qGenre = "r&";
	private final String qTrack = "adapt";
	private final String qPlaylist = "heart";

	
	@BeforeEach
	void init() {
		artistService = new ArtistService(artistRepo, modelMapper);
	}
	
	@Test
	void testSearchArtists() throws Exception {
		String artistString = "[{\"id\":4,\"name\":\"The Weeknd\",\"";
		
		String output = this.mock.perform(request(HttpMethod.GET, "/search/artists/" + this.qArtist)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isFound()).andReturn().getResponse().getContentAsString();
		
		String[] splitOutput = output.split("alb", 2);
		
		assertEquals(artistString, splitOutput[0]);
	}
	
	@Test
	void testSearchAlbums() throws Exception {
		String albumString = "[{\"id\":4,\"name\":\"Kiss Land\",\"";
		
		String output = this.mock.perform(request(HttpMethod.GET, "/search/albums/" + this.qAlbum)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isFound()).andReturn().getResponse().getContentAsString();
	
		String[] splitOutput = output.split("track", 2);
		
		assertEquals(albumString, splitOutput[0]);
	}
	
	@Test
	void testSearchGenres() throws Exception {
		String genreString= "[{\"id\":3,\"name\":\"R&B\",\"";
		
		String output = this.mock.perform(request(HttpMethod.GET, "/search/genres/" + this.qGenre)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isFound()).andReturn().getResponse().getContentAsString();
		
		String[] splitOutput = output.split("pic", 2);
		
		assertEquals(genreString, splitOutput[0]);
	}
	
	@Test
	void testSearchTracks() throws Exception {
		String trackString = "[{\"id\":15,\"name\":\"Adaptation\",\"";
		
		String output = this.mock.perform(request(HttpMethod.GET, "/search/tracks/" + this.qTrack)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isFound()).andReturn().getResponse().getContentAsString();
		
		String[] splitOutput = output.split("alb", 2);
		
		assertEquals(trackString, splitOutput[0]);
	}
	
	@Test
	void testSearchPlaylists() throws Exception {
		List<PlaylistDTO> playlistSearch = new ArrayList<>();
		playlistSearch.add(this.playlistDTO);
		
		String output = this.mock.perform(request(HttpMethod.GET, "/search/playlists/" + this.qPlaylist)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isFound()).andReturn().getResponse().getContentAsString();
		
		assertEquals(output, output);
	}
	
	
	public static boolean containsIgnoreCase(String str, String subString) {
        return str.toLowerCase().contains(subString.toLowerCase());
    }
}
