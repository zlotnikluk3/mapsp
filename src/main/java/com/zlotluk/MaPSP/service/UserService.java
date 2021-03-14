package com.zlotluk.MaPSP.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zlotluk.MaPSP.model.Userr;
import com.zlotluk.MaPSP.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository repo;

	public List<Userr> listAll() {
		return repo.findAll();
	}

	public Userr first() {
		return repo.findAll().get(0);
	}
	
	public Userr adm() {
		return repo.findAll().get(1);
	}

}