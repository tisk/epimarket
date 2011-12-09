package fr.models;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.mysql.jdbc.ResultSet;

public class DbItem  extends MyDbUtils
{

	public Set<Product>			getProductList()
	{
		Set<Product> listProduct = new HashSet<Product>();
		try 
		{
			ResultSet rs = (ResultSet) select("select * from product;");
			while (rs.next())
			{
				String name = rs.getString(3);
				Product product = new Product(name);
				listProduct.add(product);
			}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return listProduct;
	}
	
	public Set<Reduction>			getReductionList()
	{
		Set<Reduction> listReduction = new HashSet<Reduction>();
		try
		{
			ResultSet rs = (ResultSet) select("select * from reduction;");
			while (rs.next())
			{
				String name = rs.getString(2);
				Reduction reduction = new Reduction(name);
				listReduction.add(reduction);
			}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return listReduction;
	}
	
	public Set<Category>			getCategoryList()
	{
		Set<Category> listCategory = new HashSet<Category>();
		try
		{
			ResultSet rs = (ResultSet) select("select * from category;");
			while (rs.next())
			{
				String name = rs.getString(2);
				Category category = new Category(name);
				listCategory.add(category);
			}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return listCategory;
	}

	public Set<Order>			getOrderList()
	{
		Set<Order> listOrder = new HashSet<Order>();
		try
		{
			ResultSet rs = (ResultSet) select("select * from order;");
			while (rs.next())
			{
				int id = rs.getInt(1);
				Order order = new Order(id);
				listOrder.add(order);
			}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return listOrder;
	}
	
	public Set<Customer>			getCustomerList()
	{
		Set<Customer> listCustomer = null;
		return listCustomer;
	}
}
