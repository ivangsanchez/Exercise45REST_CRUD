package com.Exercise45.services;

import java.sql.Connection;
import java.util.ArrayList;
import com.Exercise45.model.Customer;

public interface DAOCustomer {
	
	
	public void SaveCustomer(Customer myCustomer);
	public boolean UpdateCustomer(Customer myCustomer);
	public boolean DeleteCustomer(int idCustomer);
	public Customer ReadCustomer(int idCustomer);
	public ArrayList<Customer> ReadAllCustomer();

}
