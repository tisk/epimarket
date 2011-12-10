package fr.models;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.mysql.jdbc.ResultSet;

public class DbItem extends MyDbUtils
{

	static public Set<Product>			getProductList()
	{
		return getProductList(null);
	}
	
	static public Set<Product>			getProductList(Category cat)
	{
		Set<Product> listProduct = new HashSet<Product>();
		try 
		{
			ResultSet rs;
			if (cat == null)
				rs = (ResultSet)select("select * from product;");
			else
				rs = (ResultSet)select("select * from product where categoryId = " + cat.getId().toString() + ";");
			if (rs == null)
				return null;
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
	
	static public Set<Reduction>			getReductionList()
	{
		Set<Reduction> listReduction = new HashSet<Reduction>();
		try
		{
			ResultSet rs = (ResultSet) select("select * from reduction;");
			if (rs == null)
				return null;
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
	
	static public Set<Category>			getCategoryList()
	{
		Set<Category> listCategory = new HashSet<Category>();
		try
		{
			ResultSet rs = (ResultSet) select("select * from category;");
			if (rs == null)
				return null;
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

	static public Set<Order>			getOrderList()
	{
		Set<Order> listOrder = new HashSet<Order>();
		try
		{
			ResultSet rs = (ResultSet) select("select * from order;");
			if (rs == null)
				return null;
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
	
	static public Set<Customer>			getCustomerList()
	{
		Set<Customer> listCustomer = null;
		return listCustomer;
	}
}
