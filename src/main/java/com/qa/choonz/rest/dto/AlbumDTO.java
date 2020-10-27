package com.qa.choonz.rest.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.persistence.domain.Track;

public class AlbumDTO {

    private long id;
    private String name;
    private Map<Long, String> tracks;
    private String artistName;
    private Long artistId;
    private String genreName;
    private Long genreId;
    private String cover;

    public AlbumDTO() {
        super();
        // TODO Auto-generated constructor stub
    }

    public AlbumDTO(long id, String name, List<Track> tracks, Artist artist, Genre genre, String cover) {
        super();
        this.id = id;
        this.name = name;
        this.tracks = new HashMap<Long, String>();
        for (Track t: tracks) {
        	this.tracks.put(t.getId(), t.getName());
        }
        this.artistName = artist.getName();
        this.artistId = artist.getId();
        this.genreName = genre.getName();
        this.genreId = genre.getId();
        this.cover = cover;
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

    public Map<Long, String> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = new HashMap<Long, String>();
        for (Track t: tracks) {
        	this.tracks.put(t.getId(), t.getName());
        }
    }

    public String getArtistName() {
        return artistName;
    }
    
    public Long getArtistId() {
    	return artistId;
    }

    public void setArtist(Artist artist) {
    	this.artistName = artist.getName();
        this.artistId = artist.getId();
    }

    public String getGenreName() {
        return genreName;
    }
    
    public Long getGenreId() {
    	return this.genreId;
    }

    public void setGenre(Genre genre) {
        this.genreName = genre.getName();
        this.genreId = genre.getId();
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AlbumDTO [id=").append(id).append(", name=").append(name).append(", tracks=").append(tracks)
                .append(", artist=").append(artistName).append(", genre=").append(genreName).append(", cover=").append(cover)
                .append("]");
        return builder.toString();
        // TODO add artistId, genreId
    }

    @Override
    public int hashCode() {
        return Objects.hash(artistName, artistId, cover, genreName, genreId, id, name, tracks);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AlbumDTO)) {
            return false;
        }
        AlbumDTO other = (AlbumDTO) obj;
        return Objects.equals(artistName, other.artistName) && Objects.equals(cover, other.cover)
                && Objects.equals(genreName, other.genreName) && id == other.id && Objects.equals(name, other.name)
                && Objects.equals(tracks, other.tracks);
        // TODO add artistId, genreId
    }

}
