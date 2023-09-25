package model;

public class TagElement implements Element
{
	private String tag;
	private boolean eTag;
	public Element content;
	public String attributes = "";
	private static int id = 1;
	private int myID = 0;
	private static boolean idOn;

	public TagElement(String tagName, boolean endTag, Element content, String attributes)//constructor
	{
		myID = id;
		tag = tagName;
		eTag = endTag;
		this.content = content;
		this.attributes = attributes;
		id++;	
	}
	
	public int getId() //returns id
	{
		return myID;
	}

	public String getStringId() //returns string id
	{
		return tag + " " + myID;
	}
	
	public String getStartTag() //checks if id is on. if it is adds that to the start tag 
	{							//sets attributes to blank if there isnt one and returns a string 
								//with the start tag + formatting
		if(attributes == null) 
		{
			attributes = "";
		}
		else
		{
			attributes = " " + attributes;
		}
		if(tag == "a" || tag == "img") //anchor and image have different tags
			if(idOn) //checks for id to see if it shouod add an id
			{
				return "<" + tag + " id=\"" + tag + myID + attributes + "\"";
			}				

			else
			{
				return "<" + tag + attributes;
			}
		else
			if(idOn)
			{
				return "<" + tag + " id=\"" + tag + myID + "\"" + attributes +">";
			}
				
			else
			{
				return "<" + tag + attributes + ">";
			}
	}
	
	public String getEndTag() //if there is an etag returns it
	{
		if(eTag)
		{
			return "</" + tag + ">";
		}
		else
		{
			return "";
		}
	}
	
	public void setAttributes(String attributes) //sets attributes
	{
		this.attributes = attributes;
	}

	public static void resetIds() //resets id to 1
	{
		id = 1;
		
	}

	public static void enableId(boolean choice) //turns on/off ids
	{
		idOn = choice;
		
	}

	public String genHTML(int indentation)  //prints general html
	{
		{
			return Utilities.spaces(indentation) + getStartTag() + attributes +  getEndTag();
		}
	}

}
