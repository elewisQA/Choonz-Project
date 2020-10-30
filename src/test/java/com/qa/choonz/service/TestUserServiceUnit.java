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
import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.User;
import com.qa.choonz.persistence.repository.UserRepository;
import com.qa.choonz.rest.dto.ArtistDTO;
import com.qa.choonz.rest.dto.UserDTO;

@SpringBootTest
class TestUserServiceUnit {
	
	@Autowired
	private UserService service;
	
	@MockBean
	private UserRepository repo;
	
	@MockBean
	private ModelMapper modelMapper;

	//--[ Test Variables ]--
	
			private UserDTO userDTO;
			private User testUser;
			private User testUserWithId;
			final Long id = 1l;
			final String username = "Admin";
			final String password = "Guest";
			private List<Playlist> testPlaylists;
			private List<User> userList;
			
			//--[Test setup]--
			@BeforeEach
			void init() {
				this.testPlaylists = new ArrayList<Playlist>();
				this.testUser = new User(
						this.id,
						this.username,
						this.password,
						this.testPlaylists);
				this.userList = new ArrayList<>();
				userList.add(testUser);
			}
			
			@Test
			void testCreateUser() {
				when(this.repo.save(this.testUser)).thenReturn(this.testUserWithId);
				when(this.modelMapper.map(this.testUserWithId, UserDTO.class)).thenReturn(this.userDTO);
				
				UserDTO expected = this.userDTO;
				UserDTO actual = this.service.create(this.testUser);
				assertThat(expected).isEqualTo(actual);
				
				verify(this.repo, times(1)).save(this.testUser);
				
			}
			
			@Test
			void testReadUser() {
				when(this.repo.findById(this.id)).thenReturn(Optional.of(this.testUser));
				when(this.modelMapper.map(testUserWithId, UserDTO.class)).thenReturn(userDTO);
				
				assertThat(this.userDTO).isEqualTo(this.service.read(this.id));
				verify(this.repo, times(1)).findById(this.id);
			}
			
			@Test
			void testReadAllUsers() {
				when(this.repo.findAll()).thenReturn(this.userList);
				when(this.modelMapper.map(testUserWithId, UserDTO.class)).thenReturn(userDTO);
				
				assertThat(this.service.read().isEmpty()).isFalse();
				verify(this.repo, times(1)).findAll();
			}
			
			@Test
			void testUpdateUser() {
				User user = new User(this.id, this.username, this.password, this.testPlaylists);
				UserDTO userDTO = new UserDTO(this.id, this.username, this.password, this.testPlaylists);
				
				User updatedUser = new User(this.id, userDTO.getUsername(), userDTO.getPassword(), this.testPlaylists);
				UserDTO updatedUserDTO = new UserDTO(this.id, updatedUser.getUsername(), updatedUser.getPassword(), updatedUser.getPlaylists());
				
				when(this.repo.findById(this.id)).thenReturn(Optional.of(user));
				when(this.repo.save(user)).thenReturn(updatedUser);
				when(this.modelMapper.map(updatedUser, UserDTO.class)).thenReturn(updatedUserDTO);
				
				assertThat(updatedUserDTO).isEqualTo(this.service.update(testUser, this.id));
				
				verify(this.repo, times(1)).findById(1L);
				verify(this.repo, times(1)).save(updatedUser);
			}
			
			@Test
			void testDeleteUser() {
				
			}
}
