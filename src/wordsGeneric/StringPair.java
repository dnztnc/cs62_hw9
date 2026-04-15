package wordsGeneric;
/**
 * Class to represent a pair of strings, used as keys in our symbol table
 * @author Deniz Tanaci
*/
import java.util.HashMap;
import java.util.Objects;
public class StringPair {
    // StringPair should represent a pair of two strings. 
    // Filled in the constructor 
    private String first;
    private String second;

    public StringPair(String first, String second) {
        this.first = first;
        this.second = second;
    }

    public String getFirst() {
        return first;
    }

    public String getSecond() {
        return second;
    }
    //and override the toString method that will return something like <string_one,string_two>
    @Override
    public String toString() {
        return "<" + first + "," + second + ">";
    }
    // Because we will be using StringPair objects in HashMaps you will also have to implement equals and hashCode methods
    // (look for shortcut 1 in lecture slides) that do their comparisons and computations based on both of the string values.
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        StringPair other = (StringPair) obj;
        return first.equals(other.first) && second.equals(other.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
    //use Objects.hash()for all fields (except arrays)



public static void main(String[] args) {
// create a main() method that tests them by:
//creating multiple StringPair objects, for the same and different pairs of strings.
//ensuring your equals method correctly identifies matching and non-matching pairs. Don't forget that String equality is tested with equals not ==.
//creating a <StringPair,String> HashMap.
//populating it with mappings for many different StringPairs.
//confirming that you can find and update those mappings.
StringPair pair1 = new StringPair("hello", "world");
StringPair pair2 = new StringPair("hello", "world");
StringPair pair3 = new StringPair("goodbye", "world");

System.out.println(pair1); // Output <hello,world>
System.out.println(pair1.equals(pair2)); // Output true
System.out.println(pair1.equals(pair3)); // Output false

HashMap<StringPair, String> map = new HashMap<>();

map.put(pair1, "value1");
map.put(pair3, "value2");

// retrieve using "same" key (pair2 should work!)
System.out.println(map.get(pair2)); // should print "value1"

// update
map.put(pair2, "updated");
System.out.println(map.get(pair1)); // should print "updated"
}}
