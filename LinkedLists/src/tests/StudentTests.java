package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import org.junit.Test;

import listClasses.BasicLinkedList;
import listClasses.SortedLinkedList;

public class StudentTests {
	
	
//START BASIC LINKED LIST CLASS METHOD TESTS
	
	@Test
	public void size() //testing the getSize method and making sure the size is adjusted appropriately
	{
		BasicLinkedList<Integer> linkedList = new BasicLinkedList<Integer>();
		
		assertTrue(linkedList.getSize() == 0);
		
		linkedList.addToEnd(1);
		
		assertTrue(linkedList.getSize() == 1);
		
		linkedList.addToEnd(2);
		
		assertTrue(linkedList.getSize() == 2);
	}

	@Test
	public void addToEnd() //tests addToEnd by making sure the size and tail are adjusted appropriately
	{
		BasicLinkedList<Integer> linkedList = new BasicLinkedList<Integer>();
		
		assertTrue(linkedList.getSize() == 0);
		
		linkedList.addToEnd(1);
		
		assertTrue(linkedList.getSize() == 1);
		
		linkedList.addToEnd(2);
		
		assertTrue(linkedList.tail.data == 2);	
	}
	
	@Test
	public void addToFront() //tests addToFront by making sure the size and head are adjusted apporpriately
	{
		BasicLinkedList<Integer> linkedList = new BasicLinkedList<Integer>();
		
		linkedList.addToFront(1);
		
		assertTrue(linkedList.head.data == 1);
		
		linkedList.addToFront(2);
		linkedList.addToFront(3);
		
		assertTrue((linkedList.head.data == 3) && (linkedList.tail.data == 1));
	}
	
	@Test
	public void getFirst() //tests GetFirst by making sure the head's data matches what .getFirst() returns
	{
		BasicLinkedList<Integer> linkedList = new BasicLinkedList<Integer>();

		linkedList.addToFront(1);
		linkedList.addToFront(2);
		linkedList.addToFront(3);
		
		assertTrue((linkedList.head.data == 3) && (linkedList.getFirst() == 3));
	}
	
	@Test
	public void getLast() //tests getLast by making sure the tail's data matches what .getLast() returns
	{
		BasicLinkedList<Integer> linkedList = new BasicLinkedList<Integer>();
		
		linkedList.addToFront(1);
		linkedList.addToFront(2);
		linkedList.addToFront(3);
		
		assertTrue((linkedList.tail.data == 1) && (linkedList.getLast() == 1));
	}
	
	@Test
	public void remove() //tests remove by making sure the head and tail pointers are reassigned when items are removed as well as the size being adjusted
	{
		BasicLinkedList<Integer> linkedList = new BasicLinkedList<Integer>();
		
		Comparator<Integer> comparator = new Comparator<Integer>()
		{
			@Override
			public int compare(Integer o1, Integer o2) 
			{
				return o1.compareTo(o2);
			}
		};
		
		linkedList.addToEnd(1);
		linkedList.addToEnd(2);
		linkedList.addToEnd(3);
		linkedList.addToEnd(4);
		linkedList.addToEnd(5);
		linkedList.addToEnd(3);
		
		assertTrue(linkedList.getSize() == 6 && linkedList.head.data == 1 && linkedList.tail.data == 3); //list created properly
		
		linkedList.remove(3, comparator); //removes two elements (testing the loop) and removes a tail
		
		assertTrue(linkedList.getSize() == 4 && linkedList.tail.data == 5); //size adjusted correctly and tail adjusted
		
		linkedList.remove(1, comparator); //removes head to make sure head is reassigned correctly
		
		assertTrue(linkedList.getSize() == 3);
		assertTrue(linkedList.head.data == 2);
		
		linkedList.remove(5, comparator); //removes new tail to check size and tail are adjusted correctly
		
		assertTrue(linkedList.getSize() == 2);
		assertTrue(linkedList.tail.data == 4);
	}
	
