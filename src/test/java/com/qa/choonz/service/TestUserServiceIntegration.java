package com.qa.choonz.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.User;
import com.qa.choonz.persistence.repository.UserRepository;
import com.qa.choonz.rest.dto.UserDTO;

@SpringBootTest
class TestUserServiceIntegration {
	
	@Autowired
	private UserService service;
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	private UserDTO mapToDTO(User user) {
		return this.modelMapper.map(user,  UserDTO.class);
	}
	
	private Long id = 1L;
	private final String username = "Admin";
	private final String password = "Guest";
	private List<Playlist> testPlaylists;
	private User testUser;
	private User testUserWithId;
	private UserDTO userDTOWithId;
	
	@BeforeEach
	void init() {
		this.repo.deleteAll();
		this.testPlaylists = new ArrayList<>();
		this.testUser = new User();
		this.testUser.setUsername(this.username);
		this.testUser.setPassword(this.password);
		this.testUser.setPlaylists(this.testPlaylists);
		
		this.testUserWithId = this.repo.save(testUser);
		this.id = this.testUserWithId.getId();
		
		this.userDTOWithId = this.mapToDTO(this.testUserWithId);
	}
	
	@Test
	void testCreate() {
		assertThat(this.userDTOWithId.getUsername())
			.isEqualTo(this.service.create(this.testUser).getUsername());
	}
	
	@Test
	void testRead() {
		assertThat(this.userDTOWithId).isEqualTo(this.service.read(this.id));
	}
	
	@Test
	void testReadAll() {
		assertThat(Stream.of(this.userDTOWithId).collect(Collectors.toList()))
			.isEqualTo(this.service.read());
	}
	
	@Test
	void testUpdate() {
		assertThat(this.userDTOWithId.getPassword())
			.isEqualTo(this.service.update(this.testUser, this.id).getPassword());
		assertThat(this.userDTOWithId.getId())
			.isEqualTo(this.service.update(this.testUser, this.id).getId());
	}
	
	@Test
	void testDelete() {
		assertThat(this.service.delete(this.id)).isTrue();
	}

}
