package com.gestionusuario.app.edao;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.gestionusuario.app.request.Requester;
import com.gestionusuario.app.utils.CommonUtils;

@Repository
public class GrupoRedEdao {

	@Value("${service.ldap}")
	private String ldapPath;
	
	@Autowired
	private Requester requester;
	
	public Iterable<Map<String, Object>> listarGruposAD(String url, String baseDn, String managerUsername, String managerPassword, String filter, String password) throws ClientProtocolException, IOException, JSONException{
		HttpPost post = new HttpPost(ldapPath);
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();		
		params.add(new BasicNameValuePair("url", url));
		params.add(new BasicNameValuePair("baseDn", baseDn));
		params.add(new BasicNameValuePair("managerUsername", managerUsername));
		params.add(new BasicNameValuePair("managerPassword", managerPassword));
		params.add(new BasicNameValuePair("filter", filter));
		params.add(new BasicNameValuePair("password", password));
		post.setEntity(new UrlEncodedFormEntity(params));
		CloseableHttpResponse httpResponse = requester.request(post);
		if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			String response = EntityUtils.toString(httpResponse.getEntity());
			JSONArray responseJson = new JSONArray(response);		
			return CommonUtils.jsonArrayToMap(responseJson);
		}else {
			return null;
		}
		
	}
}
