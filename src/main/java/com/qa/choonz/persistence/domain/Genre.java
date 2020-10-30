package com.qa.choonz.persistence.domain;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(max = 100)
    @Column(unique = true)
    private String name;

    @NotNull
    @Size(max = 250)
    @Column(unique = true)
    private String description;

    @JsonManagedReference(value="secondary")
    @OneToMany(mappedBy = "genre", cascade = CascadeType.ALL)
    private List<Album> albums;
    
    private String picture;

    public Genre() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Genre(long id, @NotNull @Size(max = 100) String name, String picture, @NotNull @Size(max = 250) String description,
            List<Album> albums) {
        super();
        this.id = id;
        this.name = name;
        this.picture = picture;
        this.description = description;
        this.albums = albums;
    }
    
	@Override
	 public String toString() {
	      StringBuilder builder = new StringBuilder();
	      builder.append("Genre [id=").append(this.id).append(", name=").append(this.name).append(", picture=").append(this.picture).append(", description=")
	               .append(this.description).append(", albums=").append(this.albums).append("]");
	      return builder.toString();
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}



    @Override
    public int hashCode() {
        return Objects.hash(albums, description, id, name, picture);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Genre)) {
            return false;
        }
        Genre other = (Genre) obj;
        return Objects.equals(albums, other.albums) && Objects.equals(description, other.description) && id == other.id
                && Objects.equals(name, other.name) && Objects.equals(picture, other.picture);
    }

}
