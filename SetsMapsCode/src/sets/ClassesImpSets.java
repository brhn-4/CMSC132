/*
 * Example showing some set examples.
 */
package sets;

import java.util.*;

public class ClassesImpSets {

	public static void main(String[] args) {
		System.out.println("************* HashSet test *************");
		test(new HashSet<>());

		System.out.println("************* TreeSet test *************");
		test(new TreeSet<>());

		System.out.println("************* LinkedHashSet test *************");
		test(new LinkedHashSet<>());
	}

	public static void test(Set<String> set) {
		/* Simple set */
		set.add("Tom");
		set.add("Frank");
		set.add("Beth");

		/* We could have used a for loop */
		System.out.println("***** Set Contents:");
		Iterator<String> iterator = set.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}

		/* Membership test */
		if (set.contains("Frank")) {
			System.out.println("Frank found");
		}

		if (set.contains("Laura")) {
			System.out.println("Laura found");
		}

		/* containsAll test */
		ArrayList<String> elements = new ArrayList<>();
		elements.add("Frank");
		elements.add("Kathy");
		String answer = "All";
		if (!set.containsAll(elements)) {
			answer = "Not all";
		}
		System.out.println(answer + " elements in the arraylist are in the set.");

		/* Union of elements */
		set.addAll(elements);
		System.out.println("After the union: " + set);
	}
}