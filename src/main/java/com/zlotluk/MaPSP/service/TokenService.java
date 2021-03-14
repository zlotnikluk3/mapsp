package com.zlotluk.MaPSP.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zlotluk.MaPSP.model.Tokenn;
import com.zlotluk.MaPSP.repository.TokenRepository;

@Service
@Transactional
public class TokenService {

	@Autowired
	private TokenRepository repo;

	public List<Tokenn> listAll() {
		return repo.findAll();
	}

	public void save(Tokenn token) {
		repo.save(token);
	}

	public void update(String tok, boolean sav, String nam) {
		repo.save(new Tokenn(tok, sav, nam));
	}

	public void delete(String tok) {
		repo.deleteById(tok);
	}
}