package fr.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Product extends ABDDConnection
{

	private String name;
	private int CategoryId;
	private int price;
	private String description;
	private String tab;
	
	public Product(MyDbUtils test) 
	{
		setTab("Product");
		setConnection(test);
		this.setId(-1);
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

	@Override
	public String getCreateDbRequest() 
	{
		return ("insert into Product (name, category, price, description) VALUES ('" + this.name + "', '" + this.CategoryId + "', '" + this.price + "', '" + this.description + "');");
	}	
	
	public bool isRegister()
	{	
		Statement myState;
		try 
		{
			myState = this.getConnection().myConnect.createStatement();
			//System.out.print("Select " + this.getTab() + "Id" +  from " + this.getTab() + ";" + "\n");"
			ResultSet myResultSet;
			myResultSet = myState.executeQuery("Select * from " + this.getTab() + ";" + "\n");
		} 	
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bool.FALSE;
	}
}
