package com.nime.ezdisco.data;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.FetchMode;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;

@Entity
@Table(name = "playlist")
@NamedQueries( {
        @NamedQuery(name = "Playlist.findAll", query = "SELECT p FROM Playlist p LEFT JOIN FETCH p.playlistStyle")     
})
public class Playlist {
	
	@Column(name="playlist_id")
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="playliststyle_id",referencedColumnName="playliststyle_id", nullable=true)
	PlaylistStyle playlistStyle;
	
	@Column(name="playlistname")
	private String playlist;
	
	@OneToMany(mappedBy="playlist")
	private List<PlaylistItem> items;
	
	public int getId() {
		return id;
	}
	
	public List<PlaylistItem> getItems() {
		return items;
	}
	
	public String getPlaylist() {
		return playlist;
	}
	
	public PlaylistStyle getPlaylistStyle() {
		return playlistStyle;
	}

}
