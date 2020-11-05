package com.qa.choonz.rest.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.User;
import com.qa.choonz.rest.dto.UserDTO;
import com.qa.choonz.service.UserService;
import com.qa.choonz.utils.AuthUtils;

@SpringBootTest
class TestUserControllerUnit {

	@Autowired
	private UserController controller;
	
	@MockBean
	private UserService service;
	
	@Autowired
	private ModelMapper modelMapper;
	
	private UserDTO mapToDTO(User user) {
		return this.modelMapper.map(user, UserDTO.class);
	}
	
	private List<User> userList;
	private User testUser;
	private User badTestUser;
	private UserDTO userDTO;
	
	private final long id = 1L;
	private final String username = "username";
	private final String password = "password";
	private final String badUser = "badUname";
	private final String badPass = "badPword";
	private List<Playlist> playlists;
	
	private String token;
	
	@BeforeAll
	static void setup() {
		AuthUtils auth = new AuthUtils();
	}
	
    @BeforeEach
    void init() {
    	this.userList = new ArrayList<>();
    	this.playlists = new ArrayList<>();
    	this.testUser = new User(this.id,this.username,this.password,this.playlists);
    	this.badTestUser = new User(this.id,this.badUser,this.badPass,this.playlists);
    	this.userList.add(testUser);
    	this.userDTO = this.mapToDTO(testUser);
    	
    	this.token = AuthUtils.newToken(this.id);
//    	this.token = AuthUtils.
    	
    }
    
    @Test
    void createTest() {
        when(this.service.create(testUser)).thenReturn(this.userDTO);
        
    	HttpHeaders headers = new HttpHeaders();
    	headers.add("token", token);
    	
        assertThat(new ResponseEntity<UserDTO>(this.userDTO,headers,HttpStatus.CREATED).getBody())
                .isEqualTo(this.controller.create(testUser).getBody());
        
        verify(this.service, times(1)).create(this.testUser);
    }
    
    @Test
    void loginTest() {
    	when(this.service.login(username, password)).thenReturn(testUser.getId());
    	
    	HttpHeaders headers = new HttpHeaders();
    	headers.add("token", token);
    	
    	assertThat(String.class)
    		.isEqualTo(this.controller.login(this.username, this.password).getBody().getClass());
    	
    	verify(this.service, times(1)).login(this.username, this.password);
    }
    
    @Test
    void failLoginTest() {
    	when(this.service.login(badUser, badUser)).thenReturn(badTestUser.getId());
    	
    	HttpHeaders headers = new HttpHeaders();
    	headers.add("token", token);
    	
    	assertThat(String.class)
    		.isEqualTo(this.controller.login(this.badUser,this.badPass).getBody().getClass());
    	
    	verify(this.service, times(1)).login(this.badUser, this.badPass);
    }
    
    @Test
    void logoutTest() {
    	assertThat(new ResponseEntity<String>("TOKEN DELETED", HttpStatus.OK))
    	.isEqualTo(this.controller.logout(this.token));
    }
    
    @Test
    void readTest() {
    	when(this.service.read(this.id)).thenReturn(this.userDTO);
    	
    	assertThat(new ResponseEntity<UserDTO>(this.userDTO,HttpStatus.OK))
    		.isEqualTo(this.controller.read(this.id));
    	
    	verify(this.service,times(1)).read(this.id);
    }
    
    @Test
    void readAllTest() {
    	when(this.service.read())
    		.thenReturn(this.userList
    				.stream()
    				.map(this::mapToDTO)
    				.collect(Collectors.toList()));
    	
    	assertThat(this.controller.read().getBody().isEmpty()).isFalse();
    	
    	verify(this.service, times(1)).read();
    }
    
    @Test
    void updateTest() {
    	User newUser = new User(this.id,this.username,this.password,this.playlists);
    	
    	UserDTO updatedUser = new UserDTO(this.id,newUser.getUsername()
    			,newUser.getPassword()
    			,newUser.getPlaylists());
    	
    	when(service.update(newUser, id)).thenReturn(updatedUser);
    	
    	assertThat(new ResponseEntity<UserDTO>(updatedUser,HttpStatus.ACCEPTED))
		.isEqualTo(this.controller.update(newUser,this.id,this.token));
    }
    
    @Test
    void deleteTest() {
    	this.controller.delete(this.id,this.token);
    	
    	verify(this.service, times(1)).delete(id);
    }
}
