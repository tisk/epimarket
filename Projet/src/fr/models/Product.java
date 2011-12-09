package fr.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Product
{

	private String name;
	private int CategoryId;
	private int price;
	private String description;
	private String tab;
	
	public Product(MyDbUtils test) 
	{
		
	}

	public void setName(String name) {
		// TODO Auto-generated method stub
	this.name = name;	
	}

	public void setCategory(int i) {
		// TODO Auto-generated method stub
		this.CategoryId = i;
	}

	public void setPrice(int i) {
		// TODO Auto-generated method stub
		this.price = i;
	}

	public void setDescription(String string) {
		// TODO Auto-generated method stub
		this.description = string;
	}

	
	
	
}
