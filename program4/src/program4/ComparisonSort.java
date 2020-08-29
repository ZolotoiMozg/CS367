package program4;

import java.util.Arrays;

/**
 * This class implements six different comparison sorts (and an optional
 * seventh sort for extra credit):
 * <ul>
 * <li>selection sort</li>
 * <li>insertion sort</li>
 * <li>merge sort</li>
 * <li>quick sort</li>
 * <li>heap sort</li>
 * <li>selection2 sort</li>
 * <li>(extra credit) insertion2 sort</li>
 * </ul>
 * It also has a method that runs all the sorts on the same input array and
 * prints out statistics.
 */

public class ComparisonSort {
	//set up into for tracking data moves
	private static int dmoves = 0;

    /**
     * Sorts the given array using the selection sort algorithm. You may use
     * either the algorithm discussed in the on-line reading or the algorithm
     * discussed in lecture (which does fewer data moves than the one from the
     * on-line reading). Note: after this method finishes the array is in sorted
     * order.
     * 
     * @param <E>  the type of values to be sorted
     * @param A    the array to sort
     */
    public static <E extends Comparable<E>> void selectionSort(E[] A) {
        int j, k, minIndex;
        E min;
        int N = A.length;

        for (k = 0; k < N; k++) {
        	//one data move for setting min
        	dmoves++;
            min = A[k];
            minIndex = k;
            for (j = k+1; j < N; j++) {
                if (A[j].compareTo(min) < 0) {
                    min = A[j];
                    minIndex = j;
                }
            }
            A[minIndex] = A[k];
            //one data move for setting A[minIndex]
            dmoves++;
            A[k] = min;
            //one data move for setting A[k]
            dmoves++;
        }
    }

    /**
     * Sorts the given array using the insertion sort algorithm. Note: after
     * this method finishes the array is in sorted order.
     * 
     * @param <E>  the type of values to be sorted
     * @param A    the array to sort
     */
    public static <E extends Comparable<E>> void insertionSort(E[] A) {
        int k, j;
        E tmp;
        int N = A.length;
        for (k = 1; k < N; k++) {
        	//one data move for setting tmp
        	dmoves++;
            tmp = A[k];
            j = k - 1;
            while ((j >= 0) && (A[j].compareTo(tmp) > 0)) {
                A[j+1] = A[j]; // move one value over one place to the right
                //one data move for setting A[j+1]
            	dmoves++;
                j--;
            }
            //one data move for setting A[j+1]
        	dmoves++;
            A[j+1] = tmp;    // insert kth value in correct place relative 
                               // to previous values
        }
    }

    /**
     * Sorts the given array using the merge sort algorithm. Note: after this
     * method finishes the array is in sorted order.
     * 
     * @param <E>  the type of values to be sorted
     * @param A    the array to sort
     */
    public static <E extends Comparable<E>> void mergeSort(E[] A) {
        mergeAux(A, 0, A.length - 1); //call the aux function to do all the work
    }
    private static <E extends Comparable<E>> void mergeAux(E[] A, int low, int 
    		high) {
        // base case
        if (low == high) return;
     
        // recursive case
        
     // Step 1: Find the middle of the array (conceptually, divide it in half)
        int mid = (low + high) / 2;
         
     // Steps 2 and 3: Sort the 2 halves of A
        mergeAux(A, low, mid);
        mergeAux(A, mid+1, high);
     
     // Step 4: Merge sorted halves into an auxiliary array
        E[] tmp = (E[])(new Comparable[high-low+1]);
        int left = low;    // index into left half
        int right = mid+1; // index into right half
        int pos = 0;       // index into tmp
         
        while ((left <= mid) && (right <= high)) {
        	// choose the smaller of the two values "pointed to" by left, right
        	// copy that value into tmp[pos]
        	// increment either left or right as appropriate
        	// increment pos

        	    if (A[left].compareTo(A[right]) <= 0) {
        	    	//one data move for setting tmp[pos]
                	dmoves++;
        	        tmp[pos] = A[left];
        	        left++;
        	    }
        	    else {
        	    	//one data move for tmp[pos]
                	dmoves++;
        	        tmp[pos] = A[right];
        	        right++;
        	    }
        	    pos++;
        }
        
        // when one of the two sorted halves has "run out" of values, but
        // there are still some in the other half; copy all the remaining 
        // values to tmp
        // Note: only 1 of the next 2 loops will actually execute
        while (left <= mid) {
        	//one data move for setting tmp[pos]
        	dmoves++;
        	tmp[pos] = A[left];  
        	left++;
        	pos++;
        }
        while (right <= high) {
        	//one data move for setting tmp[pos]
        	dmoves++;
        	tmp[pos] = A[right]; 
        	right++;
        	pos++;
        }  
     
        // all values are in tmp; copy them back into A
        //tmp.length data moves to create new array
        dmoves = dmoves + tmp.length;
        System.arraycopy(tmp, 0, A, low, tmp.length);								
    }

