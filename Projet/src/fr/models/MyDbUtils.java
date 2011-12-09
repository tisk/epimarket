package fr.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MyDbUtils 
{
	public String login;
	public String pass;
	public String url;
	static Connection myConnect;
	
	public MyDbUtils()
	{
		// TODO Auto-generated constructor stub
	}

	public void setLogin(String string)
	{
		this.login = string;
	}

	public void setPass(String string)
	{
		this.pass = string;
	}

	public void setUrl(String string)
	{
		this.url = string;
	}


	 public void connect()
     {
         try
             {
        	 		Class.forName("com.mysql.jdbc.Driver");
                     myConnect = DriverManager.getConnection(url, login, pass);
             }
             catch (ClassNotFoundException e)        {System.out.println("dbConnect ClassNotFoundException: " + e.toString()); e.printStackTrace();} 
             catch (Exception e)                             {System.out.println("dbConnect Exception: " + e.toString());    e.printStackTrace();}   
     }

	 public void insert(String request)
		{
			try {
				
					Statement  myState                                 = myConnect.createStatement();
					System.out.print("STARTED...\n" + "INSERT " + request);
					myState.executeUpdate(request);
					System.out.print("\nSUCCESS\n");	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	 
	 public ResultSet select(String request)
	 {
		 ResultSet myResultSet = null;
		 try 
			{
				Statement myState;
				myState = myConnect.createStatement();
				//System.out.print("STARTED...\n" + "SELECT " + request);
				myResultSet = myState.executeQuery(request);
				//System.out.print("\nSUCCESS\n");
			} 
		 catch (SQLException e) 
			{
				System.out.print("FAILURE\n");
				e.printStackTrace();
			}	 
		 return myResultSet;
	 }
	 
	 public void update(String request)
	 {
		 try 
		 {
			 Statement  myState                                 = myConnect.createStatement();
			 System.out.print("STARTED...\n" + "UPDATE " + request);
			 myState.executeUpdate(request);
			 System.out.print("\nSUCCESS\n");	
		 } 
		 catch (SQLException e) 
		 {
			 e.printStackTrace();
		 }
	 }
	 
	 public void delete(String request)
	 {	
		 try 
		 {
			 Statement  myState                                 = myConnect.createStatement();
			 System.out.print("DELETE " + request + " STARTED...");
			 myState.executeUpdate(request);
			 System.out.print("\nSUCCESS\n");
		 } 
		 catch (SQLException e) 
		 {
			 e.printStackTrace();
		 }
	 }	 
}
