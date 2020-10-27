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
    private Map<Long, String> albums;

    public ArtistDTO() {
        super();
        // TODO Auto-generated constructor stub
    }

    public ArtistDTO(long id, String name, List<Album> albums) {
        super();
        this.id = id;
        this.name = name;
        this.albums = new HashMap<Long, String>();
        for (Album a: albums) {
        	this.albums.put(a.getId(), a.getName());
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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ArtistDTO [id=").append(id).append(", name=").append(name).append(", albums=").append(albums)
                .append("]");
        return builder.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(albums, id, name);
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
        return Objects.equals(albums, other.albums) && id == other.id && Objects.equals(name, other.name);
    }

}
