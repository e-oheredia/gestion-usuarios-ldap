package com.gestionusuario.app.configuration;

import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.context.annotation.Configuration;


@Configuration
public class Conectar {

	private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static String server = "jdbc:sqlserver://localhost;databaseName=GestionUsuarios;user=sa;password=123456";
	private static Connection conexion = null;
	
	
	public boolean validarcnx() {
		 boolean rpta=false;
		 
        try {
            Class.forName(driver);
            conexion = DriverManager.getConnection(server);
            rpta=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return rpta;
    }
	
	
}
