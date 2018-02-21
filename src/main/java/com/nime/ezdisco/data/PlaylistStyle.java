package com.nime.ezdisco.data;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "playliststyle")
@NamedQueries( {
        @NamedQuery(name = "PlaylistStyle.findAll", query = "SELECT p FROM PlaylistStyle p")     
})
public class PlaylistStyle {
	
	@Column(name="playliststyle_id")
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String style;
	
	private String description;
	
	@OneToMany(mappedBy = "playlistStyle")
	private List<Playlist> playlists;

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}

}
