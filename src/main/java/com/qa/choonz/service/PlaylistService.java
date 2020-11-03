package com.qa.choonz.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.qa.choonz.exception.PlaylistNotFoundException;
import com.qa.choonz.exception.TrackNotFoundException;
import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.persistence.repository.PlaylistRepository;
import com.qa.choonz.persistence.repository.TrackRepository;
import com.qa.choonz.rest.dto.PlaylistDTO;

@Service
public class PlaylistService {

    private PlaylistRepository repo;
    private TrackRepository trackRepo;
    private ModelMapper mapper;

    public PlaylistService(PlaylistRepository repo, TrackRepository trackRepo,ModelMapper mapper) {
        super();
        this.repo = repo;
        this.trackRepo = trackRepo;
        this.mapper = mapper;
    }

    private PlaylistDTO mapToDTO(Playlist playlist) {
        return this.mapper.map(playlist, PlaylistDTO.class);
    }
    
    public PlaylistDTO create(Playlist playlist) {
        Playlist created = this.repo.save(playlist);
        return this.mapToDTO(created);
    }

    public List<PlaylistDTO> read() {
        return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public PlaylistDTO read(long id) {
        Playlist found = this.repo.findById(id).orElseThrow(PlaylistNotFoundException::new);
        return this.mapToDTO(found);
    }

    public PlaylistDTO update(Playlist playlist, long id) {
        Playlist toUpdate = this.repo.findById(id).orElseThrow(PlaylistNotFoundException::new);
        toUpdate.setName(playlist.getName());

        if(playlist.getDescription() != null) {
        	toUpdate.setDescription(playlist.getDescription());
        }
        if(playlist.getArtwork() != null) {
        	toUpdate.setArtwork(playlist.getArtwork());
        }
        if(playlist.getTracks() != null) {
        	toUpdate.setTracks(playlist.getTracks());
        }
       	Playlist updated = this.repo.save(toUpdate);

        return this.mapToDTO(updated);
    }

    public boolean delete(long id) {
        this.repo.deleteById(id);
        return !this.repo.existsById(id);
    }
    
    public PlaylistDTO addTrack(long playlistId,long trackId) {
    	Track track = this.trackRepo.findById(trackId).orElseThrow(TrackNotFoundException::new);
    	Playlist playlist = this.repo.findById(playlistId).orElseThrow(PlaylistNotFoundException::new);    	
    	List<Track> tracks = playlist.getTracks();
    	List<Playlist> trackPlaylists = track.getPlaylists();

    	tracks.add(track);
    	trackPlaylists.add(playlist);
    	playlist.setTracks(tracks);
    	track.setPlaylists(trackPlaylists);
    	
    	Playlist added = this.repo.save(playlist);
    	this.trackRepo.save(track);
    	
    	return this.mapToDTO(added);
    }
    
    public PlaylistDTO removeTrack(long playlistId,long trackId) {
    	Track track = this.trackRepo.findById(trackId).orElseThrow(TrackNotFoundException::new);
    	Playlist playlist = this.repo.findById(playlistId).orElseThrow(PlaylistNotFoundException::new);    	
    	List<Track> tracks = playlist.getTracks();
    	List<Playlist> trackPlaylists = track.getPlaylists();
    	
    	if(tracks.contains(track)) {
    		tracks.remove(track);
    	}
    	
    	if(trackPlaylists.contains(playlist)) {
    		trackPlaylists.remove(playlist);
    	}
    	
    	playlist.setTracks(tracks);
    	track.setPlaylists(trackPlaylists);
    	
    	Playlist removed = this.repo.save(playlist);
    	this.trackRepo.save(track);
    	
    	return this.mapToDTO(removed);
    }

}
