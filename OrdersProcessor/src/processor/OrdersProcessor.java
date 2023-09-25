package processor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;



public class OrdersProcessor {
	
	
	
	
	public static void main (String[] args)
	{
		Map<String, Double> items = new HashMap<String, Double>(); //map for the items name as a key and price as value
		Map<String, Integer> allItemQuantities = new TreeMap<>(); //map to keep track of the total count of each item from every order for the summary
		Scanner scanner = new Scanner(System.in); //scanner for the user input
		String itemFileName;
		String multiThread;
		String baseFileName;
		String resultFile;
		
		int threadCount = 1;
	
		System.out.println("Enter item's data file name: "); //gather the item data file
		itemFileName = scanner.next();
		
		System.out.println("Enter 'y' for multiple threads, any other character otherwise: "); //usre input for multi thread or not
		multiThread = scanner.next();
	
		System.out.println("Enter number of orders to process: "); //gathers number of orders (threads)
		threadCount = scanner.nextInt();
		
		System.out.println("Enter order's base filename: "); //gathers info to find the file name for orders
		scanner.nextLine();
		baseFileName = scanner.next();
		
		System.out.println("Enter result's filename"); //gathers the result's file name
		resultFile = scanner.next();
		
		long startTime = System.currentTimeMillis(); //finds start time
		
		Scanner itemScanner; //scanner for item file
		
		try 
		{
			itemScanner = new Scanner(new File(itemFileName)); //accessing the item file with scanner
		} 
		catch (FileNotFoundException e1) 
		{
			itemScanner = null;
			e1.printStackTrace();
		}
		
		String itemLine;
		
		while(itemScanner.hasNextLine()) //loops through lines of item file
		{
			itemLine = itemScanner.nextLine();
			items.put(itemLine.split(" ")[0], Double.parseDouble(itemLine.split(" ")[1])); //splits into key of the item name and value of the item price
		}
		
		// get all files based on base name 
		// for loop through all of those
		// for each one create a new OrderThread

		ArrayList<Thread> threads = new ArrayList<Thread>(); //ArrayList of threads
		ArrayList<OrderThread> orderThreads = new ArrayList<OrderThread>(); //ArrayList of orderThreads
		
		for(int x = 1; x<= threadCount; x++) //loop based on user input (thread count) to access proper files and create threads
		{
			String name = baseFileName + x + ".txt"; //creates file name by using base name the thread count and adding .txt
			OrderThread clientOrder = new OrderThread(name, allItemQuantities); //creates new orderThread object (passes in file name and the map for total quantities)
		
			orderThreads.add(clientOrder); //adds that orderThread to the arrayList
			
			if(multiThread.equals("y"))  //checks for multi threading
			{
				Thread thread = new Thread(clientOrder); //creates new thread "thread" for multithreading
				threads.add(thread); //adds "thread" to arraylist
				thread.start(); //starts "thread"
			}	
			else 
			{
				clientOrder.run(); //single thread, just runs the thread (no need for .start())
			}		
		}
		
		if(multiThread.equals("y")) //if multi threading
		{
			for(Thread t : threads)  //loops through every thread in arraylist
			{
				try 
				{ //joins threads together
					t.join();
				} 		
				catch (InterruptedException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	
		try //try + catch for writing to file
		{
			FileWriter fileWriter = new FileWriter(resultFile, false); //creates fileWriter object with the resultFile name as the destination
			double grandTotal = 0.0;
			
			for(OrderThread order: orderThreads) //loops through each orderThread object
			{
				Map<String, Integer> currClient = order.getClientMap(); //current map of items and their counts for a client
				double total = 0.0;
			
				fileWriter.write("----- Order details for client with Id: " +  order.clientId + " -----\n");

				for (String item : currClient.keySet()) //loops through each item in map, printing the name, price, quantity, and cost formatted (use currency format)
				{
					fileWriter.write("Item's name: " + item + ", Cost per item: " + NumberFormat.getCurrencyInstance().format(items.get(item)));
					fileWriter.write(", Quantity: " + currClient.get(item) + ", Cost: ");
					fileWriter.write(NumberFormat.getCurrencyInstance().format(items.get(item)*currClient.get(item)) + "\n");
					total = total + items.get(item)*currClient.get(item); //calculates total
				}
				
				fileWriter.write("Order Total: " + NumberFormat.getCurrencyInstance().format(total) + "\n"); //writes total at the end
			}
			
			//SUMMARY
			fileWriter.write("***** Summary of all orders *****\n");
			
			for(String item: allItemQuantities.keySet()) //loops through each item in the "allItemQuantites" map to print the toal summary foramtted
			{
				fileWriter.write("Summary - Item's name: " + item + ", Cost per item: " + NumberFormat.getCurrencyInstance().format(items.get(item)));
				fileWriter.write(", Number sold: " + allItemQuantities.get(item) + ", Item's Total: ");
				fileWriter.write(NumberFormat.getCurrencyInstance().format(allItemQuantities.get(item)*items.get(item)) + "\n");
				grandTotal = grandTotal + (allItemQuantities.get(item)*items.get(item)); //calculates grand total
			}
			
			fileWriter.write("Summary Grand Total: " + NumberFormat.getCurrencyInstance().format(grandTotal) + "\n");
			fileWriter.close(); //closes scanner
			
			
		}
		catch (IOException e) 
		{
			System.err.println(e.getMessage());
		}
		
		long endTime = System.currentTimeMillis();	//finds end time
	
		System.out.println("Processing time (msec): " + (endTime - startTime)); //calculates run time
	}	
}
		
		
	


