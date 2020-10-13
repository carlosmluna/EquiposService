package com.mx.lbg.bimbo.equiposservice.model.dto;

public class MensajeDTO {
	private String mensaje;

	
	public MensajeDTO() {}
	
    public MensajeDTO(String mensaje) {
        this.mensaje = mensaje;
    }
    
    
    public String getMensaje() {
        return mensaje;
    }
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    

	@Override
	public String toString() {
		return "MensajeDTO [mensaje=" + mensaje + "]";
	}
}
