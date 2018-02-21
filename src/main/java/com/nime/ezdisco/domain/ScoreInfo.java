package com.nime.ezdisco.domain;

import java.util.List;

public class ScoreInfo {
	
	private List<ScoreItem> items;
	
	private String key;
	
	private String len;
	
	private String max;
	
	private String size;
	
	public ScoreInfo(String key, String len, String max, String size, List<ScoreItem> items) {
		this.key = key;
		this.len = len;
		this.max = max;
		this.size = size;
		this.items = items;
	}
	
	public String toString() {
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("BEGIN\n");
		sb.append("KEY=" + key + "\n");
		
		if (items!=null) {
			for (ScoreItem itm : items) {
				sb.append(itm.toString());
			}
		}

		sb.append("LEN=" + len + "\n");
		sb.append("MAX=" + max + "\n");
		sb.append("SIZE=" + size + "\n");
		sb.append("END" + "\n");
		
		
		return sb.toString();
	}
	
	

}
