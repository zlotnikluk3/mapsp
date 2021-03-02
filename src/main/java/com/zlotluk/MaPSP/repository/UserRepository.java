package com.zlotluk.MaPSP.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zlotluk.MaPSP.model.Userr;
@Repository
public interface UserRepository extends JpaRepository<Userr, String> {

}
