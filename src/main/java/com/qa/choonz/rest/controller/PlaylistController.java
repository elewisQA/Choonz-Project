package com.qa.choonz.rest.controller;

import java.util.List;

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

import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.rest.dto.PlaylistDTO;
import com.qa.choonz.service.PlaylistService;
import com.qa.choonz.utils.AuthUtils;

@RestController
@RequestMapping("/playlists")
@CrossOrigin
public class PlaylistController {

    private PlaylistService service;

    public PlaylistController(PlaylistService service) {
        super();
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<PlaylistDTO> create(@RequestBody Playlist playlist, @RequestHeader("token") String token) {
        if (AuthUtils.validToken(token)) {
        	return new ResponseEntity<PlaylistDTO>(this.service.create(playlist), HttpStatus.CREATED);
        } else {
        	return new ResponseEntity<PlaylistDTO>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/read")
    public ResponseEntity<List<PlaylistDTO>> read() {
        return new ResponseEntity<List<PlaylistDTO>>(this.service.read(), HttpStatus.OK);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<PlaylistDTO> read(@PathVariable long id) {
        return new ResponseEntity<PlaylistDTO>(this.service.read(id), HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<PlaylistDTO> update(@RequestBody Playlist playlist, @PathVariable long id, @RequestHeader("token") String token) {
        if (AuthUtils.validToken(token)) {
        	return new ResponseEntity<PlaylistDTO>(this.service.update(playlist, id), HttpStatus.ACCEPTED);
        } else {
        	return new ResponseEntity<PlaylistDTO>(HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<PlaylistDTO> delete(@PathVariable long id, @RequestHeader("token") String token) {
        if (AuthUtils.validToken(token)) {
    	return this.service.delete(id) ? new ResponseEntity<PlaylistDTO>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<PlaylistDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
        	return new ResponseEntity<PlaylistDTO>(HttpStatus.UNAUTHORIZED);
        }
    }
    
    @PostMapping("/add/{playlistId}/{trackId}")
    public ResponseEntity<PlaylistDTO> add(@PathVariable Long playlistId,@PathVariable Long trackId){
    	return new ResponseEntity<PlaylistDTO>(this.service.addTrack(playlistId, trackId), HttpStatus.ACCEPTED);
    }
    
    @PostMapping("/remove/{playlistId}/{trackId}")
    public ResponseEntity<PlaylistDTO> remove(@PathVariable Long playlistId,@PathVariable Long trackId){
    	return new ResponseEntity<PlaylistDTO>(this.service.removeTrack(playlistId, trackId), HttpStatus.ACCEPTED);
    }

}
