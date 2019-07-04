package com.gestionusuario.app.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@NamedStoredProcedureQueries(
		{
			
			@NamedStoredProcedureQuery(
					name="usuario.InsertarUsuario", 
					procedureName="InsertarUsuario",
					resultClasses= Usuario.class,
					parameters={
							@StoredProcedureParameter(mode=ParameterMode.IN,name="nombre",type=String.class),
							@StoredProcedureParameter(mode=ParameterMode.IN,name="apellido",type=String.class),
							@StoredProcedureParameter(mode=ParameterMode.IN,name="correo",type=String.class),
							@StoredProcedureParameter(mode=ParameterMode.IN,name="dni",type=String.class),
							@StoredProcedureParameter(mode=ParameterMode.IN,name="matricula",type=String.class),
							@StoredProcedureParameter(mode=ParameterMode.OUT,name="idUsuario",type=Long.class)
						}					
			),
			@NamedStoredProcedureQuery(
					name="usuario.ModificarUsuario", 
					procedureName="ModificarUsuario",
					resultClasses= Usuario.class,
					parameters={
							@StoredProcedureParameter(mode=ParameterMode.IN,name="idUsuario",type=Long.class),
							@StoredProcedureParameter(mode=ParameterMode.IN,name="nombre",type=String.class),
							@StoredProcedureParameter(mode=ParameterMode.IN,name="apellido",type=String.class),
							@StoredProcedureParameter(mode=ParameterMode.IN,name="dni",type=String.class),
							@StoredProcedureParameter(mode=ParameterMode.IN,name="matricula",type=String.class),
							@StoredProcedureParameter(mode=ParameterMode.IN,name="correo",type=String.class)
							
						}
				),
			
			@NamedStoredProcedureQuery(
					name="usuario.ValidarUsuarioExistente", 
					procedureName="ValidarUsuarioExistente",
					resultClasses= Usuario.class,
					parameters={
							@StoredProcedureParameter(mode=ParameterMode.IN,name="idUsuario",type=Long.class),
							@StoredProcedureParameter(mode=ParameterMode.OUT,name="rpta",type=Long.class)
						}					
			),
			@NamedStoredProcedureQuery(
					name="usuario.ModificarPerfilUsuario", 
					procedureName="ModificarPerfilUsuario",
					resultClasses= Usuario.class,
					parameters={
							@StoredProcedureParameter(mode=ParameterMode.IN,name="idPerfil",type=Long.class),
							@StoredProcedureParameter(mode=ParameterMode.IN,name="idUsuario",type=Long.class)
						}					
			),
			@NamedStoredProcedureQuery(
					name="usuario.ValidarUsuarioActivo", 
					procedureName="ValidarUsuarioActivo",
					resultClasses= Usuario.class,
					parameters={
							@StoredProcedureParameter(mode=ParameterMode.IN,name="idUsuario",type=Long.class),
							@StoredProcedureParameter(mode=ParameterMode.OUT,name="rpta",type=Long.class)
						}					
			),
			@NamedStoredProcedureQuery(
					name="usuario.ValidarDatosExistentes", 
					procedureName="ValidarDatosExistentes",
					resultClasses= Usuario.class,
					parameters={
							@StoredProcedureParameter(mode=ParameterMode.IN,name="dni",type=String.class),
							@StoredProcedureParameter(mode=ParameterMode.IN,name="correo",type=String.class),
							@StoredProcedureParameter(mode=ParameterMode.IN,name="matricula",type=String.class),
							@StoredProcedureParameter(mode=ParameterMode.OUT,name="rpta",type=Long.class)
						}					
			),
			@NamedStoredProcedureQuery(
					name="usuario.ValidarDatosExistentesAModificar", 
					procedureName="ValidarDatosExistentesAModificar",
					resultClasses= Usuario.class,
					parameters={
							@StoredProcedureParameter(mode=ParameterMode.IN,name="idUsuario",type=Long.class),
							@StoredProcedureParameter(mode=ParameterMode.IN,name="dni",type=String.class),
							@StoredProcedureParameter(mode=ParameterMode.IN,name="correo",type=String.class),
							@StoredProcedureParameter(mode=ParameterMode.IN,name="matricula",type=String.class),
							@StoredProcedureParameter(mode=ParameterMode.OUT,name="rpta",type=Long.class)
						}					
			),
			@NamedStoredProcedureQuery(

					name="usuario.ValidarSiActivaDesactiva", 
					procedureName="ValidarSiActivaDesactiva",
					resultClasses= Usuario.class,
					parameters={
							@StoredProcedureParameter(mode=ParameterMode.IN,name="idUsuarioDest",type=Long.class),
							@StoredProcedureParameter(mode=ParameterMode.IN,name="activo",type=Integer.class),
							@StoredProcedureParameter(mode=ParameterMode.OUT,name="rpta",type=Long.class)
						}					
			),
			@NamedStoredProcedureQuery(
					name="usuario.AsignarPerfilAUsuario", 
					procedureName="AsignarPerfilAUsuario",
					resultClasses= Usuario.class,
					parameters={
							@StoredProcedureParameter(mode=ParameterMode.IN,name="idUsuario",type=Long.class),
							@StoredProcedureParameter(mode=ParameterMode.IN,name="idPerfil",type=Long.class)
							}					

			),
			@NamedStoredProcedureQuery(
					name="usuario.LoguearUsuario", 
					procedureName="LoguearUsuario",
					resultClasses= Usuario.class,
					parameters={
							@StoredProcedureParameter(mode=ParameterMode.IN,name="username",type=String.class),
							@StoredProcedureParameter(mode=ParameterMode.IN,name="password",type=String.class),
							@StoredProcedureParameter(mode=ParameterMode.OUT,name="rpta",type=Long.class)
							}					

			)
			
				
		}
	)

@Entity
@Table(name="Usuarios")
public class Usuario implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idUsuario;
	
	private String nombre;
	private String apellido;
	protected int activo;
	private String dni;
	private String matricula;
	private String correo;
	
	private String username;
	private String password;
	
	
	public Usuario() {
		this.activo=1;
		
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getActivo() {
		return activo;
	}
	public void setActivo(int activo) {
		this.activo = activo;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	public Usuario(Long idUsuario, String nombre, String apellido, int activo, String dni, String matricula,
			String correo) {
		super();
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.apellido = apellido;
		this.activo = activo;
		this.dni = dni;
		this.matricula = matricula;
		this.correo = correo;
	}
	

	
	
}
