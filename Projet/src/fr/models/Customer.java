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
				if (0 < listProduct.get(product))
					listProduct.put(product, listProduct.get(product) - 1);
				else
					listProduct.remove(product);
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
					"VALUES(" + count.toString() + ",'" + 
			 	prod.getId() + "','" + this.getId()  + "','" + basket.products().get(prod) + "');");
		return true;
	}
	
	public int			getId()			{ return id; }
	public String		getFirstName()	{ return firstName; }
	public String		getLastName()	{ return lastName; }
	public String		getAddress()	{ return address; }
	public String		getEmail() 		{ return email; }
	public String		getDate()		{ return date; }
	public boolean		isGender()		{ return gender; }
	public String		getPhone()		{ return phone; }
	public eCom			getComType()	{ return communicationType; }
	public final Basket	getBasket() 	{ return basket; }
	
	public void setId(int id)					{ this.id = id; }
	public void setFirstName(String firstName)	{ this.firstName = firstName; }
	public void setLastName(String lastName)	{ this.lastName = lastName; }
	public void setAddress(String address)		{ this.address = address; }
	public void setEmail(String email) 			{ this.email = email; }
	public void setGender(boolean gender)		{ this.gender = gender; }
	public void setPhone(String phone)			{ this.phone = phone; }
	public void setComType(eCom com)			{ this.communicationType = com; }
}
