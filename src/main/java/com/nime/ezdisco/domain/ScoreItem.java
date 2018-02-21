package com.nime.ezdisco.domain;

public class ScoreItem {
	
	private int trackId;
	
	private String artist;
	
	private String title;
	
	private String id;
	
	private String vol;
	
	private String cp;
	
	private String ss;
	
	private String sp;
	
	private String es;
	
	private String ep;
	
	private String[] fades;
	
	public ScoreItem(
			int trackId,
			String art, 
			String tit, 
			String id, 
			String vol, 
			String cp, 
			String ss, 
			String sp, 
			String es, 
			String ep, 
			String[] fades) {
		this.trackId = trackId;
		this.artist = art;
		this.title = tit;
		this.id = id;
		this.vol = vol;
		this.cp = cp;
		this.ss = ss;
		this.sp = sp;
		this.es = es;
		this.ep = ep;
		this.fades = fades;	
	}
	
	public String toString() {
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("TRACK" + "\n");
		sb.append("ART=" + artist + "\n");
		sb.append("TIT=" + title + "\n");
		sb.append("ID=" + id + "\n");
		sb.append("VOL=" + vol + "\n");
		sb.append("CP=" + cp + "\n");
		sb.append("SS=" + ss + "\n");
		sb.append("SP=" + sp + "\n");
		sb.append("ES=" + es + "\n");
		sb.append("EP=" + ep + "\n");
		
		if (fades!=null) {
			for (String fade : fades) {
				sb.append("SF=" + fade + "\n");
			}
		}
		sb.append("END_TRACK" + "\n");
		
		
		return sb.toString();
	}
	
	public int getTrackId() {
		return trackId;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public String getCp() {
		return cp;
	}
	
	public String getEp() {
		return ep;
	}
	
	public String getEs() {
		return es;
	}
	
	public String[] getFades() {
		return fades;
	}
	
	public String getId() {
		return id;
	}
	public String getSp() {
		return sp;
	}
	
	public String getSs() {
		return ss;
	}
	public String getTitle() {
		return title;
	}
	public String getVol() {
		return vol;
	}
	
	/**
	 * Set the end speed
	 * 
	 * @param es
	 */
	public void setEs(String es) {
		this.es = es;
	}
	
	/**
	 * Set the end-point
	 * 
	 * @param ep
	 */
	public void setEp(String ep) {
		this.ep = ep;
	}
	

}