	@Test
	public void iterator() //tests iterator by checking if the iterator will print out the contents of the linkedList through a for each loop
	{
		BasicLinkedList<Integer> linkedList = new BasicLinkedList<Integer>();
		
		String answer ="123456"; //hardcoded contents of the list
		String results = ""; //empty string which the iterator will print into
		
		linkedList.addToEnd(1);
		linkedList.addToEnd(2);
		linkedList.addToEnd(3);
		linkedList.addToEnd(4);
		linkedList.addToEnd(5);
		linkedList.addToEnd(6);
		
		for(Integer x: linkedList) //for each loop that uses the iterator to add the contents to results
		{
		 	results += x;
		}
		
		assertTrue(answer.equals(results));
	}
	
	@Test
	public void retrieveFirst() //tests retrieve first by adding elements and seeing fi the head and size are adjusted correctly
	{
		BasicLinkedList<Integer> linkedList = new BasicLinkedList<Integer>();
		
		assertTrue(linkedList.retrieveFirstElement() == null); //basecase
		
		linkedList.addToEnd(1);
		linkedList.addToEnd(2);
		linkedList.addToEnd(3);
		linkedList.addToEnd(4);
		
		assertTrue(linkedList.tail.data == 4 && linkedList.head.data == 1 && linkedList.getSize() == 4); //checks to see if the list was created correctly 
		
		int firstItem = linkedList.retrieveFirstElement(); //temp variable that will store what is returned by .retrieveFristElemtn()
		
		assertTrue(linkedList.head.data == 2); //checks to see if the head is reassigned
		assertTrue(linkedList.getSize() == 3); //checks to see if the size is adjusted
		assertTrue(firstItem == 1); //checks to see if firstItem == the old head
	}
	
	@Test
	public void retrieveLast() //tests retreieve last by checking to see fi the tail and size are adjusted properly and if what is returned is the old tail
	{
		BasicLinkedList<Integer> linkedList = new BasicLinkedList<Integer>();
		
		assertTrue(linkedList.retrieveLastElement() == null); //basecase
		
		linkedList.addToEnd(1);
		linkedList.addToEnd(2);
		linkedList.addToEnd(3);
		linkedList.addToEnd(4);
		
		assertTrue(linkedList.tail.data == 4);
		assertTrue(linkedList.head.data == 1);
		assertTrue(linkedList.getSize() == 4);
		
		int lastItem = linkedList.retrieveLastElement(); //temp variable to hold tail's data
		
		assertTrue(linkedList.tail.data == 3); //check to see if tail is reassigned properly
		assertTrue(linkedList.getSize() == 3); //check to see if size is adjusted
		assertTrue(lastItem == 4); //checks to see if lastItem == old tail
	}
	
	

//END BASIC LINKED LIST CLASS METHOD TESTS
	
//START SORTED LINKED LIST CLASS METHOD TESTS
	
	@Test
	public void testWithException() //tests addToEnd and addToFront making sure they throw the exception
	{								//TA said it was fine to test them both in one test...
		Comparator<String> comparator = new Comparator<String>()
		{
			@Override
			public int compare(String o1, String o2) 
			{
				return o1.compareTo(o2);
			}
		};
		try { //testing addtoFront making sure the exception is thrown
			new SortedLinkedList<String>(comparator).addToFront("Hello");
			fail("No exception thrown.");
		} catch (UnsupportedOperationException ex) {
}
		try { //testing addToEnd making sure the exception is thrown
			new SortedLinkedList<String>(comparator).addToEnd("Hello");
			fail("No exception thrown.");
		} catch (UnsupportedOperationException ex) { }
	}

	
	@Test
	public void sortedAdd() //tests add method, making sure the added item is still in order
	{
		Comparator<Integer> comparator = new Comparator<Integer>()
		{
			@Override
			public int compare(Integer o1, Integer o2) 
			{
				return o1.compareTo(o2);
			}
		};
		SortedLinkedList<Integer> sortedList = new SortedLinkedList<Integer>(comparator);
		
		sortedList.add(3);
		sortedList.add(2);
		
		assertTrue(sortedList.head.data == 2 && sortedList.tail.data == 3);
		
		sortedList.add(1);
		
		assertTrue(sortedList.head.data == 1 && sortedList.head.next.data == 2); //checks to see if the head is adjusted and the list is still in order

		sortedList.add(4);
		
		assertTrue(sortedList.tail.data == 4 && sortedList.head.next.next.data == 3); //checks to see if the tail is adjusted and the list is still in order
	}
	
