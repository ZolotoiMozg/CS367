package program1;

import java.util.*;
/**
 * The CustomerDatabase class is used to represent a collection of Customer 
 * objects, each of which are made up of a username and wish list.
 * 
 * @author Stefen Showers
 */
public class CustomerDatabase {
	private List<Customer> cdb;         // the customer database   
	private List<String> templist; 		// for getCustuomers and get Products
	/**
     * Constructs an empty customer database.
     */
    public CustomerDatabase()     {
        cdb = new ArrayList<Customer>();
    }
    
    /**
     * Add a customer with the given username c to the end of the database.
     * If a customer with username c is already in the database, just return.
     * 
     * @param (c) this is the customer's username
     * @return void
     */
    void addCustomer(String c){
    	//check for null param
    	if (c == null){
    		throw new IllegalArgumentException();
    	}
    	//use containsCustomer method to check for matches, else add Customer
    	if (cdb.size() > 0){
    		if (this.containsCustomer(c) != true){
        		cdb.add(new Customer(c));
        	}
        	else {
        		return;
        	}
    	}
    	else{
    		cdb.add(new Customer(c));
    	}
    }
    
    /**Add the given product p to the wish list for customer c in the database. 
     * If customer c is not in the database throw a 
     * java.lang.IllegalArgumentException. 
     * If p is already in the wish list for customer c, just return.
     * 
     * @param (c) this is the customer's name
     * @param (p) this is the product's name
     * @return void
     */
    void addProduct(String c, String p){
    	//check for null param
    	if (c == null || p == null || !this.containsCustomer(c)){
    		throw new IllegalArgumentException();
    	}
    	//iterate through cdb until we find where c == cust.getUsername()
    	Iterator<Customer> iter = iterator();
		while (iter.hasNext()){
			Customer cust = iter.next();
			//looking for the customer..
			if (c.equals(cust.getUsername())){
				Iterator<String> iter2 = cust.getWishlist().iterator();
    			while (iter2.hasNext()){
    				if (p.equals(iter2.next())) {
    					return;		//returns if product is found
    				}
    			}
    			cust.getWishlist().add(p);
				//"returns" whether the product was added or not
				return;
			}
		}
		//throw exception if customer doesn't exist
		//throw new java.lang.IllegalArgumentException();
    }
    
    /**
     * Return true iff customer c is in the database.
     * 
     * @param (c) this is the customer's name
     * @return boolean
     */
    boolean containsCustomer(String c){
    	//check for null param
    	if (c == null){
    		throw new IllegalArgumentException();
    	}
    	//iterate through cdb until we find where c == cust.getUsername()
    	if (this.size() > 0){
    		Iterator<Customer> iter = iterator();
    		while (iter.hasNext()){
        		//if match, return true
    			if (c.equals(iter.next().getUsername())){
        			return true;
        		}
    		}
    	}
    	//if no matches, return false
    	return false;
    }
    
    /**
     * Return true iff product p appears in at least one customer's wish list 
     * in the database.
     * 
     * @param (p) this is the product's name
     * @return boolean
     */
    boolean containsProduct(String p){
    	//check for null param
    	if (p == null){
    		throw new IllegalArgumentException();
    	}
    	//iterate through cdb for all customers
    	Iterator<Customer> iter = iterator();
		while (iter.hasNext()){
			//at the first match, return true
			Iterator<String> iter2 = iter.next().getWishlist().iterator();
			while (iter2.hasNext()){
				if (p.equals(iter2.next())){
					return true;
				}
			}
		}

    	//if nothing found...
    	return false;
    }
    
    /**
     * Returns true iff product p is in the wish list for customer c. 
     * If customer c is not in the database, return false.
     * 
     * @param (c) this is the customer's name
     * @param (p) this is the product's name
     * @return boolean
     */
    boolean hasProduct(String c, String p){
    	//check for null param
    	if (c == null || p == null){
    		throw new IllegalArgumentException();
    	}
    	//iterate through cdb until we find where c == cust.getUsername()
    	Iterator<Customer> iter = iterator();
		while (iter.hasNext()){
			Customer cust = iter.next();
    		//if match...
    		if (c.equals(cust.getUsername())){
    			//...check the wishlist contents
    			Iterator<String> iter2 = cust.getWishlist().iterator();
    			while (iter2.hasNext()){
    				if (p.equals(iter2.next())){
    					return true;
    				}
    			}
    		}
		}
    	//if not...
    	return false;
    }
    
