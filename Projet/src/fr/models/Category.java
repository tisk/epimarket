package fr.models;

import java.sql.SQLException;

import com.mysql.jdbc.ResultSet;

public class Category extends MyDbUtils
{
	private int		id;
	private String	name;
	private String	description;

	public Category(){}
	public int 		getId() 			{ return id; }
	public String	getName()			{ return name; }
	public String	getDescription()	{ return description; }
	
	public String getAttr(int type)		
	{	
		switch (type)
		{
		case 0:
			return "name";
		case 1:
			return "description";
		default	:
			return "";		
		}
		
	}
	public String getAttrVal(int type)		
	{	
		switch (type)
		{
		case 0:
			return name;
		case 1:
			return description;
		default	:
			return "";		
		}
		
	}
	public void setAttrVal(int type, String val)		
	{	
		switch (type)
		{
		case 0:
			name = val;
			break;
		case 1:
			description = val;
			break;
		default	:
			break;		
		}
		
	}
	public void setId(int id)				{ this.id = id; }
	public void setName(String name)		{ this.name = name; }
	public void setDescription(String desc) { this.description = desc; }

	private bool existObj(int type, String content)
	{
		bool res = bool.FALSE;
		ResultSet rs = null;
		try 
		{
			if (type == 0)
				rs = (ResultSet) select("select * from category where " + getAttr(type) + " = '" + content + "';");
			if (rs != null && rs.next())
			{
				res = bool.TRUE;
			}	
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return res;
	}
	
	public void create()
	{
		if (existObj(0, name) == bool.FALSE)
		 insert("insert into category (name, description) VALUES('" + name +"','" + description + "');");
	}
	
	public void modify(int type, String content)
	{
		if (existObj(type, content) == bool.FALSE)
			if (existObj(type, getAttrVal(type)) == bool.TRUE)
				update("update category set " + getAttr(type) + "=" + "'" + content + "';");
		setAttrVal(type, content);
	}
	
	public void remove()
	{
		delete("delete from category where name = '" + name + "';");
	}
}
