package com.mx.lbg.bimbo.equiposservice.model.dto;

public class EquipoBusquedaDTO {
	// Declaro atributos de la clase
	private String id;
	private String estatus;
	private String region;
	private String localidad;
	private String bodega;
	private String razon_social;
	private String modelo_uevo;
	private String accion;
	private String programacion;
	private String proveedor;
	private String serie_cambio;
	
	
	public EquipoBusquedaDTO() { }
	
	public EquipoBusquedaDTO(String id, String estatus, String region, String localidad, String bodega, String razon_social,
			String modelo_uevo, String accion, String programacion, String proveedor, String serie_cambio) {
		this.id 			= id;
		this.estatus 		= estatus;
		this.region 		= region;
		this.localidad 		= localidad;
		this.bodega 		= bodega;
		this.razon_social 	= razon_social;
		this.modelo_uevo 	= modelo_uevo;
		this.accion 		= accion;
		this.programacion 	= programacion;
		this.proveedor 		= proveedor;
		this.serie_cambio   = serie_cambio;
	}

	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public String getBodega() {
		return bodega;
	}
	public void setBodega(String bodega) {
		this.bodega = bodega;
	}
	public String getRazon_social() {
		return razon_social;
	}
	public void setRazon_social(String razon_social) {
		this.razon_social = razon_social;
	}
	public String getModelo_uevo() {
		return modelo_uevo;
	}
	public void setModelo_uevo(String modelo_uevo) {
		this.modelo_uevo = modelo_uevo;
	}
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}
	public String getProgramacion() {
		return programacion;
	}
	public void setProgramacion(String programacion) {
		this.programacion = programacion;
	}
	public String getProveedor() {
		return proveedor;
	}
	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}
	public String getSerie_cambio() {
		return serie_cambio;
	}
	public void setSerie_cambio(String serie_cambio) {
		this.serie_cambio = serie_cambio;
	}
	

	@Override
	public String toString() {
		return "EquipoBusquedaDTO [id=" + id + ", estatus=" + estatus + ", region=" + region + ", localidad="
				+ localidad + ", bodega=" + bodega + ", razon_social=" + razon_social + ", modelo_uevo=" + modelo_uevo
				+ ", accion=" + accion + ", programacion=" + programacion + ", proveedor=" + proveedor
				+ ", serie_cambio=" + serie_cambio + "]";
	}
}
