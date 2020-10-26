package com.qa.choonz.rest.controller;

//--[ Imports ]--
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.persistence.repository.AlbumRepository;
import com.qa.choonz.rest.dto.AlbumDTO;
import com.qa.choonz.service.AlbumService;

//===[ Testing Code ]===
public class TESTAlbumController {
	//--[ Test Variables ]--
	AlbumController testController;
	AlbumService testService;
	AlbumRepository testRepo;
	ModelMapper testMapper;
	final Long id = 1L;
	final String name = "Test Album";
	final String cover = "../";
	List<Track> tracks;
	Album testAlbum;
	AlbumDTO testDTO;
	
	//--[ Test Setup ]--
	@BeforeEach
	void init() {
		// Initialize service & controller
		this.testService = new AlbumService(
				this.testRepo, 
				this.testMapper);
		this.testController = new AlbumController(
				this.testService);
		// Initialize album & album-dto
		this.tracks = new ArrayList<Track>();
		this.testAlbum = new Album(
				this.id, 
				this.name, 
				this.tracks, 
				null, 
				null, 
				this.cover);
		this.testDTO = new AlbumDTO(
				this.id, 
				this.name, 
				this.tracks, 
				null, 
				null, 
				this.cover);
	}
	
	//--[ Test Cases ]--
	@Test
	void testConstructor() {
		AlbumController newController = new AlbumController(
				this.testService);
		assertThat(newController instanceof AlbumController);
	}
	
	//--[ Test Tear-down ]--
	@AfterEach
	void teardown() {
		this.testController = null;
	}
}
