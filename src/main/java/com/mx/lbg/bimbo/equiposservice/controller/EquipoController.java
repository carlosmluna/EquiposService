package com.mx.lbg.bimbo.equiposservice.controller;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mx.lbg.bimbo.equiposservice.model.LocalidadEntity;
import com.mx.lbg.bimbo.equiposservice.model.EquipoEntity;
import com.mx.lbg.bimbo.equiposservice.model.dto.EquipoBusquedaDTO;
import com.mx.lbg.bimbo.equiposservice.model.dto.MensajeDTO;
import com.mx.lbg.bimbo.equiposservice.service.IEquipoService;
import com.mx.lbg.bimbo.equiposservice.service.ILocalidadService;

@RestController
@RequestMapping("/equipos")
public class EquipoController {

	
	@Autowired
	IEquipoService equipoService;
	@Autowired
	ILocalidadService localidadService;
	
	
	@GetMapping("/equipos-consultar")
    public ResponseEntity<List<EquipoEntity>> consultaEquipos() {
		List<EquipoEntity> equiposEntity = equipoService.consultarEquiposExcel();
		if ( equiposEntity.isEmpty() ) {
			return new ResponseEntity(new MensajeDTO("No existen Registros en la Base de datos"), HttpStatus.OK);
		}        
        return new ResponseEntity(equiposEntity, HttpStatus.OK);
    }
	
	@GetMapping("/equipos-consultarPorId/{idEquipo}")
    public ResponseEntity<EquipoEntity> consultarEquipo( @PathVariable("idEquipo") String idEquipo ) {
		int equipoId = Integer.valueOf(idEquipo);
		
		EquipoEntity usuarioEntity = equipoService.consultaEquipoPorID(equipoId);
        return new ResponseEntity(usuarioEntity, HttpStatus.OK);
    }
	
	@GetMapping("/equipos-buscardto")
	public ResponseEntity<List<EquipoEntity>> consultarEquiposorDTO( 
			@RequestParam (value="estatus") String estatus,   @RequestParam (value="region")   String region, 
			@RequestParam (value="local")   String localidad, @RequestParam (value="bodega")   String bodega, 
			@RequestParam (value="razon")   String razon,	  @RequestParam (value="model2")   String model2,
			@RequestParam (value="accion")  String accion,    @RequestParam (value="fProgram") String fProgram,
			@RequestParam (value="proveed") String proveed,   
			// 26Oct2020 - Campos Nuevos
			@RequestParam (value="serie1")   String serie1,
			@RequestParam (value="id") String id ) {	
		
		EquipoBusquedaDTO equipoBusquedaDTO = new EquipoBusquedaDTO();
		equipoBusquedaDTO.setEstatus(estatus);
		equipoBusquedaDTO.setRegion(region);
		equipoBusquedaDTO.setLocalidad(localidad);
		equipoBusquedaDTO.setBodega(bodega);
		equipoBusquedaDTO.setRazon_social(razon);
		equipoBusquedaDTO.setModelo_uevo(model2);
		equipoBusquedaDTO.setAccion(accion);
		equipoBusquedaDTO.setProgramacion(fProgram);
		equipoBusquedaDTO.setProveedor(proveed);
		equipoBusquedaDTO.setSerie_cambio(serie1);	// 26Oct2020 - Añado este criterio de busqueda 
		equipoBusquedaDTO.setId(id);
		
		List<EquipoEntity> equiposEntity = equipoService.consultarEquiposDTO(equipoBusquedaDTO);
		List<EquipoEntity> equiposList = equipoService.quitarNulosResultados(equiposEntity);
		if ( equiposEntity.isEmpty() ) {
			return new ResponseEntity(new MensajeDTO("No existen Registros en la Base de datos"), HttpStatus.OK);
		}        
		
        return new ResponseEntity(equiposList, HttpStatus.OK);
    } 
	
	@GetMapping("/equipos-actualizarcorto")
	public ResponseEntity<List<EquipoEntity>> actualizaEquipoNoAdminDTO( 
			@RequestParam (value="id")   int    id,    @RequestParam (value="est")    String est, 
			@RequestParam (value="est1") String est1,  @RequestParam (value="com")    String com,
			@RequestParam (value="usr")  String usr ) {
			
		EquipoEntity equipo = new EquipoEntity();
		
		equipo.setId(id);
		equipo.setEst( Integer.valueOf(est) );
		equipo.setEstatus_cambio(est1);
		equipo.setComentarios(com);
		equipo.setUsuario_modifica(usr);
		
		EquipoEntity equipoEntity = equipoService.actualizarEquipoCorto(equipo);
        return new ResponseEntity(equipoEntity, HttpStatus.OK); 
    }
	
