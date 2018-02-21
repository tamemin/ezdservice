package com.nime.ezdisco.business;

import java.util.List;

import com.nime.ezdisco.data.Artist;
import com.nime.ezdisco.data.Genre;
import com.nime.ezdisco.data.Song;
import com.nime.ezdisco.data.Track;
import com.nime.ezdisco.domain.PlaylistInfo;
import com.nime.ezdisco.domain.ScoreInfo;
import com.nime.ezdisco.domain.TrackInfo;

public interface MusicSession {

	List<Artist> getArtists();
	
	List<Genre> getGenres();
	
	List<Song> getSongs();
	
	List<Track> getTracks();
	
	List<PlaylistInfo> getPlaylists();
	
	List<TrackInfo> getPlaylist(int playlistId);
	
	ScoreInfo getScore(int playlistId);

	ScoreInfo getMixedScore(int playlistId);
	
}