	@Test
	public void removeFromSorted() //tests remove method by checking to see if the size, head, and tail are adjusted correctly
	{
		Comparator<Integer> comparator = new Comparator<Integer>()
		{
			@Override
			public int compare(Integer o1, Integer o2) 
			{
				return o1.compareTo(o2);
			}
		};
		
		SortedLinkedList<Integer> sortedList = new SortedLinkedList<Integer>(comparator);
		
		assertTrue(sortedList.remove(1,comparator) == null); //empty list, base case
		
		sortedList.add(1);
		sortedList.add(1);
		sortedList.add(2);
		sortedList.add(4);
		sortedList.add(4);
		sortedList.add(3);
		
		assertTrue(sortedList.getSize() == 6 && sortedList.head.data == 1 && sortedList.tail.data == 4);
		
		sortedList.remove(1, comparator); //removes two items from head (testing one of the while loops)
		
		assertTrue(sortedList.getSize() == 4 && sortedList.head.data == 2); //head and size properly adjusted
		
		sortedList.remove(4, comparator); //removes two items from the tail
		
		assertTrue(sortedList.getSize() == 2); //size properly adjusted
		assertTrue(sortedList.tail.data == 3); //tail properly adjusted
		
		sortedList.remove(2, comparator); //remove head
		
		assertTrue(sortedList.getSize() == 1); //size properly adjusted
		assertTrue(sortedList.tail.data == 3 && sortedList.head.data == 3); //tail and head adjusted
	}
	
	@Test 
	public void reverseList() //tests reverse list by making sure the new list == the old list but in reverse order
	{
		BasicLinkedList<Integer> linkedList = new BasicLinkedList<Integer>();
		BasicLinkedList<Integer> reversedList = new BasicLinkedList<Integer>();
		int tempCount = linkedList.getSize();
		
		assertTrue(linkedList.getReverseList() == null);
		
		linkedList.addToEnd(1);
		linkedList.addToEnd(2);
		linkedList.addToEnd(3);
		linkedList.addToEnd(4);
		linkedList.addToEnd(5);
		linkedList.addToEnd(6);
		
		reversedList = linkedList.getReverseList(); //new list
		
		assertTrue(linkedList.getSize() == reversedList.getSize()); //checks the size is the same
		assertTrue(linkedList.head.data == reversedList.tail.data && reversedList.head.data == linkedList.tail.data); //checks if the head of new list is == to tail of old, and tail of new == head of old
		
		while(tempCount > 0) //loop that will check every item of lists against each other
		{
			assertTrue(linkedList.retrieveFirstElement() == reversedList.retrieveLastElement());
			tempCount--;
		}	
	}
	
	@Test
	public void reverseToArray() //tests reverse array list by checking the reversed array list against the reversed link list
	{
		BasicLinkedList<Integer> linkedList = new BasicLinkedList<Integer>();
		BasicLinkedList<Integer> reversedList = new BasicLinkedList<Integer>();
		ArrayList<Integer> reverseArray = new ArrayList<Integer>(); 
		
		assertTrue(linkedList.getReverseArrayList() == null); //basecase
		
		linkedList.addToEnd(1);
		linkedList.addToEnd(2);
		linkedList.addToEnd(3);
		linkedList.addToEnd(4);
		linkedList.addToEnd(5);
		linkedList.addToEnd(6);
		
		reversedList = linkedList.getReverseList(); //temp reversed linkLsit
		reverseArray = linkedList.getReverseArrayList(); //temp reversed arrayList
		
		assertTrue(reverseArray.get(0) == reversedList.head.data && reverseArray.get(reverseArray.size()-1) == reversedList.tail.data); //cheks if first elem of array == to head of the link list and if last elem of array == to tail of link list
		
		for(int x = 0; x < reverseArray.size(); x++) //loops through arraylist
		{
			assertTrue(reverseArray.get(x) == reversedList.retrieveFirstElement()); //checks if every element == the data in the reveresed link list
		}
		
	}
}
