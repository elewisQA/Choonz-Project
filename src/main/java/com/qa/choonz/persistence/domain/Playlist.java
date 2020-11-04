package com.qa.choonz.persistence.domain;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(max = 100)
    @Column(unique = true)
    private String name;

    @NotNull
    @Size(max = 500)
    private String description;

    @NotNull
    @Size(max = 1000)
    private String artwork;

    @JsonIdentityInfo(
    		  generator = ObjectIdGenerators.PropertyGenerator.class, 
    		  property = "id")
    @ManyToMany(mappedBy = "playlists", cascade = CascadeType.ALL)
    private List<Track> tracks;
    
    @JsonBackReference(value="owner")
    @ManyToOne
    private User user;

    public Playlist() {
        super();
    }

    public Playlist(long id, @NotNull @Size(max = 100) String name, @NotNull @Size(max = 500) String description,
            @NotNull @Size(max = 1000) String artwork, List<Track> tracks, User user) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.artwork = artwork;
        this.tracks = tracks;
        this.user = user;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getArtwork() {
        return artwork;
    }

    public void setArtwork(String artwork) {
        this.artwork = artwork;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
    
    public User getUser() {
    	return this.user;
    }
    
    public void setUser(User user) {
    	this.user = user;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Playlist [id=").append(id).append(", name=").append(name).append(", description=")
                .append(description).append(", artwork=").append(artwork).append(", tracks=").append(tracks)
                .append(", user=").append(this.user)
                .append("]");
        return builder.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(artwork, description, id, name, tracks, user);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Playlist)) {
            return false;
        }
        Playlist other = (Playlist) obj;
        return Objects.equals(artwork, other.artwork) && Objects.equals(description, other.description)
                && id == other.id && Objects.equals(name, other.name) && Objects.equals(tracks, other.tracks)
                && user == other.user && Objects.equals(user, other.user);
    }

}