	@GetMapping("/equipos-actualizar-admin")
	public ResponseEntity<List<EquipoEntity>> actualizaEquipoAdmin ( 
			@RequestParam (value="id")     int    id,     @RequestParam (value="est")    String est,
			@RequestParam (value="reg")    String reg,    @RequestParam (value="loc")    String loc,
			@RequestParam (value="bodg")   String bodg,   @RequestParam (value="razon")  String razon,
			// Informacion del Equipo a Reemplazar
			@RequestParam (value="empl")   String emp,    @RequestParam (value="nomb1")  String nomb1, 
			@RequestParam (value="serie1") String serie1, @RequestParam (value="est1")   String est1,  
			@RequestParam (value="marca")  String marca,  @RequestParam (value="modelo") String modelo,
			// Informacion del Equipo Nuevo
			@RequestParam (value="nomb2")  String nomb2,  @RequestParam (value="serie2") String serie2,
			@RequestParam (value="alta")   String alta,   @RequestParam (value="model2") String model2,
			@RequestParam (value="garant") String garant, @RequestParam (value="so")     String so,
			@RequestParam (value="orden")  String orden,  @RequestParam (value="comm")   String comm,
			@RequestParam (value="fProg")  String fProg,  @RequestParam (value="prov")   String prov, 
			@RequestParam (value="accn")   String accn,	  @RequestParam (value="estCtl") String estCtl, 
			// 26Oct2020 - Campos Nuevos
			@RequestParam (value="mes")    String mes,	  @RequestParam (value="tkt") String tkt,
			@RequestParam (value="comC")   String comC,
			@RequestParam (value="usr")    String usr ) {
						
		EquipoEntity equipo = new EquipoEntity();
		
		equipo.setId(id);
				
		equipo.setEst( Integer.valueOf( est!=null ? est : "0" ) );
		equipo.setReg( Integer.valueOf( reg!=null ? reg : "0" ) );
		equipo.setLoc( Integer.valueOf( loc!=null ? loc : "0" ) );
		equipo.setBod( Integer.valueOf( bodg!=null ? bodg : "0" ) );
		equipo.setRaz( Integer.valueOf( razon!=null ? razon : "0" ) );
		// Informacion del Equipo a Reemplazar
		equipo.setEmpleado(emp);
		equipo.setNombre_cambio( nomb1!=null ? nomb1 : "" );
		equipo.setSerie_cambio( serie1!=null ? serie1 : "" );
		equipo.setEstatus_cambio( est1!=null ? est1 : "");
		equipo.setMarca( marca!=null ? marca : "" );
		equipo.setModelo( modelo!=null ? modelo : "" );
		// Informacion del Equipo Nuevo
		equipo.setNombre_nuevo( nomb2!=null ? nomb2 : "" );
		equipo.setSerie_nuevo( serie2!=null ? serie2 : "" );
		equipo.setAlta( alta!=null ? alta : "" );		
		equipo.setModelo_nuevo( model2!=null ? model2 : "" );
		equipo.setGarantia( garant!=null ? garant : "" );
		equipo.setSistema( so!=null ? so : "" );		
		equipo.setOrden( orden!=null ? orden : "" );
		equipo.setComentarios( comm!=null ? comm : "" );
		equipo.setProgramado( fProg!=null ? fProg : "" );
		equipo.setProveedor( prov!=null ? prov : "" );		
		equipo.setAcc( Integer.valueOf( accn!=null ? accn : "0" ) );
		equipo.setCtrl( 0 );	// Integer.valueOf( estCtl!=null ? estCtl : "0" ) );
		equipo.setUsuario_modifica(  usr!=null ? usr : "No Registrado" );			
		equipo.setEstatus_control( estCtl!=null ? estCtl : "" );
		equipo.setUsuario_modifica( usr!=null ? usr : "sysadmin" );
		// equipo.setNombre_recambio( nomb3!=null ? nomb3 : "" );	
		// equipo.setSerie_renombre( serie3!=null ? serie3 : "" );
		// 26Oct2020 - Campos Nuevos
		equipo.setMes_renovacion( mes!=null ? mes : "" );
		equipo.setTicket( tkt!=null ? tkt : "" );
		equipo.setComm_control( comC!=null ? comC : "" );
		
		EquipoEntity equipoEntity = equipoService.actualizarEquipo(equipo);
        return new ResponseEntity(equipoEntity, HttpStatus.OK); 
    } 
	
