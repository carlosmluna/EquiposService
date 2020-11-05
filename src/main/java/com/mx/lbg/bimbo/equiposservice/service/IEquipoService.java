package com.mx.lbg.bimbo.equiposservice.service;

import java.util.List;

import com.mx.lbg.bimbo.equiposservice.model.EquipoEntity;
import com.mx.lbg.bimbo.equiposservice.model.LocalidadEntity;
import com.mx.lbg.bimbo.equiposservice.model.dto.EquipoBusquedaDTO;

public interface IEquipoService {

	List<EquipoEntity> consultarEquiposExcel();					// Consulta los equipos de la base de datos
	EquipoEntity consultaEquipoPorID(Integer idEquipo);			// Consulta un registro del equipo de la base de datos
	boolean existeEquipoPorID(Integer idEquipo);				// Valida existencia del Equipo
	EquipoEntity insertarEquipo( EquipoEntity equipo );			// Inserta equipo en la base de datos
	EquipoEntity actualizarEquipo( EquipoEntity equipo );		// Actualiza equipo en la base de datos
	EquipoEntity actualizarEquipoCorto( EquipoEntity equipo );	// Actualiza equipo en la base de datos
	EquipoEntity eliminarEquipo( EquipoEntity equipo );			// Eliminar equipo en la base de datos
	
	List<EquipoEntity> consultarEquiposDTO(EquipoBusquedaDTO equipoBuscar);					// Consulta los equipos de la base de datos por medio del DTO
	public List<EquipoEntity> quitarNulosResultados( List<EquipoEntity> equiposEntity );	// Quita valores nulos de la lista

	// LocalidadEntity consulotaLocalidadPorID (int idCatalogo);	// Consulta el registro de localidad
}
