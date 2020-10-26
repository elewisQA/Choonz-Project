package com.qa.choonz.rest.controller;

//--[ Imports ]--
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.persistence.repository.AlbumRepository;
import com.qa.choonz.rest.dto.AlbumDTO;
import com.qa.choonz.service.AlbumService;

//===[ Testing Code ]===
public class TESTAlbumController {
	//--[ Test Variables ]--
	AlbumRepository testRepo;
	ModelMapper testMapper;
	final Long id = 1L;
	final String name = "Test Album";
	final String cover = "../";
	List<Track> tracks;
	Album testAlbum;
	AlbumDTO testDTO;
	
	//--[ Test Setup ]--
	@MockBean
	private AlbumRepository repo;
	
	@MockBean
	private AlbumService service;
	
	@InjectMocks
	private AlbumController controller;
	
	@BeforeEach
	void init() {
		
	}
	
	//--[ Test Cases ]--
	@Test
	void testConstructor() {
		AlbumController newController = new AlbumController(this.service);
		assertThat(newController instanceof AlbumController);
	}
	
	@Test
	void testCreate() {
		Album testAlbum = mock(Album.class);
		when(testAlbum.getId()).thenReturn(1L);
		controller.create(testAlbum);
		// TODO Assert the returned object matches
	}
	
	@Test
	void testRead() {
		controller.read();
		// TODO some assertion for reading
	}
	
	@Test
	void testUpdate() {
		final Long ID = 1L;
		Album updateAlbum = mock(Album.class);
		when(updateAlbum.getId()).thenReturn(ID);
		controller.update(updateAlbum, ID);
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
