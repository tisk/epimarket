package fr.models;

import java.sql.SQLException;
import com.mysql.jdbc.ResultSet;


public class Product extends MyDbUtils
{
	public enum eField
	{
		NONE,
		NAME,
		DESCRIPTION,
		PICTURE,
		CATEGORY,
		QUANTITY,
		BUYPRICE,
		SELLPRICE
	}
	
	private String	name;
	private String	description;
	private String	picture;
	private int		categoryId;
	private int		quantity;
	private int		buy_price;
	private int		sell_price;
	private int		id;
	private String  next_buying;
	
	public Product(String name) 
	{
		try
		{
			this.name = name;
			ResultSet rs = (ResultSet) select("select * from product where name = '" + name + "';");
			if (rs.next())
			{
				id = rs.getInt(1);
				categoryId = rs.getInt(2);
				description = rs.getString(4);
				picture = rs.getString(5);
			}
			rs = (ResultSet) select("select * from stock where product_id = " + id + ";");
			if (rs.next())
			{
				quantity = rs.getInt(3);
				buy_price = rs.getInt(4);
				sell_price = rs.getInt(5);
				next_buying = rs.getString(6);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	
	public Product() 
	{
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof Product == false)
			return false;
		if (((Product)obj).getId() == id)
			return true;
		if (((Product)obj).getName() == name &&
				((Product)obj).getDescription() == description)
			return true;
		return false;
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	private boolean existObj(eField type, String content)
	{
		boolean res = false;
		ResultSet rs = null;
		try 
		{
			if (type == eField.NAME)
				rs = (ResultSet) select("select * from product where " + getAttr(type) + " = '" + content + "';");
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
	
	public void create()
	{
		if (existObj(eField.NAME, name) == false)
		{
		 insert("insert into product (categoryId, name, description, picture) VALUES(" + categoryId + ",'" + 
				 	name  + "','" + description  + "','" + picture + "');");
		 try {
				ResultSet rs = null;
				rs = (ResultSet) select("select * from product where name = '" + name + "';");
				if (rs.next())
				id = rs.getInt(1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 insert("insert into stock (product_id, quantity, buy_price, sell_price, next_buying) VALUES(" + id + "," + quantity +"," +
				 	buy_price + "," + sell_price + "," + 0 + ");");
		}
	}
	
	public void modify(eField type, String content, int cont)
	{
		if (existObj(type, content) == false)
			if ((type != eField.NAME) || (existObj(type, getAttrVal(type)) == true))
			{
				if (type == eField.NAME || type == eField.DESCRIPTION || type == eField.PICTURE)
				{
					update("update product set " + getAttr(type) + "=" + "'" + content + "';");
				}
				else
				{
					if (type == eField.CATEGORY)
						update("update product set " + getAttr(type) + "=" + cont + ";");
					else
						update("update stock set " + getAttr(type) + "= " + cont + " ;");
				}
			}
		setAttrVal(type, content, cont);
	}
	
	public void remove()
	{
		delete("delete from product, stock where name = '" + name + "' AND product.id = stock.productId;");
	}
	
	public void setName(String name)			{ this.name = name; }
	public void setId(int id)					{ this.id = id; }
	public void setCategory(int i)				{ this.categoryId = i; }
	public void setPicture(String i)			{ this.picture = i; }
	public void setDescription(String string)	{ this.description = string; }
	public void setQuantity(int quantity)		{ this.quantity = quantity; }
	public void setBuy_price(int buy_price)		{ this.buy_price = buy_price; }
	public void setSell_price(int sell_price)	{ this.sell_price = sell_price; }
	public void setNext_buying(String next)		{ this.next_buying = next; }


	
	public Integer	getId()				{ return id; }
	public String	getName()			{ return name; }
	public Integer	getCategory()		{ return categoryId; }
	public String	getPicture()		{ return picture; }
	public String	getDescription()	{ return description; }
	public Integer	getQuantity()		{ return quantity; }
	public Integer	getBuy_price()		{ return buy_price; }
	public Integer	getSell_price()		{ return sell_price; }
	public String	getNext_buying()	{ return next_buying; }
	
	public String getAttr(eField type)		
	{	
		switch (type)
		{
		case NAME :
			return "name";
		case DESCRIPTION :
			return "description";
		case PICTURE :
			return "picture";
		case CATEGORY :
			return "categoryId";
		case QUANTITY :
			return "quantity";
		case BUYPRICE :
			return "buy_price";
		case SELLPRICE :
			return "sell_price";
		default	:
			return "";
		}
		
	}
	public String getAttrVal(eField type)		
	{	
		switch (type)
		{
		case NAME :
			return name;
		case DESCRIPTION:
			return description;
		case PICTURE :
			return picture;
		default	:
			return "";
		}
		
	}	
	
	public int getAttrInt(eField type)		
	{	
		switch (type)
		{
		case CATEGORY :
			return categoryId;
		case QUANTITY :
			return quantity;
		case BUYPRICE :
			return buy_price;
		case SELLPRICE :
			return sell_price;
		default	:
			return (-1);
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
		case PICTURE :
			picture = val;
			break;
		case CATEGORY :
			categoryId = valInt;
			break;
		case QUANTITY :
			quantity = valInt;
			break;
		case BUYPRICE :
			buy_price = valInt;
			break;
		case SELLPRICE :
			sell_price = valInt;
			break;
		default	:
			break;
		}
	}	
}
