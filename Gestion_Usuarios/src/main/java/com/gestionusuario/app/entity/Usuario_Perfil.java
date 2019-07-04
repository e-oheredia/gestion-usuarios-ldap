package com.gestionusuario.app.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="UsuarioPerfil")
public class Usuario_Perfil extends GenericEntity{

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idUsuario_Perfil;
	
	@Temporal(TemporalType.DATE)
	private Date fAsignada;
	
	@Temporal(TemporalType.DATE)
	private Date fBaja;
	
	protected String activo;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Perfil perfil;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Usuario usuario;
	
	

	public Usuario_Perfil() {
		
	}

	public String getActivo() {
		return activo;
	}
	public void setActivo(String activo) {
		this.activo = activo;
	}
	public Long getIdUsuario_Perfil() {
		return idUsuario_Perfil;
	}
	public void setIdUsuario_Perfil(Long idUsuario_Perfil) {
		this.idUsuario_Perfil = idUsuario_Perfil;
	}

	public Date getfAsignada() {
		return fAsignada;
	}

	public void setfAsignada(Date fAsignada) {
		this.fAsignada = fAsignada;
	}

	public Date getfBaja() {
		return fBaja;
	}

	public void setfBaja(Date fBaja) {
		this.fBaja = fBaja;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
	
	
}
