package com.nime.ezdisco.domain;

public class PlaylistInfo {
	
	private int id;
	
	
	private String title;
	
	public PlaylistInfo(int id, String title) {
		this.id = id;
		this.title = title;
	}
	
	public int getId() {
		return id;
	}
	
	
	public String getTitle() {
		return title;
	}

	@Override
	public String toString() {
		return "PlaylistInfo [id=" + id + ", title=" + title + "]";
	}
	
	

}
