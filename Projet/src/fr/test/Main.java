package fr.test;

import fr.models.Category;
import fr.models.MyDbUtils;
import fr.models.Product;
import fr.models.Customer.eCom;
import fr.models.Reduction.eType;
import fr.models.Customer;
import fr.models.Reduction;
import fr.models.Shop;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		MyDbUtils test = new MyDbUtils();
        test.setLogin("root");
        test.setPass("");
        test.setUrl("jdbc:mysql://localhost/epimarket");
        test.connect();
        Shop shop = new Shop();
		testbat(test);
        //shop.open();
	}
	
	public static void testbat(MyDbUtils test)
	{
		//Categories
		Category c1	= new Category();
		c1.setName("Film");
		c1.setDescription("Sa se regarde !");
		//Creation d'une nouvelle categorie si elle n'existe pas
		c1.create();
		//Modification d'une categorie existante
		c1.modify(0, "billis film");
		c1.modify(1, "les meilleurs films !");
		
		Category c2	= new Category();
		c2.setName("Video");
		c2.create();
		
		Category c3	= new Category();
		c3.setName("Sound");
		c3.create();
		//Suppression d'une categorie
		c3.remove();
		
		//Products
		Product p1 = new Product();
		p1.setName("video fly");
		p1.setCategory(1);
		p1.setBuy_price(10);
		p1.setSell_price(20);
		p1.setPicture("c::bibi/pict/");
		p1.setDescription("un koala qui vole !");
		p1.setQuantity(100);
		// creation d'un nouvau produit
		p1.create();
		p1.modify(Product.eField.NAME, "video box", -1);
		p1.modify(Product.eField.QUANTITY, "", 1000);
		
		
		Product p2 = new Product();
		p2.setName("video down");
		p2.setCategory(2);
		p2.setBuy_price(10);
		p2.setSell_price(20);
		p2.setPicture("c::bibi/pict/");
		p2.setDescription("un koala qui crash !");
		p2.setQuantity(100);
		// creation d'un nouvau produit
		p2.create();
		
		Product p3 = new Product();
		p3.setName("video down");
		p3.setCategory(2);
		p3.setBuy_price(10);
		p3.setSell_price(20);
		p3.setPicture("c::bibi/pict/");
		p3.setDescription("un koala qui crash !");
		p3.setQuantity(100);
		// creation d'un nouvau produit
		p3.create();
		
		
		//clients
		Customer cl1 = new Customer();
		cl1.setFirstName("billi");
		cl1.setLastName("bob");
		cl1.setAddress("2 rue de ouest 44000 nantes");
		cl1.setEmail("billi.bob@bobidic.net");
		cl1.setGender(true);
		cl1.setPhone("0000000001");
		cl1.setComType(Customer.eCom.EMAIL);
		cl1.setDate("21-12-2012");
		cl1.create();
		cl1.modify(Customer.eType.ADDRESS, "rue du paradis", -1);

		Customer cl2 = new Customer();
		cl2.setFirstName("bob");
		cl2.setLastName("bobidic");
		cl2.setAddress("2 toudoun 77000 para");
		cl2.setEmail("billi.bob@bobidic.net");
		cl2.setGender(true);
		cl2.setPhone("0000000002");
		cl2.setComType(Customer.eCom.EMAIL);
		cl2.setDate("21-12-2012");
		cl2.create();
		
		
		//Reduction
		Reduction r1 = new Reduction();
		r1.setName("sa decoiffe ?");
		r1.setDescription("va te recoiffer !");
		r1.setType(Reduction.eType.PERCENT);
		r1.setValue(90);
		r1.setTarget(1);
		r1.create();
		r1.modify(Reduction.eField.DESCRIPTION, "bill est decoiffe ??!!!!", -1);
	
		
		Reduction r2 = new Reduction();
		r2.setName("presque gratuit ?");
		r2.setDescription("hum !");
		r2.setType(Reduction.eType.PERCENT);
		r2.setValue(10);
		r2.setTarget(1);
		r2.create();
	}
}