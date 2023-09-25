package maps;

import java.util.*;

public class Course {
	private Map<Integer, Set<String>> allSectionsMap = new HashMap<>();

	public void addStudent(Integer sectionNumber, String name) {
		Set<String> sectionSet = allSectionsMap.get(sectionNumber);

		if (sectionSet == null) {
			sectionSet = new TreeSet<>();
			allSectionsMap.put(sectionNumber, sectionSet);
		}
		sectionSet.add(name);
	}

	public boolean removeStudent(String name) {
		for (Integer sectionNum : allSectionsMap.keySet()) {
			Set<String> sectionSet = allSectionsMap.get(sectionNum);
			
			if (sectionSet.contains(name)) {
				sectionSet.remove(name);
				if (sectionSet.isEmpty()) {
					allSectionsMap.remove(sectionNum);
				}
				return true;
			}
		}

		return false;
	}

	public void printAllStudents() {
		for (Integer sectionNum : allSectionsMap.keySet()) {
			Set<String> sectionSet = allSectionsMap.get(sectionNum);
			
			for (String name : sectionSet) {
				System.out.println(name);
			}
		}
	}

	public static void main(String[] args) {
		Course course = new Course();

		course.addStudent(201, "Jose");
		course.addStudent(101, "Mary");
		course.addStudent(101, "Kelly");
		course.printAllStudents();
	}
}