    /**
     * Sorts the given array using the quick sort algorithm, using the median of
     * the first, last, and middle values in each segment of the array as the
     * pivot value. Note: after this method finishes the array is in sorted
     * order.
     * 
     * @param <E>  the type of values to be sorted
     * @param A   the array to sort
     */
    public static <E extends Comparable<E>> void quickSort(E[] A) {
        quickAux(A, 0, A.length-1);
    }
    private static <E extends Comparable<E>> void quickAux(E[] A, int low, int 
    		high) {
        if (high-low < 2) {
        	if (A[low].compareTo(A[high]) > 0){
        		swap(A, low, high);
        	}
        }					    
    	else {
            int right = partition(A, low, high);
            quickAux(A, low, right);
            quickAux(A, right+2, high);
        }
    }   
    /**																			
     * This is a private helper method, only called by the quickSort method.
     * It is used to divide up the array for further sorting.  It also does some
     * of the sorting work (via calls to swap) along the way.
     * 
     * @param <E>  the type of values to be sorted
     * @param A
     * @param low
     * @param high
     * @return an integer representing the location of the pivot
     */
    private static <E extends Comparable<E>> int partition(E[] A, int low, int 
    		high) {
    		// precondition: A.length > 2
    	    E pivot = medianOfThree(A, low, high); // this does step 1
    	    //one data move for setting pivot
    		dmoves++;
    	    int left = low+1, right = high-2;
    	    while ( left <= right ) {
    	        while (A[left].compareTo(pivot) < 0) left++;
    	        while (A[right].compareTo(pivot) > 0) right--;
    	        if (left <= right) {
    	            swap(A, left, right);
    	            left++;
    	            right--;
    	        }
    	    }
    	    swap(A, left, high-1); // step 4
    	    return right;
    	}
    /**																			
     * This is a private helper method, only called by the partition method 
     * (which is itself a helper method of quickSort).  It is used to manually
     * sort values at three positions within an array.
     * 
     * @param <E>  the type of values to be sorted
     * @param A
     * @param low
     * @param high
     * @return the median value, after all values have been sorted
     */
    private static <E extends Comparable<E>> E medianOfThree(E[] A, int left, 
    		int right) {
        int middle = (left + right) / 2;
        //if left is bigger than center, swap
        if (A[left].compareTo(A[middle]) > 0){
        	swap(A, left, middle);
        }
        //if left is bigger than right, swap
        if (A[left].compareTo(A[right]) > 0){
        	swap(A, left, right);
        }
        //if center is bigger than right, swap
        if (A[middle].compareTo(A[right]) > 0){
        	swap(A, middle, right);
        }
        //now middle should be the median value/pivot
        //swap pivot with second value from the end
        swap(A, middle, right - 1);
        //return median value/pivot
        return A[right - 1];
      }
    /**																			
     * This is a private helper method called by quickSort (and its other helper
     * methods).  It's used to directly switch the values in an array at two 
     * specified positions.
     * 
     * @param <E>  the type of values to be sorted
     * @param A
     * @param a
     * @param b
     */
    private static <E extends Comparable<E>> void swap(E[] A, int a, int b){	
        //do nothing if a = b
    	if (a != b){
        	//store value at A[a]
            E temp = A[a];
            //replace A[a] with A[b]'s value
            A[a] = A[b];
            //replace A[b] with A[a]'s value, stored in temp
            A[b] = temp;
            //count 3 data moves for the above three assignments
            dmoves += 3;
        }
    }
 
