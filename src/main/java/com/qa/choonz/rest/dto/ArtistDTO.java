package com.qa.choonz.rest.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.qa.choonz.persistence.domain.Album;

public class ArtistDTO {

    private long id;
    private String name;
    private String picture;
    private Map<Long, String> albums;

    public ArtistDTO() {
        super();
        // TODO Auto-generated constructor stub
    }

    public ArtistDTO(long id, String name, String picture, List<Album> albums) {
        super();
        this.id = id;
        this.name = name;
        this.picture = picture;
        this.albums = new HashMap<Long, String>();
        if (albums != null) {
	        for (Album a: albums) {
	        	this.albums.put(a.getId(), a.getName());
	        }
        }
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
    
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Map<Long, String> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
    	this.albums = new HashMap<Long, String>();
    	if (albums != null) {
	        for (Album a: albums) {
	        	this.albums.put(a.getId(), a.getName());
	        }
    	}
    }
    
    public void setAlbums(Map<Long, String> albums) {
    	this.albums = albums;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ArtistDTO [id=").append(id).append(", name=").append(name).append(", picture=").append(picture).append(", albums=").append(albums)
                .append("]");
        return builder.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(albums, id, name, picture);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ArtistDTO)) {
            return false;
        }
        ArtistDTO other = (ArtistDTO) obj;
        return Objects.equals(albums, other.albums) && id == other.id && Objects.equals(name, other.name) && Objects.equals(picture, other.picture);
    }

}
