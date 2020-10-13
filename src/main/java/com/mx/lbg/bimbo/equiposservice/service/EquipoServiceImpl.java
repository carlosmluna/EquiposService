package com.mx.lbg.bimbo.equiposservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Predicate;

import javax.transaction.Transactional;

import org.hibernate.result.NoMoreReturnsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mx.lbg.bimbo.equiposservice.exception.CustomException;
import com.mx.lbg.bimbo.equiposservice.model.EquipoEntity;
import com.mx.lbg.bimbo.equiposservice.model.LocalidadEntity;
import com.mx.lbg.bimbo.equiposservice.model.dto.CatalogoDTO;
import com.mx.lbg.bimbo.equiposservice.model.dto.EquipoBusquedaDTO;
import com.mx.lbg.bimbo.equiposservice.repository.IEquipoRepository;
import com.mx.lbg.bimbo.equiposservice.repository.ILocalidadRepository;

@Service
@Transactional

public class EquipoServiceImpl implements IEquipoService {
	@Autowired
	IEquipoRepository     equiposRepository;
	@Autowired
	ILocalidadRepository  localidadesRepository;
	@Autowired
	private EntityManager entityManager;
	
	
	@Override
	public List<EquipoEntity> consultarEquiposExcel() {
		List<EquipoEntity> equiposList = new ArrayList<EquipoEntity>();
		try {
			List<EquipoEntity> equiposEntity = equiposRepository.findAll();
			EquipoEntity equipo = null;
			
			// Quito los Nulos en los campos tipo String
			for(EquipoEntity equipoEntity : equiposEntity) {
				equipo = new EquipoEntity();
				
				equipo.setId( equipoEntity.getId() );
				equipo.setEstatus( equipoEntity.getEstatus()!=null ? equipoEntity.getEstatus() : "" );
				equipo.setRegion( equipoEntity.getRegion()!=null ? equipoEntity.getRegion() : "" );
				equipo.setLocalidad( equipoEntity.getLocalidad()!=null ? equipoEntity.getLocalidad() : "" );
				equipo.setBodega( equipoEntity.getBodega()!=null ? equipoEntity.getBodega() : "" );
				equipo.setRazon( equipoEntity.getRazon()!=null ? equipoEntity.getRazon() : "" );
				equipo.setEmpleado( equipoEntity.getEmpleado()!=null ? equipoEntity.getEmpleado() : "" );
				equipo.setSerie_cambio( equipoEntity.getSerie_cambio()!=null ? equipoEntity.getSerie_cambio() : "" );
				equipo.setNombre_cambio( equipoEntity.getNombre_cambio()!=null ? equipoEntity.getNombre_cambio() : "" );
				equipo.setEstatus_cambio( equipoEntity.getEstatus_cambio()!=null ? equipoEntity.getEstatus_cambio() : "" );
				equipo.setMarca( equipoEntity.getMarca()!=null ? equipoEntity.getMarca() : "" );
				equipo.setModelo( equipoEntity.getModelo()!=null ? equipoEntity.getModelo() : "" );
				equipo.setNombre_nuevo( equipoEntity.getNombre_nuevo()!=null ? equipoEntity.getNombre_nuevo() : "" );
				equipo.setSerie_nuevo( equipoEntity.getSerie_nuevo()!=null ? equipoEntity.getSerie_nuevo() : "" );
				equipo.setModelo_nuevo( equipoEntity.getModelo_nuevo()!=null ? equipoEntity.getModelo_nuevo() : "" );
				equipo.setOrden( equipoEntity.getOrden()!=null ? equipoEntity.getOrden() : "" );
				equipo.setAccion( equipoEntity.getAccion()!=null ? equipoEntity.getAccion() : "" );
				equipo.setProgramado( equipoEntity.getProgramado()!=null ? equipoEntity.getProgramado() : "" );
				equipo.setProveedor( equipoEntity.getProveedor()!=null ? equipoEntity.getProveedor() : "" );			  
				equipo.setComentarios( equipoEntity.getComentarios()!=null ? equipoEntity.getComentarios() : "" ); 
				equipo.setArchivo( equipoEntity.getArchivo()!=null ? equipoEntity.getArchivo() : "" );  
				equipo.setGarantia( equipoEntity.getGarantia()!=null ? equipoEntity.getGarantia() : "" );  
				equipo.setSistema( equipoEntity.getSistema()!=null ? equipoEntity.getSistema() : "" );  
				// Nuevos Campos
				equipo.setSerie_renombre( equipoEntity.getSerie_renombre()!=null ? equipoEntity.getSerie_renombre() : "" );
				equipo.setAlta( equipoEntity.getAlta()!=null ? equipoEntity.getAlta() : "" );
				equipo.setEstatus_control( equipoEntity.getEstatus_control()!=null ? equipoEntity.getEstatus_control() : "" );
				equipo.setEst( equipoEntity.getEst() );
				equipo.setReg( equipoEntity.getReg() );
				equipo.setLoc( equipoEntity.getLoc() );
				equipo.setBod( equipoEntity.getBod() );
				equipo.setRaz( equipoEntity.getRaz() );
				equipo.setAcc( equipoEntity.getAcc() );			
				equipo.setNombre_recambio( equipoEntity.getNombre_recambio()!=null ? equipoEntity.getNombre_recambio() : "" );
				
				equiposList.add(equipo);
			}
			return equiposList;
		} catch(CustomException e) {
			throw e;
		} catch (RuntimeException re) {
			throw new CustomException(re.getMessage(), HttpStatus.ACCEPTED, "error");
		}
	}

