package fr.models;

public class Reduction
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
