package com.gestionusuario.app.security;

import static com.gestionusuario.app.enumerator.Identificadores.HEADER_STRING;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.codec.binary.Base64;

public class Decoder {
	
	public String[] decode(HttpServletRequest header, String prefix) {
		
		String decoderString = null;
        String [] part ;
        Base64 decoder = new Base64();
        byte[] decodedBytes;
        String Basictoken64= null;
        
		String auth = header.getHeader(HEADER_STRING);
		
		Basictoken64 = auth.replace(prefix,"");
    	decodedBytes = decoder.decode(Basictoken64);
    	decoderString = new String (decodedBytes);
    	part=decoderString.split(":");
    	
		
		return part;
		
	}

}
