package fr.models;

import java.sql.SQLException;

import com.mysql.jdbc.ResultSet;

public class Order extends MyDbUtils
{
	private int		id;
	private int		orderId;
	private int		productId;
	private int		customerId;
	private int		quantity;
	private String	date;
	
	public Order(){}
	public Order(int id)
	{
		try
		{
			this.id = id;
			ResultSet rs = (ResultSet) select("select * from order where id = " + id + ";");
			if (rs.next())
			{
				orderId = rs.getInt(2);
				productId = rs.getInt(3);
				customerId = rs.getInt(4);
				quantity = rs.getInt(5);
				date = rs.getString(6);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	public int getId()			{ return id; }
	public int getOrderId() 	{ return orderId; }
	public int getProductId()	{ return productId; }
	public int getCustomerId()	{ return customerId; }
	public int getQuantity()	{ return quantity; }
	public String getDate()		{ return date; }
	
	public void setId(int id)					{ this.id = id; }
	public void setOrderId(int orderId)			{ this.orderId = orderId; }
	public void setProductId(int productId)		{ this.productId = productId; }
	public void setCustomerId(int customerId)	{ this.customerId = customerId; }
	public void setQuantity(int quantity)		{ this.quantity = quantity; }
	public void setDate(String date)			{ this.date = date; }
}
