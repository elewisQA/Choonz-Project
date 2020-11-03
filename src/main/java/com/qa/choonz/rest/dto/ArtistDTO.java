package com.qa.choonz.rest.dto;

import java.util.List;
import java.util.Objects;

import com.qa.choonz.persistence.domain.Album;

public class ArtistDTO {

    private long id;
    private String name;
    private List<Album> albums;
    private String picture;

    public ArtistDTO() {
        super();
    }

    public ArtistDTO(long id, String name, String picture, List<Album> albums) {
        super();
        this.id = id;
        this.name = name;
        this.albums = albums;
        this.picture = picture;
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

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
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