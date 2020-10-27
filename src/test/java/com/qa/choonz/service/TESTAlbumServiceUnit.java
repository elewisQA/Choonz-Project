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
import com.qa.choonz.persistence.domain.Genre;
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
//		Track testTracks;
		Genre testGenre;
		Artist testArtist;
		Album testAlbum;
		Album testAlbumtWithId;
		final Long id = 1l;
		final String name = "Beauty Behind The Madness";
		String cover = "Test";
		List<Track> testTracks;
		List<Album> testAlbums;

		//--[ Test Setup ]--
		@BeforeEach
		void init() {
			// Initialize testing vars
			this.testAlbums = new ArrayList<Album>();
			this.testAlbum = new Album(this.id, this.name,
					this.testTracks, this.testArtist,
					this.testGenre, this.cover);

		}
		
		@Test
		void testCreateAlbum() {
			when(this.repo.save(this.testAlbum)).thenReturn(this.testAlbumtWithId);
			
			when(this.modelMapper.map(this.testAlbumtWithId, AlbumDTO.class)).thenReturn(this.albumtDTO);
			
			AlbumDTO expected = this.albumtDTO;
			AlbumDTO actual = this.service.create(this.testAlbum);
			assertThat(expected).isEqualTo(actual);
			
		}
		
		@Test
		void testReadAlbum() {
			when(this.repo.findById(this.id)).thenReturn(Optional.of(this.testAlbum));
			
			when(this.modelMapper.map(this.testAlbumtWithId, AlbumDTO.class)).thenReturn(this.albumtDTO);
			
			assertThat(this.albumtDTO).isEqualTo(this.service.read(this.id));
			verify(this.repo, times(1)).findById(this.id);

		}
		
		@Test
		void testReadAllAlbums() {
			when(this.repo.findAll()).thenReturn(this.testAlbums);
			when(this.modelMapper.map(testAlbumtWithId, AlbumDTO.class)).thenReturn(albumtDTO);
			
			assertThat(this.service.read().isEmpty()).isFalse();
			verify(this.repo, times(1)).findAll();
			
		}
		
		@Test
		void testUpdateAlbum() {
			
			Album album = new Album(this.id, this.name, this.testTracks, this.testArtist, this.testGenre, this.cover);
			
			AlbumDTO albumDTO = new AlbumDTO(this.id, this.name, this.testTracks, this.testArtist, this.testGenre, this.cover);
			
			Album updatedAlbum = new Album(this.id, albumDTO.getName(), albumDTO.getTracks(), albumDTO.getArtist(), albumDTO.getGenre(), albumDTO.getCover());
			
			AlbumDTO updatedAlbumDTO = new AlbumDTO(this.id, updatedAlbum.getName(), updatedAlbum.getTracks(), updatedAlbum.getArtist(), updatedAlbum.getGenre(), updatedAlbum.getCover());
			
			
			when(this.repo.findById(this.id)).thenReturn(Optional.of(album));
			when(this.repo.save(album)).thenReturn(updatedAlbum);
			when(this.modelMapper.map(updatedAlbum, AlbumDTO.class)).thenReturn(updatedAlbumDTO);
			
			assertThat(updatedAlbumDTO).isEqualTo(this.service.update(testAlbum, this.id));
			verify(this.repo, times(1)).findById(1L);
			verify(this.repo, times(1)).save(updatedAlbum);
		}
		
		@Test
		void testDeleteAlbum() {

			when(this.repo.existsById(id)).thenReturn(true, false);
			
			assertThat(this.service.delete(id)).isFalse();
			verify(this.repo, times(1)).deleteById(id);
			verify(this.repo, times(1)).existsById(id);
		}
}
