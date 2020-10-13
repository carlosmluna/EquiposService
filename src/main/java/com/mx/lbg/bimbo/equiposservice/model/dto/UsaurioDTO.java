package com.mx.lbg.bimbo.equiposservice.model.dto;

public class UsaurioDTO {
	// Declaro atributos de la clase
	private String usario;

	
	// Metodos Constructores
	public UsaurioDTO() { }
	
	public UsaurioDTO(String usario) {
		this.usario = usario;
	}
	
	
	// Metodos Getter / Setter de los atributos
	public String getUsario() {
		return usario;
	}
	public void setUsario(String usario) {
		this.usario = usario;
	}
	
	
	// Implemento metodo ToString()
	@Override
	public String toString() {
		return "ConsultaUsaurioDTO [usario=" + usario + "]";
	}
}
