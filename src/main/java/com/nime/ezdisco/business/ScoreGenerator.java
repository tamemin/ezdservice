package com.nime.ezdisco.business;

import java.util.List;

import com.nime.ezdisco.domain.PlaylistInfo;
import com.nime.ezdisco.domain.ScoreInfo;
import com.nime.ezdisco.domain.TrackInfo;

/**
 * Defines the interface for score generation from a playlist and mix info
 * 
 * @author tamer
 *
 */
public interface ScoreGenerator {
	
	
	/**
	 * Generate a basic score from a playlist
	 * 
	 * @param playlist
	 * @return
	 */
	ScoreInfo generateBasicScore(List<TrackInfo> tracks);
	
	
	
	/**
	 * Generate a mixed score using Mix information
	 * 
	 * @param tracks
	 * @return
	 */
	ScoreInfo generateMixedScore(List<TrackInfo> tracks);

}