	@Override
	public EquipoEntity consultaEquipoPorID(Integer idEquipo) {
		try {
			EquipoEntity equipo = new EquipoEntity();
			
			Optional<EquipoEntity> equipoEntity = equiposRepository.findById(idEquipo);
			equipo.setId( equipoEntity.get().getId() );
			equipo.setEstatus( equipoEntity.get().getEstatus()!=null ? equipoEntity.get().getEstatus() : "" );
			equipo.setRegion( equipoEntity.get().getRegion()!=null ? equipoEntity.get().getRegion() : "" );
			equipo.setLocalidad( equipoEntity.get().getLocalidad()!=null ? equipoEntity.get().getLocalidad() : "" );
			equipo.setBodega( equipoEntity.get().getBodega()!=null ? equipoEntity.get().getBodega() : "" );
			equipo.setRazon( equipoEntity.get().getRazon()!=null ? equipoEntity.get().getRazon() : "" );
			equipo.setEmpleado( equipoEntity.get().getEmpleado()!=null ? equipoEntity.get().getEmpleado() : "" );
			equipo.setSerie_cambio( equipoEntity.get().getSerie_cambio()!=null ? equipoEntity.get().getSerie_cambio() : "" );
			equipo.setNombre_cambio( equipoEntity.get().getNombre_cambio()!=null ? equipoEntity.get().getNombre_cambio() : "" );
			equipo.setEstatus_cambio( equipoEntity.get().getEstatus_cambio()!=null ? equipoEntity.get().getEstatus_cambio() : "" );
			equipo.setMarca( equipoEntity.get().getMarca()!=null ? equipoEntity.get().getMarca() : "" );
			equipo.setModelo( equipoEntity.get().getModelo()!=null ? equipoEntity.get().getModelo() : "" );
			equipo.setNombre_nuevo( equipoEntity.get().getNombre_nuevo()!=null ? equipoEntity.get().getNombre_nuevo() : "" );
			equipo.setSerie_nuevo( equipoEntity.get().getSerie_nuevo()!=null ? equipoEntity.get().getSerie_nuevo() : "" );
			equipo.setModelo_nuevo( equipoEntity.get().getModelo_nuevo()!=null ? equipoEntity.get().getModelo_nuevo() : "" );
			equipo.setOrden( equipoEntity.get().getOrden()!=null ? equipoEntity.get().getOrden() : "" );
			equipo.setAccion( equipoEntity.get().getAccion()!=null ? equipoEntity.get().getAccion() : "" );
			equipo.setProgramado( equipoEntity.get().getProgramado()!=null ? equipoEntity.get().getProgramado() : "" );
			equipo.setProveedor( equipoEntity.get().getProveedor()!=null ? equipoEntity.get().getProveedor() : "" );			  
			equipo.setComentarios( equipoEntity.get().getComentarios()!=null ? equipoEntity.get().getComentarios() : "" ); 
			equipo.setArchivo( equipoEntity.get().getArchivo()!=null ? equipoEntity.get().getArchivo() : "" );  
			equipo.setGarantia( equipoEntity.get().getGarantia()!=null ? equipoEntity.get().getGarantia() : "" );  
			equipo.setSistema( equipoEntity.get().getSistema()!=null ? equipoEntity.get().getSistema() : "" );  
			// Nuevos Campos
			equipo.setSerie_renombre( equipoEntity.get().getSerie_renombre()!=null ? equipoEntity.get().getSerie_renombre() : "" );
			equipo.setAlta( equipoEntity.get().getAlta()!=null ? equipoEntity.get().getAlta() : "" );
			equipo.setEstatus_control( equipoEntity.get().getEstatus_control()!=null ? equipoEntity.get().getEstatus_control() : "" );
			equipo.setEst( equipoEntity.get().getEst() );
			equipo.setReg( equipoEntity.get().getReg() );
			equipo.setLoc( equipoEntity.get().getLoc() );
			equipo.setBod( equipoEntity.get().getBod() );
			equipo.setRaz( equipoEntity.get().getRaz() );
			equipo.setAcc( equipoEntity.get().getAcc() );			
			equipo.setNombre_recambio( equipoEntity.get().getNombre_recambio()!=null ? equipoEntity.get().getNombre_recambio() : "" );
			
			return equipo;
		} catch(CustomException e) {
			throw e;
		} catch (RuntimeException re) {
			throw new CustomException(re.getMessage(), HttpStatus.ACCEPTED, "error");
		}
	}
	
	
	@Override
	public boolean existeEquipoPorID(Integer idEquipo) {
		try {
			boolean existe = equiposRepository.existsById(idEquipo);
			return existe;
		} catch(CustomException e) {
			throw e;
		} catch (RuntimeException re) {
			throw new CustomException(re.getMessage(), HttpStatus.ACCEPTED, "error-eliminar");
		} 
	}

