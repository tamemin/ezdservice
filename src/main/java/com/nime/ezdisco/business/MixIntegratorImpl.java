package com.nime.ezdisco.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.nime.ezdisco.data.Mix;
import com.nime.ezdisco.data.Track;
import com.nime.ezdisco.domain.ScoreItem;

@Named("mixIntegrator")
public class MixIntegratorImpl implements MixIntegrator {
	
	
	@PersistenceContext
    private EntityManager em;

	@Override
	public List<ScoreItem> addMixes(List<ScoreItem> score) {
		
		ArrayList<ScoreItem> newItems = new ArrayList<ScoreItem>(score.size());
		
		ScoreItem PreviousItem = null;
		
		for (ScoreItem item : score) {
			
			if (PreviousItem!=null) {
				
				int trackAid = PreviousItem.getTrackId();
				int trackBid = item.getTrackId();
				
				List<Mix> mixes = getMix(trackAid, trackBid);
				
				if (mixes.size()>0) {
					System.out.println("Found Mix ");
					
					Mix mix = mixes.get(0);
					
					String mixVol = Integer.valueOf(mix.getVol()).toString();
					String cp = mix.getCp();
					
					// Start speed of this trick is determined by the mix info
					String ss = mix.getSs().toString();
					
					// The end speed of this track is defined by the track
					String es = item.getEs();
					
					// The end speed of the previous track is determined by the mix info
					PreviousItem.setEs(mix.getEs().toString());

					// The end point of the previous track is determined by the mix info
					PreviousItem.setEp(mix.getEp());
					
					// Start point of the new track against the time of the current track
					String sp = Long.valueOf(Utils.timeToSample(mix.getSp())).toString();

					// End point is always determined by the track
					String ep = Long.valueOf(Utils.timeToSample(item.getEp())).toString();

					
					String[] fades = convertFades(mix.getSf());
					
					ScoreItem newItem = new ScoreItem(item.getTrackId(),
							item.getArtist(), 
							item.getTitle(),
							item.getId(),
							mixVol,
							cp,
							ss,
							sp,
							es,
							ep,
							fades);
					
					newItems.add(newItem);
				} else {
					// mix not found
					
					System.out.println("Mix not found, adding default");
					
					newItems.add(item);
	
				}
				
				
			} else {
				// Add this first item
				newItems.add(item);
			}
			
			PreviousItem = item;
			
		}
		return newItems;
	}
	
	
	
	
	/**
	 * Find all the mixes for the current track
	 * @param t
	 * @return
	 */
	private List<Mix> getMix(int trackId) {
		
		return em.createNamedQuery("Mix.findForTrackId").setParameter("track1id", trackId).getResultList();
		
	}
	
	/**
	 * Find all the mixes for the current track and previous track
	 * @param t
	 * @return
	 */
	private List<Mix> getMix(int track1Id, int track2Id) {
		
		return em.createNamedQuery("Mix.findForTrackIds").setParameter("track1id", track1Id).
				setParameter("track2id", track2Id).getResultList();
		
	}
	
	/**
	 * Convert the DB representation of set fades to ISL fade commmands
	 * 
	 * @param setFades
	 * @return
	 */
	private String[] convertFades(String setFades) {
		
		String[] fades = setFades.split("/");
		
		
		
		return fades;
	}
	
	

}
