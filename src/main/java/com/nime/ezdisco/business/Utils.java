package com.nime.ezdisco.business;

public class Utils {
	
	public static final int SAMPLE_RATE = 44100;
	
	
	/**
	 * Convert a time value to sample number
	 * @param time
	 * mm:ss.sss
	 * 
	 * or hh:mm:ss
	 * 
	 * 
	 * @return
	 */
	public static long timeToSample(String time) {
		
		long sample = 0;
		
		String[] parts = time.split(":");
		 
		// Convert hh:mm:ss format first
		
		if (parts.length==3) {
			long mins = Long.valueOf(parts[1]);
			long sec = Long.valueOf(parts[2]);
			sample = ((mins*60)+sec)*SAMPLE_RATE;
		} else if (parts.length==2) {
			
			// mm:ss.sss format
			long mins = Long.valueOf(parts[0]);
			long ms = Long.valueOf(parts[1].replace(".", ""));
			sample = (((mins*60*1000) + ms) * SAMPLE_RATE)/1000;	
		} else if (parts.length==1) {
			sample = Long.valueOf(time);
		}
		
		return sample;
	}

}
