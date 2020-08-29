package program1;

import java.util.*;
import java.io.*;

public class test {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Customer c1;
		c1 = new Customer("TesterGuy");
		System.out.print(c1.getUsername() + ": ");
		
		c1.getWishlist().add("product 1");
		
		
		c1.getWishlist().add("something else");
		
		
		System.out.println(c1.getWishlist());
		
		
		Customer c2;
		c2 = new Customer("Birdo");
		c2.getWishlist().add("product 1");
		c2.getWishlist().add("product 2");
		c2.getWishlist().add("p3, p4");
		
		System.out.print(c2.getUsername() + ": ");
		System.out.println(c2.getWishlist());
		
		String line = "cust1,p1,p2,p3";
		System.out.println(line.split(",",2)[0]);
		for (String retval: line.split(",",2)[1].split(",")){
			System.out.println(retval);
		}
		
		CustomerDatabase db1 = new CustomerDatabase();
		db1.addCustomer("bob");
		db1.addCustomer("Yoshi");
		db1.addCustomer("Yoshi");
		db1.addProduct("Yoshi","p1");
		db1.addProduct("Yoshi","p2");
		db1.addCustomer(line.split(",",2)[0]);
		db1.addProduct(line.split(",",2)[0],line.split(",",2)[1]);
		db1.addProduct("bob", "p1");
		
		
		//Iterator<Customer> iter = db1.iterator();
		System.out.println(db1.size());
		
		Iterator<Customer> iter = db1.iterator();
		while (iter.hasNext()){
			//looking for the customer..
			if (iter.next().getUsername() == "Yoshi"){
				System.out.println(db1.getProducts("Yoshi"));
				System.out.println(db1.getCustomers("p1"));
				//System.out.println("Output:");
				//System.out.println(db1.getProducts(iter.next()));
			}
		}
		
		/**
		File srcFile = new File("sampleinput.txt");
    	Scanner fileIn = new Scanner(srcFile);
    	
    	CustomerDatabase db2 = new CustomerDatabase();
    	
    	while(fileIn.hasNext()){
    		line = fileIn.nextLine();
        	String c = line.split(",",2)[0];
        	String p = line.split(",",2)[1];
        	db2.addCustomer(c);
        	db2.addProduct(c, p);	 //can add all products as one string
    	}
		
    	System.out.println(db2.getProducts("bluesky23"));
    	
    	
    	fileIn.close();
    	
		*/
		
		
		/**
		File srcFile = new File("sampleinput.txt");
    	Scanner fileIn = new Scanner(srcFile);
    	CustomerDatabase db2 = new CustomerDatabase();
    	while(fileIn.hasNext()){
        	line = fileIn.nextLine();
            String c = line.split(",",2)[0];
            System.out.println(c); // testing
            System.out.println(c.equals("1luckydog")); // testing
            db2.addCustomer(c);
            String pline = line.split(",",2)[1];	
    		String[] tokens = pline.split(",");
    		for (int i = 0; i < tokens.length; i++){
    			String p = tokens[i];
    			db2.addProduct(c,p);
    			System.out.println("Customer " + c + " wants " + p);
    		}
    	}
    	
    	
    	
    	
    	fileIn.close();
    	System.out.println(db1.containsCustomer("Yoshi"));
    	System.out.println(db2.containsCustomer("1luckydog"));
    	*/
    	
    	
    	int[] metrics = new int[4];
    	metrics[0] = 4;
    	metrics[1] = 5;
    	metrics[2] = 6;
    	
    	System.out.println(metrics[1]);
    	System.out.println(db1.containsProduct("p1"));
    	System.out.println(db1.containsProduct("p2"));
    	System.out.println(db1.containsProduct("p3"));
    	
    	
    	
    	
	}


	

	

}
