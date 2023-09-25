package model;

public class ImageElement extends TagElement 
{
	private String iURL;
	private int width;
	private int height;
	private String alt;
	
	public ImageElement(String imageURL, int width, int height, String alt, String attributes) //constructor
	{
		super("img",false, null, attributes);
		iURL = imageURL;
		this.width = width;
		this.height = height;
		this.alt = alt;
	}
	
	public String genHTML(int indentation) //prints html for imageElement
	{									   
		return super.getStartTag() + " src=\"" + iURL + "\" width=\"" + width + "\" height=\"" + height + "\" alt=\"" + alt + "\">";
	}
	
	public String getImageURL() //returns url
	{
		return iURL;
	}
}
