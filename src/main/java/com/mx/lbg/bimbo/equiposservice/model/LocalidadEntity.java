package com.mx.lbg.bimbo.equiposservice.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cat_localidades")
public class LocalidadEntity {
	// Declaro los atributos de la clase
	@Id
	private Integer id;
	private String  localidad;	
	
	
	public LocalidadEntity() { }
	
	public LocalidadEntity(Integer id, String localidad) {
		this.id = id;
		this.localidad = localidad;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
}
