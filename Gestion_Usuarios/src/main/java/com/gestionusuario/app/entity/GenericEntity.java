package com.gestionusuario.app.entity;

import java.io.Serializable;

public class GenericEntity implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	protected String estado;
	
	public GenericEntity() {
		
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	

}
