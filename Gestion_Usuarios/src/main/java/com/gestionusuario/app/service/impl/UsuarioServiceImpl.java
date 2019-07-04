package com.gestionusuario.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionusuario.app.dao.IUsuarioDao;
import com.gestionusuario.app.entity.Usuario;
import com.gestionusuario.app.repository.UsuarioDAO;
import com.gestionusuario.app.service.UsuarioService;

@Service("usuarioservice")
public class UsuarioServiceImpl implements UsuarioService {

	@SuppressWarnings("uncheked")
	@Autowired
	private UsuarioDAO usuariodao;
	
	@Autowired
	private IUsuarioDao iusuarioDao;

	public UsuarioDAO getUsuariodao() {
		return usuariodao;
	}

	public void setUsuariodao(UsuarioDAO usuariodao) {
		this.usuariodao = usuariodao;
	}

	@Override
	public int insertar(Usuario usuario) throws Exception {
		try {
			return this.getUsuariodao().insertar(usuario);
		} catch (Exception e) {
			throw new Exception();
		}
	}

	@Override
	public int modificar(Usuario usuario) throws Exception {
		try {

			return this.getUsuariodao().modificar(usuario);
		} catch (Exception e) {
			throw new Exception();
		}
	}

	@Override
	public boolean eliminar(Usuario objeto) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Usuario> ListarUsuarioPorNombre(String nombre) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean validarUsuario(Usuario usuario) throws Exception {
		try {
			return this.getUsuariodao().validarUsuario(usuario);
		} catch (Exception e) {
			throw new Exception();
		}
	}

	@Override
	public int ValidarUsuarioExistente(Long idUsuario) throws Exception {
		try {
			return this.getUsuariodao().ValidarUsuarioExistente(idUsuario);
		} catch (Exception e) {
			throw new Exception();
		}
	}

	@Override
	public int ValidarUsuarioActivo(Long idUsuario) throws Exception {
		try {
			return this.getUsuariodao().ValidarUsuarioActivo(idUsuario);
		} catch (Exception e) {
			throw new Exception();
		}
	}

	@Override
	public int ValidarDatosExistentes( String dni, String correo, String matricula) throws Exception {
		try {
			return this.getUsuariodao().ValidarDatosExistentes(dni, correo, matricula);
		} catch (Exception e) {
			throw new Exception();
		}
	}

	@Override
	public int ValidarSiActivaDesactiva(Long idUsuarioDest, int activo) throws Exception {
		try {
			return this.getUsuariodao().ValidarSiActivaDesactiva(idUsuarioDest, activo);
		} catch (Exception e) {
			throw new Exception();
		}
	}

	@Override
	public boolean AsignarPerfilAUsuario(Long idUsuario, Long idPerfil) throws Exception {
		try {
			return this.getUsuariodao().AsignarPerfilAUsuario(idUsuario, idPerfil);
		} catch (Exception e) {
			throw new Exception();
		}
	}

	@Override
	public boolean ModificarPerfilUsuario(Long idUsuario, Long idPerfil) throws Exception {
		try {
			return this.getUsuariodao().ModificarPerfilUsuario(idUsuario, idPerfil);
		} catch (Exception e) {
			throw new Exception();
		}
	}

	@Override
	public int LoguearUsuario(String username, String password) throws Exception {
		try {
			return this.getUsuariodao().LoguearUsuario(username, password);
		} catch (Exception e) {
			throw new Exception();
		}
	}

	@Override
	public int ValidarDatosExistentesAModificar(Long idUsuario, String dni, String correo, String matricula) throws Exception {
	try {
		return this.getUsuariodao().ValidarDatosExistentesAModificar(idUsuario, dni, correo, matricula);
	} catch (Exception e) {
		throw new Exception();
	}
	
	}

	@Override
	public String findNombreUsuario(Long usuarioId) throws Exception {
		return iusuarioDao.findNombreUsuario(usuarioId);
	}
	
	
}