    /**
     * Return the list of customers who have product p in their wish list. 
     * If product p is not in the database, return null.
     * 
     * @param (p) this is the product's name
     * @return List<String> of customer usernames
     */
    List<String> getCustomers(String p){
    	//check for null param
    	if (p == null){
    		throw new IllegalArgumentException();
    	}
    	templist = new ArrayList<String>();
    	if (this.containsProduct(p)){
    		//iterate through all customers in cdb
        	Iterator<Customer> iter = iterator();
    		while (iter.hasNext()){
    			Customer cust = iter.next();
    			Iterator<String> iter2 = cust.getWishlist().iterator();
    			while (iter2.hasNext()){
    				if (p.equals(iter2.next())){
    					templist.add(cust.getUsername());
    				}
    			}
    		}
    		return templist;
    	}
    	return null;
    }
    
    /**
     * Return the wish list for the customer c. 
     * If a customer c is not in the database, return null.
     * 
     * @param (c) this is the customer's name
     * @return List<String> of product names in customer's wish list
     */
    List<String> getProducts(String c){
    	//check for null param
    	if (c == null){
    		throw new IllegalArgumentException();
    	}
    	//iterate through cdb until we find where c == cust.getUsername()
    	if (this.containsCustomer(c)){
    		Iterator<Customer> iter = iterator();
    		while (iter.hasNext()){
    			Customer cust = iter.next();
        		//if match...
        		if (c.equals(cust.getUsername())){
        			return cust.getWishlist();
        		}
    		}
    	}
    	return null;
		//if customer not found, return null
    }
    
    /**
     * Return an Iterator over the Customer objects in the database. 
     * The customers should be returned in the order they were added to the 
     * database (resulting from the order in which they are in the text file).
     * 
     * @return new iterator
     */
    public Iterator<Customer> iterator(){
    	return cdb.iterator();
   }
    
    /**
     * Remove customer c from the database. 
     * If customer c is not in the database, return false; 
     * otherwise (i.e., the removal is successful) return true.
     * 
     * @param (c) this is the customer's name
     * @return boolean
     */
    boolean removeCustomer(String c){
    	//check for null param
    	if (c == null){
    		throw new IllegalArgumentException();
    	}
    	if (this.containsCustomer(c)){   //not sure if this IF is necessary
    		//iterate through cdb until we find where c == cust.getUsername()
    		Iterator<Customer> iter = iterator();
    		while (iter.hasNext()){
    			Customer cust = iter.next();
        		//if match...
        		if (c.equals(cust.getUsername())){
        			cdb.remove(cust);
                	return true;
        		}
    		}
    	}
    	return false;
    }
    
    /**
     * Remove product p from the database, 
     * i.e., remove product p from every wish list in which it appears. 
     * If product p is not in the database, return false; 
     * otherwise (i.e., the removal is successful) return true.
     * 
     * @param (p) this is the product's name
     * @return boolean
     */
    boolean removeProduct(String p){
    	//check for null param
    	if (p == null){
    		throw new IllegalArgumentException();
    	}
    	if (this.containsProduct(p)){
    		//iterate through all of cdb
    		Iterator<Customer> iter = iterator();
    		while (iter.hasNext()){
    			Customer cust = iter.next();
        		//check each wishlist for the product
    			if (cust.getWishlist().contains(p)){
    				cust.getWishlist().remove(p);
    			}
        	}
    		return true;
    	}
    	return false;
    }
    
    /**
     * Return the number of customers in this database.
     * 
     * @return int
     */
    int size(){
    	//utilizes the existing size method
    	return cdb.size();
    }
}
