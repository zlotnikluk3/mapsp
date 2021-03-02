package com.zlotluk.MaPSP.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Eventt {
	@Id
	@Column(name = "id")
	private Long id;
	@Column(name = "opis", nullable = false)
	private String opis;
	@Column(name = "lat", nullable = false)
	private String lat;
	@Column(name = "lng", nullable = false)
	private String lng;

	public Eventt() {
	}

	public Eventt(Long id, String opis, String lat, String lng) {
		super();
		this.id = id;
		this.opis = opis;
		this.lat = lat;
		this.lng = lng;
	}

	public Eventt(String opis, String lat, String lng) {
		super();
		this.opis = opis;
		this.lat = lat;
		this.lng = lng;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

}
