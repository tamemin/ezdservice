package com.nime.ezdisco.business;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import com.nime.ezdisco.domain.ScoreItem;
import com.nime.ezdisco.domain.TrackInfo;

@Named("scoreBuilder")
public class ScoreBuilderImpl implements ScoreBuilder {
	

	@Override
	public List<ScoreItem> getScoreItems(List<TrackInfo> tracks) {
		
		ArrayList<ScoreItem> scoreItems = new ArrayList<ScoreItem>();
		
		TrackInfo previous = null;
		
		for (TrackInfo track : tracks) {
			
			String vol = "100";
			String cp = "00:00.000";
			String ss = "100";
			String es = "100";
			String sp = "000000";
			
			// Default start point it just before the end of the last track
			if (previous!=null) {
				sp =  Long.valueOf(Utils.timeToSample(previous.getLength())-Utils.SAMPLE_RATE*4).toString();
			}
			
			String ep = Long.valueOf(Utils.timeToSample(track.getLength())).toString();

			
			String[] fades = new String[0];

			ScoreItem item= new ScoreItem(
					track.getId(),
					track.getArtist(), 
					track.getSongTitle(), 
					Integer.valueOf(track.getId()).toString(), 
					vol, 
					cp, 
					ss, 
					sp, 
					es, 
					ep, 
					fades);
			
			scoreItems.add(item);
			
			previous = track;
			
		}
		return scoreItems;
	}
	
}
