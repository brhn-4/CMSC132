package listClasses;

import java.util.Comparator;



public class SortedLinkedList<T> extends BasicLinkedList<T>
{
	protected Comparator<T> comparator;
	
	public SortedLinkedList(Comparator<T> comparator) //cosntructor. calls super 
	{
		super();
		this.comparator = comparator;
	}
	
	public SortedLinkedList<T> add(T element) //adds t param in the right order so the list is still sorted
	{
		Node newNode = new Node(element); //new node with param data
		Node index = head;
	
		if(size == 0) //if list is empty set head and tail = to new node
		{
			head = newNode;
			tail = newNode;
			size++;
			
			return this;
		}
		
		if(size == 1) //if there is only 1 item, order those two items
		{
			
			if(comparator.compare(element, index.data)>0)
			{
				index.next = newNode;
				tail = newNode;
				size++;
				
				return this;
			}
			
			else 
			{
				newNode.next = index;
				head = newNode;
				tail = index;
				size++;
				
				return this;
			}
		}
		
		while(index.next != null) //loops through entire list
		{
			if(comparator.compare(element, index.data)<0) //check if the new node should be the first item (smaller than head)
			{
				head = newNode;
				newNode.next = index;
				size++;
				
				return this;
			}
			
			if(comparator.compare(element, index.data)>0 && comparator.compare(element, index.next.data)<0) //checks to see if the item before is smaller and the item after is bigger
			{
				newNode.next = index.next; //if so adds the node with param data
				index.next = newNode;
				size ++;
				
				return this;
			}
			
			if(comparator.compare(element, index.data) == 0) //checks to see if an item is == to the param (duplicate data)
			{
				newNode.next = index.next; //if so adds the node with param data
				index.next = newNode;
				size++;
				
				return this;	
			}
						
			index = index.next;
		}
		
		index.next = newNode; //if the param doesnt go at the front and doesnt go between any elements it must go at the end
		tail = newNode;
		size++;
		
		return this;
	}
	
	public BasicLinkedList<T> addToEnd(T data) //cannot add a node to the end of a sorted list (it will no longer be sorted)
	{
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	
	public BasicLinkedList<T> addToFront(T data) //cannot add a node to the front of a sorted list (it will no longer be sorted)
	{
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	
	public SortedLinkedList<T> remove(T targetData) //calls super to remove any instances of a node with target data
	{
		super.remove(targetData, comparator);
		
		return this;
	}
} 