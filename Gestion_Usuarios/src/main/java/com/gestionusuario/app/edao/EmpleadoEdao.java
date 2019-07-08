package com.gestionusuario.app.edao;

import java.io.IOException;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.gestionusuario.app.utils.CommonUtils;


import com.gestionusuario.app.repository.IEmpleadoEdao;
import com.gestionusuario.app.request.Requester;

@Repository
public class EmpleadoEdao implements IEmpleadoEdao{

	@Value("${service.empleados}")
	private String empleadosPath;
	
	private final String path = "/empleados";
	@Autowired
	private Requester requester;
	
	@Override
	public Map<String, Object> findEmpleadoByMatricula(String matricula) throws ClientProtocolException, IOException, JSONException {
		HttpGet httpGet = new HttpGet(empleadosPath + path + "?matricula=" + matricula);
		CloseableHttpResponse httpResponse = requester.request(httpGet);
		String response = EntityUtils.toString(httpResponse.getEntity());
		JSONObject responseJson = new JSONObject(response);		
		return CommonUtils.jsonToMap(responseJson);
	}

}
