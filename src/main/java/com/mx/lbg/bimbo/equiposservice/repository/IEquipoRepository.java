package com.mx.lbg.bimbo.equiposservice.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mx.lbg.bimbo.equiposservice.model.EquipoEntity;
import com.mx.lbg.bimbo.equiposservice.model.dto.EquipoBusquedaDTO;


@Repository
public interface IEquipoRepository extends CrudRepository<EquipoEntity, Integer> {
	
	/**
	 * Consulta de la entidad Personas dependiendo el criterio de busqueda
	 * 
	 * @param EquipoBusquedaDTO equipo
	 * @return List<EquipoBusquedaDTO>
	 */
	// List<EquipoEntity> findEquipoPorDTO( EquipoBusquedaDTO equipoBuscar  );
	
	List<EquipoEntity> findAll();
	
}
