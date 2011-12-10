package fr.models;

import java.util.Set;

public class Shop
{
	private Set<Category>	cats;
	private Customer		cust;
	
	public Shop()
	{
		cats = DbItem.getCategoryList();
		cust = new Customer();
	}
	
	
	
	public void open()
	{
		Printer.writeFull();
		Printer.writeCenter("Bienvenue dans l'EpiMarket");
		Printer.writeFull();
		add();
		del();
	}
	
	public void		add()
	{
		Category	cat;
		Product		prod;
		do
		{
			cat = chooseCategory();
			prod = chooseProduct(cat);
			if (buyProduct(prod) == true)
				cust.addInBasket(prod);	
		}
		while (confirmBuying() == false);
	}
	
	public void		del()
	{
		do
		{
			selectFromBasket();
		}
		while (confirmBuying() == false);
	}
	
	public Category	chooseCategory()
	{
		Integer i = 0;
		
		Printer.writeFull();
		Printer.write("Les categories:");
		Printer.writeEmpty();
		for (Category cat : cats)
		{
			i++;
			Printer.write(i.toString() + " - " + cat.getName());
		}
		Printer.writeEmpty();
		i = Printer.askInt("Quelle catégorie de produits vous intéresserez ?") - 1;
		Printer.writeFull();
		if (i < 0 || cats.size() <= i)
			return null;
		Printer.write("Vous avez choisi la catégorie " + ((Category)cats.toArray()[i]).getName());
		Printer.writeFull();
		return (Category)cats.toArray()[i];
	}
	
	public Product	chooseProduct(Category cat)
	{
		Integer i = 0;
		Set<Product>	prods;
		
		Printer.write("Les produits:");
		Printer.writeEmpty();
		prods = DbItem.getProductList(cat);
		for (Product prod : prods)
		{
			i++;
			Printer.write(i.toString() + " - " + prod.getName());
		}
		i = Printer.askInt("Quel produit voulez-vous acheter ?") - 1;
		Printer.writeFull();
		if (i < 0 || prods.size() <= i)
			return null;
		Printer.write("Vous avez choisi le produit " + ((Product)prods.toArray()[i]).getName());
		Printer.writeFull();
		return (Product)prods.toArray()[i];
	}
	
	public boolean	buyProduct(Product prod)
	{
		boolean	rep = false;
		if (prod == null)
			return false;
		Printer.writeCenter(prod.getName());
		Printer.writeEmpty();
		Printer.write(prod.getDescription());
		Printer.writeEmpty();
		Printer.write("prix:");
		Printer.writeCenter(prod.getSell_price().toString() + "€");
		Printer.writeEmpty();
		if (prod.getQuantity() == 0)
		{
			Printer.writeCenter("Nous sommes désolé");
			Printer.writeCenter("Mais nous sommes en rupture de stock.");
			Printer.writeCenter("Renouvellement du stock le " + prod.getNext_buying());
		}
		else
		{
			Printer.write("1 - oui");
			Printer.write("2 - non");
			rep = (Printer.askInt("Confirmez-vous l'ajout de ce produit dans votre panier?") == 1 ? true : false);
			Printer.write("Vous avez " + (rep ? "confirmez" : "annulez") + " l'ajout à votre panier");
			if (rep)
			{
				prod.setQuantity(prod.getQuantity() - 1);
				prod.modify(Product.eType.QUANTITY, "", prod.getQuantity());
			}
		}
		Printer.writeFull();
		return rep;
	}
	
	public void		showBasket()
	{
		Integer	quantity;
		Integer	total = 0;
		
		Printer.write("Votre panier contient:");
		for (Product key : cust.getBasketContent().keySet())
		{
			Printer.write(key.getName() + ":");
			quantity = cust.getBasketContent().get(key);
			Printer.writeCenter(quantity.toString() + " * " +
						key.getSell_price() + " € = " +
						((Integer)(quantity * key.getSell_price())).toString() + " €");
			total += quantity * key.getSell_price();
		}
		Printer.write("Total:");
		Printer.writeCenter(total.toString() + " €");
		Printer.writeFull();
	}
	
	public Product	selectFromBasket()
	{
		Product	prod;
		Integer	quantity;
		Integer	i = 0;
		Integer	total = 0;
		
		Printer.write("Votre panier contient:");
		for (Product key : cust.getBasketContent().keySet())
		{
			i++;
			Printer.write(i.toString() + " - " + key.getName() + ":");
			quantity = cust.getBasketContent().get(key);
			Printer.writeCenter(quantity.toString() + " * " +
						key.getSell_price() + " € = " +
						((Integer)(quantity * key.getSell_price())).toString() + " €");
			total += quantity * key.getSell_price();
		}
		Printer.write("Total:");
		Printer.writeCenter(total.toString() + " €");
		Printer.writeEmpty();
		i = Printer.askInt("Quel produit voulez-vous retirer du panier ?") - 1;
		if (i < 0 || cust.getBasketContent().keySet().size() <= i)
		{
			Printer.writeFull();
			return null;
		}
		prod = (Product)cust.getBasketContent().keySet().toArray()[i];
		Printer.write("Vous avez choisi le produit " + prod.getName());
		Printer.writeFull();
		cust.rmFromBasket(prod);
		prod.setQuantity(prod.getQuantity() + 1);
		prod.modify(Product.eType.QUANTITY, "", prod.getQuantity());
		return null;
	}
	
	public boolean	confirmBuying()
	{
		boolean	rep = false;
		
		showBasket();
		Printer.write("1 - oui");
		Printer.write("2 - non");
		rep = (Printer.askInt("Voulez-vous passez à la caisse ?") == 1 ? true : false);
		Printer.writeFull();
		return rep;
	}
}
