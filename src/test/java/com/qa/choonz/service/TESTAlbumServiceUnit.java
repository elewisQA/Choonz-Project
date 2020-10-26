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
import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.persistence.repository.AlbumRepository;
import com.qa.choonz.rest.dto.AlbumDTO;


@SpringBootTest
public class TESTAlbumServiceUnit {

	@Autowired
	private AlbumService service;
	
	@MockBean
	private AlbumRepository repo;
	
	@MockBean
	private ModelMapper modelMapper;
	//--[ Test Variables ]--
		private AlbumDTO albumtDTO;
		Track testTracks;
		Artist testArtist;
		Album testAlbum;
		Album testAlbumtWithId;
		final Long id = 1l;
		final String name = "Beauty Behind The Madness";
//		List<Track> testTracks;
		List<Album> testAlbums;

		//--[ Test Setup ]--
		@BeforeEach
		void init() {
			// Initialize testing vars
			this.testAlbums = new ArrayList<Album>();
			this.testAlbumtWithId = new Album(this.id, testAlbum.getName(),
					testAlbum.getTracks(), testAlbum.getArtist(),
					testAlbum.getGenre(), testAlbum.getCover());

		}
		
		@Test
		void testCreateAlbum() {
			
		}
		
		@Test
		void testReadAlbum() {

		}
		
		@Test
		void testUpdateAlbum() {
		
		}
		
		@Test
		void testDeleteAlbum() {

		}
}
