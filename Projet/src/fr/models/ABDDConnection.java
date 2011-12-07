package fr.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

abstract class ABDDConnection 
{
	private int id;
	private MyDbUtils connection;
	private String tab;
	
	public abstract String getCreateDbRequest();
	
	public ABDDConnection() {
		// TODO Auto-generated constructor stub
	}

	public abstract bool isRegister();

	public void create()
	{
		try {
			if (this.isRegister() == bool.FALSE)
			{
				Statement  myState                                 = connection.myConnect.createStatement();
				System.out.print("REQUEST \n" + getCreateDbRequest() + "\nSTARTED...\n");
				myState.executeUpdate(getCreateDbRequest());
				System.out.print("SUCCESS\n");
				
			}
			else
				System.out.print("ALREADY REGISTER\n");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setId(int id) {
		this.id = id;
	}

	void setConnection(MyDbUtils conn)
	{
		this.connection = conn;
	}
	
	public int getId() {
		return id;
	}
	
	MyDbUtils getConnection()
	{
		return this.connection;
	}
	
	public void setTab(String string)
	{
		this.tab = string;
	}
	
	public String getTab()
	{
		return (tab);
	}
	
}
