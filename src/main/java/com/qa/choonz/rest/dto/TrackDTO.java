package com.qa.choonz.rest.dto;

import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Playlist;

import java.util.List;
import java.util.Objects;

public class TrackDTO {

    private long id;
    private String name;
    private Album album;
    private List<Playlist> playlists;
    private Float duration;
    private String lyrics;

    public TrackDTO() {
        super();
    }

    public TrackDTO(long id, String name, Album album, List<Playlist> playlists, Float duration, String lyrics) {
    	super();
    	this.id = id;
    	this.name = name;
    	this.album = album;
    	this.playlists = playlists;
    	this.duration = duration;
    	this.lyrics = lyrics;
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Album getAlbum() {
		return this.album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}
	
	
	public List<Playlist> getPlaylists() {
		return this.playlists;
	}

	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}

	public Float getDuration() {
		return duration;
	}

	public void setDuration(Float duration) {
		this.duration = duration;
	}

	public String getLyrics() {
		return lyrics;
	}

	public void setLyrics(String lyrics) {
		this.lyrics = lyrics;
	}

	
	@Override
	public String toString() {
		return "TrackDTO [id=" + id + ", name=" + name + ", album=" + album + ", playlist=" + playlists + ", duration="
				+ duration + ", lyrics=" + lyrics + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(album, duration, id, lyrics, name, playlists);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TrackDTO other = (TrackDTO) obj;
		if (album == null) {
			if (other.album != null)
				return false;
		} else if (!album.equals(other.album))
			return false;
		if (duration != other.duration)
			return false;
		if (id != other.id)
			return false;
		if (lyrics == null) {
			if (other.lyrics != null)
				return false;
		} else if (!lyrics.equals(other.lyrics))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (playlists == null) {
			if (other.playlists != null)
				return false;
		} else if (!playlists.equals(other.playlists))
			return false;
		return true;
	}
    
    
}
