package com.mx.lbg.bimbo.equiposservice.service;

import com.mx.lbg.bimbo.equiposservice.model.LocalidadEntity;

public interface ILocalidadService {
	
	LocalidadEntity consulotaLocalidadPorID (int idCatalogo);		// Consulta el registro de localidad
	LocalidadEntity consulotaLocalidadPorNombre (String localidad);	// Consulta el registro de localidad

}
