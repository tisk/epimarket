package fr.models;

import java.sql.SQLException;

import com.mysql.jdbc.ResultSet;

public class Reduction extends MyDbUtils
{
	public enum eField
	{
		NONE,
		NAME,
		DESCRIPTION,
		TYPE,
		VALUE,
		TARGET,
		DEADLINE,
		ALLUSER
	}
	
	public enum eType
	{
		PERCENT,
		SUB,
		EQUAL,
		GIVEN;
		
		static public eType	convert(int i)
		{
			if (i < 0 || eType.values().length <= i)
				return PERCENT;
			return eType.values()[i];
		}
	};
	
	private int		id;
	private String	name;
	private String	description;
	private eType	type;
	private int		value;
	private int		target;
	private String	deadLine;
	private int		allUser;
	
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
				type = eType.convert(rs.getInt(4));
				value = rs.getInt(5);
				target = rs.getInt(6);
				deadLine = (String)rs.getString(7);
				allUser = rs.getInt(8);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void create()
	{
		if (existObj(eField.NAME, name) == false)
		{
		 insert("insert into reduction (name, description, type, value, target, deadLine, all_user) VALUES('" + name  + "','" + description  + "'," +
				 type + "," + value + "," + target + "," + deadLine + "," + allUser +");");
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
	
	public void modify(eField type, String content, int cont)
	{
		if (existObj(type, content) == false)
			if ((type != eField.NONE) || (existObj(type, getAttrVal(type)) == true))
			{
				if (type == eField.NAME || type == eField.DESCRIPTION || type == eField.DEADLINE)
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
	
	private boolean existObj(eField type, String content)
	{
		boolean res = false;
		ResultSet rs = null;
		try 
		{
			if (type == eField.NAME)
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
	
	public String getAttr(eField type)		
	{	
		switch (type)
		{
		case NAME :
			return "name";
		case DESCRIPTION :
			return "description";
		case TYPE :
			return "type";
		case VALUE :
			return "value";
		case TARGET :
			return "target";
		case DEADLINE :
			return "deadLine";
		case ALLUSER :
			return "all_user";
		default	:
			return "";		
		}
		
	}
	public String getAttrVal(eField type)		
	{	
		switch (type)
		{
		case NAME:
			return name;
		case DESCRIPTION:
			return description;
		case DEADLINE:
			return deadLine;
		default	:
			return "";		
		}
		
	}
	public int getAttrValInt(eField type)		
	{	
		switch (type)
		{
		case TYPE:
			return type.ordinal();
		case VALUE:
			return value;
		case TARGET:
			return target;
		case ALLUSER :
			return allUser;
		default	:
			return -1;		
		}
		
	}
	public void setAttrVal(eField type, String val, int valInt)		
	{	
		switch (type)
		{
		case NAME :
			name = val;
			break;
		case DESCRIPTION :
			description = val;
			break;
		case TYPE :
			this.type = eType.convert(valInt);
			break;
		case VALUE :
			value = valInt;
			break;
		case TARGET :
			target = valInt;
			break;
		case DEADLINE :
			deadLine = val;
			break;
		case ALLUSER :
			allUser = valInt;
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
	public Integer	getTarget()		{ return target; }
	public String	getDeadLine()	{ return deadLine; }
	public Integer	getAllUser()	{ return allUser; }
	
	public void setId(int id)						{ this.id = id; }
	public void setName(String name)				{ this.name = name; }
	public void setDescription(String description)	{ this.description = description; }
	public void setType(eType type)					{ this.type = type; }
	public void setValue(int value)					{ this.value = value; }
	public void setTarget(int target)				{ this.target = target; }
	public void setDeadLine(String deadLine)		{ this.deadLine = deadLine; }
	public void setAllUser(int all)					{ this.allUser = all; }
}
