package model;

public class TextElement implements Element 
{
	private String text;
	
	public TextElement(String text)//constructor
	{
		this.text = text;
	}
	
	public String genHTML(int indentation)//prints html for textElement
	{
		String output = Utilities.spaces(indentation);
		output += this.text;
		
		return  output;
	}
}
