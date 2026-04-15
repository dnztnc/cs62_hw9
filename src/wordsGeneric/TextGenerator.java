/**
 * Class to artificially generate sentences
 * 
 * Fix these comments!!
 * @author NAME GOES HERE!!
 **/
package wordsGeneric;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class TextGenerator {

    // symbol table of string pairs and frequency lists
    protected HashMap<StringPair, FreqList> letPairList;

    // add any instance variables needed and a constructor
    StringPair currentPair; // current pair of words
    Random rand; // random number generator
    public TextGenerator() {
        letPairList = new HashMap<StringPair, FreqList>();
        rand = new Random();
    }   

    /** 
     * Add a reference to <first,second>->third to our letPairList
     * @param first string in triad
     * @param second string in triad
     */
    public void enter(String first, String second, String third) {
		// implement TextGenerator.enter()
        StringPair pair = new StringPair(first, second);
        if (!letPairList.containsKey(pair)) {
            letPairList.put(pair, new FreqList());
        }
        letPairList.get(pair).add(third);
    }

    /**
     * Use the <first,second> FreqList to choose a word to follow them
     * @param first String in triad
     * @param second String in triad
     * @return likely third String to follow the first two
	 *
	 * Note: it would also be very good to do something graceful
	 *       if nothing has followed the <first,second> pair.
     */
    public String getNextWord(String first, String second) {
		// implement TextGenerator.getNextWord()
        StringPair pair = new StringPair(first, second);
        if (letPairList.containsKey(pair)) {
            FreqList freqList = letPairList.get(pair);
            double p = rand.nextDouble();
            return freqList.get(p);
        } else {
            return ""; // if pair not found
        }
    }

    // START OF CODE FOR MAIN PROGRAM -- WRITE & FIX COMMENTS
    public static void main(String args[]) {

        WordStream ws = new WordStream();
        JFileChooser dialog = new JFileChooser(".");

        // Display the dialog box and make sure the user did not cancel.
        if (dialog.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            // Find out which file the user selected.
            File file = dialog.getSelectedFile();
            try {
                // Open the file.
                BufferedReader input = new BufferedReader(new FileReader(file));

                // Fill up the editing area with the contents of the file being
                // read.
                String line = input.readLine();
                while (line != null) {
                    ws.addLexItems(line.toLowerCase());
                    System.out.println(line);
                    line = input.readLine();
                }
                System.out.println("Finished reading data");
                // Close the file
                input.close();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Can't load file "
                        + e.getMessage());
            }

			// create TextGenerator, populate it from the WordStream
            TextGenerator tg = new TextGenerator();
            if (ws.hasMoreTokens()) {
                String first = ws.nextToken();
                if (ws.hasMoreTokens()) {
                    String second = ws.nextToken();

                    while (ws.hasMoreTokens()) {
                        String third = ws.nextToken();
                        tg.enter(first, second, third);
                        first = second;
                        second = third;
                    }
                }
            }			// (for debug) print resulting <StringPair,FreqList> map
            for (Map.Entry<StringPair, FreqList> entry : tg.letPairList.entrySet()) {
                System.out.println(entry.getKey() + " -> " + entry.getValue());
            }
			// (for debug) add a few getNextWord test cases
            System.out.println(tg.getNextWord("the", "cat"));
            System.out.println(tg.getNextWord("a", "dog"));
			// pick two starting words
            String first = "how";
            String second = "many";
			// generate 400 words of text, choosing likely random
			//      words to follow each preceding pair.
            System.out.print("Generated data: \n");
            System.out.print(first + " "+second + " ");
            for (int i = 0; i < 400; i++) {
                String nextWord = tg.getNextWord(first, second);
                if (nextWord.equals("")) {
                     // randomly pick a simple word pair to continue from if we get stuck
                    Random random = new Random();
                    int index = random.nextInt(15); // assuming we have 15 simple pairs
                    String[] simpleWords = {"how", "are", "you", "are" , "we" , "is" , "it" , "i", "and", "the", "a", "an", "in", "on", "at" };
                    nextWord = simpleWords[index];
                }
                System.out.print(nextWord + " ");
                if (i % 20 == 19) {
                    System.out.println(); // new line every 20 words for readability
                }
                first = second;
                second = nextWord;
            }
        } else {
            System.out.println("User cancelled file chooser");
        }
    }
}
