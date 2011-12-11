package fr.models;

public class Stock
{
	private int	id;
	private int productId;
	private int quantity;
	private int buyPrice;
	private int sellPrice;
	private String nextBuying;
	
	public Integer	getId() 		{ return id;	}
	public Integer	getProductId()	{	return productId; }
	public Integer	getQuantity()	{ return quantity; }
	public Integer	getBuyPrice()	{ return buyPrice; }
	public Integer	getSellPrice()	{ return sellPrice; }
	public String	getNextBuying()	{ return nextBuying; }
	
	public void setId(int id)						{ this.id = id; }
	public void setProductId(int productId)			{ this.productId = productId; }
	public void setQuantity(int quantity)			{ this.quantity = quantity; }
	public void setBuyPrice(int buyPrice)			{ this.buyPrice = buyPrice; }
	public void setSellPrice(int sellPrice)			{ this.sellPrice = sellPrice; }
	public void setNextBuying(String nextBuying)	{ this.nextBuying = nextBuying; }
}
