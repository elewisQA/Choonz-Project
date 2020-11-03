package com.qa.choonz.rest.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.User;
import com.qa.choonz.persistence.repository.UserRepository;
import com.qa.choonz.rest.dto.UserDTO;
import com.qa.choonz.utils.AuthUtils;

@SpringBootTest
@AutoConfigureMockMvc
class TestUserControllerIntegration {
	
    @Autowired
    private MockMvc mock;
    
    @Autowired
    private UserRepository repo;
    
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ObjectMapper objectMapper;

	private UserDTO mapToDTO(User user) {
		return this.modelMapper.map(user, UserDTO.class);
	}
	
	private User testUser;
	private User testUserWithId;
	private UserDTO userDTO;
	
	private Long id;
	private final String username = "username";
	private final String password = "password";
	private final String badUser = "badUname";
	private final String badPass = "badPword";
	private List<Playlist> playlists;
	
	private String token;
	private HttpHeaders headers;
	
	@BeforeAll
	static void setup() {
		AuthUtils auth = new AuthUtils();
	}
	
    @BeforeEach
    void init() {
    	this.repo.deleteAll();
    	
    	this.playlists = new ArrayList<>();
    	this.testUser = new User();
    	this.testUser.setUsername(username);
    	this.testUser.setPassword(password);
    	this.testUser.setPlaylists(playlists);
    	this.testUserWithId = this.repo.save(this.testUser);
    	this.userDTO = this.mapToDTO(testUser);
    	
    	this.id = this.testUserWithId.getId();
    	this.token = AuthUtils.newToken(this.id);
    	this.headers = new HttpHeaders();
    	headers.add("token", token);
    }
    
    @Test
    void testCreate() throws Exception {
        this.mock
        	.perform(request(HttpMethod.POST, "/users/create").header("token", token)
        		.contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(testUser))
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(content().json(this.objectMapper.writeValueAsString(userDTO)));
    }
    
    @Test
    void testLogin() throws Exception {
    	this.mock
    		.perform(request(HttpMethod.POST, "/users/login")
    				.header("username", username)
    				.header("password", password)
    				.contentType(MediaType.APPLICATION_JSON)
    					.content(this.objectMapper.writeValueAsString(testUser))
    					.accept(MediaType.APPLICATION_JSON))
    		.andExpect(status().isOk());
    }
    
    @Test
    void failLoginTest() throws Exception {
    	this.mock
    		.perform(request(HttpMethod.POST, "/users/login")
    				.header("username", badUser)
    				.header("password", badPass)
    				.contentType(MediaType.APPLICATION_JSON)
    				.content(this.objectMapper.writeValueAsString(testUser))
    				.accept(MediaType.APPLICATION_JSON))
    		.andExpect(status().isBadRequest());
    }
    
    @Test
    void testLogout() throws Exception {
    	// seemingly not implemented yet
    }
    
    @Test
    void testReadOne() throws Exception{
    	this.mock
    			.perform(request(HttpMethod.GET,"/users/read/" + this.id).accept(MediaType.APPLICATION_JSON))
    			.andExpect(status().isOk())
    			.andExpect(content().json(this.objectMapper.writeValueAsString(this.userDTO)));
    }
    
    @Test
    void testReadAll() throws Exception{
    	List<UserDTO> userList = new ArrayList<>();
    	userList.add(this.userDTO);
    	
    	String content = this.mock
    			.perform(request(HttpMethod.GET,"/users/read").accept(MediaType.APPLICATION_JSON))
    			.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
    	
    	assertEquals(this.objectMapper.writeValueAsString(userList),content);
    }
    
    @Test
    void testUpdate() throws Exception{
    	User newUser = new User(this.id,"nameuser","wordpass",this.playlists);

    	User updatedUser = new User(this.id,newUser.getUsername(),newUser.getPassword()
    			,newUser.getPlaylists());

    	
        String output = this.mock
                .perform(request(HttpMethod.POST,"/users/update/" + this.id).headers(headers).accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(newUser)))
                .andExpect(status().isAccepted()).andReturn().getResponse().getContentAsString();
    	
    	assertEquals(this.objectMapper.writeValueAsString(this.mapToDTO(updatedUser)),output);
    }
    
    @Test
    void testDelete() throws Exception {
        this.mock.perform(request(HttpMethod.DELETE, "/users/delete/" + this.id).header("token", token)).andExpect(status().isNoContent());
    }
}
