package com.Exercise45.services;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
	
	//se crea el objeto properties para poder acceder a la base de datos del archivo dao.properties
	static Properties props=new Properties();
	static InputStream in = null;

	//Declaracion de variables de credencial
	static String driver="";
	static String urlServer="";
	static String username="";
	static String password="";
	
	//CREACIÓN DEL SINGLETON//
	
	//Paso 2-se crea una instancia de la clase debe de ser privada y estatica
	private static ConnectionFactory connection=null;
	private static Connection conn =null;
	
	//metodo para crear la conexión con la base de datos
	public static Connection getConnection() {
		in = connection.getClass().getClassLoader().getResourceAsStream("dao.properties");
		
		
		try {
			props.load(in);
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		
		
		if (props!=null) {
			driver = props.getProperty("driver");
			urlServer = props.getProperty("urlServer");
			username=props.getProperty("username");
			password=props.getProperty("password");
		}
		
		
		try {
			Class.forName(driver);
			conn =DriverManager.getConnection(urlServer,username,password);
			
		}
		catch(SQLException | ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		return conn;

	}
	
	//Paso 1-Se crea un constructor privado
	private void ConnectionFactory() {
		
	}
	
	//Paso 3-Create getInstance method
	public static ConnectionFactory getInstance()
	{
		if (connection==null) {
			connection=new ConnectionFactory();
		}
		return connection;
	}

}
