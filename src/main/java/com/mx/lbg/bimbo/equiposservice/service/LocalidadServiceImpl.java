package com.mx.lbg.bimbo.equiposservice.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mx.lbg.bimbo.equiposservice.exception.CustomException;
import com.mx.lbg.bimbo.equiposservice.model.LocalidadEntity;
import com.mx.lbg.bimbo.equiposservice.repository.ILocalidadRepository;

@Service
@Transactional
public class LocalidadServiceImpl implements ILocalidadService {
	@Autowired
	ILocalidadRepository  localidadesRepository;
	
	
	@Override
	public LocalidadEntity consulotaLocalidadPorID(int idCatalogo) {
		try {
			LocalidadEntity localidad = new LocalidadEntity();
			
			Optional<LocalidadEntity> localidadEntity = localidadesRepository.findById(idCatalogo);
			localidad.setId( localidadEntity.get().getId() );
			localidad.setLocalidad( localidadEntity.get().getLocalidad() );
			
			return localidad;
		} catch(CustomException e) {
			throw e;
		} catch (RuntimeException re) {
			throw new CustomException(re.getMessage(), HttpStatus.ACCEPTED, "error-localidades");
		}
	}


	@Override
	public LocalidadEntity consulotaLocalidadPorNombre(String localidad) {
		LocalidadEntity localidadRet = new LocalidadEntity();
		
		try {			
			LocalidadEntity localidadEntity = localidadesRepository.findByLocalidad( localidad );
			if ( localidadEntity!=null ) {
				localidadRet.setId( localidadEntity.getId() );
				localidadRet.setLocalidad( localidadEntity.getLocalidad().trim() );
			} else {
				localidadRet.setId( 0 );
				localidadRet.setLocalidad( "" );
			}			
			return localidadRet;
		} catch(CustomException e) {
			throw e;
		} catch (RuntimeException re) {
			throw new CustomException(re.getMessage(), HttpStatus.ACCEPTED, "error-localidades-pornombre");
		}
	}

}
