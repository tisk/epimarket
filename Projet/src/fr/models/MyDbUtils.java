package fr.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MyDbUtils 
{
	public String		login;
	public String		pass;
	public String		url;
	static Connection	myConnect;
	
	public MyDbUtils()
	{
	}

	public void setLogin(String string)	{ this.login = string; }
	public void setPass(String string)	{ this.pass = string; }
	public void setUrl(String string) 	{ this.url = string; }


	public void connect()
    {
		try
        {
			Class.forName("com.mysql.jdbc.Driver");
			myConnect = DriverManager.getConnection(url, login, pass);
        }
        catch (ClassNotFoundException e)
        {
        	System.out.println("dbConnect ClassNotFoundException: " + e.toString());
        	e.printStackTrace();
        }
        catch (Exception e)
        {
        	System.out.println("dbConnect Exception: " + e.toString());
        	e.printStackTrace();
        }
    }

	static public void insert(String request)
	{
		try
		{
			Statement  myState = myConnect.createStatement();
			System.out.print("STARTED...\n" + "INSERT " + request);
			myState.executeUpdate(request);
			System.out.print("\nSUCCESS\n");
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 
	static public ResultSet select(String request)
	{
		ResultSet myResultSet = null;
		try
		{
			Statement myState = myConnect.createStatement();
			//System.out.print("STARTED...\n" + "SELECT " + request);
			myResultSet = myState.executeQuery(request);
			//System.out.print("\nSUCCESS\n");
		}
		catch (SQLException e)
		{
			System.out.print("FAILURE\n");
			e.printStackTrace();
			return null;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
		return myResultSet;
	}
	
	static public void update(String request)
	{
		try 
		{
			Statement  myState = myConnect.createStatement();
			System.out.print("STARTED...\n" + "UPDATE " + request);
			myState.executeUpdate(request);
			System.out.print("\nSUCCESS\n");	
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	static public void delete(String request)
	{
		try 
		{
			Statement  myState = myConnect.createStatement();
			System.out.print("DELETE " + request + " STARTED...");
			myState.executeUpdate(request);
			System.out.print("\nSUCCESS\n");
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((pass == null) ? 0 : pass.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MyDbUtils other = (MyDbUtils) obj;
		if (login == null)
		{
			if (other.login != null)
				return false;
		}
		else if (!login.equals(other.login))
			return false;
		if (pass == null)
		{
			if (other.pass != null)
				return false;
		}
		else if (!pass.equals(other.pass))
			return false;
		if (url == null)
		{
			if (other.url != null)
				return false;
		}
		else if (!url.equals(other.url))
			return false;
		return true;
	}
}
