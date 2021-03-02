package com.zlotluk.MaPSP.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zlotluk.MaPSP.model.Eventt;
@Repository
public interface EventRepository extends JpaRepository<Eventt, Long> {

}
