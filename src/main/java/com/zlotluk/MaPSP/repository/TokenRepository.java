package com.zlotluk.MaPSP.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zlotluk.MaPSP.model.Tokenn;
@Repository
public interface TokenRepository extends JpaRepository<Tokenn, String> {

}
