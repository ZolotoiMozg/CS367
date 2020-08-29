package program3;

import java.util.*;
import java.io.*;

/**
 * The WordCloudGenerator class generates a word cloud from a command-line
 * specified .txt file.  The maximum number of words to include in the word
 * cloud and any words that should be ignored entirely are both specified as
 * command-line arguments as well.
 * 
 * This is the main class of the program.
 *
 * @author Stefen Showers
 */

public class WordCloudGenerator {
    /**
     * The main method generates a word cloud as described in the program 
     * write-up.  You will need to add to the code given here.
     * 
     * @param args the command-line arguments that determine where input and 
     * output is done:
     * <ul>
     *   <li>args[0] is the name of the input file</li>
     *   <li>args[1] is the name of the output file</li>
     *   <li>args[2] is the name of the file containing the words to ignore 
     *       when generating the word cloud</li>
     *   <li>args[3] is the maximum number of words to include in the word 
     *       cloud</li> 
     * </ul>
     */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = null;         // for input from text file
        PrintStream out = null;    // for output to html file
        Scanner inIgnore = null;   // for input from ignore file
        DictionaryADT<KeyWord> dictionary = new BSTDictionary<KeyWord>();
        int maxWords = 0;

        // Check the command-line arguments and set up input and output files
        
        //Check if exactly 4 command line arguments are found before setting up
        if (args.length == 4){
        	//set up the input file
        	File infile = new File(args[0]);
            if (!infile.exists() || !infile.canRead()) {
                System.err.println("Error: cannot access file " + args[0]);
                System.exit(1);
            }
            in = new Scanner(infile);
            
            //set up the output file
            File outfile = new File(args[1]);
            out = new PrintStream(outfile);
            
            //set up the input ignore file
            File inIgnoreFile = new File(args[2]);
            if (!inIgnoreFile.exists() || !inIgnoreFile.canRead()) {
                System.err.println("Error: cannot access file " + args[2]);
                System.exit(1);
            }
            inIgnore = new Scanner(inIgnoreFile);
            
            //parse args[3] (which is string), to use it as an integer
            try {
            	maxWords = Integer.parseInt(args[3]);
            } catch (NumberFormatException e) {
                System.err.println("Error: maxWords must be a positive "
                		+ "integer");
                System.exit(1);
            }
        } else {
        	//tell the user they need 4 command line arguments
        	System.out.println("Four arguments required: inputFileName "
        			+ "outputFileName ignoreFileName maxWords");
        	return;
        }
        
        // Create the dictionary of words to ignore
        // You do not need to change this code.
        DictionaryADT<String> ignore = new BSTDictionary<String>();
        while (inIgnore.hasNext()) {
            try {
                ignore.insert(inIgnore.next().toLowerCase());
            } catch (DuplicateException e) {
                // if there is a duplicate, we'll just ignore it
            }
        }
        
        // Process the input file line by line
        // Note: the code below just prints out the words contained in each
        // line.  You will need to replace that code with code to generate
        // the dictionary of KeyWords.
        while (in.hasNext()) {
            String line = in.nextLine();
            List<String> words = parseLine(line);
            //for each word that has been parsed from the given line...
            for (String word : words){
            	KeyWord tempkw = new KeyWord(word.toLowerCase());
            	//build out the dictionary of words from input file
            	//Only add words that aren't on the ignore list
            	if (ignore.lookup(word.toLowerCase()) == null){				
            		//use try/catch to handle when to insert and increment
            		try {
                        dictionary.insert(tempkw);
                    } catch (DuplicateException e) {
                    	//if word is in dictionary, increase # of occurrences...
                    	//use an iterator to search for KeyWord in dictionary
                    	Iterator<KeyWord> iter = dictionary.iterator();
                    	//use bool to short circuit once keyword is incremented
                    	boolean quit = false;
                    	while(iter.hasNext() && !quit){
                    		KeyWord tempkwiter = iter.next();
                    		if(tempkwiter.compareTo(tempkw)==0){
                    			tempkwiter.increment();
                    			quit = true;
                    		}
                    	}
            		}
            	}
            } // end for loop
        } // end while loop
        
        //Print out the information about the dictionary:
        //# of keys
        System.out.println("  # keys: " + dictionary.size());
        //average path length
        System.out.println("  avg path length: " 
        		+ (float)dictionary.totalPathLength()/dictionary.size() + ".");	
        //linear average path length
        System.out.println("  linear avg path length: " 
        		+ (float)(1+dictionary.size())/2 + ".");						
        