    /**
     * Sorts the given array using the heap sort algorithm outlined below. Note:
     * after this method finishes the array is in sorted order.
     * <p>
     * The heap sort algorithm is:
     * </p>
     * 
     * <pre>
     * for each i from 1 to the end of the array
     *     insert A[i] into the heap (contained in A[0]...A[i-1])
     *     
     * for each i from the end of the array up to 1
     *     remove the max element from the heap and put it in A[i]
     * </pre>
     * 
     * @param <E>  the type of values to be sorted
     * @param A    the array to sort
     */
    public static <E extends Comparable<E>> void heapSort(E[] A) {
    	//create a new array, which we will populate as a heap
    	E[] arrheap = (E[])(new Comparable[A.length + 1]);
    	//integer for tracking the number of items in arrheap
    	int numItems = 0;														//probably don't need
    	//generate the array heap, but adding all objects in A to arrheap
    	for (E ob : A){
    		//add the object to next open "node" in arrheap
        	arrheap[numItems + 1] = ob;
        	//one data move for setting value in arrheap
        	dmoves++;
        	//restore "order" to the heap if necessary by swapping up
            boolean done = false;
        	for (int i = numItems + 1; i > 1 && !done; i=i/2){
            	if (arrheap[i].compareTo(arrheap[i/2]) > 0){
            		E temp = arrheap[i];
            		arrheap[i] = arrheap[i/2];
            		arrheap[i/2] = temp;
            		//count 3 data moves for the above three assignments
            		dmoves += 3;
            	}
            	else{
            		//used to quit out of for loop if no longer need to swap up
            		done = true;
            	}
            }
            numItems++;
    	}
    	//now replace all values in A, from right to left, by removing the max
    	//value from arrheap
    	while (numItems > 0){
    		//the max value from arrheap is placed back into A, at A[numItems-1]
    		A[numItems-1] = arrheap[1];
    		//one data move for setting value in A
    		dmoves++;
        	//replace max with the right-most leaf at depth D (i.e. last item)
        	arrheap[1] = arrheap[numItems];
        	//one data move for replacing max in arrheap
        	dmoves++;
        	arrheap[numItems] = null;
        	numItems--;
        	//then use the swapDown helper method to swap
        	swapDown(1, arrheap, numItems);
    	}	
    }
    /**
     * This is a helper method called by the heapSort method. It is used to 
     * recursively swap down from the root of the array heap if/as necessary
     * 
     * @param pos - the position within the array of the current object being
     * considered for swapping down
     * @param arr an array implementation of a heap
     * @param numItems the number of items in the array heap
     */
    private static <E extends Comparable<E>> void swapDown(int pos, E[] arr, 
    		int numItems){
    	int p = pos;
    	int l = pos*2;
    	int r = pos*2+1;
    	if (l > numItems){
    		//if there's no left child, there are no children to swap with
    		return;
    	}
    	//if there's no right child, only look at l
    	else if (r > numItems){
    		//if (arr[p].getPriority() >= arr[l].getPriority()){
    		if (arr[p].compareTo(arr[l]) >= 0){
    			return;
    		}
    		E templ = arr[l];
    		arr[l] = arr[p];
    		arr[p] = templ;
    		//count 3 data moves for the above three assignments
    		dmoves += 3;
    		swapDown(l, arr, numItems);
    	}
    	//check if left child is greater than p
    	else if (arr[l].compareTo(arr[p]) > 0){
    		//then check if left child >= right child
    		if (arr[l].compareTo(arr[r]) >= 0){	
        		E templ = arr[l];
        		arr[l] = arr[p];
        		arr[p] = templ;
        		//count 3 data moves for the above three assignments
        		dmoves += 3;
        		swapDown(l, arr, numItems);	
        	}
    		else {
    			//we know l > p, and r > l, so r > p
    			E tempr = arr[r];
        		arr[r] = arr[p];
        		arr[p] = tempr;
        		//count 3 data moves for the above three assignments
        		dmoves += 3;
        		swapDown(r, arr, numItems);	
    		}
    	}
    	//check if right child is greater than p
    	else if (arr[r].compareTo(arr[p]) > 0){
    		//we already know l < p < r
    		E tempr = arr[r];
    		arr[r] = arr[p];
    		arr[p] = tempr;
    		//count 3 data moves for the above three assignments
    		dmoves += 3;
    		swapDown(r, arr, numItems);	
    	}
    	else {
    		//we know that p is >= both l and r
    		return;
    	}
    }
    
