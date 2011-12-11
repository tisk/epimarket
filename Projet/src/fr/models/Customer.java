package fr.models;

import java.sql.SQLException;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

import com.mysql.jdbc.ResultSet;


public class Customer extends MyDbUtils
{
	public enum eCom
	{
		EMAIL,
		ADDRESS,
		PHONE
	}

	public enum eType
	{
		NONE,
		FIRSTNAME,
		LASTNAME,
		ADDRESS,
		EMAIL,
		PHONE,
		DATE,
		COMMUNICATIONTYPE
	}
	
	static public class Basket
	{
		private Map<Product, Integer>	listProduct;
		private Set<Reduction>			listReduc;
		
		Basket()
		{
			listProduct = new HashMap<Product, Integer>();
			listReduc = new HashSet<Reduction>();
		}
		
		public void addProduct(Product product)
		{
			if (listProduct.containsKey(product) == false)
				listProduct.put(product, 1);
			else
				listProduct.put(product, listProduct.get(product) + 1);
		}
		
		public void rmProduct(Product product)
		{
			if (listProduct.containsKey(product) == true)
			{
				if (0 < listProduct.get(product) - 1)
					listProduct.put(product, listProduct.get(product) - 1);
				else
				{
					listProduct.remove(product);
					//listProduct.keySet().remove(product);
				}
			}
		}
		
		public void addReduc(Reduction reduc)
		{
			listReduc.add(reduc);
		}
		
		public void rmReduc(Reduction reduc)
		{
			listReduc.remove(reduc);
		}
		
		public Map<Product, Integer> products() { return listProduct; }
	}
	
	private int		id;
	private String	firstName;
	private String	lastName;
	private String	address;
	private String	email;
	private String	date;
	private boolean	gender;
	private String	phone;
	private eCom	communicationType;
	private Basket	basket;
	
	public Customer()
	{
		basket = new Customer.Basket();
		id = -1;
	}
	
	public boolean buy()
	{
		ResultSet rs = null;
		Integer count = 0;
		try
		{
			rs = (ResultSet)select("select count(*) from order groupby orderId");
			if (rs.next())
				count = rs.getInt(1) + 1;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		for (Product prod : basket.products().keySet())
			insert("insert into order (orderId, productId, customerId, quantity) " +
					"VALUES(" + count.toString() + "," + 
			 	prod.getId() + "," + this.getId()  + "," + basket.products().get(prod) + ");");
		return true;
	}
	
	public void addInBasket(Product product)
	{
		basket.addProduct(product);
	}
	
	public void rmFromBasket(Product product)
	{
		basket.rmProduct(product);
	}
	
	public Map<Product, Integer> getBasketContent()
	{
		return basket.products();
	}
	
	public Set<Reduction> getAssociatedOffer()
	{
		ResultSet rs = null;
		Set<Reduction>	reducs = new HashSet<Reduction>();
		
		/*try
		{
			rs = (ResultSet)select("select * from Reduction");
			while (rs.next())
			{
				count = rs.getInt(1);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();*/
			return null;
		//}
	}
	
	private boolean existObj(eType type, int idd)
	{
		boolean res = false;
		ResultSet rs = null;
		try 
		{
			if (type == eType.PHONE)
				rs = (ResultSet) select("select * from customer where " + getAttr(type) + " = '" + getAttrVal(type) + "';");
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
		if (existObj(eType.PHONE, id) == false)
		{
			java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
		 insert("insert into customer (communication_type, first_name, last_name, adress, email, phone, date) VALUES(" + getIds(communicationType) + ",'" + 
				 	firstName  + "','" + lastName  + "','" + address + "','" + email + "','" + phone + "','" + sqlDate + "');");
		 try {
				ResultSet rs = null;
				rs = (ResultSet) select("select * from customer where phone = '" + phone + "';");
				if (rs.next())
				id = rs.getInt(1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void modify(eType type, String content, int cont)
	{
		if (id != -1)
			{
				if (type == eType.COMMUNICATIONTYPE)
				{
					update("update customer set " + getAttr(type) + "=" + cont + ";");
				}
				else
				{
					update("update customer set " + getAttr(type) + "= '" + content + "';");
				}
			}
		setAttrVal(type, content, cont);
	}
	
	public void remove()
	{
		delete("delete from customer where id = " + id + ";");
	}
	
	public String getAttr(eType type)		
	{	
		switch (type)
		{
		case FIRSTNAME :
			return "first_name";
		case LASTNAME :
			return "last_name";
		case ADDRESS :
			return "adress";
		case EMAIL :
			return "email";
		case PHONE :
			return "phone";
		case DATE :
			return "date";
		case  COMMUNICATIONTYPE :
			return "communication_type";
		default	:
			return "";
		}
		
	}
	public String getAttrVal(eType type)		
	{	
		switch (type)
		{
		case FIRSTNAME :
			return firstName;
		case LASTNAME :
			return lastName;
		case ADDRESS :
			return address;
		case EMAIL :
			return email;
		case PHONE :
			return phone;
		case DATE :
			return date;
		default	:
			return "";
		}
		
	}
	
	public void setAttrVal(eType type, String v, int t)		
	{	
		switch (type)
		{
		case FIRSTNAME :
			firstName = v;
			break;
		case LASTNAME :
			lastName = v;
			break;
		case ADDRESS :
			address = v;
			break;
		case EMAIL :
			email = v;
			break;
		case PHONE :
			phone = v;
			break;
		case DATE :
			date = v;
			break;
		case  COMMUNICATIONTYPE :
			communicationType = eCom.values()[t];
			break;
		default	:
			break;
		}
		
	}
	public Integer		getIds(eCom communicationType2)			
	{ 
		switch (communicationType2)
		{
		case EMAIL :
			return 1;
		case ADDRESS:
			return 2;
		case PHONE :
			return 3;
		default :
			return 0;
		}
	
	}
	
	public int			getId() 		{ return this.id; }
	public String		getFirstName()	{ return firstName; }
	public String		getLastName()	{ return lastName; }
	public String		getAddress()	{ return address; }
	public String		getEmail() 		{ return email; }
	public String		getDate()		{ return date; }
	public Boolean		isGender()		{ return gender; }
	public String		getPhone()		{ return phone; }
	public eCom			getComType()	{ return communicationType; }
	public final Basket	getBasket() 	{ return basket; }
	
	public void setId(int id)					{ this.id = id; }
	public void setDate(String date)			{ this.date = date; }
	public void setFirstName(String firstName)	{ this.firstName = firstName; }
	public void setLastName(String lastName)	{ this.lastName = lastName; }
	public void setAddress(String address)		{ this.address = address; }
	public void setEmail(String email) 			{ this.email = email; }
	public void setGender(boolean gender)		{ this.gender = gender; }
	public void setPhone(String phone)			{ this.phone = phone; }
	public void setComType(eCom com)			{ this.communicationType = com; }
}
