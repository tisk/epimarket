package fr.models;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyDbUtils 
{

	public String login;
	public String pass;
	public String url;
	Connection myConnect;
	
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

}
