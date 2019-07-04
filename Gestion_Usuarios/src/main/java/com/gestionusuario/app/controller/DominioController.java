package com.gestionusuario.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.gestionusuario.app.entity.Dominio;
import com.gestionusuario.app.service.DominioService;

@RestController
@RequestMapping("/dominios")
public class DominioController {
	
	@Autowired
	DominioService dominioService;
	
	@GetMapping("/activos")
	public ResponseEntity<String> listarActivos() throws JsonProcessingException {
        
		String[] fields = {
				"url","baseDn","managerUsername","managerPassword","filterPattern"
		};
        
        ObjectMapper mapper = new ObjectMapper();	
        
        mapper.setFilterProvider(new SimpleFilterProvider()
                                   .addFilter("dominioFilter",
                                              SimpleBeanPropertyFilter.serializeAllExcept(fields)));
                
		return new ResponseEntity<String>(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(dominioService.listarActivos()), HttpStatus.OK);	
	}
}
