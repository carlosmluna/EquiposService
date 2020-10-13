package com.mx.lbg.bimbo.equiposservice.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException  {
	private static final long serialVersionUID = 1L;
	
	// Stributos de la clase
	private String     message;
	private HttpStatus status;
	private String     level;

	// Metosod sconstructores
	public CustomException(String message, HttpStatus status) {
	    this.message = message;
	    this.status  = status;
	    this.level   = "warning";		
    }	
	
	public CustomException(String message, HttpStatus status, String level) {
	    this.message = message;
	    this.status  = status;
	    this.level   = level;		
    }
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	@Override
	public String toString() {
		return "CustomException [message=" + message + ", status=" + status + ", level=" + level + "]";
	}
}
