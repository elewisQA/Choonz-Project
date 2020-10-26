package com.qa.choonz.rest.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.modelmapper.ModelMapper;

import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.repository.ArtistRepository;
import com.qa.choonz.rest.dto.ArtistDTO;
import com.qa.choonz.service.ArtistService;

//===[ Testing Code ]===
public class TESTArtistController {
	ArtistController testController;
	ArtistService testService;
	ArtistRepository testRepo;
	ModelMapper testMapper;
	final Long id = 1L;
	final String name = "Test Artist";
	Artist testArtist;
	ArtistDTO testDTO;
	
	//--[ Test Setup ]--
	@BeforeEach
	void init() {
		// Initialize service & controller
		this.testService = new ArtistService(
				this.testRepo,
				this.testMapper);
		this.testController = new ArtistController(
				this.testService);
		// Initialize artist & artist-dto
		this.testArtist = new Artist();
		this.testDTO = new ArtistDTO();
	}
	
	//--[ Test Cases ]--
	void testConstructor() {
		ArtistController newController = new ArtistController(
				this.testService);
		assertThat(newController instanceof ArtistController);
	}
	
	//--[ Test Tear-down ]--
	@AfterEach
	void teardown() {
		this.testController = null;
	}
}
