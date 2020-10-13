package com.mx.lbg.bimbo.equiposservice.service;

import com.mx.lbg.bimbo.equiposservice.model.dto.CatalogoDTO;

public class ServiceUtils {

	public CatalogoDTO consultaAccionDTO ( int idCatalogo) {
		CatalogoDTO catalogo = new CatalogoDTO();
		catalogo.setId(idCatalogo);		
		switch (idCatalogo) {
			case 1:  catalogo.setValor("RENOVADO"); 			break;
			case 2:  catalogo.setValor("PROGRAMADO"); 			break;
			case 3:  catalogo.setValor("NO CONTESTA"); 			break;
			case 4:  catalogo.setValor("NO PROCEDE CAMBIO"); 	break;
			default: catalogo.setValor(""); 					break;
		}		
		return catalogo;
	} 
	
	public CatalogoDTO consultaBodegaDTO ( int idCatalogo) {
		CatalogoDTO catalogo = new CatalogoDTO();
		catalogo.setId(idCatalogo);		
		switch (idCatalogo) {
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
		return catalogo;
	} 
	
	public CatalogoDTO consultaEstatusDTO ( int idCatalogo) {
		CatalogoDTO catalogo = new CatalogoDTO();
		catalogo.setId(idCatalogo);		
		switch (idCatalogo) {
			case 1:  catalogo.setValor("RENOVADO"); 	break;
			case 2:  catalogo.setValor("POR RENOVAR");	break;
			case 3:  catalogo.setValor("PENDIENTES"); 	break;			
			default: catalogo.setValor(""); 			break;
		}		
		return catalogo;
	} 
	
	public CatalogoDTO consultaRazonSocialUsrDTO ( int idCatalogo) {
		CatalogoDTO catalogo = new CatalogoDTO();
		catalogo.setId(idCatalogo);		
		switch (idCatalogo) {
			case 1:  catalogo.setValor("BIMBO, S.A. DE C.V."); 						break;
			case 2:  catalogo.setValor("TRADICIÓN EN PASTELERÍAS S. A. DE C. V.");	break;
			case 3:  catalogo.setValor("PRODUCTOS RICOLINO SAPI DE CV"); 			break;
			case 4:  catalogo.setValor("BARCEL, S.A. DE C.V."); 					break;
			case 5:  catalogo.setValor("CORPORATIVO BIMBO S.A. DE C.V."); 			break;
			case 6:  catalogo.setValor("EQUIPO DE USO COMUN"); 						break;
			case 7:  catalogo.setValor("MOLDES Y EXHIBIDORES, S.A DE C.V."); 		break;
			default: catalogo.setValor(""); 										break;
		}		
		return catalogo;
	} 
	
	public CatalogoDTO consultaRegionesDTO ( int idCatalogo) {
		CatalogoDTO catalogo = new CatalogoDTO();
		catalogo.setId(idCatalogo);		
		switch (idCatalogo) {
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
		return catalogo;
	} 
}