	@Override
	public EquipoEntity insertarEquipo(EquipoEntity equipo) {
		try {
			EquipoEntity equipoentity = equiposRepository.save(equipo);
			return equipoentity;
		} catch(CustomException e) {
			throw e;
		} catch (RuntimeException re) {
			throw new CustomException(re.getMessage(), HttpStatus.ACCEPTED, "error-eliminar");
		} 
	}
	
	@Override
	public EquipoEntity actualizarEquipo(EquipoEntity equipo) {
		ServiceUtils utilidades = new ServiceUtils();
		
		try {
			// Obtengo al Descripcion del Catalogo de Accion
			CatalogoDTO catalogo = new CatalogoDTO();
			catalogo.setId( equipo.getAcc() );		
			switch ( equipo.getAcc() ) {
				case 1:  catalogo.setValor("RENOVADO"); 			break;
				case 2:  catalogo.setValor("PROGRAMADO"); 			break;
				case 3:  catalogo.setValor("NO CONTESTA"); 			break;
				case 4:  catalogo.setValor("NO PROCEDE CAMBIO"); 	break;
				default: catalogo.setValor(""); 					break;
			}			
			equipo.setAccion( catalogo.getValor() );
			
			// Obtengo al Descripcion del Catalogo de Bodega
			catalogo = new CatalogoDTO();
			catalogo.setId( equipo.getBod() );		
			switch ( equipo.getBod() ) {
				case 1:  catalogo.setValor("FABRICA BIMBO IRAPUATO"); 		break;
				case 2:  catalogo.setValor("FABRICA BIMBO AZCAPOTZALCO"); 	break;
				case 3:  catalogo.setValor("GLOBAL SERVICES"); 				break;
				case 4:  catalogo.setValor("FABRICA BIMBO MEXICALI"); 		break;
				case 5:  catalogo.setValor("FABRICA BIMBO CHIHUAHUA"); 		break;
				case 6:  catalogo.setValor("FABRICA BIMBO GOLFO"); 			break;
				case 7:  catalogo.setValor("FABRICA BIMBO TOLUCA"); 		break;
				case 8:  catalogo.setValor("FABRICA BIMBO DEL SURESTE"); 	break;
				case 9:  catalogo.setValor("FABRICA BIMBO NORTE"); 			break;
				case 10: catalogo.setValor("CORPORATIVO BIMBO SANTA FE I"); break;
				case 11: catalogo.setValor("FABRICA BIMBO PUEBLA"); 		break;
				case 12: catalogo.setValor("FABRICA MARINELA OCCIDENTE"); 	break;
				case 13: catalogo.setValor("CORPORATIVO BIMBO SANTA FE "); 	break;
				case 14: catalogo.setValor("FABRICA BARCEL OCCIDENTE"); 	break;
				case 15: catalogo.setValor("FABRICA BARCEL MEXICO TOLUCA"); break;
				case 16: catalogo.setValor("FABRICA BIMBO SAN LUIS"); 		break;
				default: catalogo.setValor(""); 							break;
			}	
			equipo.setBodega( catalogo.getValor() );
			
			// Obtengo al Descripcion del Catalogo de Estatus
			catalogo = new CatalogoDTO();
			catalogo.setId( equipo.getEst() );		
			switch ( equipo.getEst() ) {
				case 1:  catalogo.setValor("RENOVADO"); 	break;
				case 2:  catalogo.setValor("POR RENOVAR");	break;
				case 3:  catalogo.setValor("PENDIENTES"); 	break;			
				default: catalogo.setValor(""); 			break;
			}		
			equipo.setEstatus( catalogo.getValor() );			
			
			// Obtengo al Descripcion del Catalogo de Region 
			catalogo.setId( equipo.getReg() );	
			switch ( equipo.getReg() ) {
				case 1:  catalogo.setValor("CENTRAL"); 		break;
				case 2:  catalogo.setValor("METRO");		break;
				case 3:  catalogo.setValor("SURESTE"); 		break;
				case 4:  catalogo.setValor("CORPORATIVO "); break;
				case 5:  catalogo.setValor("NORTE");		break;
				case 6:  catalogo.setValor("BAJIO"); 		break;
				case 7:  catalogo.setValor("GLOBO"); 		break;
				case 8:  catalogo.setValor("NOROESTE"); 	break;
				default: catalogo.setValor(""); 			break;
			}
			equipo.setRegion( catalogo.getValor() );
			
			// Obtengo al Descripcion del Catalogo de Region Social del Usaurio
			catalogo.setId( equipo.getRaz() );	
			switch ( equipo.getRaz() ) {
				case 1:  catalogo.setValor("BIMBO, S.A. DE C.V."); 		break;
				case 2:  catalogo.setValor("TRADICIÓN EN PASTELERÍAS S. A. DE C. V.");	break;
				case 3:  catalogo.setValor("PRODUCTOS RICOLINO SAPI DE CV"); 			break;
				case 4:  catalogo.setValor("BARCEL, S.A. DE C.V. ");	 				break;
				case 5:  catalogo.setValor("CORPORATIVO BIMBO S.A. DE C.V.");			break;
				case 6:  catalogo.setValor("EQUIPO DE USO COMUN"); 						break;
				case 7:  catalogo.setValor("MOLDES Y EXHIBIDORES, S.A DE C.V."); 		break;
				default: catalogo.setValor(""); 			break;
			}
			equipo.setRazon( catalogo.getValor() );
			
			String localidad = "";
			try {
				Optional<LocalidadEntity> localidadDto = localidadesRepository.findById( equipo.getLoc() );
				localidad = localidadDto.get().getLocalidad()!=null ? localidadDto.get().getLocalidad() : "";
			} catch (Exception e) {
				localidad = "";
			}
			equipo.setLocalidad( localidad );
			
			EquipoEntity equipoentity = equiposRepository.save(equipo);
			return equipoentity;
		} catch(CustomException e) {
			throw e;
		} catch (RuntimeException re) {
			throw new CustomException(re.getMessage(), HttpStatus.ACCEPTED, "error-eliminar");
		} 
	}

