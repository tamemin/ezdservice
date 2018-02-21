package com.nime.ezdisco.data;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "song")
@NamedQueries( {
        @NamedQuery(name = "Song.findAll", query = "SELECT s FROM Song s LEFT JOIN FETCH s.artist")     
})
public class Song {
	
	@Column(name="song_id")
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
    @JoinColumn(name="artist_id",referencedColumnName="artist_id", nullable=true)
	private Artist artist;
	
	private int popularity;
	
	@OneToMany(mappedBy="song")
	private List<Track> versions;
	
	@Column(name="songtitle")
	private String songTitle;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public int getPopularity() {
		return popularity;
	}

	public void setPopularity(int popularity) {
		this.popularity = popularity;
	}

	public String getSongTitle() {
		return songTitle;
	}

	public void setSongTitle(String songTitle) {
		this.songTitle = songTitle;
	}
	
	public List<Track> getVersions() {
		return versions;
	}
	
	public void setVersions(List<Track> versions) {
		this.versions = versions;
	}

	@Override
	public String toString() {
		return "Song [id=" + id + ", artist=" + artist + ", popularity=" + popularity + ", versions=" + versions
				+ ", songTitle=" + songTitle + "]";
	}
	
	
	
	

}
