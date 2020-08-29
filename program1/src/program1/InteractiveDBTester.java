package program1;

import java.util.*;
import java.io.*;
/**
 * The InteractiveDBTester class is used to load a (specially formatted .txt 
 * file and build out a CustomerDatabase object.
 * 
 * It also has various command line options, that allow interaction and 
 * manipulation of the created CustomerDatabase object.
 * 
 * @author Stefen Showers
 */

public class InteractiveDBTester {
    public static void main(String[] args) throws FileNotFoundException {
    	
    	//Load the data from the input file and use it to construct a customer 
    	//database.  Use the .split() string method to read line break and comma 
    	//delimiters
    	
    	//Check whether the input file exists and is readable; if not, error
    	if (args.length == 0){
    		System.out.println("Error: Cannot access input file");
    		return;
    	}
    	if (args.length > 1){
    		System.out.println("Please provide input file as command-line "
    				+ "argument");
    	}
    	File srcFile = new File(args[0]);
    	Scanner fileIn = new Scanner(srcFile);
    	CustomerDatabase db = new CustomerDatabase();
    	while(fileIn.hasNext()){
        	String line = fileIn.nextLine();
        	String c = line;
        	if (line.contains(",")){
        		c = line.split(",",2)[0];
            }
            db.addCustomer(c);
            if (line.contains(",")){
            	String pline = line.split(",",2)[1];
            	String[] tokens = pline.split(",");
        		for (int i = 0; i < tokens.length; i++){
        			String p = tokens[i];
        			db.addProduct(c, p);
        		}
            }	
    	}
    	fileIn.close();
    	
        Scanner stdin = new Scanner(System.in);  // for reading console input
        printOptions();
        boolean done = false;
        while (!done) {
            System.out.print("Enter option ( dfhisqr ): ");
            String input = stdin.nextLine();
            input = input.toLowerCase();  // convert input to lower case

            // only do something if the user enters at least one character
            if (input.length() > 0) {
                char choice = input.charAt(0);  // strip off option character
                String remainder = "";  // used to hold the remainder of input
                if (input.length() > 1) {
                    // trim off any leading or trailing spaces
                    remainder = input.substring(1).trim(); 
                }

                switch (choice) {
                
                /**
                 * If product is not in the database, 
                 * display "product not found". Otherwise, discontinue product 
                 * (i.e., remove the product from all the wish lists in which it 
                 * appears) and display "product discontinued".
                 */
                case 'd':
                	if (db.containsProduct(remainder)){
                		db.removeProduct(remainder);
                		System.out.println("Product discontinued.");
                	}
                	else{
                		System.out.println("Product not found.");
                	}
                    break;

                /**
                 * If customer is not in the database, display "customer not 
                 * found". Otherwise, find customer and display the customer (on 
                 * one line) in the format: customer:product1,product2,product3
                 */
                case 'f':
                    if (db.containsCustomer(remainder)){
                    	System.out.println(remainder + ":" 
                    			+ db.getProducts(remainder));
                    }
                    else{
                    	System.out.println("Customer not found.");
                    }
                    break;
                
                /**
                 * Provide help by displaying the list of command options.
                 */
                case 'h': 
                    printOptions();
                    break;

                /**
                 * Display information about this database
                 */
                case 'i':
                	//build out a full list of unique products in db
                	List<String> plist = new ArrayList<String>();
                	//and a full list of non-unique products in db
                	List<String> fullplist = new ArrayList<String>();
                	Iterator<Customer> citer = db.iterator();
                	while(citer.hasNext()){
                		Iterator<String> piter = 
                				citer.next().getWishlist().iterator();
                		while(piter.hasNext()){
                			String prd = piter.next();
                			if(!plist.contains(prd)){
                				plist.add(prd);
                			}
                			fullplist.add(prd);
                		}
                	}

                	//values in the following are associated by position
                	String[] productnames = new String[plist.size()];
                	int[] customercount = new int[plist.size()];
                	
                	//loop through fullplist, add unique items to productname[i]
                	//add 1 for every product counted to customercount[i];
                	int i=0; 
                	Iterator<String> iter = plist.iterator();
                	while (iter.hasNext()){
                		productnames[i] = iter.next();
                		Iterator<String> iter2 = fullplist.iterator();
                		while (iter2.hasNext()){
                			if (iter2.next().equals(productnames[i])){
                				customercount[i]++;
                			}
                		}
                		i++;
                	}
                	
                	//most # of products per customer
                	int ppcmost = 0; //initial value set to 0
                	//iterate through db, find the max and min wishlist sizes
                	Iterator<Customer> pmiter = db.iterator();
                	while(pmiter.hasNext()){
                		int wlistlen = pmiter.next().getWishlist().size();
                		if (wlistlen > ppcmost){
                			ppcmost = wlistlen;
                		}
                	}
                	
                	//least # of products per customer
                	int ppcleast = ppcmost; //initial value set to max
                	//iterate through db, find the max and min wishlist sizes
                	Iterator<Customer> pliter = db.iterator();
                	while(pliter.hasNext()){
                		int wlistlen = pliter.next().getWishlist().size();
                		if (wlistlen < ppcleast){
                			ppcleast = wlistlen;
                		}
                	}

                	//most number of customers per product
                	int cppmost = 0;
                	if (db.size() > 0){
                		cppmost = customercount[0];
                	}
                	//least number of customers per product
                	int cppleast = 0;
                	if (db.size() > 0){
                		cppleast = customercount[0];
                	}
                	//loop through plist to find largest and smallest numbers
                	//set this equal to cppmost
                	for (int j=0; j < plist.size(); j++){
                		if (customercount[j] > cppmost){
                			cppmost = customercount[j];
                		}
                		if (customercount[j] < cppleast){
                			cppleast = customercount[j];
                		}
                	}
                	
                	//build out list of most popular products (may be multiple)
                	List<String> popprod = new ArrayList<String>();
                	Iterator<String> piter2 = plist.iterator();
                	while(piter2.hasNext()){
                		String prd = piter2.next();
                		if(db.getCustomers(prd).size()==cppmost){
                			popprod.add(prd);
                		}
                	}
                	
                	//calculate average products per customer
                	//will be -1 if div by zero
                	double pcaverage = -1;
                	if (db.size() > 0){
                		pcaverage = (double)plist.size()/db.size();
                	}
                	//and average customers per product
                	//will be -1 if div by zero
                	double cpaverage = -1;
                	if (plist.size() > 0){
                		cpaverage = (double)db.size()/plist.size();
                	}

                	//when dividing by zero products or customers return NA
                	System.out.println("Customers: " + db.size() + ", Products:"
                			+ " " + plist.size());
                    System.out.println("# of products/customer: most " + ppcmost 
                    		+ ", least " + ppcleast + ", average " + pcaverage);
                    System.out.println("# of customers/product: most " + cppmost 
                    		+ ", least " + cppleast + ", average " + cpaverage);
                    System.out.println("Most popular product(s): " + popprod 
                    		+ " [" + cppmost + "]");
                    break;
                    
                /**
                 * If product is not in the database, display "product not 
                 * found". Otherwise, search for product and display the product 
                 * along with the customers who have that product in their wish 
                 * list (on one line) in the format: 
                 * product:customer1,customer2,customer3
                 */
                case 's':
                    if (db.containsProduct(remainder)){
                    	System.out.println(remainder + ":" 
                    			+ db.getCustomers(remainder));
                    }
                    else{
                    	System.out.println("Product not found.");
                    }
                    
                    break;

                /**
                 * Display "quit" and quit the program.
                 */
                case 'q':
                    done = true;
                    System.out.println("quit");
                    break;

                /**
                 * If customer is not in the database, display "customer not 
                 * found". Otherwise, remove customer and display "customer 
                 * removed".
                 */
                case 'r':
                    if (db.containsCustomer(remainder)){
                    	db.removeCustomer(remainder);
                    	System.out.println("Customer removed.");
                    }
                    else{
                    	System.out.println("Customer not found.");
                    }
                    break;

                default:  // ignore any unknown commands
                    break;
                }
            }
        }
        stdin.close();
    }

    /**
     * Prints the list of command options along with a short description of
     * one.  This method should not be modified.
     */
    private static void printOptions() {
        System.out.println("d <product> - discontinue the given <product>");
        System.out.println("f <customer> - find the given <customer>");
        System.out.println("h - display this help menu");
        System.out.println("i - display information about this customer database");
        System.out.println("s <product> - search for the given <product>");
        System.out.println("q - quit");
        System.out.println("r <customer> - remove the given <customer>");
    }
}