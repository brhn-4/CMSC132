package model;

public class HeadingElement extends TagElement
{
	private int level;
	
	public HeadingElement(Element content, int level, String attributes) //constructor
	{
		super("h" + level , true, null, attributes);
		this.level = level;
		this.content = content;
	}
	
	public String genHTML(int indentation)//prints html for headingElement
	{
		return super.getStartTag() + content.genHTML(indentation) + " " + attributes + getEndTag();
	}
}
