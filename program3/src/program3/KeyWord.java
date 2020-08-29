package program3;

/**
 * This class creates a data type which contains a word and a integer 
 * representing the priority of the associated word, relative to other KeyWords. 
 * 
 * For the purposes of the KeyWord class, a word is a non-empty sequence of 
 * characters in which all the letters have been converted to lower-case.
 * 
 * @author Stefen
 *
 */

public class KeyWord extends java.lang.Object 
implements Comparable<KeyWord>, Prioritizable {
	String w;
	int o;
						
	/**
	 * Constructs a new KeyWord with the given word (converted to lower-case) 
	 * and one occurrence.
	 * 
	 * @param word
	 */
	public KeyWord(String word){
		w = word;
		o = 1;
	}
	
	/**
	 * Gets the word (w) data member for a given KeyWord
	 * 
	 * @return the word for this KeyWord
	 */
	public String getWord(){
		return w;
	}
	
	/**
	 * Gets the occurrences (o) data member for a given KeyWord
	 * 
	 * @return the number of occurrences of this KeyWord
	 */
	public int getOccurrences(){
		return o;
	}
	
	/**
	 * Increments the number of occurrences up by 1 for a given KeyWord
	 */
	public void increment(){
		o++;
	}
	
	/**
	 * Gets the priority (which is the same as occurrences (o)) for a KeyWord
	 * 
	 * @return the priority of this KeyWord
	 */
	public int getPriority() {
		return o;
	}

	/**
	 * Compares the KeyWord to another specified. Two KeyWords are compared by 
	 * comparing the word associated with the two KeyWords, ignoring case 
	 * differences in the names
	 */ 
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(KeyWord other){
		String otherstr = other.getWord();
		String thisstr = this.getWord();
		return thisstr.compareTo(otherstr);
	}
	
	/**
	 * This method uses the compareTo method to decide whether or not two 
	 * KeyWords' word data members are considered "equal".
	 * 
	 * @param other - a second KeyWord to compare to this KeyWord
	 * @return true if both KeyWords have the same word, otherwise false
	 */
	public boolean equals(KeyWord other){
		return compareTo(other)==0;
	}
	



}
