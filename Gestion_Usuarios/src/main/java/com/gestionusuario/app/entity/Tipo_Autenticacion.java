package com.gestionusuario.app.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TipoAutenticacion")
public class Tipo_Autenticacion implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idTipo_Autenticacion;
	
	private String nombre;
	private String descripcion;
	
	public Long getIdTipo_Autenticacion() {
		return idTipo_Autenticacion;
	}
	public void setIdTipo_Autenticacion(Long idTipo_Autenticacion) {
		this.idTipo_Autenticacion = idTipo_Autenticacion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	

}
