package program4;

import java.util.Arrays;

import java.util.Random;

public class test {

	public static void main(String[] args) {
		String[] intarr = new String[10];
		intarr[0] = "43";
		intarr[1] = "12";
		intarr[2] = "95";
		intarr[3] = "11";
		intarr[4] = "9";
		intarr[5] = "53";
		intarr[6] = "3";
		intarr[7] = "22";
		intarr[8] = "11";
		intarr[9] = "53";
		
		System.out.println(Arrays.toString(intarr));
		ComparisonSort.insertion2Sort(intarr);
		System.out.println(Arrays.toString(intarr));
		
		
		// Create the input array of unsorted objects.
        SortObject[] arr = new SortObject[5000];
        Random random = new Random(43210);
        for (int k = 0; k < 5000; k++)
            arr[k] = new SortObject(random.nextInt());
        
        //System.out.println(Arrays.toString(arr));
        //ComparisonSort.selectionSort(arr);
        //ComparisonSort.insertionSort(arr);
        //ComparisonSort.mergeSort(arr);
        //ComparisonSort.quickSort(arr);
        //ComparisonSort.heapSort(arr);
        //System.out.println(Arrays.toString(arr));
        //ComparisonSort.selection2Sort(arr);
        ComparisonSort.insertion2Sort(arr);
        System.out.println(Arrays.toString(arr));
       
        //check sorted order
        boolean pass = true;
        for (int i = 0; i < arr.length-1; i++ ){
        	if (arr[i].compareTo(arr[i+1]) > 0){
        		pass=false;
        	}
        }
        System.out.println("Is array sorted?: " + pass);
        
        
        
        
        

	}

}
