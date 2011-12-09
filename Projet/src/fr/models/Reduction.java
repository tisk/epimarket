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
	public Reduction(){}
	public int		getId()			{ return id; }
	public String	getName()		{ return name; }
	public String	getDescription(){ return description; }
	public eType	getType()		{ return type; }
	public int		getValue()		{ return value; }
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
