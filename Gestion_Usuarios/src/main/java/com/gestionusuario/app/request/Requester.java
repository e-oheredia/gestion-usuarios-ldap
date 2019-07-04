package com.gestionusuario.app.request;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Component;

@Component
public class Requester {
	private CloseableHttpClient httpClient;
	private CloseableHttpResponse response;
	
	public Requester() {
		httpClient = HttpClients.createDefault();
	}

	public CloseableHttpResponse request(HttpUriRequest httpGet) throws ClientProtocolException, IOException {
		response = httpClient.execute(httpGet);
		return response;
	}
}
