package processor;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class OrderThread implements Runnable 
{
	Thread orderThread;
	String fileName; 
	String resultsFileName;
	Map<String, Integer> allItemQuantities; //map of items and their total count from each thread
	Map<String,Integer> itemList = new TreeMap<String,Integer>(); //map of items and their count (tree map to sort)
	public int clientId;

	public OrderThread(String fileName, Map<String, Integer> allItemQuantities) //constructor
	{
		this.fileName = fileName;
		this.allItemQuantities = allItemQuantities;
	}

	@Override
	public void run() //
	{	
		Scanner scanner; //creates scanner
		
		try 
		{
			scanner = new Scanner(new File(fileName)); //scanner accessing file
		} 
		catch (FileNotFoundException e)
		{
			scanner = null;	
			e.printStackTrace();
		}
		
		String fileLine = scanner.nextLine(); //current line of file
		clientId = Integer.parseInt(fileLine.split(" ")[1]); //gets the client id from first line
		
		while(scanner.hasNext()) //loops through every line
		{
			fileLine = scanner.nextLine(); //moves current line down 1 line
			String item = fileLine.split(" ")[0]; //splits line to get the item name
			
			if(itemList.containsKey(item)) //if the item already occurs in the map of items for the client
			{
				int count = itemList.get(item) + 1; //add 1 to the count of that item
				itemList.replace(item,count);  //update value
			}
			else
			{
				itemList.put(item,1); //otherwise add the item to the map with count == 1
			}
			
			synchronized(allItemQuantities) //synchronize block for the grand total map
			{
				if(allItemQuantities.containsKey(item)) //if the item already occurs in the map of grand totals for items
				{
					int count = allItemQuantities.get(item) + 1; //add 1 to the count of that item
					allItemQuantities.replace(item,count); //update value
				}
				else
				{
					allItemQuantities.put(item,1); //otherwise add the item to the map with count == 1
				}		
			}	
		}	
		
		scanner.close(); //closes scanner
	}
		
	public Map<String,Integer> getClientMap() //returns itemList
	{
		return itemList;
	}

}