    /**
     * Sorts the given array using the selection2 sort algorithm outlined
     * below. Note: after this method finishes the array is in sorted order.
     * <p>
     * The selection2 sort is a bi-directional selection sort that sorts
     * the array from the two ends towards the center. The selection2 sort
     * algorithm is:
     * </p>
     * 
     * <pre>
     * begin = 0, end = A.length-1
     * 
     * // At the beginning of every iteration of this loop, we know that the 
     * // elements in A are in their final sorted positions from A[0] to A[begin-1]
     * // and from A[end+1] to the end of A.  That means that A[begin] to A[end] are
     * // still to be sorted.
     * do
     *     use the MinMax algorithm (described below) to find the minimum and maximum 
     *     values between A[begin] and A[end]
     *     
     *     swap the maximum value and A[end]
     *     swap the minimum value and A[begin]
     *     
     *     ++begin, --end
     * until the middle of the array is reached
     * </pre>
     * <p>
     * The MinMax algorithm allows you to find the minimum and maximum of N
     * elements in 3N/2 comparisons (instead of 2N comparisons). The way to do
     * this is to keep the current min and max; then
     * </p>
     * <ul>
     * <li>take two more elements and compare them against each other</li>
     * <li>compare the current max and the larger of the two elements</li>
     * <li>compare the current min and the smaller of the two elements</li>
     * </ul>
     * 
     * @param <E>  the type of values to be sorted
     * @param A    the array to sort
     */
    public static <E extends Comparable<E>> void selection2Sort(E[] A) {
    	int j, k, minIndex, maxIndex;
        E currmin, currmax;
        int N = A.length;
        for (k = 0; k < N/2; k++) {
            currmin = A[k];
            currmax = A[N-k-1];
            minIndex = k;
            maxIndex = N-k-1;
            //two data moves for setting currmin and currmax
            dmoves+=2;
            //check the initial beginning and end values and swap if necessary
            if (A[k].compareTo(A[N-k-1]) > 0){
            	E tmp = A[k];
        		A[k] = A[N-k-1];
        		A[N-k-1] = tmp;
            }	
            for (j = k+1; j <= N/2; j++) {
            	//check the beginning and end values and swap if necessary
            	if (A[j].compareTo(A[N-j]) > 0) {
            		E tmp = A[j];
            		A[j] = A[N-j];
            		A[N-j] = tmp;
            	}
                if (A[j].compareTo(currmin) < 0) {
                    currmin = A[j];
                    minIndex = j;
                }
                if (A[N-j].compareTo(currmax) > 0) {
                	currmax = A[N-j];
                	maxIndex = N-j;
                }
            }
            A[minIndex] = A[k];
            A[maxIndex] = A[N-k-1];
            //two data moves for setting A[minIndex] and A[maxIndex]
            dmoves+=2;
            A[k] = currmin;
            A[N-k-1] = currmax;
            //two data moves for setting A[k] and A[N-k-1]
            dmoves+=2;
        }	
    }

