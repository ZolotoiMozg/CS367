package program3;

import java.util.*;

/**
 * BSTDictionaryIterator implements an iterator for a binary search tree
 * implementation of a Dictionary (BSTDictionary).  The iterator iterates over 
 * the tree in order of the key values (from smallest to largest).
 * 
 * @author Stefen Showers
 * 
 */
public class BSTDictionaryIterator<K> implements Iterator<K> {	
	//Below are the data members
	private BSTnode<K> currnode, parent; //current node and parent of current
	private int size;	//total number of objects to iterate over
	private int count;	//current number of iterations
	private Stack<BSTnode<K>> stk; //used to push/pop next values for iteration
	
	//Below is the constructor
	BSTDictionaryIterator(BSTnode<K> root, int numItems){
		currnode = root;
		parent = root;
		size = numItems;
		count = 0;
		stk = new Stack<BSTnode<K>>();
		//start by pushing the root to the top of the stack
		stk.push(root);
		//traverse the left-most branch of the tree, adding progressively 
		//smaller and smaller values to the stack, until the smallest is on top
		while(currnode.getLeft() != null){
			stk.push(currnode.getLeft());
			parent = currnode;
			currnode = currnode.getLeft();
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.util.Iterator#hasNext()
	 */
    public boolean hasNext() {
    	return count < size;
    }
    
    /*
     * (non-Javadoc)
     * @see java.util.Iterator#next()
     */
    public K next() {
    	if (!hasNext()){
			throw new NoSuchElementException();
		}
    	//store node to be returned while we queue up the _next_ node
    	BSTnode<K> rtn = stk.pop();
    	
    	//if currnode.getRight exists, set currnode = currnode.getRight, then 
    	//traverse all the way down left-most path (if exists) to find next node
    	if (rtn.getRight() != null){
    		stk.push(rtn.getRight());
    		currnode = rtn.getRight();
    		while(currnode.getLeft() != null){
    			stk.push(currnode.getLeft());
    			parent = currnode;
    			currnode = currnode.getLeft();
    		}
    	} 
    	//else the next node will be the parent (already on top of the stack)
    	else {
    		currnode = parent;
    	}
    	
    	count++;
        return rtn.getKey();
    }
    
    /*
     * (non-Javadoc)
     * @see java.util.Iterator#remove()
     */
    public void remove() {
        // DO NOT CHANGE: you do not need to implement this method
        throw new UnsupportedOperationException();
    }    
}