        //Put the dictionary into this priority queue
        PriorityQueueADT<KeyWord> pq;
        //Priority Queue has to be big enough to hold all words in dictionary
        pq = new ArrayHeap<KeyWord>(dictionary.size());
        //Iterate through dictionary, adding each KeyWord to the priority queue
        Iterator<KeyWord> iter = dictionary.iterator();							
        while(iter.hasNext()){
        	pq.insert(iter.next());
        }
        
        //Use p.q. to create new dictionary of KeyWords of appropriate length
        DictionaryADT<KeyWord>	topWords = new BSTDictionary<KeyWord>();																	
        //loop maxWords times, inserting the removed max value from pq
        for (int i = 0; i < maxWords && !pq.isEmpty(); i++){
        	try{
        		topWords.insert(pq.removeMax());
        	} catch (DuplicateException e){
        		//do nothing if it was a duplicate (which should never happen)
        	}
        }
        
        //Generate the html output file
        generateHtml(topWords, out);											

        // Close everything
        if (in != null) 
            in.close();
        if (inIgnore != null) 
            inIgnore.close();
        if (out != null) 
            out.close();
    }
    
    /**
     * Parses the given line into an array of words.
     * 
     * @param line a line of input to parse
     * @return a list of words extracted from the line of input in the order
     *         they appear in the line
     *         
     * DO NOT CHANGE THIS METHOD.
     */
    private static List<String> parseLine(String line) {
        String[] tokens = line.split("[ ]+");
        ArrayList<String> words = new ArrayList<String>();
        for (int i = 0; i < tokens.length; i++) {  // for each word
            
            // find index of first digit/letter
              boolean done = false; 
              int first = 0;
            String word = tokens[i];
            while (first < word.length() && !done) {
                if (Character.isDigit(word.charAt(first)) ||
                    Character.isLetter(word.charAt(first)))
                    done = true;
                else first++;
            }
            
            // find index of last digit/letter
            int last = word.length()-1;
            done = false;
            while (last > first && !done) {
                if (Character.isDigit(word.charAt(last)) ||
                        Character.isLetter(word.charAt(last)))
                        done = true;
                    else last--;
            }
            
            // trim from beginning and end of string so that is starts and
            // ends with a letter or digit
            word = word.substring(first, last+1);
  
            // make sure there is at least one letter in the word
            done = false;
            first = 0;
            while (first < word.length() && !done)
                if (Character.isLetter(word.charAt(first)))
                    done = true;
                else first++;           
            if (done)
                words.add(word);
        }
        
        return words;
    }
    
    /**
     * Generates the html file using the given list of words.  The html file
     * is printed to the provided PrintStream.
     * 
     * @param words a list of KeyWords
     * @param out the PrintStream to print the html file to
     * 
     * DO NOT CHANGE THIS METHOD
     */
    private static void generateHtml(DictionaryADT<KeyWord> words, 
                                     PrintStream out) {
           String[] colors = { 
                "6F", "6A", "65", "60",
                "5F", "5A", "55", "50",
                "4F", "4A", "45", "40",
                "3F", "3A", "35", "30",
                "2F", "2A", "25", "20",
                "1F", "1A", "15", "10",        
                "0F", "0A", "05", "00" 
                };
           int initFontSize = 80;
           
        // Print the header information including the styles
        out.println("<head>\n<title>Word Cloud</title>");
        out.println("<style type=\"text/css\">");
        out.println("body { font-family: Arial }");
        
        // Each style is of the form:
        // .styleN {
        //      font-size: X%;
        //      color: #YYAA;
        // }
        // where N and X are integers and Y is two hexadecimal digits
        for (int i = 0; i < colors.length; i++)
            out.println(".style" + i + 
                    " {\n    font-size: " + (initFontSize + i*20)
                    + "%;\n    color: #" + colors[i] + colors[i]+ "AA;\n}");
        
        out.println("</style>\n</head>\n<body><p>");        
        
        // Find the minimum and maximum values in the collection of words
        int min = Integer.MAX_VALUE, max = 0;
        for (KeyWord word : words) {
            int occur = word.getOccurrences();
            if (occur > max)
                max = occur;
            else if (occur < min)
                min = occur;
        }

        double slope = (colors.length - 1.0)/(max - min);
        
        for (KeyWord word : words) {
            out.print("<span class=\"style");
            
            // Determine the appropriate style for this value using
            // linear interpolation
            // y = slope *(x - min) (rounded to nearest integer)
            // where y = the style number
            // and x = number of occurrences
            int index = (int)Math.round(slope*(word.getOccurrences() - min));
            
            out.println(index + "\">" + word.getWord() + "</span>&nbsp;");
        }
        
        // Print the closing tags
        out.println("</p></body>\n</html>");
    }
 }