	@GetMapping("/equipos-insertar")
	public ResponseEntity<EquipoEntity> insertarEquipo( 
			@RequestParam (value="est")    String est,	  @RequestParam (value="reg")    String reg,    
			@RequestParam (value="loc")    String loc,    @RequestParam (value="bodg")   String bodg,   
			@RequestParam (value="raz")    String raz,	  @RequestParam (value="empl")   String empl,
			// Informacion del Equipo a Reemplazar
			@RequestParam (value="nomb1")  String nomb1,  @RequestParam (value="serie1") String serie1, 
			@RequestParam (value="est1")   String est1,   @RequestParam (value="marca")  String marca,  
			@RequestParam (value="model")  String model,
			// Informacion del Equipo Nuevo
			@RequestParam (value="nomb2")  String nomb2,  @RequestParam (value="serie2") String serie2,
			@RequestParam (value="alta")   String alta,   @RequestParam (value="model2") String model2,			
			@RequestParam (value="grnt")   String grnt,   @RequestParam (value="so")     String so,
			@RequestParam (value="orden")  String orden,  @RequestParam (value="comm")   String comm,
			@RequestParam (value="fProg")  String fProg,  @RequestParam (value="prov")   String prov,
			// 26Oct2020 - Campos Nuevos
			@RequestParam (value="mes")    String mes,	  @RequestParam (value="tkt") String tkt,
			@RequestParam (value="comC")   String comC,
			// Informacion Adicional
			@RequestParam (value="estCtl") String estCtl ) {
						
		EquipoEntity equipo = new EquipoEntity();
		
		// Informacion de Ids del Equipo
		equipo.setEst( 0 );
		equipo.setReg( 0 );
		equipo.setLoc( 0 );		
		equipo.setBod( 0 );
		equipo.setRaz( 0 );
		equipo.setCtrl( 0 );
		equipo.setAcc( 0 );
		// Informacion Principal del Equipo
		equipo.setEstatus( est!=null ? est : "" );
		equipo.setRegion( reg!=null ? reg : "" );
		equipo.setLocalidad( loc!=null ? loc : "" );
		equipo.setBodega( bodg!=null ? bodg : "" );
		equipo.setRazon( raz!=null ? raz : "" );
		equipo.setEmpleado( empl!=null ? empl : "" );
		// Informacion del Equipo a Reemplazar
		equipo.setNombre_cambio( nomb1!=null ? nomb1 : "" );
		equipo.setSerie_cambio( serie1!=null ? serie1 : "" );
		equipo.setEstatus_cambio( est1!=null ? est1 : "");
		equipo.setMarca( marca!=null ? marca : "" );
		equipo.setModelo( model!=null ? model : "" );
		// Informacion del Equipo Nuevo
		equipo.setNombre_nuevo( nomb2!=null ? nomb2 : "" );
		equipo.setSerie_nuevo( serie2!=null ? serie2 : "" );
		equipo.setAlta( alta!=null ? alta : "" );
		equipo.setModelo_nuevo( model2!=null ? model2 : "" );
		equipo.setGarantia( grnt!=null ? grnt : "" );
		equipo.setSistema( so!=null ? so : "" );
		equipo.setOrden( orden!=null ? orden : "" );
		equipo.setComentarios( comm!=null ? comm : "" );
		equipo.setProgramado( fProg!=null ? fProg : "" );
		equipo.setProveedor( prov!=null ? prov : "" );
		equipo.setUsuario_modifica( "FileUpload" );	
		equipo.setEstatus_control( estCtl!=null ? estCtl : "" );
		equipo.setAccion( "" );
		// equipo.setNombre_recambio( nomb3!=null ? nomb3 : "" );	
		// equipo.setSerie_renombre( serie3!=null ? serie3 : "" );
		// 26Oct2020 - Campos Nuevos
		equipo.setMes_renovacion( mes!=null ? mes : "" );
		equipo.setTicket( tkt!=null ? tkt : "" );
		equipo.setComm_control( comC!=null ? comC : "" );
		
		EquipoEntity equipoEntity = equipoService.insertarEquipo(equipo);
        return new ResponseEntity(equipoEntity, HttpStatus.OK); 
    } 
	
	/* @PutMapping("/equipos-insertar")
	public ResponseEntity<List<EquipoEntity>> insertarEquipo( @RequestBody EquipoEntity equipo ) {
		EquipoEntity equipoEntity = equipoService.insertarEquipo(equipo);
        return new ResponseEntity(equipoEntity, HttpStatus.OK);
    } */
	
	/* @PostMapping("/equipos-actualizar")
	public ResponseEntity<List<EquipoEntity>> actualizarEquipo( @RequestBody EquipoEntity equipo ) {		
		System.out.println("Entrando a actualizarEquipo() ");
		EquipoEntity equipoEntity = equipoService.actualizarEquipo(equipo);
        return new ResponseEntity(equipoEntity, HttpStatus.OK);
    } */
	
	/* @PostMapping("/equipos-eliminar")
	public ResponseEntity<List<EquipoEntity>> actualizarEquipo( @RequestBody EquipoEntity equipo ) {		
		System.out.println("Entrando a actualizarEquipo() ");
		EquipoEntity equipoEntity = equipoService.actualizarEquipo(equipo);
        return new ResponseEntity(equipoEntity, HttpStatus.OK);
    } */
	
	@GetMapping("/equipos-consultar-localidad/{idLoclidad}")
    public ResponseEntity<List<LocalidadEntity>> consultarRegistroLocalidades( @PathVariable("idLoclidad") String idLoclidad ) {		
		LocalidadEntity localidadesEntity = localidadService.consulotaLocalidadPorID( Integer.valueOf( idLoclidad ) );
        return new ResponseEntity(localidadesEntity, HttpStatus.OK);
    }
}
