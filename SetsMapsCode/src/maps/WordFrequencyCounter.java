/*
 * Example of using maps to keep track of instances of words.  
 * Run using command line argument.
 * 
 */
package maps;

import java.util.Map;
import java.util.TreeMap;

public class WordFrequencyCounter {
	public static void main(String[] args) {
		Map<String, Integer> map = new TreeMap<>();
		
		for (String word : args) {
			if (!map.containsKey(word)) {
				map.put(word, 1); /* First instance seen */
			} else {
				map.put(word, map.get(word) + 1);
			}
		}
		
		System.out.println("Words Frequency:\n");
		for (String word : map.keySet()) {
			System.out.println(word + "\t" + map.get(word));
		}
	}
}