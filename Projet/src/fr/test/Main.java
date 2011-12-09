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
		
		
		/*Product p1 = new Product(test);
		p1.setName("video fly");
		p1.setCategory(1);
		p1.setPrice(20);
		p1.setDescription("un koala qui vole !");
		System.out.println(p1.getCreateDbRequest());
		*/
		
	}
}
