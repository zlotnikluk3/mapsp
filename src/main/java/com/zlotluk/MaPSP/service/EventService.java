package com.zlotluk.MaPSP.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zlotluk.MaPSP.model.Eventt;
import com.zlotluk.MaPSP.repository.EventRepository;

@Service
@Transactional
public class EventService {

	@Autowired
	private EventRepository repo;

	public List<Eventt> listAll() {
		return repo.findAll();
	}

	public void save(Eventt event) {
		repo.save(event);
	}

	public void update(long id, String opis, String lat, String lng) {
		repo.save(new Eventt(id, opis, lat, lng));
	}

	public Eventt get(long id) {
		return repo.findById(id).get();
	}

	public void delete(long id) {
		repo.deleteById(id);
	}
}