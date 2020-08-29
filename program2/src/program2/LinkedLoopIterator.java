package program2;

import java.util.*;

/**
 * LinkedLoopIterator<E> is the type of iterator returned by the LinkedLoop<E>'s 
 * iterator() method. Implements the Iterator<E> interface (but throws 
 * UnsupportedOperationException for removes method). 
 * 
 * <p> Has a package-access constructor is used that takes a DblListnode<E> as a 
 * parameter.  Starts with the current item and proceeds through the items in 
 * the loop once.
 *
 * @author Stefen Showers
 */

public class LinkedLoopIterator<E> implements Iterator<E> {
	//Below are the data members
	private DblListnode<E> currnode;
	private int size;
	private int count;
	
	//Below is the constructor
	LinkedLoopIterator(DblListnode<E> cn, int numItems){
		currnode = cn;
		size = numItems;
		count = 0;
	}
	
	//returns true if there are more total items than have been iterated
	@Override
	public boolean hasNext() {
		return size > count;  //change count to 0 to loop forever on nonempty ll
	}

	@Override
	public E next() throws NoSuchElementException{
		if (!hasNext()){
			throw new NoSuchElementException();
		}
		E item = currnode.getData();
		count++;
		currnode = currnode.getNext();
		return item;
	}

	@Override
	public void remove() throws UnsupportedOperationException{
		throw new UnsupportedOperationException();
	}

}
