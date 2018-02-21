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
@Table(name = "genre")
@NamedQueries( {
        @NamedQuery(name = "Genre.findAll", query = "SELECT g FROM Genre g")     
})
public class Genre {
	
	@Column(name="genre_id")
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@OneToMany(mappedBy="genre")
	private List<Track> tracks;
	
	private String genre;

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public List<Track> getTracks() {
		return tracks;
	}

	@Override
	public String toString() {
		return "Genre [id=" + id + ", tracks=" + tracks + ", genre=" + genre + "]";
	}
	
	
	
	

}
