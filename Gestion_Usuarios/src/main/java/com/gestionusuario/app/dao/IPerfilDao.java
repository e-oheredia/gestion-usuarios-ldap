package com.gestionusuario.app.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gestionusuario.app.entity.Perfil;


@Repository
public interface IPerfilDao extends CrudRepository<Perfil, Long> {
	
	@Query("from Perfil P where P.idPerfil = (Select GR.perfil.idPerfil FROM GrupoRed GR WHERE GR.nombre = ?1)")
	public Perfil findPerfilByNombreGrupoRed(String nombre);
	
	@Query(value="select p.permisos_id_permiso from perfiles_permisos p where p.perfil_id_perfil=?1",nativeQuery=true)
	public String findPermisosByPerfilId(Long perfilId);
	
		
}
