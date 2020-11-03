package com.qa.choonz.persistence.domain;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(max = 100)
    @Column(unique = true)
    private String name;

    @JsonBackReference(value="album")
    @ManyToOne
    private Album album;

    @JsonIdentityInfo(
    		  generator = ObjectIdGenerators.PropertyGenerator.class, 
    		  property = "id")
    @ManyToMany
    @JoinTable(
    		name = "track_playlists",
    		joinColumns = @JoinColumn(name = "track_id"),
    		inverseJoinColumns = @JoinColumn(name = "playlist_id"))
    private List<Playlist> playlists;

    // in seconds
    private Float duration;

    private String lyrics;

    public Track() {
        super();
    }

    public Track(long id, @NotNull @Size(max = 100) String name, Album album, List<Playlist> playlists, Float duration,
            String lyrics) {
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
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
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
        StringBuilder builder = new StringBuilder();
        builder.append("Track [id=").append(id).append(", name=").append(name).append(", album=").append(album)
                .append(", playlists=").append(playlists).append(", duration=").append(duration).append(", lyrics=")
                .append(lyrics).append("]");
        return builder.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(album, duration, id, lyrics, name, playlists);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Track)) {
            return false;
        }
        Track other = (Track) obj;
        return Objects.equals(album, other.album) && duration == other.duration && id == other.id
                && Objects.equals(lyrics, other.lyrics) && Objects.equals(name, other.name)
                && Objects.equals(playlists, other.playlists);
    }

}