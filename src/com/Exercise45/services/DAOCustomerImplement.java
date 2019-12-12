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
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.Exercise45.model.Customer;
import com.Exercise45.services.ConnectionFactory;

@Path("/Customer")
public class DAOCustomerImplement implements DAOCustomer {
	
	Connection conn=null;
	PreparedStatement pstmnt=null;
	ResultSet rs=null;
	String sentenciaSQL="";
	int rowsaffected=0;
	
	private Connection getConnection() {
		Connection conn=null;
		conn=ConnectionFactory.getInstance().getConnection();
		return conn;
	}
	
	@Override
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void SaveCustomer(Customer myCustomer) {
		String sentenciaSQL = "INSERT INTO Customer(nameCustomer,addressCustomer,ageCustomer,heightCustomer,weightCustomer,isSingle) VALUES(?,?,?,?,?,?)";
		conn =getConnection();
		
		try {
			pstmnt=conn.prepareStatement(sentenciaSQL);
			pstmnt.setString(1, myCustomer.getNameCustomer());
			pstmnt.setString(2, myCustomer.getAddressCustomer());
			pstmnt.setInt(3, myCustomer.getAgeCustomer());
			pstmnt.setDouble(4,myCustomer.getHeightCustomer());
			pstmnt.setDouble(5, myCustomer.getWeightCustomer());
			pstmnt.setBoolean(6, myCustomer.getIsSingle());
			pstmnt.executeUpdate();
			
			System.out.println("Record added to database");
		} catch (SQLException e) {

			e.printStackTrace();
		}
		finally {
			
			try {
				pstmnt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	@Override
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean UpdateCustomer(Customer myCustomer) {
		// TODO Auto-generated method stub
		conn = getConnection();
		Properties props = new Properties();
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("dao.properties");
		
		try {
			props.load(in);
			if(props!=null) {
				sentenciaSQL=props.getProperty("SQLUpdateCustomer");
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		try {
			pstmnt=conn.prepareStatement(sentenciaSQL);
			pstmnt.setString(1, myCustomer.getNameCustomer());
			pstmnt.setString(2, myCustomer.getAddressCustomer());
			pstmnt.setInt(3, myCustomer.getAgeCustomer());
			pstmnt.setDouble(4, myCustomer.getHeightCustomer());
			pstmnt.setDouble(5, myCustomer.getWeightCustomer());
			pstmnt.setBoolean(6, myCustomer.getIsSingle());
			pstmnt.setInt(7, myCustomer.getIdCustomer());
			rowsaffected = pstmnt.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally {
			try {
				pstmnt.close();
				conn.close();
				
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
		
		return (rowsaffected!=0)?true:false;
	}

	@Override
	public boolean DeleteCustomer(int idCustomer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Customer ReadCustomer(int idCustomer) {

		return null;
	}

	@Override
	@GET
	public ArrayList<Customer> ReadAllCustomer() {
		ArrayList<Customer> myCustomers = new ArrayList<Customer>();
		
		conn = getConnection();
		Properties props = new Properties();
		InputStream in =this.getClass().getClassLoader().getResourceAsStream("dao.properties");
		
		try {
			props.load(in);
			if(props!=null) {
				sentenciaSQL = props.getProperty("SQLReadAllCustomer");
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		try {
			pstmnt = conn.prepareStatement(sentenciaSQL);
			rs =pstmnt.executeQuery();
			while(rs.next()) {
				Customer myCustomer = new Customer();
				myCustomer.setIdCustomer(rs.getInt("idCustomer"));
				myCustomer.setNameCustomer(rs.getString("nameCustomer"));
				myCustomer.setAddressCustomer(rs.getString("addressCustomer"));
				myCustomer.setAgeCustomer(rs.getInt("ageCustomer"));
				myCustomer.setHeightCustomer(rs.getDouble("heightCustomer"));
				myCustomer.setWeightCustomer(rs.getDouble("weightCustomer"));
				myCustomer.setIsSingle(rs.getBoolean("isSingle"));
				myCustomers.add(myCustomer);
				
			}
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		finally {
			
			try {
				rs.close();
				pstmnt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return myCustomers;
	}


}
