package com.Exercise45.services;

import java.util.ArrayList;

import com.Exercise45.model.Products;

public interface DAOProducts {
	public void SaveProduct(Products myProduct);
	public boolean UpdateProduct(int idProduct);
	public boolean DeleteProduct(int idProduct);
	public Products ReadProduct(int idProduct);
	public ArrayList<Products> ReadAllProduct();
	
}
