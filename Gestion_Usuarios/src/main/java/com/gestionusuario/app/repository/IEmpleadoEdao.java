package com.gestionusuario.app.repository;

import java.io.IOException;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

public interface IEmpleadoEdao {
	
	public Map<String,Object> findEmpleadoByMatricula(String matricula)throws ClientProtocolException, IOException, JSONException;
}