    /**
     * <b>Extra Credit:</b> Sorts the given array using the insertion2 sort 
     * algorithm outlined below.  Note: after this method finishes the array 
     * is in sorted order.
     * <p>
     * The insertion2 sort is a bi-directional insertion sort that sorts the 
     * array from the center out towards the ends.  The insertion2 sort 
     * algorithm is:
     * </p>
     * <pre>
     * precondition: A has an even length
     * left = element immediately to the left of the center of A
     * right = element immediately to the right of the center of A
     * if A[left] > A[right]
     *     swap A[left] and A[right]
     * left--, right++ 
     *  
     * // At the beginning of every iteration of this loop, we know that the elements
     * // in A from A[left+1] to A[right-1] are in relative sorted order.
     * do
     *     if (A[left] > A[right])
     *         swap A[left] and A[right]
     *  
     *     starting with with A[right] and moving to the left, use insertion sort 
     *     algorithm to insert the element at A[right] into the correct location 
     *     between A[left+1] and A[right-1]
     *     
     *     starting with A[left] and moving to the right, use the insertion sort 
     *     algorithm to insert the element at A[left] into the correct location 
     *     between A[left+1] and A[right-1]
     *  
     *     left--, right++
     * until left has gone off the left edge of A and right has gone off the right 
     *       edge of A
     * </pre>
     * <p>
     * This sorting algorithm described above only works on arrays of even 
     * length.  If the array passed in as a parameter is not even, the method 
     * throws an IllegalArgumentException
     * </p>
     *
     * @param  A the array to sort
     * @throws IllegalArgumentException if the length or A is not even
     */    
    public static <E extends Comparable<E>> void insertion2Sort(E[] A) { 
    	//throw exception if A has odd number of elements
    	if (A.length % 2 == 1){
    		throw new IllegalArgumentException();
    	}

    	int k, j, l;
    	E tmp;
        int left = (A.length)/2 - 1;
        int right = (A.length)/2;
        int N = A.length;
    	
    	//switch the middle two values if necessary
        if (A[left].compareTo(A[right]) > 0){
        	tmp = A[left];
        	A[left] = A[right];
        	A[right] = tmp;
        }

        //move out one place left and right from the center two elements
        left--;
        right++;

        for (k = 1; k < N/2; k++){
        	//switch the current "left" and "right" elements if necessary
            if (A[left].compareTo(A[right]) > 0){
            	tmp = A[left];
            	A[left] = A[right];
            	A[right] = tmp;
            }
        	//starting with with A[right] and moving to the left, use insertion sort 
            //algorithm to insert the element at A[right] into the correct location 
            //between A[left+1] and A[right-1]
            tmp = A[right];
            //j = k - 1;
            j = right - 1;
            while ((j >= left + 1) && (A[j].compareTo(tmp) > 0)) {
                A[j+1] = A[j]; // move one value over one place to the right
                //one data move for setting A[j+1]
            	dmoves++;
                j--;
            }
            //one data move for setting A[j+1]
        	dmoves++;
            A[j+1] = tmp;    // insert right value in correct place relative 
                               // to previous values
            
            //starting with A[left] and moving to the right, use the insertion sort 
            //algorithm to insert the element at A[left] into the correct location 
            //between A[left+1] and A[right-1]
            tmp = A[left];
            //j = N - k + 1;
            j = left + 1;
            while ((j < right - 1) && (A[j].compareTo(tmp) < 0)) {
                A[j-1] = A[j]; // move one value over one place to the left
                //one data move for setting A[j+1]
            	dmoves++;
                j++;
            }
            //one data move for setting A[j+1]
        	dmoves++;
            A[j-1] = tmp;    // insert left value in correct place relative 
                               // to previous values
            left--;
            right++;
        }
    }

