package model;

import java.util.ArrayList;


public class ParagraphElement extends TagElement
{
	private ArrayList<Element> paraElems;
	
	public ParagraphElement(String attributes) //constructor
	{
		super("p", true, null, attributes);
		this.paraElems = new ArrayList<Element>();
	}
	
	public void addItem(Element item)//adds element to arrayList
	{
		paraElems.add(item);
	}
	
	public String genHTML(int indentation) //need array list (can access indentation in genHTML 
	{									   //where we print the elements
		String indent = Utilities.spaces(indentation);
		String items = "";
		
		for(int i = 0; i < paraElems.size(); i++) //loops through array printing the 
		{										  //elements into items
			items += paraElems.get(i).genHTML(indentation) + "\n";
		}
		
		return indent + super.getStartTag() + items + super.getEndTag();
	}

}
