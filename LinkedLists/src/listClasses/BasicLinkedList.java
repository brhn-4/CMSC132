package listClasses;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class BasicLinkedList<T> implements Iterable<T>
{
	public class Node //node class to help implement the linked list
	{ 
		public T data;
		public Node next;

		public Node(T data) 
		{
			this.data = data;
			next = null; 
		}
	}

	public Node head;
	public Node tail;
	public int size;
	
	public BasicLinkedList() //constructor. sets head and tail to null
	{
		 head = null;
		 tail = null;
	}
	
	public BasicLinkedList<T> addToEnd(T data)  //adds T param to a node at the end of the list
	{
		Node newNode = new Node(data);
		
		if(size == 0) //if there is nothing in the list set head and tail = to the new node
			{
				head = newNode;
				tail = newNode;
			}
		
		else //other wise just the new node becomes the new tail
			{
				tail.next = newNode;
				tail = newNode;
			}
		
		size++;
		
		return this;
	}
	
	public BasicLinkedList<T> addToFront(T data) //adds T to a new node at the front of the list
	{
		Node newNode = new Node(data);
		
		if(size == 0) //if there is nothing in the list set head and tail = to the new node
			{
				head = newNode;
				tail = newNode;	
			}
		
		else //otherwise set the new node as the new head
			{	
				newNode.next = head;
				head = newNode;
			}
		
		size++;
	
		return this;
	}
	
	public T getFirst() //returns the data of the head 
	{
		if(head == null) //if no head return null
		{
			return null;
		}
		
		return head.data;
	}
	
	public T getLast() //returns the data of the tail
	{
		if(tail == null) //if no tail return null
		{
			return null;
		}
		
		return tail.data;
	}
	
	public ArrayList<T> getReverseArrayList() //reverses a linked list and puts the linked list into an array list
	{
		ArrayList<T> reverseArray = new ArrayList<T>(); 
		
		if(head == null) //if its empty return null
		{
			return null;
		}
		
		return reverseArrayList(head, reverseArray); //call recursive reverse method
		
	}
	
	public BasicLinkedList<T> getReverseList() //returns a link list in reverse order
	{
		BasicLinkedList<T> reversedList = new BasicLinkedList<T>();
		
		if(head == null) //if its empty return null
		{
			return null;
		}
		
		if(head.next == null) //there is only 1 item in the list return the list
		{
			return this;
		}
		
		return reverse(head, reversedList); //call recursive reverse method
	}
	
	public BasicLinkedList<T> reverse(Node current, BasicLinkedList<T> reversedList) //reverses linked list recursively
	{
		if(current == null) //if the data of param return the list (exit the recursion)
		{
			return reversedList;
		}
		
		else
		{
			reversedList.addToFront(current.data); //add the param data (when you call the method use
											  //retrieveLastElement to start at the end and remove the item)
			return reverse(current.next,reversedList); //recursive call
		}
	}
	
	public ArrayList<T> reverseArrayList(Node current, ArrayList<T> reversedArrayList) //reverses linked list recursively
	{
		if(current == null) //if the data of param return the list (exit the recursion)
		{
			return reversedArrayList;
		}
		
		else
		{
			reversedArrayList.add(0,current.data); //add the param data (when you call the method use
											  //retrieveLastElement to start at the end and remove the item)
			return reverseArrayList(current.next,reversedArrayList); //recursive call
		}
	}
	
	public int getSize() //returns size of link list (size++ antime something is added)
	{
		return size;
	}
	
	public Iterator<T> iterator() //iterates through entire list
	{
		return new Iterator<T>()
		
		{
			private Node temp = head;
			
			public boolean hasNext()
			{
				return temp != null;
			}
			
			public T next()
			{	
				T data = temp.data;
				temp = temp.next;
				return data;		
			}
			
			public void remove() //dont have this method
			{
				throw new UnsupportedOperationException("Remove not implemented.");
	        }
		};
	}
	
	public BasicLinkedList<T> remove(T targetData, Comparator<T> comparator) //removes all instances of target data by using the comparator
	{
		Node temp = head;
		
		if(temp == null) //if the list is empty just return (return null or this?)
		{
			return null;
		}
		
		while(comparator.compare(head.data,targetData) == 0) //checks the head first to see if it is = to param data
		{
			head = head.next;
			temp = head;
			size--;
		}
		
		while(temp != null && temp.next != null) //goes through entire list by adjusting temp. Stops at tail
		{
			if(comparator.compare(temp.next.data, targetData) == 0) //checks for instances of param data
			{
				if(temp.next == tail)
				{
					tail = temp;
				}
				temp.next = temp.next.next; //skips over the param data
				size--;
			} 
			else {
				temp = temp.next;
			}
		}
		
		return this;
	}
	
	
	public T retrieveFirstElement() //returns the data of the first element and removes the node from the list
	{
		if(size == 0) //if list is empty return null
		{
			return null;
		}
		
		else //stores the head's data in a temp node then skips over it, adjusts the size, and returns the data
		{
			Node nodeTemp = head; 
			head = head.next;
			size--;
			
			return nodeTemp.data;
		}
		
	}
	
	public T retrieveLastElement()  //returns the data of the last element and removes the node from the list
	{
		Node oldTail = tail; //temp node of the tail
		
		if(size == 0) //if the list is empty return null
		{
			return null;
		}
		
		if(size == 1)
		{
			head = null;
			tail = null;
			size --;
			
			return oldTail.data;
		}
		
		else  
		{
			Node temp = head;
			
			while(temp.next.next != null) //starts at head then goes through list until it gets to one item before the tail
			{
				temp = temp.next; 
			}
			
			tail = temp; //sets the item one before tail as the new tail
			temp.next = null; //sets the next item to null
			size--; 
		}
		
		return oldTail.data;
	}
}

