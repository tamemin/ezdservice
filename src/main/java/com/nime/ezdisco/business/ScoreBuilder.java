package com.nime.ezdisco.business;

import java.util.List;

import com.nime.ezdisco.domain.ScoreItem;
import com.nime.ezdisco.domain.TrackInfo;

public interface ScoreBuilder {
	
	public List<ScoreItem> getScoreItems(List<TrackInfo> tracks);
	
}
