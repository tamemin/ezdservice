package com.nime.ezdisco.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "artist")
@NamedQueries( {
        @NamedQuery(name = "Artist.findAll", query = "SELECT a FROM Artist a")     
})
public class Artist {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="artist_id")
	private int id;
	
	private String artist;

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Artist [id=" + id + ", artist=" + artist + "]";
	}
	
	
	
	

}
