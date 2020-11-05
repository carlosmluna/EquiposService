package com.mx.lbg.bimbo.equiposservice.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mx.lbg.bimbo.equiposservice.model.EquipoEntity;
import com.mx.lbg.bimbo.equiposservice.model.dto.EquipoBusquedaDTO;


@Repository
public interface IEquipoRepository extends CrudRepository<EquipoEntity, Integer> {
	List<EquipoEntity> findAll();	
}
