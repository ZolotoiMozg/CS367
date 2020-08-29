package program2;

import java.util.*;

/**
 * LinkedLoop<E> class implements the Loop<E> interface. In addition to methods 
 * given in the Loop<E> interface, LinkedLoop<E> class has a constructor that 
 * takes no arguments and creates an empty loop.
 * 
 * <p> The internal data structure is a circular, doubly-linked chain of nodes 
 * (using the DblListnode<E> class to represent the nodes).  It has a field of
 * type DblListnode<E> that references the node representing the current item 
 * and another field of type int that references the total number of items.
 *
 * @author Stefen Showers
 */

public class LinkedLoop<E> implements Loop<E>{
	private DblListnode<E> curr;
	private int numItems;
	
	/**
     * Constructs a new empty LinkedLoop.
     */
    public LinkedLoop() {
    	curr = null;
    	numItems = 0;
    }
    
    /**
     * Returns the current item.  If the Loop is empty, an 
     * <tt>EmptyLoopException</tt> is thrown.
     * 
     * @return the current item
     * @throws EmptyLoopException if the Loop is empty
     */
	@Override
	public E getCurrent() throws EmptyLoopException{
		if (numItems < 1){
			throw new EmptyLoopException();
		}
		return curr.getData();
	}
	
	/**
     * Adds the given <tt>item</tt> immediately <em>before</em> the current 
     * item.  After the new item has been added, the new item becomes the 
     * current item.
     * 
     * @param item the item to add
     */
	@Override
	public void insert(E item) {
		DblListnode<E> newcurr = new DblListnode<E>(item);
		if (numItems > 0){
			newcurr.setPrev(curr.getPrev());
			newcurr.setNext(curr);
			curr.getPrev().setNext(newcurr);
			curr.setPrev(newcurr);
			curr = newcurr;
		}
		else{
			curr = newcurr;
			curr.setNext(curr);
			curr.setPrev(curr);
		}
		numItems++;
	}
	
	 /**
     * Removes and returns the current node's data.  The item immediately 
     * <em>after</em> the removed item then becomes the  current item.  
     * If the Loop is empty initially, an 
     * <tt>EmptyLoopException</tt> is thrown.
     * 
     * @return the removed item
     * @throws EmptyLoopException if the Loop is empty
     */
	@Override
	public E removeCurrent() throws EmptyLoopException{
		if (numItems < 1){
			throw new EmptyLoopException();
		}
		E rtn = curr.getData();
		if (numItems > 1){
			curr.getNext().setPrev(curr.getPrev());
			curr.getPrev().setNext(curr.getNext());
			curr = curr.getNext();
		}
		if (numItems == 1){
			curr = null;
		}
		numItems --;
		return rtn;
		
	}

	/**
     * Advances the current item forward one item resulting in the item 
     * that is immediately <em>after</em> the current item becoming the  
     * current item.  If the Loop is empty initially, an 
     * <tt>EmptyLoopException</tt> is thrown.
     *
     * @throws EmptyLoopException if the Loop is empty
     */
	@Override
	public void forward() throws EmptyLoopException{
		if (numItems < 1){
			throw new EmptyLoopException();
		}
		curr = curr.getNext();
	}

	/**
     * Moves the current item backward one item resulting in the item 
     * that is immediately <em>before</em> the current item becoming the  
     * current item.  If the Loop is empty initially, an 
     * <tt>EmptyLoopException</tt> is thrown.
     *
     * @throws EmptyLoopException if the Loop is empty
     */
	@Override
	public void backward() throws EmptyLoopException{
		if (numItems < 1){
			throw new EmptyLoopException();
		}
		curr = curr.getPrev();
	}

	/**
     * Returns the number of items in this Loop.
     * @return the number of items in this Loop
     */
	@Override
	public int size() {
		return numItems;
	}

	/**
     * Returns true if this Loop is empty and false otherwise.
     * @return true if this Loop is empty; false otherwise.
     **/
	@Override
	public boolean isEmpty() {
		if (numItems > 0){
			return false;
		}
		return true;
	}

	
	@Override
	public Iterator<E> iterator() {
        return new LinkedLoopIterator(curr, numItems);
	}





	
	
}
