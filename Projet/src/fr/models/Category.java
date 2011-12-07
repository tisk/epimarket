package fr.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Category extends ABDDConnection
{

	private String name;
	
	public Category(MyDbUtils test)
	{
		setTab("Category");
		this.setConnection(test);
	}
	
	public String getName()					{return name;}
	
	public void setName(String name)		{this.name = name;}


	@Override
	public String getCreateDbRequest() {
		return ("insert into Category(name) VALUES ('" + name + "');");
	}
	public bool isRegister()
	{
		
		try 
		{
			Statement myState;
			myState = this.getConnection().myConnect.createStatement();
			System.out.print("Select * from " + this.getTab() + ";" + "\n");
			ResultSet myResultSet;
			myResultSet = myState.executeQuery("Select * from " + this.getTab() + ";" + "\n");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bool.FALSE;
	}
}
