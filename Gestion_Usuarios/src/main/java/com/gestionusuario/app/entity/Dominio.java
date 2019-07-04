package com.gestionusuario.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFilter;

@Entity
@Table(name="dominio")
@JsonFilter("dominioFilter")
public class Dominio implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="dominio_id")
	private Long dominioId;	
	private String nombre;	
	private String url;	
	@Column(name="base_dn")
	private String baseDn;	
	@Column(name="manager_username")
	private String managerUsername;
	@Column(name="manager_password") 
	private String managerPassword;
	@Column(name="filter_pattern")
	private String filterPattern;
	private Boolean activo;		
	
	public Long getDominioId() {
		return dominioId;
	}

	public void setDominioId(Long dominioId) {
		this.dominioId = dominioId;
	}

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getBaseDn() {
		return baseDn;
	}

	public void setBaseDn(String baseDn) {
		this.baseDn = baseDn;
	}

	public String getManagerUsername() {
		return managerUsername;
	}

	public void setManagerUsername(String managerUsername) {
		this.managerUsername = managerUsername;
	}
	
	public String getManagerPassword() {
		return managerPassword;
	}

	public void setManagerPassword(String managerPassword) {
		this.managerPassword = managerPassword;
	}

	public String getFilterPattern() {
		return filterPattern;
	}

	public void setFilterPattern(String filterPattern) {
		this.filterPattern = filterPattern;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}
