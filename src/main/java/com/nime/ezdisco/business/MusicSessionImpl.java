package com.nime.ezdisco.business;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.nime.ezdisco.data.Artist;
import com.nime.ezdisco.data.Genre;
import com.nime.ezdisco.data.Playlist;
import com.nime.ezdisco.data.PlaylistItem;
import com.nime.ezdisco.data.Song;
import com.nime.ezdisco.data.Track;
import com.nime.ezdisco.data.User;
import com.nime.ezdisco.domain.PlaylistInfo;
import com.nime.ezdisco.domain.ScoreInfo;
import com.nime.ezdisco.domain.ScoreItem;
import com.nime.ezdisco.domain.TrackInfo;

@Transactional
@Named(value="musicSession")
@Scope(value="request")
public class MusicSessionImpl implements MusicSession {
	
	@PersistenceContext
    private EntityManager em;
	
	@Autowired
	ScoreGenerator scoreGenerator;
	

	@Override
	public List<Artist> getArtists() {
		Query q = em.createNamedQuery("Artist.findAll");
		List<Artist> res = q.getResultList();
		return res;
	}

	@Override
	public List<Genre> getGenres() {
		Query q = em.createNamedQuery("Genre.findAll");
		List<Genre> res = q.getResultList();
		return res;
	}

	@Override
	public List<Song> getSongs() {
		Query q = em.createNamedQuery("Song.findAll");
		List<Song> res = q.getResultList();
		for (Song s : res) {
			s.getVersions().size(); // wake up lazy init
		}
		return res;
	}

	@Override
	public List<Track> getTracks() {
		Query q = em.createNamedQuery("Track.findAll");
		List<Track> res = q.getResultList();
		return res;
	}

	@Override
	public List<TrackInfo> getPlaylist(int playlistId) {
		Playlist pl = em.find(Playlist.class, playlistId);
		
		ArrayList<TrackInfo> res = new ArrayList<TrackInfo>();
		
		
		for (PlaylistItem item : pl.getItems()) {
			res.add(new TrackInfo(item.getTrack().getId(), 
					item.getTrack().getVersion(), 
					item.getTrack().getBpm(), 
					item.getTrack().getLength(),
					item.getTrack().getDate1(), 
					item.getTrack().getSong().getArtist().getArtist(),
					item.getTrack().getSong().getSongTitle(), 
					item.getTrack().getGenre().getGenre()));
		}
		
		return res;
	}

	@Override
	public List<PlaylistInfo> getPlaylists() {
		Query q = em.createNamedQuery("Playlist.findAll");
		List<Playlist> pls = q.getResultList();
		ArrayList<PlaylistInfo> res = new ArrayList<PlaylistInfo>();
		
		for (Playlist pl : pls) {
			
			if (pl.getPlaylistStyle()!=null) {
				res.add(new PlaylistInfo(pl.getId(), pl.getPlaylistStyle().getStyle()));			
			}
			
		}
		return res;
	}

	@Override
	public ScoreInfo getScore(int playlistId) {
		

		List<TrackInfo> tracks = getPlaylist(playlistId);
		
		ScoreInfo score = scoreGenerator.generateBasicScore(tracks);
		
		return score;
	}
	
	@Override
	public ScoreInfo getMixedScore(int playlistId) {
		

		List<TrackInfo> tracks = getPlaylist(playlistId);
		
		ScoreInfo score = scoreGenerator.generateMixedScore(tracks);
		
		return score;
	}
	

}
