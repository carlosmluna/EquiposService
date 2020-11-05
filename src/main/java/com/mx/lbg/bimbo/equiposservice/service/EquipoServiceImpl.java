package com.mx.lbg.bimbo.equiposservice.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Predicate;

import javax.transaction.Transactional;

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
	private EntityManager entityManager;
	@Autowired
	ILocalidadService  	  localidadService; 
	/* @Autowired
	ILocalidadRepository  localidadesRepository; */
	
	
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
				// Informacion del Equipo a reemplazar
				equipo.setNombre_cambio( equipoEntity.getNombre_cambio()!=null ? equipoEntity.getNombre_cambio() : "" );
				equipo.setSerie_cambio( equipoEntity.getSerie_cambio()!=null ? equipoEntity.getSerie_cambio() : "" );				
				equipo.setEstatus_cambio( equipoEntity.getEstatus_cambio()!=null ? equipoEntity.getEstatus_cambio() : "" );
				equipo.setMarca( equipoEntity.getMarca()!=null ? equipoEntity.getMarca() : "" );
				equipo.setModelo( equipoEntity.getModelo()!=null ? equipoEntity.getModelo() : "" );
				// Informacion del Equipo Nuevo				
				equipo.setNombre_nuevo( equipoEntity.getNombre_nuevo()!=null ? equipoEntity.getNombre_nuevo() : "" );
				equipo.setSerie_nuevo( equipoEntity.getSerie_nuevo()!=null ? equipoEntity.getSerie_nuevo() : "" );
				equipo.setAlta( equipoEntity.getAlta()!=null ? equipoEntity.getAlta() : "" );				
				equipo.setModelo_nuevo( equipoEntity.getModelo_nuevo()!=null ? equipoEntity.getModelo_nuevo() : "" );
				equipo.setGarantia( equipoEntity.getGarantia()!=null ? equipoEntity.getGarantia() : "" );  
				equipo.setSistema( equipoEntity.getSistema()!=null ? equipoEntity.getSistema() : "" );  
				equipo.setOrden( equipoEntity.getOrden()!=null ? equipoEntity.getOrden() : "" );
				equipo.setComentarios( equipoEntity.getComentarios()!=null ? equipoEntity.getComentarios() : "" ); 
				equipo.setProgramado( equipoEntity.getProgramado()!=null ? equipoEntity.getProgramado() : "" );
				equipo.setProveedor( equipoEntity.getProveedor()!=null ? equipoEntity.getProveedor() : "" );	
				equipo.setEstatus_control( equipoEntity.getEstatus_control() );				
				equipo.setAccion( equipoEntity.getAccion()!=null ? equipoEntity.getAccion() : "" );				
				equipo.setEst( equipoEntity.getEst() );
				equipo.setReg( equipoEntity.getReg() );
				equipo.setLoc( equipoEntity.getLoc() );
				equipo.setBod( equipoEntity.getBod() );
				equipo.setRaz( equipoEntity.getRaz() );
				equipo.setAcc( equipoEntity.getAcc() );		
				equipo.setCtrl( equipoEntity.getCtrl() );	
				equipo.setUsuario_modifica( equipoEntity.getUsuario_modifica() );
				equipo.setFecha_modifica( equipoEntity.getFecha_modifica() );
				// equipo.setArchivo( equipoEntity.getArchivo()!=null ? equipoEntity.getArchivo() : "" );  
				// equipo.setSerie_renombre( equipoEntity.getSerie_renombre()!=null ? equipoEntity.getSerie_renombre() : "" );
				// equipo.setNombre_recambio( equipoEntity.getNombre_recambio()!=null ? equipoEntity.getNombre_recambio() : "" );
				// 26Oct2020 - campos Nuevos
            	equipo.setMes_renovacion( equipoEntity.getMes_renovacion()!=null ? equipoEntity.getMes_renovacion() : "" );
            	equipo.setTicket( equipoEntity.getTicket()!=null ? equipoEntity.getTicket() : "" );
            	equipo.setComm_control( equipoEntity.getComm_control()!=null ? equipoEntity.getComm_control() : "" );
				
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
			// Informacion del Equipo a reemplazar
			equipo.setNombre_cambio( equipoEntity.get().getNombre_cambio()!=null ? equipoEntity.get().getNombre_cambio() : "" );
			equipo.setSerie_cambio( equipoEntity.get().getSerie_cambio()!=null ? equipoEntity.get().getSerie_cambio() : "" );			
			equipo.setEstatus_cambio( equipoEntity.get().getEstatus_cambio()!=null ? equipoEntity.get().getEstatus_cambio() : "" );
			equipo.setMarca( equipoEntity.get().getMarca()!=null ? equipoEntity.get().getMarca() : "" );
			equipo.setModelo( equipoEntity.get().getModelo()!=null ? equipoEntity.get().getModelo() : "" );
			// Informacion del Equipo Nuevo	
			equipo.setNombre_nuevo( equipoEntity.get().getNombre_nuevo()!=null ? equipoEntity.get().getNombre_nuevo() : "" );
			equipo.setSerie_nuevo( equipoEntity.get().getSerie_nuevo()!=null ? equipoEntity.get().getSerie_nuevo() : "" );
			equipo.setAlta( equipoEntity.get().getAlta()!=null ? equipoEntity.get().getAlta() : "" );
			equipo.setModelo_nuevo( equipoEntity.get().getModelo_nuevo()!=null ? equipoEntity.get().getModelo_nuevo() : "" );
			equipo.setGarantia( equipoEntity.get().getGarantia()!=null ? equipoEntity.get().getGarantia() : "" );  
			equipo.setSistema( equipoEntity.get().getSistema()!=null ? equipoEntity.get().getSistema() : "" );  
			equipo.setOrden( equipoEntity.get().getOrden()!=null ? equipoEntity.get().getOrden() : "" );
			equipo.setComentarios( equipoEntity.get().getComentarios()!=null ? equipoEntity.get().getComentarios() : "" ); 
			equipo.setProgramado( equipoEntity.get().getProgramado()!=null ? equipoEntity.get().getProgramado() : "" );
			equipo.setProveedor( equipoEntity.get().getProveedor()!=null ? equipoEntity.get().getProveedor() : "" );	
			equipo.setEstatus_control( equipoEntity.get().getEstatus_control()!=null ? equipoEntity.get().getEstatus_control() : "" );
			equipo.setAccion( equipoEntity.get().getAccion()!=null ? equipoEntity.get().getAccion() : "" );			
			equipo.setEst( equipoEntity.get().getEst() );
			equipo.setReg( equipoEntity.get().getReg() );
			equipo.setLoc( equipoEntity.get().getLoc() );
			equipo.setBod( equipoEntity.get().getBod() );
			equipo.setRaz( equipoEntity.get().getRaz() );
			equipo.setAcc( equipoEntity.get().getAcc() );	
			equipo.setCtrl( equipoEntity.get().getCtrl() );
			equipo.setUsuario_modifica( equipoEntity.get().getUsuario_modifica()!=null ? equipoEntity.get().getUsuario_modifica() : "" );
			equipo.setFecha_modifica( equipoEntity.get().getFecha_modifica()!=null ? equipoEntity.get().getFecha_modifica() : "" );
			// equipo.setAccion( equipoEntity.get().getAccion()!=null ? equipoEntity.get().getAccion() : "" );		  
			// equipo.setArchivo( equipoEntity.get().getArchivo()!=null ? equipoEntity.get().getArchivo() : "" );  
			// equipo.setSerie_renombre( equipoEntity.get().getSerie_renombre()!=null ? equipoEntity.get().getSerie_renombre() : "" );
			// equipo.setNombre_recambio( equipoEntity.get().getNombre_recambio()!=null ? equipoEntity.get().getNombre_recambio() : "" );
			// 26Oct2020 - campos Nuevos
        	equipo.setMes_renovacion( equipoEntity.get().getMes_renovacion()!=null ? equipoEntity.get().getMes_renovacion() : "" );
        	equipo.setTicket( equipoEntity.get().getTicket()!=null ? equipoEntity.get().getTicket() : "" );
        	equipo.setComm_control( equipoEntity.get().getComm_control()!=null ? equipoEntity.get().getComm_control() : "" );
			
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
		int idEstatus   = 0;
		int idRegion    = 0;
		int idBodega    = 0;
		int idRazonSoc  = 0;
		int idAccion    = 0;
		int idLocalidad = 0;
		int idEstCtrl   = 0;
		
		try {
			// ********** Estatus  **********
			if ( equipo.getEstatus().trim().equals("RENOVADO") ) {
				idEstatus = 1;
			} if ( equipo.getEstatus().trim().equals("POR RENOVAR") ) {
				idEstatus = 2;
			} if ( equipo.getEstatus().trim().equals("PENDIENTES") ) {
				idEstatus = 3;
			}
			
			// ********** Region **********
			if ( equipo.getRegion().trim().equals("CENTRAL") ) {
				idRegion = 1;
			} if ( equipo.getRegion().trim().equals("METRO") ) {
				idRegion = 2;
			} if ( equipo.getRegion().trim().equals("SURESTE") ) {
				idRegion = 3;
			} if ( equipo.getRegion().trim().equals("CORPORATIVO") ) {
				idRegion = 4;
			} if ( equipo.getRegion().trim().equals("NORTE") ) {
				idRegion = 5;
			} if ( equipo.getRegion().trim().equals("BAJIO") ) {
				idRegion = 6;
			} if ( equipo.getRegion().trim().equals("GLOBO") ) {
				idRegion = 7;
			} if ( equipo.getRegion().trim().equals("NOROESTE") ) {
				idRegion = 8;
			}
			
			// ********** Localidad **********
			try {
				LocalidadEntity localidadDto = localidadService.consulotaLocalidadPorNombre( equipo.getLocalidad().trim() );
				idLocalidad = localidadDto.getId();
			} catch (Exception e) {
				idLocalidad = 0;
			}
			
			// ********** Bodega **********
			if ( equipo.getBodega().trim().equals("FABRICA BIMBO IRAPUATO") ) {
				idBodega = 1;
			} if ( equipo.getBodega().trim().equals("FABRICA BIMBO AZCAPOTZALCO") ) {
				idBodega = 2;
			} if ( equipo.getBodega().trim().equals("GLOBAL SERVICES") ) {
				idBodega = 3;
			} if ( equipo.getBodega().trim().equals("FABRICA BIMBO MEXICALI") ) {
				idBodega = 4;
			} if ( equipo.getBodega().trim().equals("FABRICA BIMBO CHIHUAHUA") ) {
				idBodega = 5;
			} if ( equipo.getBodega().trim().equals("FABRICA BIMBO GOLFO") ) {
				idBodega = 6;
			} if ( equipo.getBodega().trim().equals("FABRICA BIMBO TOLUCA") ) {
				idBodega = 7;
			} if ( equipo.getBodega().trim().equals("FABRICA BIMBO DEL SURESTE") ) {
				idBodega = 8;
			} if ( equipo.getBodega().trim().equals("FABRICA BIMBO NORTE") ) {
				idBodega = 9;
			} if ( equipo.getBodega().trim().equals("CORPORATIVO BIMBO SANTA FE I") ) {
				idBodega = 10;
			} if ( equipo.getBodega().trim().equals("FABRICA BIMBO PUEBLA") ) {
				idBodega = 11;
			} if ( equipo.getBodega().trim().equals("FABRICA MARINELA OCCIDENTE") ) {
				idBodega = 12;
			} if ( equipo.getBodega().trim().equals("CORPORATIVO BIMBO SANTA FE") ) {
				idBodega = 13;
			} if ( equipo.getBodega().trim().equals("FABRICA BARCEL OCCIDENTE") ) {
				idBodega = 14;
			} if ( equipo.getBodega().trim().equals("FABRICA BARCEL MEXICO TOLUCA") ) {
				idBodega = 15;
			} if ( equipo.getBodega().trim().equals("FABRICA BIMBO SAN LUIS") ) {
				idBodega = 16;
			}
			
			// ********** Empresa o Razon Social **********		
			if ( equipo.getRazon().trim().equals("BIMBO, S.A. DE C.V.") ) {
				idRazonSoc = 1;
			} if ( equipo.getRazon().trim().equals("TRADICIÓN EN PASTELERÍAS S. A. DE C. V.") ) {
				idRazonSoc = 2;
			} if ( equipo.getRazon().trim().equals("PRODUCTOS RICOLINO SAPI DE CV") ) {
				idRazonSoc = 3;
			} if ( equipo.getRazon().trim().equals("BARCEL, S.A. DE C.V.") ) {
				idRazonSoc = 4;
			} if ( equipo.getRazon().trim().equals("CORPORATIVO BIMBO S.A. DE C.V.") ) {
				idRazonSoc = 5;
			} if ( equipo.getRazon().trim().equals("EQUIPO DE USO COMUN") ) {
				idRazonSoc = 6;
			} if ( equipo.getRazon().trim().equals("MOLDES Y EXHIBIDORES, S.A DE C.V.") ) {
				idRazonSoc = 7;
			}
			
			// ********** Accion **********		
			if ( equipo.getAccion().trim().equals("RENOVADO") ) {
				idAccion = 1;
			} if ( equipo.getAccion().trim().equals("PROGRAMADO") ) {
				idAccion = 2;
			} if ( equipo.getAccion().trim().equals("NO CONTESTA") ) {
				idAccion = 3;
			} if ( equipo.getAccion().trim().equals("NO PROCEDE CAMBIO") ) {
				idAccion = 4;
			}
			
			// ********** Estatus de Control **********		
			if ( equipo.getEstatus_control().trim().equals("RENOVADO") ) {
				idEstCtrl = 1;
			} if ( equipo.getEstatus_control().trim().equals("NO PROCEDE") ) {
				idEstCtrl = 2;
			} if ( equipo.getEstatus_control().trim().equals("PENDIENTE DE CONTACTO") ) {
				idEstCtrl = 3;
			} if ( equipo.getEstatus_control().trim().equals("SIN RESPUESTA") ) {
				idEstCtrl = 4;
			} if ( equipo.getEstatus_control().trim().equals("CANCELADO") ) {
				idEstCtrl = 5;
			} if ( equipo.getEstatus_control().trim().equals("PENDIENTE AGENDAR") ) {
				idEstCtrl = 6;
			} if ( equipo.getEstatus_control().trim().equals("INVENTARIO AGOTADO") ) {
				idEstCtrl = 7;
			} if ( equipo.getEstatus_control().trim().equals("PROGRAMADO") ) {
				idEstCtrl = 8;
			} if ( equipo.getEstatus_control().trim().equals("NO SE PRESENTO") ) {
				idEstCtrl = 9;
			}
			
			// Asigna valores para Ids
			equipo.setEst( idEstatus );
			equipo.setReg( idRegion );
			equipo.setBod( idBodega );
			equipo.setRaz( idRazonSoc );
			equipo.setAcc( idAccion );
			equipo.setLoc( idAccion );
			equipo.setCtrl( idEstCtrl );
			
			Date fechaHoy = new Date();
			SimpleDateFormat formatoddmmyy = new SimpleDateFormat("dd-MM-yyyy");
			System.out.println( formatoddmmyy.format(fechaHoy) );
			equipo.setFecha_modifica( formatoddmmyy.format(fechaHoy) );
			
			EquipoEntity equipoentity = equiposRepository.save(equipo); 
			return equipoentity;
		} catch(CustomException e) {
			throw e;
		} catch (RuntimeException re) {
			throw new CustomException(re.getMessage(), HttpStatus.ACCEPTED, "error-insertar");
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
				LocalidadEntity localidadDto = localidadService.consulotaLocalidadPorID( equipo.getLoc() );
				localidad = localidadDto.getLocalidad()!=null ? localidadDto.getLocalidad() : "";
			} catch (Exception e) {
				localidad = "";
			}
			equipo.setLocalidad( localidad );
			
			// Obtengo al Descripcion del Catalogo de Estatus d eControl
			catalogo.setId( equipo.getCtrl() ); 
			switch ( equipo.getCtrl() ) {
				case 1:  catalogo.setValor("RENOVADO"); 				break;
				case 2:  catalogo.setValor("NO PROCEDE");				break;
				case 3:  catalogo.setValor("PENDIENTE DE CONTACTO");	break;
				case 4:  catalogo.setValor("SIN RESPUESTA");	 		break;
				case 5:  catalogo.setValor("CANCELADO");				break;
				case 6:  catalogo.setValor("PENDIENTE AGENDAR"); 		break;
				case 7:  catalogo.setValor("INVENTARIO AGOTADO"); 		break;
				case 8:  catalogo.setValor("PROGRAMADO"); 				break;
				case 9:  catalogo.setValor("NO SE PRESENTO"); 			break;
				default: catalogo.setValor(""); 						break;
			}
			// equipo.setEstatus_control( catalogo.getValor() );
			
			Date fechaHoy = new Date();
			SimpleDateFormat formatoddmmyy = new SimpleDateFormat("dd-MM-yyyy");
			System.out.println( formatoddmmyy.format(fechaHoy) );
			equipo.setFecha_modifica( formatoddmmyy.format(fechaHoy) );
						
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
			// 26Oct2020 - Añado este criterio de busqueda al servicio
			if( equipoBuscar.getSerie_cambio()!=null && !equipoBuscar.getSerie_cambio().contentEquals("") ) {
				searchCriterias.add( criteriaBuilder.equal( root.get("serie_cambio"), (String) equipoBuscar.getSerie_cambio() ) );
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
	public EquipoEntity actualizarEquipoCorto(EquipoEntity equipo) {
		try {
			EquipoEntity equipoBD = this.consultaEquipoPorID( equipo.getId() );
			equipoBD.setEst( equipo.getEst() );
			equipoBD.setEstatus_cambio( equipo.getEstatus_cambio() );
			equipoBD.setComentarios( equipo.getComentarios() );
			equipoBD.setUsuario_modifica( equipo.getUsuario_modifica() );
			
			// Obtengo al Descripcion del Catalogo de Estatus
			CatalogoDTO catalogo = new CatalogoDTO();
			catalogo.setId( equipo.getEst() );		
			switch ( equipo.getEst() ) {
				case 1:  catalogo.setValor("RENOVADO"); 	break;
				case 2:  catalogo.setValor("POR RENOVAR");	break;
				case 3:  catalogo.setValor("PENDIENTES"); 	break;			
				default: catalogo.setValor(""); 			break;
			}		
			equipoBD.setEstatus( catalogo.getValor() );	
			
			Date fechaHoy = new Date();
			SimpleDateFormat formatoddmmyy = new SimpleDateFormat("dd-MM-yyyy");
			System.out.println( formatoddmmyy.format(fechaHoy) );
			equipoBD.setFecha_modifica( formatoddmmyy.format(fechaHoy) );
			
			EquipoEntity equipoUpd = equiposRepository.save(equipoBD); 
			return equipoUpd;
		} catch(CustomException e) {
			throw e;
		} catch (RuntimeException re) {
			throw new CustomException(re.getMessage(), HttpStatus.ACCEPTED, "error-eliminar");
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
			// Informacion del Equipo a reemplazar
			equipo.setNombre_cambio( equipoEntity.getNombre_cambio()!=null ? equipoEntity.getNombre_cambio() : "" );
			equipo.setSerie_cambio( equipoEntity.getSerie_cambio()!=null ? equipoEntity.getSerie_cambio() : "" );			
			equipo.setEstatus_cambio( equipoEntity.getEstatus_cambio()!=null ? equipoEntity.getEstatus_cambio() : "" );
			equipo.setMarca( equipoEntity.getMarca()!=null ? equipoEntity.getMarca() : "" );
			equipo.setModelo( equipoEntity.getModelo()!=null ? equipoEntity.getModelo() : "" );
			// Informacion del Equipo Nuevo	
			equipo.setNombre_nuevo( equipoEntity.getNombre_nuevo()!=null ? equipoEntity.getNombre_nuevo() : "" );
			equipo.setSerie_nuevo( equipoEntity.getSerie_nuevo()!=null ? equipoEntity.getSerie_nuevo() : "" );
			equipo.setAlta( equipoEntity.getAlta()!=null ? equipoEntity.getAlta() : "" );
			equipo.setModelo_nuevo( equipoEntity.getModelo_nuevo()!=null ? equipoEntity.getModelo_nuevo() : "" );
			equipo.setGarantia( equipoEntity.getGarantia()!=null ? equipoEntity.getGarantia() : "" );  
			equipo.setSistema( equipoEntity.getSistema()!=null ? equipoEntity.getSistema() : "" );  
			equipo.setOrden( equipoEntity.getOrden()!=null ? equipoEntity.getOrden() : "" );
			equipo.setComentarios( equipoEntity.getComentarios()!=null ? equipoEntity.getComentarios() : "" ); 
			equipo.setProgramado( equipoEntity.getProgramado()!=null ? equipoEntity.getProgramado() : "" );
			equipo.setProveedor( equipoEntity.getProveedor()!=null ? equipoEntity.getProveedor() : "" );	
			equipo.setEstatus_control( equipoEntity.getEstatus_control()!=null ? equipoEntity.getEstatus_control() : "" );
			equipo.setAccion( equipoEntity.getAccion()!=null ? equipoEntity.getAccion() : "" );
			equipo.setEst( equipoEntity.getEst() );
			equipo.setReg( equipoEntity.getReg() );
			equipo.setLoc( equipoEntity.getLoc() );
			equipo.setBod( equipoEntity.getBod() );
			equipo.setRaz( equipoEntity.getRaz() );
			equipo.setAcc( equipoEntity.getAcc() );		
			equipo.setCtrl( equipoEntity.getCtrl() );
			equipo.setUsuario_modifica( equipoEntity.getUsuario_modifica()!=null ? equipoEntity.getUsuario_modifica() : "" );
			equipo.setFecha_modifica( equipoEntity.getFecha_modifica()!=null ? equipoEntity.getFecha_modifica() : "" );
			// equipo.setArchivo( equipoEntity.getArchivo()!=null ? equipoEntity.getArchivo() : "" );  
			// equipo.setSerie_renombre( equipoEntity.getSerie_renombre()!=null ? equipoEntity.getSerie_renombre() : "" );
			// equipo.setNombre_recambio( equipoEntity.getNombre_recambio()!=null ? equipoEntity.getNombre_recambio() : "" );
			
			// 26Oct2020 - Campos Nuevos
			equipo.setMes_renovacion( equipoEntity.getMes_renovacion()!=null ? equipo.getMes_renovacion() : "" );
			equipo.setTicket( equipoEntity.getTicket()!=null ? equipoEntity.getTicket() : "" );
			equipo.setComm_control( equipoEntity.getComm_control()!=null ? equipoEntity.getComm_control() : "" );
			
			equiposList.add(equipo); 
		}
		
		return equiposList;
	}
	
	/* @Override
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
	} */
}
