package com.qa.choonz.rest.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.choonz.persistence.domain.User;
import com.qa.choonz.rest.dto.UserDTO;
import com.qa.choonz.service.UserService;
import com.qa.choonz.utils.AuthUtils;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
	
	private UserService service;
	
	public UserController(UserService service) {
		super();
		this.service = service;
	}
	
    @PostMapping("/create")
    public ResponseEntity<UserDTO> create(@RequestBody User user) {
    	UserDTO newUser = this.service.create(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
    
    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestHeader("username") String username, @RequestHeader("password") String password) {
    	Long uid = this.service.login(username, password);
    	if (uid == null) {
    		return new ResponseEntity<>("NOT VALID DETAILS", HttpStatus.BAD_REQUEST);
    	}
		String token = AuthUtils.newToken(uid);
		
		return new ResponseEntity<>(token, HttpStatus.OK);
    	
    }
    
    @GetMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("token") String token) {
    	AuthUtils.deleteToken(token);
    	return new ResponseEntity<>("TOKEN DELETED", HttpStatus.OK);
    }
	
    @GetMapping("/read")
    public ResponseEntity<List<UserDTO>> read() {
        return new ResponseEntity<>(this.service.read(), HttpStatus.OK);
    }
    
    @GetMapping("/read/{id}")
    public ResponseEntity<UserDTO> read(@PathVariable long id) {
        return new ResponseEntity<>(this.service.read(id), HttpStatus.OK);
    }
    
    @PostMapping("/update/{id}")
    public ResponseEntity<UserDTO> update(@RequestBody User user, @PathVariable long id, @RequestHeader("token") String token) {
	    if (AuthUtils.getTokenOwner(token) != id) {
	    	return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	    }
	    return new ResponseEntity<>(this.service.update(user, id), 
	    		HttpStatus.ACCEPTED);
    }
    
    @DeleteMapping("delete/{id}")
    public ResponseEntity<UserDTO> delete(@PathVariable long id, @RequestHeader("token") String token) {
    	if (AuthUtils.getTokenOwner(token) != id) {
    		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    	}
    	return this.service.delete(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
