package com.gestionusuario.app.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;



@NamedStoredProcedureQueries(
		{
			
			@NamedStoredProcedureQuery(
					name="sesion.CrearSesion", 
					procedureName="CrearSesion",
					resultClasses= Sesion.class,
					parameters={
							@StoredProcedureParameter(mode=ParameterMode.IN,name="idUsuario",type=Long.class),
							@StoredProcedureParameter(mode=ParameterMode.OUT,name="usuarioActual",type=Long.class)
						}					
			),
			@NamedStoredProcedureQuery(
					name="sesion.CerrarSesion", 
					procedureName="CerrarSesion",
					resultClasses= Sesion.class,
					parameters={
							@StoredProcedureParameter(mode=ParameterMode.IN,name="idSesion",type=Integer.class)
						}					
			)
		}
	)


@Entity
@Table
public class Sesion implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idSesion;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat
	(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone="America/Lima")
	private Date inisesion;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat
	(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone="America/Lima")
	private Date finsesion;
	
	@Column(name="matricula_usuario")
	private String matriculaUsuario;
	
//	@ManyToOne
//	private Usuario usesion;	
	
	@ManyToOne
	private Tipo_Autenticacion autsesion;

	public Long getIdSesion() {
		return idSesion;
	}

	public void setIdSesion(Long idSesion) {
		this.idSesion = idSesion;
	}

	
	public Date getInisesion() {
		return inisesion;
	}

	public void setInisesion(Date inisesion) {
		this.inisesion = inisesion;
	}

	public Date getFinsesion() {
		return finsesion;
	}

	public void setFinsesion(Date finsesion) {
		this.finsesion = finsesion;
	}

//	public Usuario getUsesion() {
//		return usesion;
//	}
//
//	public void setUsesion(Usuario usesion) {
//		this.usesion = usesion;
//	}

	public Tipo_Autenticacion getAutsesion() {
		return autsesion;
	}

	public void setAutsesion(Tipo_Autenticacion autsesion) {
		this.autsesion = autsesion;
	}

	public String getMatriculaUsuario() {
		return matriculaUsuario;
	}

	public void setMatriculaUsuario(String matriculaUsuario) {
		this.matriculaUsuario = matriculaUsuario;
	}

	
	
	
	
	
}
