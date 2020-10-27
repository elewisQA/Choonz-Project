package com.qa.choonz.rest.dto;

import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Playlist;
import java.util.Objects;

public class TrackDTO {

    private long id;
    private String name;
    private String albumName;
    private Long albumId;
    private String playlistName;
    private Long playlistId;
    private int duration;
    private String lyrics;

    public TrackDTO() {
        super();
        // TODO Auto-generated constructor stub
    }

    public TrackDTO(long id, String name, Album album, Playlist playlist, int duration, String lyrics) {
    	super();
    	this.id = id;
    	this.name = name;
    	this.albumName = album.getName();
    	this.albumId = album.getId();
    	this.playlistName = playlist.getName();
    	this.playlistId = playlist.getId();
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

	public String getAlbumName() {
		return this.albumName;
	}
	
	public Long getAlbumId() {
		return this.albumId;
	}

	public void setAlbum(Album album) {
		if (album != null) {
			this.albumName = album.getName();
			this.albumId = album.getId();
		} else {
			this.albumName = null;
			this.albumId = null;
		}
	}

	// TODO add setters for name / id
	
	public String getPlaylistName() {
		return this.playlistName;
	}
	
	public Long getPlaylistId() {
		return this.playlistId;
	}

	public void setPlaylist(Playlist playlist) {
		if (playlist != null) {
			this.playlistName = playlist.getName();
			this.playlistId = playlist.getId();
		} else {
			this.playlistName = null;
			this.playlistId = null;
		}
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
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
		// TODO add in IDs
		return "TrackDTO [id=" + id + ", name=" + name + ", album=" + albumName + ", playlist=" + playlistName + ", duration="
				+ duration + ", lyrics=" + lyrics + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(albumName, albumId, duration, id, lyrics, name, playlistName, albumId);
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
		if (albumName == null) {
			if (other.albumName != null)
				return false;
		} else if (!albumName.equals(other.albumName))
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
		if (playlistName == null) {
			if (other.playlistName != null)
				return false;
		} else if (!playlistName.equals(other.playlistName))
			return false;
		return true;
	}
    
    
}
