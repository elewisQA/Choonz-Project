package com.qa.choonz.rest.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.repository.ArtistRepository;
import com.qa.choonz.rest.dto.ArtistDTO;
import com.qa.choonz.service.ArtistService;

@SpringBootTest
class TestArtistControllerUnit {
	
	@Autowired
	private ArtistController controller;
	
	@MockBean
	private ArtistService service;
	
	@Autowired
	private ModelMapper modelMapper;
	//--[ Test Variables ]--
		Album testAlbum;
		ArtistDTO artistDTO;
		Artist testArtist;
		Artist testArtistWithId;
		final Long id = 1l;
		final String name = "Pink Floyd";
		final String picture = "Picture";
		List<Album> testAlbums;
		List<Artist> testArtists;
		
		private ArtistDTO mapToDTO(Artist artist) {
			return this.modelMapper.map(artist, ArtistDTO.class);
		}
		
		//--[ Test Setup ]--
				@BeforeEach
				void init() {
					// Initialize testing vars
					this.testArtists = new ArrayList<>();
					this.testAlbums = new ArrayList<Album>();
					this.testArtist = new Artist(
							this.id, 
							this.name,
							this.picture,
							this.testAlbums);
					testArtists.add(testArtist);
				}
		
			
		@Test
		void testCreateArtist() {
			when(this.service.create(testArtist)).thenReturn(this.artistDTO);
			
			ArtistDTO testCreated = this.artistDTO;
			assertThat(new ResponseEntity<ArtistDTO>(testCreated, HttpStatus.CREATED))
				.isEqualTo(this.controller.create(testArtist));
				
			verify(this.service, times(1)).create(this.testArtist);
			
		}
		
		@Test
		void testReadArtist() {
			when(this.service.read(this.id)).thenReturn(this.artistDTO);
			ArtistDTO testReadOne = this.artistDTO;
			assertThat(new ResponseEntity<ArtistDTO>(testReadOne, HttpStatus.OK))
				.isEqualTo(this.controller.read(this.id));
			
			verify(this.service, times(1)).read(this.id);
		}
		
		@Test
		void testReadAllArtists() {
			when(this.service.read()).thenReturn(this.testArtists.stream()
					.map(this::mapToDTO).collect(Collectors.toList()));
			
			assertThat(this.controller.read().getBody().isEmpty()).isFalse();
			verify(this.service, times(1)).read();
			
		}
		
		@Test
		void testUpdateArtist() {
			ArtistDTO newArtist = new ArtistDTO(this.id, this.name,this.picture, this.testAlbums);
			ArtistDTO newArtistWithId = new ArtistDTO(this.id, newArtist.getName(),newArtist.getPicture(),this.testAlbums);
			
			when(this.service.update(testArtist, this.id)).thenReturn(newArtistWithId);
			
			assertThat(new ResponseEntity<ArtistDTO>(newArtistWithId, HttpStatus.ACCEPTED))
				.isEqualTo(this.controller.update(testArtist, this.id));
			
			verify(this.service, times(1)).update(testArtist, this.id);
		}
		
		@Test
		void testDeleteArtist() {
			ResponseEntity<ArtistDTO> response = controller.delete(this.id);
			assertEquals(response.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
			// TODO Check out this status code issue as well
			
		}
		
		@AfterEach
		void teardown() {
			
		}

}
