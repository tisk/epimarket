package fr.models;

public class Category extends DBUtil
{
	private int		id;
	private String	name;
	private String	description;
	
	public int 		getId() 			{ return id; }
	public String	getName()			{ return name; }
	public String	getDescription()	{ return description; }
	
	public void setId(int id)				{ this.id = id; }
	public void setName(String name)		{ this.name = name; }
	public void setDescription(String desc) { this.description = desc; }
	
}
