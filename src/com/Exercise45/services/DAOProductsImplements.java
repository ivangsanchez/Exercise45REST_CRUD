package com.Exercise45.services;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.Exercise45.model.Products;


@Path("/Products")
public class DAOProductsImplements implements DAOProducts {
	Connection conn=null;
	PreparedStatement stmnt=null;
	ResultSet rs=null;
	int rowsAffected=0;
	String sqlSentence="";
	Properties props = new Properties();
	InputStream in = this.getClass().getClassLoader().getResourceAsStream("dao.properties");
	
	public Connection getConnection() {
		Connection conn;
		conn= ConnectionFactory.getInstance().getConnection();
		return conn;
	}
	
	@Override
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void SaveProduct(Products myProduct) {
		conn = getConnection();
		
		try {
			props.load(in);
			if(props!=null) {
				sqlSentence=props.getProperty("SQLSaveProduct");
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		try {
			stmnt= conn.prepareStatement(sqlSentence);
			stmnt.setString(1, myProduct.getNameProduct());
			stmnt.setDouble(2, myProduct.getPriceProduct());
			stmnt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				stmnt.close();
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}

	}

	@Override
	public boolean UpdateProduct(int idProduct) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean DeleteProduct(int idProduct) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Products ReadProduct(int idProduct) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Products> ReadAllProduct() {
		// TODO Auto-generated method stub
		return null;
	}

}
