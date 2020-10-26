package com.qa.choonz.rest.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.persistence.repository.GenreRepository;
import com.qa.choonz.rest.dto.GenreDTO;
import com.qa.choonz.service.GenreService;

//===[ Testinc Code ]===
public class TESTGenreController {
	//--[ Test Variables ]--
	GenreController testController;
	GenreService testService;
	GenreRepository testRepo;
	ModelMapper testMapper;
	final Long id = 1L;
	final String name = "Test Genre";
	final String desc = "Description";
	Genre testGenre;
	GenreDTO testDTO;
	List<Album> albums;
	
	//--[ Test Setup ]--
	@BeforeEach
	void init() {
		// Initialize service & controller
		this.testService = new GenreService(
				this.testRepo,
				this.testMapper);
		this.testController = new GenreController(
				this.testService);
		// Initialize genre & genre DTO
		this.albums = new ArrayList<Album>();
		this.testGenre = new Genre(
				this.id,
				this.name,
				this.desc,
				this.albums);
		this.testDTO = new GenreDTO(
				this.id,
				this.name,
				this.desc,
				this.albums);
	}
	
	//--[ Test Cases ]--
	@Test
	void testConstructor() {
		GenreController newController = new GenreController(
				this.testService);
		assertThat(newController instanceof GenreController);
	}
	
	//--[ Test Tear-down ]--
	@AfterEach
	void teardown() {
		this.testController = null;
	}
}
