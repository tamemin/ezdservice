package com.nime.ezdisco.business;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;


import com.nime.ezdisco.domain.ScoreInfo;
import com.nime.ezdisco.domain.ScoreItem;
import com.nime.ezdisco.domain.TrackInfo;

@Named(value="scoreGenerator")
public class ScoreGeneratorImpl implements ScoreGenerator {
	
	@Inject
	ScoreBuilder scoreBuilder;
	
	@Inject
	MixIntegrator mixIntegrator;

	@Override
	public ScoreInfo generateBasicScore(List<TrackInfo> tracks) {
		
		String key = "123445";
		String length = "122232";
		String maxMp3 = "5790682";
		String size = "22039586";
		
		List<ScoreItem> scoreItems = scoreBuilder.getScoreItems(tracks);
	
		
		ScoreInfo score = new ScoreInfo(key, length, maxMp3, size, scoreItems);
		
		return score;
	}

	@Override
	public ScoreInfo generateMixedScore(List<TrackInfo> tracks) {
		
		String key = "123445";
		String length = "122232";
		String maxMp3 = "5790682";
		String size = "22039586";
		
		List<ScoreItem> scoreItems = scoreBuilder.getScoreItems(tracks);
		scoreItems = mixIntegrator.addMixes(scoreItems);
		
		ScoreInfo score = new ScoreInfo(key, length, maxMp3, size, scoreItems);
		
		return score;
	}
	

	


}
