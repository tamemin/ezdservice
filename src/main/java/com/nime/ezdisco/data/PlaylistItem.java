package com.nime.ezdisco.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "playlistitem")
public class PlaylistItem {
	
	@Column(name="playlistitem_id")
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="playlist_id",referencedColumnName="playlist_id", nullable=true)
	private Playlist playlist;
	
	@ManyToOne
	@JoinColumn(name="track_id",referencedColumnName="track_id", nullable=true)
	private Track track;
	
	@Column(name="playposition")
	private int playPosition;
	
	
	public int getId() {
		return id;
	}
	
	public Playlist getPlaylist() {
		return playlist;
	}
	
	public int getPlayPosition() {
		return playPosition;
	}
	
	public Track getTrack() {
		return track;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setPlaylist(Playlist playlist) {
		this.playlist = playlist;
	}
	
	public void setPlayPosition(int playPosition) {
		this.playPosition = playPosition;
	}
	
	public void setTrack(Track track) {
		this.track = track;
	}
	

}