	@Override
	public EquipoEntity eliminarEquipo(EquipoEntity equipo) {
		try {
			equiposRepository.delete(equipo); 
			return equipo;
		} catch(CustomException e) {
			throw e;
		} catch (RuntimeException re) {
			throw new CustomException(re.getMessage(), HttpStatus.ACCEPTED, "error-eliminar");
		} 
	}

	@Override
	public List<EquipoEntity> consultarEquiposDTO(EquipoBusquedaDTO equipoBuscar) {
		try {
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<EquipoEntity> criteriaQuery = criteriaBuilder.createQuery(EquipoEntity.class);
			Root<EquipoEntity> root = criteriaQuery.from(EquipoEntity.class);
			
			// iteria's for query using CriteriaBuilder 
			List<Predicate> searchCriterias = new ArrayList<>();
			
			if( equipoBuscar.getId()!=null && !equipoBuscar.getId().contentEquals("") ) {
				searchCriterias.add( criteriaBuilder.equal( root.get("id"), (String) equipoBuscar.getId() ) );
			}
			if( equipoBuscar.getEstatus()!=null && !equipoBuscar.getEstatus().contentEquals("") ) {
				searchCriterias.add( criteriaBuilder.equal( root.get("est"), (String) equipoBuscar.getEstatus() ) );
			}
			if( equipoBuscar.getRegion()!=null && !equipoBuscar.getRegion().contentEquals("") ) {
				searchCriterias.add( criteriaBuilder.equal( root.get("reg"), (String) equipoBuscar.getRegion() ) );
			}
			if( equipoBuscar.getLocalidad()!=null && !equipoBuscar.getLocalidad().contentEquals("") ) {
				searchCriterias.add( criteriaBuilder.equal( root.get("loc"), (String) equipoBuscar.getLocalidad() ) );
			}
			if( equipoBuscar.getBodega()!=null && !equipoBuscar.getBodega().contentEquals("") ) {
				searchCriterias.add( criteriaBuilder.equal( root.get("bod"), (String) equipoBuscar.getBodega() ) );
			}
			if( equipoBuscar.getRazon_social()!=null && !equipoBuscar.getRazon_social().contentEquals("") ) {
				searchCriterias.add( criteriaBuilder.equal( root.get("raz"), (String) equipoBuscar.getRazon_social() ) );
			}
			if( equipoBuscar.getModelo_uevo()!=null && !equipoBuscar.getModelo_uevo().contentEquals("") ) {
				searchCriterias.add( criteriaBuilder.equal( root.get("modelo_nuevo"), (String) equipoBuscar.getModelo_uevo() ) );
			}
			if( equipoBuscar.getAccion()!=null && !equipoBuscar.getAccion().contentEquals("") ) {
				searchCriterias.add( criteriaBuilder.equal( root.get("acc"), (String) equipoBuscar.getAccion() ) );
			}
			if( equipoBuscar.getProgramacion()!=null && !equipoBuscar.getProgramacion().contentEquals("") ) {
				searchCriterias.add( criteriaBuilder.equal( root.get("programado"), (String) equipoBuscar.getProgramacion() ) );
			}
			if( equipoBuscar.getProveedor()!=null && !equipoBuscar.getProveedor().contentEquals("") ) {
				searchCriterias.add( criteriaBuilder.equal( root.get("proveedor"), (String) equipoBuscar.getProveedor() ) );
			}
			
			criteriaQuery.select( root ).where( criteriaBuilder.and( searchCriterias.toArray(new Predicate[searchCriterias.size()]) ));
			return entityManager.createQuery(criteriaQuery).getResultList();
		} catch(CustomException e) {
			throw e;
		} catch (RuntimeException re) {
			throw new CustomException(re.getMessage(), HttpStatus.ACCEPTED, "error");
		}
	} 

