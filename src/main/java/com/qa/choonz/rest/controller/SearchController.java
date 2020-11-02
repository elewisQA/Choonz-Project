package com.qa.choonz.rest.controller;

import java.util.List;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.choonz.rest.dto.AlbumDTO;
import com.qa.choonz.rest.dto.ArtistDTO;
import com.qa.choonz.rest.dto.GenreDTO;
import com.qa.choonz.rest.dto.PlaylistDTO;
import com.qa.choonz.rest.dto.TrackDTO;
import com.qa.choonz.service.*;

@RestController
@RequestMapping("/search")
@CrossOrigin
public class SearchController {

	// Services
	private ArtistService artistService;
	private AlbumService albumService;
	private GenreService genreService;
	private TrackService trackService;
	private PlaylistService playlistService;
	
	public SearchController(ArtistService artistService, AlbumService albumService, 
			GenreService genreService, TrackService trackService,
			PlaylistService playlistService) {
		super();
		this.artistService = artistService;
		this.albumService = albumService;
		this.genreService = genreService;
		this.trackService = trackService;
		this.playlistService = playlistService;
	}
	
	/*
	@GetMapping("/{search}")
	public ResponseEntity<Object> searchAll() {
		
	}
	*/
	
	@GetMapping("/artists/{query}")
	public ResponseEntity<List<ArtistDTO>> searchArtists(@PathVariable String query) {		
		List<ArtistDTO> matchedArtists = new ArrayList<>();
		List<ArtistDTO> allArtists = this.artistService.read();
		for (ArtistDTO a: allArtists) {
			if (a.getName().contains(query)) {
				matchedArtists.add(a);
			}
		}
		return new ResponseEntity<List<ArtistDTO>>(matchedArtists, HttpStatus.FOUND);
	}
	
	@GetMapping("/albums/{query}")
	public ResponseEntity<List<AlbumDTO>> searchAlbums(@PathVariable String query) {
		List<AlbumDTO> matchedAlbums = new ArrayList<>();
		List<AlbumDTO> allAlbums = this.albumService.read();
		for (AlbumDTO a: allAlbums) {
			if (a.getName().contains(query)) {
				matchedAlbums.add(a);
			}
		}
		return new ResponseEntity<List<AlbumDTO>>(matchedAlbums, HttpStatus.FOUND);
	}
	
	@GetMapping("/genres/{query}")
	public ResponseEntity<List<GenreDTO>> searchGenres(@PathVariable String query) {
		List<GenreDTO> matchedGenres = new ArrayList<>();
		List<GenreDTO> allGenres = this.genreService.read();
		for (GenreDTO g: allGenres) {
			if (g.getName().contains(query)) {
				matchedGenres.add(g);
			}
		}
		return new ResponseEntity<List<GenreDTO>>(matchedGenres, HttpStatus.FOUND);
	}
	
	@GetMapping("/tracks/{query}")
	public ResponseEntity<List<TrackDTO>> searchTracks(@PathVariable String query) {
		List<TrackDTO> matchedTracks = new ArrayList<>();
		List<TrackDTO> allTracks = this.trackService.read();
		for (TrackDTO t: allTracks) {
			if (t.getName().contains(query)) {
				matchedTracks.add(t);
			}
		}
		return new ResponseEntity<List<TrackDTO>>(matchedTracks, HttpStatus.FOUND);
	}
	
	@GetMapping("/playlists/{query}")
	public ResponseEntity<List<PlaylistDTO>> searchPlaylists(@PathVariable String query) {
		List<PlaylistDTO> matchedPlaylists = new ArrayList<>();
		List<PlaylistDTO> allPlaylists = this.playlistService.read();
		for (PlaylistDTO p: allPlaylists) {
			if (p.getName().contains(query)) {
				matchedPlaylists.add(p);
			}
		}
		return new ResponseEntity<List<PlaylistDTO>>(matchedPlaylists, HttpStatus.FOUND);
	}
}
