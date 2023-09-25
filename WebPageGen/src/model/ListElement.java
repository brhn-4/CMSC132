package model;

import java.util.ArrayList;

public class ListElement extends TagElement
{	
	private boolean ordered;
	private String attributes;
	private ArrayList<Element> elementList;
	
	public ListElement(boolean ordered, String attributes) //constructor
	{
		super(ordered?"ol":"ul", true, null, attributes);
		this.ordered = ordered;
		this.attributes = attributes;	
		this.elementList = new ArrayList<Element>();
	}
	
	public void addItem(Element item) //adds item to elementList
	{
		elementList.add(item);
	}
	
	public String genHTML(int indentation) //prints html for element
	{
		String spacing = Utilities.spaces(indentation); 
		String elems = "";
			
		for(int i = 0; i < elementList.size(); i++) //loops through elements adding them to string
		{
			elems += spacing + Utilities.defaultSpaces(1) + "<li>\n" + spacing + Utilities.defaultSpaces(2) + elementList.get(i).genHTML(0) + "\n" + spacing + Utilities.defaultSpaces(1) + "</li>\n";
		}
				
		return spacing + super.getStartTag() +"\n" + elems + spacing + super.getEndTag();
	}
}
 