	@Override
	public List<EquipoEntity> quitarNulosResultados( List<EquipoEntity> equiposEntity ) {  
		List<EquipoEntity> equiposList = new ArrayList<EquipoEntity>();
		
		EquipoEntity equipo = null;
		
		// Quito los Nulos en los campos tipo String
		for(EquipoEntity equipoEntity : equiposEntity) {
			equipo = new EquipoEntity();
			
			equipo.setId( equipoEntity.getId() );
			equipo.setEstatus( equipoEntity.getEstatus()!=null ? equipoEntity.getEstatus() : "" );
			equipo.setRegion( equipoEntity.getRegion()!=null ? equipoEntity.getRegion() : "" );
			equipo.setLocalidad( equipoEntity.getLocalidad()!=null ? equipoEntity.getLocalidad() : "" );
			equipo.setBodega( equipoEntity.getBodega()!=null ? equipoEntity.getBodega() : "" );
			equipo.setRazon( equipoEntity.getRazon()!=null ? equipoEntity.getRazon() : "" );
			equipo.setEmpleado( equipoEntity.getEmpleado()!=null ? equipoEntity.getEmpleado() : "" );
			equipo.setSerie_cambio( equipoEntity.getSerie_cambio()!=null ? equipoEntity.getSerie_cambio() : "" );
			equipo.setNombre_cambio( equipoEntity.getNombre_cambio()!=null ? equipoEntity.getNombre_cambio() : "" );
			equipo.setEstatus_cambio( equipoEntity.getEstatus_cambio()!=null ? equipoEntity.getEstatus_cambio() : "" );
			equipo.setMarca( equipoEntity.getMarca()!=null ? equipoEntity.getMarca() : "" );
			equipo.setModelo( equipoEntity.getModelo()!=null ? equipoEntity.getModelo() : "" );
			equipo.setNombre_nuevo( equipoEntity.getNombre_nuevo()!=null ? equipoEntity.getNombre_nuevo() : "" );
			equipo.setSerie_nuevo( equipoEntity.getSerie_nuevo()!=null ? equipoEntity.getSerie_nuevo() : "" );
			equipo.setModelo_nuevo( equipoEntity.getModelo_nuevo()!=null ? equipoEntity.getModelo_nuevo() : "" );
			equipo.setOrden( equipoEntity.getOrden()!=null ? equipoEntity.getOrden() : "" );
			equipo.setAccion( equipoEntity.getAccion()!=null ? equipoEntity.getAccion() : "" );
			equipo.setProgramado( equipoEntity.getProgramado()!=null ? equipoEntity.getProgramado() : "" );
			equipo.setProveedor( equipoEntity.getProveedor()!=null ? equipoEntity.getProveedor() : "" );			  
			equipo.setComentarios( equipoEntity.getComentarios()!=null ? equipoEntity.getComentarios() : "" ); 
			equipo.setArchivo( equipoEntity.getArchivo()!=null ? equipoEntity.getArchivo() : "" );  
			equipo.setGarantia( equipoEntity.getGarantia()!=null ? equipoEntity.getGarantia() : "" );  
			equipo.setSistema( equipoEntity.getSistema()!=null ? equipoEntity.getSistema() : "" );  
			// Nuevos Campos
			equipo.setSerie_renombre( equipoEntity.getSerie_renombre()!=null ? equipoEntity.getSerie_renombre() : "" );
			equipo.setAlta( equipoEntity.getAlta()!=null ? equipoEntity.getAlta() : "" );
			equipo.setEstatus_control( equipoEntity.getEstatus_control()!=null ? equipoEntity.getEstatus_control() : "" );
			equipo.setEst( equipoEntity.getEst() );
			equipo.setReg( equipoEntity.getReg() );
			equipo.setLoc( equipoEntity.getLoc() );
			equipo.setBod( equipoEntity.getBod() );
			equipo.setRaz( equipoEntity.getRaz() );
			equipo.setAcc( equipoEntity.getAcc() );			
			equipo.setNombre_recambio( equipoEntity.getNombre_recambio()!=null ? equipoEntity.getNombre_recambio() : "" );
			
			equiposList.add(equipo);
		}
		
		return equiposList;
	}

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
}
