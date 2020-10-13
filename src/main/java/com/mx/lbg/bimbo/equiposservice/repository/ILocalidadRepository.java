package com.mx.lbg.bimbo.equiposservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mx.lbg.bimbo.equiposservice.model.LocalidadEntity;


@Repository
public interface ILocalidadRepository extends JpaRepository<LocalidadEntity, Integer> {
	
}
