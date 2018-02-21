package com.nime.ezdisco.domain;

import java.sql.Time;
import java.util.Date;

public class TrackInfo {
	
	
	private int id;
	
	private String version;
	
	private int bpm;
	
	private String length;
	
	private Date date1;
	
	private String artist;
	
	private String songTitle;
	
	private String genre;
	
	
	public TrackInfo(int id, String version, int bpm, String length, Date date1, String artist, String title, String genre) {
		this.id = id;
		this.version = version;
		this.bpm = bpm;
		this.length = length;
		this.date1 = date1;
		this.artist = artist;
		this.songTitle = title;
		this.genre = genre;
		
	}
	
	public int getId() {
		return id;
	}
	public int getBpm() {
		return bpm;
	}
	
	public Date getDate1() {
		return date1;
	}
	
	public String getGenre() {
		return genre;
	}
	
	public String getLength() {
		return length;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public String getSongTitle() {
		return songTitle;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setGenre(String genre) {
		this.genre = genre;
	}

	@Override
	public String toString() {
		return "TrackInfo [id=" + id + ", version=" + version + ", bpm=" + bpm + ", length=" + length + ", date1="
				+ date1 + ", artist=" + artist + ", songTitle=" + songTitle + ", genre=" + genre + "]";
	}
	
	

}
