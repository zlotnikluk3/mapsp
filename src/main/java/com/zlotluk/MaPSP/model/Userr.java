package com.zlotluk.MaPSP.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Userr {

	@Id
	@Column(name = "userr")
	private String user;
	@Column(name = "pass", nullable = false)
	private String pass;

	public Userr() {
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	};

}
