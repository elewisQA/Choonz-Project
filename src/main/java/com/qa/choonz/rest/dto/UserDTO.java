package com.qa.choonz.rest.dto;

//---[ Imports ]---
import java.util.List;
import java.util.Objects;

import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Playlist;

//===[ Object Code ]===
public class UserDTO {
	//--[ Class Variables ]--
	private Long id;
	private String username;
	private String password;
	private List<Playlist> playlists;
	
	//--[ Constructors ]--
	public UserDTO() {
		super();
	}
	
	public UserDTO(Long id, String username, String password, List<Playlist> playlists) {
		super();
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
        if (!(obj instanceof Album)) {
            return false;
        }
        UserDTO other = (UserDTO) obj;
        return Objects.equals(username, other.username)
                && Objects.equals(password, other.password) && id == other.id
                && Objects.equals(playlists, other.playlists);
	}
}
