package com.qa.choonz.persistence.domain;

//---[ Imports ]---
import java.util.ArrayList;
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

//===[ Object code ]===
@Entity
public class User {
	//--[ Class Variables ]--
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(max = 100)
	@Column(unique = true)
	private String username;
	
	@NotNull
	@Size(max = 100)
	@Column(unique = true)
	private String password;
	
	@JsonManagedReference(value="owner")
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Playlist> playlists;
	
	//--[ Constructors ]--
	public User() {
		super();
		this.playlists = new ArrayList<>();
	}
	
	public User(long id, @NotNull @Size(max = 100) String username, @NotNull @Size(max = 100) String password, List<Playlist> playlists) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.playlists = playlists;
	}

	//--[ Getters & Setters ]--
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}
	
	//--[ Override Methods ]--
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=")
		.append(this.id)
		.append(", username=")
		.append(this.username)
		.append(", password=")
		.append(this.password)
		.append(", playlists=")
		.append(this.playlists)
		.append("]");
		return builder.toString();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, username, password, playlists);
	}
	
	@Override
	public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof User)) {
            return false;
        }
        User other = (User) obj;
        return Objects.equals(username, other.username)
                && Objects.equals(password, other.password) 
                && this.id == other.id
                && Objects.equals(playlists, other.playlists);
	}
}
