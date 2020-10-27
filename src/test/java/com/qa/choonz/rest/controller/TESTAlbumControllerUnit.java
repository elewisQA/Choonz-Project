package com.qa.choonz.rest.controller;

import static org.assertj.core.api.Assertions.assertThat;
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
public class TESTAlbumControllerUnit {

	//--[ Test Variables ]--
		AlbumRepository testRepo;
		final Long id = 1L;
		final String name = "Test Album";
		final String cover = "../";
		List<Track> tracks;
		List<Album> testAlbums;
		Artist testArtist;
		Album testAlbum;
		Album testAlbumWithId;
		Genre testGenre;
		AlbumDTO albumDTO;
		
		//--[ Test Setup ]--
		@MockBean
		private AlbumRepository repo;
		
		@MockBean
		ModelMapper modelMapper;
		
		@MockBean
		private AlbumService service;
		
		@InjectMocks
		private AlbumController controller;
		
		private AlbumDTO mapToDTO(Album album) {
			return this.modelMapper.map(album,  AlbumDTO.class);
		}
		
		@BeforeEach
		void init() {
			this.testAlbums = new ArrayList<Album>();
			this.testAlbum = new Album(this.id, this.name, this.tracks, this.testArtist,
					this.testGenre, this.cover);
			this.testAlbumWithId = new Album(testAlbum.getId(), testAlbum.getName(), testAlbum.getTracks(), testAlbum.getArtist(), 
					testAlbum.getGenre(), testAlbum.getCover());
			this.testAlbumWithId.setId(id);
			this.testAlbums.add(testAlbumWithId);
			this.albumDTO = this.mapToDTO(testAlbumWithId);
			
		}
		
		//--[ Test Cases ]--
//		@Test
//		void testConstructor() {
//			AlbumController newController = new AlbumController(this.service);
//			assertThat(newController instanceof AlbumController);
//		}
		
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
//			final Long ID = 1L;
//			Album updateAlbum = mock(Album.class);
//			when(updateAlbum.getId()).thenReturn(ID);
//			controller.update(updateAlbum, ID);
			// TODO some assetion for update
		}
		
		@Test
		void testDelete() {
			final Long ID = 1L;
			ResponseEntity<AlbumDTO> response = controller.delete(ID);
			assertThat(response.getStatusCode() == HttpStatus.NO_CONTENT);
			// TODO add more assetions
		}
		
		//--[ Test Tear-down ]--
		@AfterEach
		void teardown() {
			
		}
}
