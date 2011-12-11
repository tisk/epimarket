package fr.models;

import java.sql.SQLException;

import com.mysql.jdbc.ResultSet;

public class Reduction extends MyDbUtils
{
	public enum eType
	{
		PERCENT,
		SUB,
		EQUAL,
		GIVEN
	};
	
	public enum eTarget
	{
		PRODUCT,
		CATEGORY,
		ALL
	};
	
	private int		id;
	private String	name;
	private String	description;
	private eType	type;
	private int		value;
	private eTarget	target;
	private String	deadLine;
	
	public Reduction(String name)
	{
		try
		{
			this.name = name;
			ResultSet rs = (ResultSet) select("select * from reduction where name = '" + name + "';");
			if (rs.next())
			{
				id = rs.getInt(1);
				description = rs.getString(3);
			//	type = (eType)rs.getInt(4);
				value = rs.getInt(5);
			//	target = (eTarget)rs.getInt(6);
				deadLine = (String)rs.getString(7);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void create()
	{
		if (existObj(0, name) == false)
		{
		 insert("insert into reduction (name, description, type, value, target, deadLine) VALUES('" + name  + "','" + description  + "'," +
				 type + "," + value + "," + target + "," + deadLine + ");");
		 try {
				ResultSet rs = null;
				rs = (ResultSet) select("select * from reduction where name = '" + name + "';");
				if (rs.next())
				id = rs.getInt(1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void modify(int type, String content, int cont)
	{
		if (existObj(type, content) == false)
			if ((type != 0) || (existObj(type, getAttrVal(type)) == true))
			{
				if (type < 4 || type == 5)
				{
					update("update reduction set " + getAttr(type) + "=" + "'" + content + "';");
				}
				else
				{
					update("update reduction set " + getAttr(type) + "=" + cont + ";");
				}
			}
		setAttrVal(type, content, cont);
	}
	
	public void remove()
	{
		delete("delete from reduction where name = '" + name + "';");
	}
	
	private boolean existObj(int type, String content)
	{
		boolean res = false;
		ResultSet rs = null;
		try 
		{
			if (type == 0)
				rs = (ResultSet) select("select * from reduction where " + getAttr(type) + " = '" + content + "';");
			if (rs != null && rs.next())
			{
				id = rs.getInt(1);
				res = true;
			}	
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return res;
	}
	
	public String getAttr(int type)		
	{	
		switch (type)
		{
		case 0:
			return "name";
		case 1:
			return "description";
		case 2 :
			return "type";
		case 3 :
			return "value";
		case 4 :
			return "target";
		case 5 :
			return "deadLine";
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
		case 5:
			return deadLine;
		default	:
			return "";		
		}
		
	}
	public int getAttrValInt(int type)		
	{	
		switch (type)
		{
		case 2:
			return type;
		case 3:
			return value;
//		case 4:
	//		return target;
		default	:
			return -1;		
		}
		
	}
	public void setAttrVal(int type, String val, int valInt)		
	{	
		switch (type)
		{
		case 0:
			name = val;
			break;
		case 1:
			description = val;
			break;
		case 2 :
			type = valInt;
			break;
		case 3 :
			value = valInt;
			break;
//		case 4 :
//			target = valInt;
//			break;
		case 5:
			deadLine = val;
			break;
		default	:
			break;		
		}
		
	}
	
	
	public Reduction(){}
	public Integer	getId()			{ return id; }
	public String	getName()		{ return name; }
	public String	getDescription(){ return description; }
	public eType	getType()		{ return type; }
	public Integer	getValue()		{ return value; }
	public eTarget	getTarget()		{ return target; }
	public String	getDeadLine()	{ return deadLine; }
	
	public void setId(int id)						{ this.id = id; }
	public void setName(String name)				{ this.name = name; }
	public void setDescription(String description)	{ this.description = description; }
	public void setType(eType type)					{ this.type = type; }
	public void setValue(int value)					{ this.value = value; }
	public void setTarget(eTarget target)			{ this.target = target; }
	public void setDeadLine(String deadLine)		{ this.deadLine = deadLine; }
}