    /**
     * Internal helper for printing rows of the output table.
     * 
     * @param sort          name of the sorting algorithm
     * @param compares      number of comparisons performed during sort
     * @param moves         number of data moves performed during sort
     * @param milliseconds  time taken to sort, in milliseconds
     */
    private static void printStatistics(String sort, int compares, int moves,
                                        long milliseconds) {
        System.out.format("%-23s%,15d%,15d%,15d\n", sort, compares, moves, 
                          milliseconds);
    }
    /**
     * Sorts the given array using the six (seven with the extra credit)
     * different sorting algorithms and prints out statistics. The sorts 
     * performed are:
     * <ul>
     * <li>selection sort</li>
     * <li>insertion sort</li>
     * <li>merge sort</li>
     * <li>quick sort</li>
     * <li>heap sort</li>
     * <li>selection2 sort</li>
     * <li>(extra credit) insertion2 sort</li>
     * </ul>
     * <p>
     * The statistics displayed for each sort are: number of comparisons, 
     * number of data moves, and time (in milliseconds).
     * </p>
     * <p>
     * Note: each sort is given the same array (i.e., in the original order) 
     * and the input array A is not changed by this method.
     * </p>
     * 
     * @param A  the array to sort
     */
    static public void runAllSorts(SortObject[] A) {
    	//Print the column headers
        System.out.format("%-23s%15s%15s%15s\n", "algorithm", "data compares", 
                          "data moves", "milliseconds");
        System.out.format("%-23s%15s%15s%15s\n", "---------", "-------------", 
                          "----------", "------------");
        //Selection Sort
        //save off array A so we can reuse with every sort
        SortObject[] selectionA = new SortObject[A.length];
        System.arraycopy( A, 0, selectionA, 0, A.length );
        //reset the compares and data moves counters
        SortObject.resetCompares();
        dmoves = 0;
        long selectionStart = System.currentTimeMillis();
        //call selectionSort
        selectionSort(selectionA);
        //print statistics for this sort
        System.out.format("%-23s%15s%15s%15s\n", "selection", 
        		SortObject.getCompares(), dmoves, System.currentTimeMillis() - 
        		selectionStart);
        
        //Insertion Sort
        //save off array A so we can reuse with every sort
        SortObject[] insertionA = new SortObject[A.length];
        System.arraycopy( A, 0, insertionA, 0, A.length );
        //reset the compares and data moves counters
        SortObject.resetCompares();
        dmoves = 0;
        long insertionStart = System.currentTimeMillis();
        //call insertionSort
        insertionSort(insertionA);
        //print statistics for this sort
        System.out.format("%-23s%15s%15s%15s\n", "insertion", 
        		SortObject.getCompares(), dmoves, System.currentTimeMillis() - 
        		insertionStart);
        
        //Merge Sort
        //save off array A so we can reuse with every sort
        SortObject[] mergeA = new SortObject[A.length];
        System.arraycopy( A, 0, mergeA, 0, A.length );
        //reset the compares and data moves counters
        SortObject.resetCompares();
        dmoves = 0;
        long mergeStart = System.currentTimeMillis();
        //call mergeSort
        mergeSort(mergeA);
        //print statistics for this sort
        System.out.format("%-23s%15s%15s%15s\n", "merge", 
        		SortObject.getCompares(), dmoves, System.currentTimeMillis() - 
        		mergeStart);
        
        //Quick Sort
        //save off array A so we can reuse with every sort
        SortObject[] quickA = new SortObject[A.length];
        System.arraycopy( A, 0, quickA, 0, A.length );
        //reset the compares and data moves counters
        SortObject.resetCompares();
        dmoves = 0;
        long quickStart = System.currentTimeMillis();
        //call quickSort
        quickSort(quickA);													    
        //print statistics for this sort
        System.out.format("%-23s%15s%15s%15s\n", "quick", 
        		SortObject.getCompares(), dmoves, System.currentTimeMillis() - 
        		quickStart);
        
        //Heap Sort
        //save off array A so we can reuse with every sort
        SortObject[] heapA = new SortObject[A.length];
        System.arraycopy( A, 0, heapA, 0, A.length );
        //reset the compares and data moves counters
        SortObject.resetCompares();
        dmoves = 0;
        long heapStart = System.currentTimeMillis();
        //call heapSort
        heapSort(heapA);														
        //print statistics for this sort
        System.out.format("%-23s%15s%15s%15s\n", "heap", 
        		SortObject.getCompares(), dmoves, System.currentTimeMillis() - 
        		heapStart);
        
        //Selection Sort 2
        //save off array A so we can reuse with every sort
        SortObject[] selection2A = new SortObject[A.length];
        System.arraycopy( A, 0, selection2A, 0, A.length );
        //reset the compares and data moves counters
        SortObject.resetCompares();
        dmoves = 0;
        long selection2Start = System.currentTimeMillis();
        //call selection2Sort
        selection2Sort(selection2A);											
        //print statistics for this sort
        System.out.format("%-23s%15s%15s%15s\n", "selection2", 
        		SortObject.getCompares(), dmoves, System.currentTimeMillis() - 
        		selection2Start);
        
        //optional...
        //Insertion Sort 2
        //save off array A so we can reuse with every sort
        SortObject[] insertion2A = new SortObject[A.length];
        System.arraycopy( A, 0, insertion2A, 0, A.length );
        //reset the compares and data moves counters
        SortObject.resetCompares();
        dmoves = 0;
        long insertion2Start = System.currentTimeMillis();
        //call insertion2Sort
        insertion2Sort(insertion2A);											
        //print statistics for this sort
        System.out.format("%-23s%15s%15s%15s\n", "insertion2", 
        		SortObject.getCompares(), dmoves, System.currentTimeMillis() - 
        		insertion2Start);
    }
}