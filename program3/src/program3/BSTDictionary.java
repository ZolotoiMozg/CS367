package program3;

import java.util.Iterator;

/**
 * The BSTDictionary class implements the DictionaryADT interface using a binary 
 * search tree of BSTnodes.  This class represents a Binary Search Tree of 
 * BSTnode<K> objects.
 * 
 * This class performs the insert, delete, and lookup operations with a 
 * worst-case complexity of O(height of BST).
 * 
 * @author Stefen Showers
 */

public class BSTDictionary<K extends Comparable<K>> implements DictionaryADT<K>{
    private BSTnode<K> root;  // the root node
    private int numItems;     // the number of items in the dictionary

    /**
     * Constructs a new empty BSTDictionary
     */
    public BSTDictionary(){
    	root = null;
    	numItems = 0;
    }
    
    /**  
     * This method is used to insert a new BSTnode<K> into BSTDictionary, given
     * some object K.  It will throw a  DuplicateException if a comparable 
     * BSTnode<K> already exists.
     * 
     * @param key - a generic object for which there will be a new BSTnode<K> 
     * inserted into the BSTDictionary 
     * 
     * @return void 
     */
    public void insert(K key) throws DuplicateException {
    	if (lookup(key)==key){
    		throw new DuplicateException();
    	}
    	numItems++;
    	if (root==null){
    		root = new BSTnode<K>(key);
    		return;
    	} else {
    		//call helper method to recursively find a place to insert
        	insertHelper(key, root, key.compareTo(root.getKey()));
        	return;
    	}
    }
    /**  
     * This is a private helper method which is only called by insert (above).  
     * It is used to recursively search the underlying Binary Search Tree to 
     * find an appropriate parent node and appropriately chooses setLeft or 
     * setRight for that parent node.
     * 
     * @assumes the BSTnode doesn't exist in the BSTDictionary.
     * 
     * @param key - a generic object for which there will be a BSTnode<K> 
     * inserted into the BSTDictionary
     * @param start - the start location to begin searching
     * @param lor - the integer result of key.compareTo(start), decides whether
     * to insert/recursively search at the left or right child of start
     * 
     * @return void 
     */
    private void insertHelper(K key, BSTnode<K> start, int lor){
    	if(lor < 0){
    		if (start.getLeft()==null){
    			start.setLeft(new BSTnode<K>(key));
    		}
    		else {
    			insertHelper(key, start.getLeft(), 
    					key.compareTo(start.getLeft().getKey()));
    		}
    	}
    	if (lor > 0){
    		if (start.getRight()==null){
    			start.setRight(new BSTnode<K>(key));
    		}
    		else {
    			insertHelper(key, start.getRight(), 
    					key.compareTo(start.getRight().getKey()));
    		}
    	}
    }
    
    /**  
     * This method is used to delete a BSTnode<K> from BSTDictionary, given 
     * some object K.
     * 
     * @param key - a generic object for which there will be a BSTnode<K> 
     * deleted from the BSTDictionary
     * 
     * @return true if key was deleted, false if not 
     */
    public boolean delete(K key) {
    	if (lookup(key)==null){
    		return false;
    	}
    	root = deleteHelper(root, key);
    	return true;
    } 
    /**  
     * This is a private helper method which is only called by delete (above).  
     * It is used to recursively search the underlying Binary Search Tree to 
     * find the node we're trying to delete.  It then deletes the node.
     * 
     * @assumes the BSTnode exists in the BSTDictionary
     * 
     * @param key - a generic object for which there will be a BSTnode<K> 
     * inserted into the BSTDictionary
     * @param n - the node that will be removed
     * 
     * @return the node that has been removed (if successful)
     */
    private BSTnode<K> deleteHelper(BSTnode<K> n, K key) {
        if (n == null) {
            return null;
        }
        
        if (key.equals(n.getKey())) {
            // n is the node to be removed
            if (n.getLeft() == null && n.getRight() == null) {
                return null;
            }
            if (n.getLeft() == null) {
                return n.getRight();
            }
            if (n.getRight() == null) {
                return n.getLeft();
            }
           
            // if we get here, then n has 2 children
            K smallVal = smallest(n.getRight());
            n.setKey(smallVal);
            n.setRight( deleteHelper(n.getRight(), smallVal) );
            return n; 
        }
        
        else if (key.compareTo(n.getKey()) < 0) {
            n.setLeft( deleteHelper(n.getLeft(), key) );
            return n;
        }
        
        else {
            n.setRight( deleteHelper(n.getRight(), key) );
            return n;
        }
    }
    /**
     * This is a helper method to the deleteHelper method (and so by 
     * transitivity, it is a helper method to the delete method).  It is used to
     * find the smallest value in a Binary Search Tree rooted at n, which is
     * then used to replace the key deleted by the delete method
     * 
     * @assumes n is not null
     * 
     * @param n
     * @return the smallest value in the subtree rooted at n
     */
    private K smallest(BSTnode<K> n) {
    	if (n.getLeft() == null) {
    		return n.getKey();
    	} else {
    		return smallest(n.getLeft());
    	}
    }
    
