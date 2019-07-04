package com.gestionusuario.app.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

@NamedStoredProcedureQueries(
		{
			@NamedStoredProcedureQuery(
					name="permisos.ObtenerPermisosID", 
					procedureName="ObtenerPermisosID",
					resultClasses= Permiso.class,
					parameters={
							@StoredProcedureParameter(mode=ParameterMode.IN,name="idUsuario", type=Long.class),
							@StoredProcedureParameter(mode=ParameterMode.OUT,name="Permisos", type=String.class)
						}					
				),
			@NamedStoredProcedureQuery(
					name="permisos.ObtenerPermisosNombre", 
					procedureName="ObtenerPermisosNombre",
					resultClasses= Permiso.class,
					parameters={
							@StoredProcedureParameter(mode=ParameterMode.IN,name="idPermiso", type=Integer.class),
							@StoredProcedureParameter(mode=ParameterMode.OUT,name="nombre", type=String.class)
						}					
				)
		}
	)



@Entity
@Table(name="Permisos")
public class Permiso implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idPermiso;
	
	private String nombre;
	private String descripcion;
	
	public Long getIdPermiso() {
		return idPermiso;
	}
	public void setIdPermiso(Long idPermiso) {
		this.idPermiso = idPermiso;
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
