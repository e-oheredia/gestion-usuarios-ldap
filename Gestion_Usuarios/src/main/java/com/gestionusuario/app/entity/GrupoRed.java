package com.gestionusuario.app.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="grupo_red")
public class GrupoRed implements Serializable {
	
	
	@Id
	@Column(name="grupo_red_id")
	private Long grupoRedId;
	private String nombre;	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="perfil_id")
	private Perfil perfil;
	
	
	
	public Perfil getPerfil() {
		return perfil;
	}
	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	public Long getGrupoRedId() {
		return grupoRedId;
	}
	public void setGrupoRedId(Long grupoRedId) {
		this.grupoRedId = grupoRedId;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	
}
