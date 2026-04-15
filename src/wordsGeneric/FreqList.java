/**
 * Frequency List of word/# of occurrence pairs
 * @author Deniz Tanaci
 **/
 
package wordsGeneric;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class FreqList {
    // list of associations holding words and their frequencies
    protected HashMap<String, Integer> flist;
    int totalCount = 0; // total number of references in this list (sum of counts)
	/**
	 * constructor
	 */
    public FreqList() {
		// implement FreqList constructor
          flist = new HashMap<String, Integer>();


    }
    
    /** 
     * Add a reference to a word to our list
     * 
     * @param word ... reference to add
     */
    public void add(String word) {
		// implement FreqList.add()
        if (flist.containsKey(word)) {
            flist.put(word, flist.get(word) + 1);
        } else {
            flist.put(word, 1);
        }
        totalCount++;
    }

    /**
     * return a word from the FreqList based on a 0-1 value
     * 
     * @param p ... number between 0 and 1
     * @return a String from the list of recorded words
     * 		the probability of returning a word should be directly
     *		proportional to the number of times it has been referenced.
     */
    public String get(double p) {
		// implement FreqList.get()
        double cumulativeProbability = 0.0;
        for (String word : flist.keySet()) {
            cumulativeProbability += (double) flist.get(word) / totalCount;
            if (p < cumulativeProbability) {
                return word;
            }
        }
        return ""; // if p is not between 0 and 1
    }

    /**
     * String representation of the list
     * @return a string containing keys and counts
     * 			e.g. "the=4, story=2, ..."
     */
    public String toString() {
		// implement FreqList.toString()
    String result = "Frequency List: ";
      for (String word : flist.keySet()) {
          result += "<" + word + "=" + flist.get(word) + ">";
      }
    	return result;
    }
    
    /**
     * static method to test this class
     * 
     * Suggested tests:
     * 		instantiate a list
     * 		add references to it
     * 		print the list to confirm correct reference counts
     * 		call p multiple times to confirm distribution of results
     */
    public static void main(String args[]) {
		// implement FreqList.main tester
        FreqList freqList = new FreqList();
        freqList.add("the");
        freqList.add("the");
        freqList.add("story");
        freqList.add("story");
        freqList.add("is");
        System.out.println(freqList.toString());

        // Test get method
        int trials = 10000;
        HashMap<String, Integer> results = new HashMap<String, Integer>();
        for (int i = 0; i < trials; i++) {
            String word = freqList.get(Math.random());
            results.put(word, results.getOrDefault(word, 0) + 1);
        }
        System.out.println("Distribution of results after " + trials + " trials:");
        for (String word : results.keySet()) {
            System.out.println(word + ": " + results.get(word));

        }//of our 10000 attemps, we should have approximately 4000 the, 4000 story, and 2000 is
    }
}
