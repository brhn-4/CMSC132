package maps;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class ListsAsKeys {
	public static void main(String[] args) {
		HashMap<List<String>, String> favoriteDessertMap = new HashMap<>();

		ArrayList<String> johnSmith = new ArrayList<>();
		johnSmith.add("John");
		johnSmith.add("Smith");
		favoriteDessertMap.put(johnSmith, "Chocolate");

		ArrayList<String> rosePeterson = new ArrayList<>();
		rosePeterson.add("Rose");
		rosePeterson.add("Peterson");
		favoriteDessertMap.put(rosePeterson, "Ice Cream");

		for (List<String> list : favoriteDessertMap.keySet()) {
			System.out.println(list + " " + favoriteDessertMap.get(list));
		}

		/* Retrieving */
		ArrayList<String> temp = new ArrayList<>();
		temp.add("John");
		temp.add("Smith");
		if (favoriteDessertMap.containsKey(temp)) {
			System.out.println("Favorite Dessert John Smith: " + favoriteDessertMap.get(temp));
		}

		/* Notice what happens when we cleared the entry */
		johnSmith.clear();
		System.out.println("Listing Map Contents After Clearing: ");
		for (List<String> list : favoriteDessertMap.keySet()) {
			System.out.println(list + " " + favoriteDessertMap.get(list));
		}
	}
}