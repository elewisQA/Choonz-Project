package com.qa.choonz.rest.dto;

import java.util.List;
import java.util.Objects;

import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.persistence.domain.Track;

public class AlbumDTO {

    private long id;
    private String name;
    private List<Track> tracks;
    private String artistName;
    private Long artistId;
    private Genre genre;
    private String cover;

    public AlbumDTO() {
        super();
        // TODO Auto-generated constructor stub
    }

    public AlbumDTO(long id, String name, List<Track> tracks, Artist artist, Genre genre, String cover) {
        super();
        this.id = id;
        this.name = name;
        this.tracks = tracks;
        this.artistName = artist.getName();
        this.artistId = artist.getId();
        this.genre = genre;
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

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
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

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
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
                .append(", artist=").append(artistName).append(", genre=").append(genre).append(", cover=").append(cover)
                .append("]");
        return builder.toString();
        // TODO add artistId
    }

    @Override
    public int hashCode() {
        return Objects.hash(artistName, artistId, cover, genre, id, name, tracks);
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
                && Objects.equals(genre, other.genre) && id == other.id && Objects.equals(name, other.name)
                && Objects.equals(tracks, other.tracks);
        // TODO add artistId
    }

}
