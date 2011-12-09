package fr.models;

import java.sql.SQLException;
import java.util.Set;

import com.mysql.jdbc.ResultSet;

public class Product extends MyDbUtils
{

	private String	name;
	private String	description;
	private String	picture;
	private int		categoryId;
	private int		quantity;
	private int		buy_price;
	private int		sell_price;
	private int		price;
	private int		id;
	
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
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	
	public Product() 
	{
	}
	
	private boolean existObj(int type, String content)
	{
		boolean res = false;
		ResultSet rs = null;
		try 
		{
			if (type == 0)
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
		if (existObj(0, name) == false)
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
	
	public void modify(int type, String content, int cont)
	{
		if (existObj(type, content) == false)
			if ((type != 0) || (existObj(type, getAttrVal(type)) == true))
			{
				if (type < 3)
				{
					update("update product set " + getAttr(type) + "=" + "'" + content + "';");
				}
				else
				{
					if (type == 3)
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
	
	public void setId(int id)					{ this.id = id; }
	public void setName(String name)
	{ 
		try
		{
			this.name = name;
			ResultSet rs = (ResultSet) select("select * from product where name = '" + name + "';");
			if (rs.next())
			{
				id = rs.getInt(1); 	
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	public void setCategory(int i)				{ this.categoryId = i; }
	public void setPicture(String i)			{ this.picture = i; }
	public void setDescription(String string)	{ this.description = string; }
	public void setPrice(int price)				{ this.price = price; }
	public void setQuantity(int quantity)		{ this.quantity = quantity; }
	public void setBuy_price(int buy_price)		{ this.buy_price = buy_price; }
	public void setSell_price(int sell_price)	{ this.sell_price = sell_price; }
	
	public int		getId()				{ return id; }
	public String	getName()			{ return name; }
	public int		getCategory()		{ return categoryId; }
	public String	getPicture()		{ return picture; }
	public String	getDescription()	{ return description; }
	public int		getPrice()			{ return price; }
	public int		getQuantity()		{ return quantity; }
	public int		getBuy_price()		{ return buy_price; }
	public int		getSell_price()		{ return sell_price; }

	public String getAttr(int type)		
	{	
		switch (type)
		{
		case 0:
			return "name";
		case 1:
			return "description";
		case 2 :
			return "picture";
		case 3 :
			return "categoryId";
		case 4 :
			return "price";
		case 5 :
			return "quantity";
		case 6 :
			return "buy_price";
		case 7 :
			return "sell_price";
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
		case 2 :
			return picture;
		default	:
			return "";
		}
		
	}	
	
	public int getAttrInt(int type)		
	{	
		switch (type)
		{
		case 3 :
			return categoryId;
		case 4 :
			return price;
		case 5 :
			return quantity;
		case 6 :
			return buy_price;
		case 7 :
			return sell_price;
		default	:
			return (-1);
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
		case 2:
			picture = val;
			break;
		case 3 :
			categoryId = valInt;
			break;
		case 4 :
			price = valInt;
			break;
		case 5 :
			quantity = valInt;
			break;
		case 6 :
			buy_price = valInt;
			break;
		case 7 :
			sell_price = valInt;
			break;
		default	:
			break;		
		}
	}	
}
