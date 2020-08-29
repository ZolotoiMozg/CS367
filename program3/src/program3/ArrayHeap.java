package program3;

import java.util.NoSuchElementException;

/**
 * This class implements the PriorityQueueADT interface using an array-based 
 * implementation of a max heap. In addition to the methods specified in the 
 * PriorityQueueADT interface, the ArrayHeap class provides two constructors: a 
 * default (no argument) constructor and a constructor that takes an initial 
 * size (an integer) for the underlying array. The ArrayHeap class compares 
 * elements in the heap using the values returned by getPriority.
 *
 * @author Stefen Showers
 */

public class ArrayHeap<E extends Prioritizable> implements PriorityQueueADT<E> {

    // default number of items the heap can hold before expanding
    private static final int INIT_SIZE = 100;
    //underlying array used for the heap
    private E[] arr;
    //the number of items in the array heap
    private int numItems;
    
    /**
     * No-argument constructor that constructs a heap whose underlying array
     * has enough space to store INIT_SIZE items before needing to expand.
     */
    public ArrayHeap(){
    	arr = (E[])(new Prioritizable[INIT_SIZE + 1]);
    	numItems = 0;
    }
    
    /**
     * A 1-argument constructor that takes an integer parameter and 
     * constructs a heap whose underlying array has enough space to store the 
     * number of items given in the parameter before needing to expand.  If
     * the parameter value is less 0, an IllegalArgumentException is thrown.
     * 
     * @param size - initial number of items array can store before expanding
     */
    public ArrayHeap(int size){
    	if (size < 0){
    		throw new IllegalArgumentException();
    	}
    	arr = (E[])(new Prioritizable[size + 1]);
    	numItems = 0;
    }
    
    /*
     * (non-Javadoc)
     * @see PriorityQueueADT#isEmpty()
     */
    public boolean isEmpty() {
    	return numItems == 0;
    }
    
    /*
     * (non-Javadoc)
     * @see PriorityQueueADT#insert(Prioritizable)
     */
    public void insert(E item) {
        //add the item to next open node
    	arr[numItems + 1] = item;
    	//restore "order" to the heap if necessary by swapping up
        for (int i = numItems + 1; i > 1; i=i/2){
        	if (arr[i].getPriority() > arr[i/2].getPriority()){
        		E temp = arr[i];
        		arr[i] = arr[i/2];
        		arr[i/2] = temp;
        	}
        }
        numItems++;
    }
    
    //helper method for swapping down
    //used by removeMax
    /**
     * This is a helper method called by the removeMax method. It is used to 
     * recursively swap down from the root of the ArrayHeap if/as necessary
     * 
     * @param pos - the position within the array of the current object being
     * considered for swapping down
     */
    private void swapDown(int pos){
    	int p = pos;
    	int l = pos*2;
    	int r = pos*2+1;
    	if (l > numItems){
    		return;
    	}
    	else if (r > numItems){
    		if (arr[p].getPriority() >= arr[l].getPriority()){
    			return;
    		}
    		E templ = arr[l];
    		arr[l] = arr[p];
    		arr[p] = templ;
    		swapDown(l);
    	}
    	//if parent has greatest (or equal) priority to children, just return
    	else if (arr[p].getPriority() >= arr[l].getPriority() 
    			&& arr[p].getPriority() >= arr[r].getPriority()){
    		return;
    	}
    	//if left child has greatest priority (favors left to right), swap and 
    	//call swapDown again
    	else if (arr[l].getPriority() > arr[p].getPriority() 
    			&& arr[l].getPriority() >= arr[r].getPriority()){
    		E templ = arr[l];
    		arr[l] = arr[p];
    		arr[p] = templ;
    		swapDown(l);	
    	}
    	//if right child has greatest priority, swap and call swapDown again
    	else if (arr[r].getPriority() > arr[p].getPriority() 
    			&& arr[r].getPriority() > arr[l].getPriority()){
    		E tempr = arr[r];
    		arr[r] = arr[p];
    		arr[p] = tempr;
    		swapDown(r);	
    	}
    }
    
    /*
     * (non-Javadoc)
     * @see PriorityQueueADT#removeMax()
     */
    public E removeMax() {
    	if (numItems == 0){
    		throw new NoSuchElementException();
    	}
    	//store for returning
    	E temp = arr[1];
    	//replace max with the right-most leaf at depth D (i.e. last item)
    	arr[1] = arr[numItems];
    	arr[numItems] = null;
    	numItems--;
    	//then use the swapDown helper method to swap
    	swapDown(1);
        return temp;
    }
    
    /*
     * (non-Javadoc)
     * @see PriorityQueueADT#getMax()
     */
    public E getMax() {
    	if (numItems == 0){
    		throw new NoSuchElementException();
    	}
        return arr[1];
    }
    
    /*
     * (non-Javadoc)
     * @see PriorityQueueADT#size()
     */
    public int size() {
        return numItems;
    }
}