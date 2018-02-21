package com.nime.ezdisco.data;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "mix")
@NamedQueries( {
    @NamedQuery(name = "Mix.findForTrackId", query = "SELECT m FROM Mix m where m.track1.id = :track1id"),
    @NamedQuery(name = "Mix.findForTrackIds", query = "SELECT m FROM Mix m where m.track1.id = :track1id and m.track2.id = :track2id")
})
public class Mix {
	
	@Column(name="mix_id")
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="track_id1",referencedColumnName="track_id", nullable=true)
	private Track track1;
	
	@ManyToOne
	@JoinColumn(name="track_id2",referencedColumnName="track_id", nullable=true)
	private Track track2;
	
	private int vol;
	
	private String cp;
	
	private BigDecimal ss;
	
	private String sp;
	
	
	@Type(type="text")
	private String sf;
	
	private String ep;
	
	private BigDecimal es;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Track getTrack1() {
		return track1;
	}
	
	public void setTrack1(Track track1) {
		this.track1 = track1;
	}
	
	public Track getTrack2() {
		return track2;
	}
	
	public void setTrack2(Track track2) {
		this.track2 = track2;
	}

	public int getVol() {
		return vol;
	}

	public void setVol(int vol) {
		this.vol = vol;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public BigDecimal getSs() {
		return ss;
	}

	public void setSs(BigDecimal ss) {
		this.ss = ss;
	}

	public String getSp() {
		return sp;
	}

	public void setSp(String sp) {
		this.sp = sp;
	}

	public String getSf() {
		return sf;
	}

	public void setSf(String sf) {
		this.sf = sf;
	}

	public String getEp() {
		return ep;
	}

	public void setEp(String ep) {
		this.ep = ep;
	}

	public BigDecimal getEs() {
		return es;
	}

	public void setEs(BigDecimal es) {
		this.es = es;
	}

	@Override
	public String toString() {
		return "Mix [id=" + id + ", track1=" + track1 + ", track2=" + track2 + ", vol=" + vol + ", cp=" + cp + ", ss="
				+ ss + ", sp=" + sp + ", sf=" + sf + ", ep=" + ep + ", es=" + es + "]";
	}
	
	
	
	
	
	

}
