package fr.test;

import fr.models.Category;
import fr.models.MyDbUtils;

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

		testbat(test);
	}
	
	public static void testbat(MyDbUtils test)
	{
		//Categories
		Category c1	= new Category(test);
		c1.setName("Film");
		System.out.println(c1.getCreateDbRequest());
		c1.create();
		
		Category c2	= new Category(test);
		c2.setName("Video");
		System.out.println(c2.getCreateDbRequest());
		c2.create();
		
		Category c3	= new Category(test);
		c3.setName("Sound");
		System.out.println(c3.getCreateDbRequest());
		c3.create();
		
		/*Product p1 = new Product(test);
		p1.setName("video fly");
		p1.setCategory(1);
		p1.setPrice(20);
		p1.setDescription("un koala qui vole !");
		System.out.println(p1.getCreateDbRequest());
		*/
		
	}
}