    /**  
     * This method which is used to search for a BSTnode<K> for some specified 
     * object K.  
     * It is used by the insert and delete methods to verify whether or not a
     * key already has a BSTnode in BSTDictionary.
     * 
     * @param key - a generic object to look for in BSTDictionary
     * 
     * @return the key searched for if it's found (otherwise null if not found)
     */
    public K lookup(K key) {
    	//if param is null, return null and do nothing
    	if(key == null){
    		return null;
    	}
    	else if (root == null){
    		return null;
    	}
    	else{
    		return lookupHelper(key, root, key.compareTo(root.getKey()));
    	}
    }
    /**  
     * This is a private helper method which is only called by insert (above).  
     * It is used to recursively search the underlying Binary Search Tree to 
     * find a node for the specified key.
     * 
     * <p> Bug: In the places indicated below (lines 219 and 235), I wasn't
     * able to make compareTo work. An error is thrown in BSTDictionaryIterator.
     * However, equals works, so that is used instead.
     * 
     * @param key - the key of the node we're looking for
     * @param start - the start location to begin searching
     * @param lor - the integer result of key.compareTo(start); decides whether
     * we've found the node or whether to continue searching from the left or 
     * right child of start
     * 
     * @return the key of the node that has been found (if successful)
     */
    private K lookupHelper (K key, BSTnode<K> start, int lor){
    	if (lor == 0){
    		return key;
    	}
    	//if the key is in left subtree start
    	else if(lor < 0){
    		if (start.getLeft()==null){								
    			return null;
    		}
    		//bug here(?): comparTo causes problems with BSTDictionaryIterator
    		//if (start.getLeft().getKey().compareTo(key)==0){ //commented out
    		//equals works though
    		if (start.getLeft().getKey().equals(key)){			    
    			return start.getLeft().getKey();
    		}
    		else {
    			return lookupHelper(key, start.getLeft(), 
    					key.compareTo(start.getLeft().getKey()));
    		}
    	}
    	//if key is in right subtree of start
    	else if(lor > 0){
    		if (start.getRight()==null){
    			return null;
    		}
    		//bug here(?): comparTo causes problems with BSTDictionaryIterator
    		//if (start.getRight().getKey().compareTo(key)==0){	//commented out
    		//equals works though
    		if (start.getRight().getKey().equals(key)){				
    			return start.getRight().getKey();
    		}
    		else {
    			return lookupHelper(key, start.getRight(), 
    					key.compareTo(start.getRight().getKey()));
    		}
    	}
    	//should never reach this point
    	else{
    		return null;
    	}
    }
    
    /**
     * This method returns whether or not the BSTDictionary is empty.
     * 
     * @return true if BSTDictionary has no nodes, false otherwise
     */
    public boolean isEmpty() {
        return root==null;
    }

    /**
     * This method returns the numItems data member value.
     * 
     * @return the number of nodes/keys in the BSTDictionary
     */
    public int size() {
        return numItems;
    }
    
    /**  
     * This method is used to calculate the total path length of the Binary
     * Search Tree underlying BSTDictionary.
     * 
     * @return the total path length of the BSTDictionary's Binary Search Tree
     * If there are no objects in BSTDictionary, it returns -1.
     */
    public int totalPathLength() {
    	//returns a -1 if there are no items
    	if (root == null){
    		return -1;
    	} else{
    		return tplHelper(root, 0);
    	}
    }
    /**  
     * This is a private helper method which is only called by totalPathLength 
     * (above).  It is used to recursively sum the lengths of paths from every
     * node to the root.
     * 
     * @assumes that the root is not null
     * 
     * @param n - starting node to begin calculating the total path length from
     * @param depth - the integer value of the depth for the given node n
     * 
     * @return the total path length of the BSTDictionary's Binary Search Tree
     */
    private int tplHelper (BSTnode<K> n, int depth){
    	int cnt = 0;
    	if (n.getLeft() != null){
    		cnt = cnt + tplHelper(n.getLeft(), depth + 1);
    	}
    	if (n.getRight() != null){
    		cnt = cnt + tplHelper(n.getRight(), depth + 1);
    	}
    	return cnt + depth;
    }
    
    /**
     * This method is used to construct a new BSTDictionaryIterator object, 
     * which is used to iterate over the BSTDictionary
     * 
     * @return the newly constructed iterator
     */
    public Iterator<K> iterator() {
        return new BSTDictionaryIterator<K>(root, numItems);
    }
}