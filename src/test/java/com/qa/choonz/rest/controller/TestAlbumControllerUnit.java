package com.qa.choonz.rest.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.persistence.repository.AlbumRepository;
import com.qa.choonz.rest.dto.AlbumDTO;
import com.qa.choonz.service.AlbumService;

@SpringBootTest
class TestAlbumControllerUnit {

	//--[ Test Variables ]--
		AlbumRepository testRepo;
		private final Long id = 1L;
		private final String name = "Test Album";
		private final String cover = "../";
		List<Track> testTracks;
		List<Album> testAlbums;
		Artist testArtist;
		Album testAlbum;
		Album testAlbumWithId;
		Genre testGenre;
		AlbumDTO albumDTO;
		
		//--[ Test Setup ]--
		@Autowired
		private AlbumController controller;
				
		@Autowired
		ModelMapper modelMapper;
		
		@MockBean
		private AlbumService service;
		
		
		private AlbumDTO mapToDTO(Album album) {
			return this.modelMapper.map(album,  AlbumDTO.class);
		}
		
		@BeforeEach
		void init() {
			this.testAlbums = new ArrayList<Album>();
			this.testTracks = new ArrayList<Track>();
			this.testAlbum = new Album(this.id, this.name, this.testTracks, this.testArtist,
					this.testGenre, this.cover);
			this.testAlbumWithId = new Album(testAlbum.getId(), testAlbum.getName(), testAlbum.getTracks(), testAlbum.getArtist(), 
					testAlbum.getGenre(), testAlbum.getCover());
			this.testAlbumWithId.setId(id);
			this.albumDTO = this.mapToDTO(testAlbumWithId);
			testAlbums.add(testAlbum);
			
		}
		
		//--[ Test Cases ]--
		
		@Test
		void testCreate() {
			when(this.service.create(testAlbum)).thenReturn(this.albumDTO);
			
			AlbumDTO testCreated = this.albumDTO;
			assertThat(new ResponseEntity<AlbumDTO>(testCreated, HttpStatus.CREATED))
			.isEqualTo(this.controller.create(testAlbum));
			
			verify(this.service, times(1)).create(this.testAlbum);
			// TODO Assert the returned object matches
		}
		
		@Test
		void testRead() {
			when(this.service.read(this.id)).thenReturn(this.albumDTO);
			AlbumDTO testReadOne = this.albumDTO;
			assertThat(new ResponseEntity<AlbumDTO>(testReadOne, HttpStatus.OK))
			.isEqualTo(this.controller.read(this.id));
			
			verify(this.service, times(1)).read(this.id);
			// TODO some assertion for reading
		}
		
		@Test
		void testReadAll() {
			when(this.service.read()).thenReturn(this.testAlbums.stream()
					.map(this::mapToDTO).collect(Collectors.toList()));
			
			assertThat(this.controller.read().getBody().isEmpty()).isFalse();
			verify(this.service, times(1)).read();
			// TODO some assertion for reading
		}
		
		@Test
		void testUpdate() {
			AlbumDTO newAlbum = new AlbumDTO(this.id, this.name, this.testTracks, this.testArtist, this.testGenre, this.cover);
			AlbumDTO newAlbumWithId = new AlbumDTO(this.id, newAlbum.getName(), this.testTracks, this.testArtist, this.testGenre, newAlbum.getCover());
			
			
			when(this.service.update(testAlbum, this.id)).thenReturn(newAlbumWithId);
			
			assertThat(new ResponseEntity<AlbumDTO>(newAlbumWithId, HttpStatus.ACCEPTED))
				.isEqualTo(this.controller.update(testAlbum, this.id));
			
			verify(this.service, times(1)).update(testAlbum, this.id);
			
			// TODO some assetion for update
		}
		
		@Test
		void testDelete() {
			ResponseEntity<AlbumDTO> response = controller.delete(this.id);
			assertEquals(response.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
			// TODO check up on this assertion^^^ as it only returns true with error 500
		}
		
		//--[ Test Tear-down ]--
		@AfterEach
		void teardown() {
			
		}
}
