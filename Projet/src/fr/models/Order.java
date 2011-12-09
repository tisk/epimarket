package fr.models;

public class Order
{
	private int		id;
	private int		orderId;
	private int		productId;
	private int		customerId;
	private int		quantity;
	private String	date;
	
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
