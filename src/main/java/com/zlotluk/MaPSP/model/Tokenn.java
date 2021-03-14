package com.zlotluk.MaPSP.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Tokenn {

	@Id
	@Column(name = "tokenn")
	private String token;

	@Column(name = "saved")
	private boolean saved;

	@Column(name = "nam")
	private String nam;

	public Tokenn(String tok, boolean sav, String nam) {
		// TODO Auto-generated constructor stub
		this.token = tok;
		this.saved = sav;
		this.nam = nam;
	}

	public Tokenn() {
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isSaved() {
		return saved;
	}

	public void setSaved(boolean saved) {
		this.saved = saved;
	}

	public String getNam() {
		return nam;
	}

	public void setNam(String nam) {
		this.nam = nam;
	}

}
