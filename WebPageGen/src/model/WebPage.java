package model;

import java.util.ArrayList;

public class WebPage implements Comparable<WebPage>
{
	private ArrayList<Element> webElems;  
	private String title;
	
	public WebPage(String title) //constructor and creates array list
	{
		this.title = title;
		this.webElems = new ArrayList<Element>();
	}
	
	public int addElement(Element element) //returns id of element just added, if it is a text element returns -1
	{
		webElems.add(element);
		if(element instanceof TagElement)
		{
			return ((TagElement)element).getId();
		}	
		
		return -1;
	}
	
	public int compareTo(WebPage webPage)//compares id's
	{
	  return (this.title).compareTo(webPage.title);
	}
	
	public Element findElem(int id) //returns element with the id passed in
	{
		for(int i = 0; i < webElems.size(); i++) //loops through elements
		{
			if(webElems.get(i) instanceof TagElement) //check that its not a txt elem
			{
				if(((TagElement)webElems.get(i)).getId() == id) //checks to see if the elem has the same id as the param
				{
					return webElems.get(i);
				}
			}
		}
		return null;//null for txtElem	
	}
	
	public String getWebPageHTML(int indentation) //prints html for web page [hardcode]
	{
		String spacing = Utilities.spaces(indentation);
		String webPageHTML = spacing + "<head>\n" + spacing + spacing + "<meta charset=\"utf-8\"/>\n" + spacing + spacing;
		webPageHTML += "<title>" + title + "</title>\n" + spacing + "</head>\n";
		webPageHTML += spacing + "<body>\n";
		
		for(int i = 0; i < webElems.size(); i++)
		{
			
			webPageHTML += spacing + spacing + webElems.get(i).genHTML(indentation) + "\n";
		}
		
		webPageHTML += "   </body>";
		
		return  "<!doctype html>\n<html>\n" + webPageHTML + "\n</html>";
	}
	
	public String stats()
	{
		int listCNT = 0;
		int tableCNT = 0;
		int paraCNT = 0;
		double tableUtilizationTotal = 0.0;
		double tableAvg = 0.0;
		String stats;
		
		for(int i = 0; i < webElems.size(); i++)
		{
			if(webElems.get(i) instanceof ListElement)
			{
				listCNT++;
			}
			if((webElems.get(i) instanceof TableElement))
			{
				tableCNT++;
				tableUtilizationTotal += ((TableElement)webElems.get(i)).getTableUtilization();
			}
			if(webElems.get(i) instanceof ParagraphElement)
			{
				paraCNT++;
			}
			
			tableAvg = 100*tableUtilizationTotal/tableCNT;
		}
		stats = "List Count: " + listCNT + "\nParagraph Count: " + paraCNT + "\nTable Count: " + tableCNT + "\nTableElement Utilization: " + tableAvg;
		
		return stats;	
	}
	
	public static void enableId(boolean choice)
	{
		TagElement.enableId(choice);
	}
	
	public void writeToFile(String filename,int indentation) 
	{
		Utilities.writeToFile(filename, getWebPageHTML(indentation));
	}
}
