package model;

public class AnchorElement extends TagElement 
{	
	private String link;
	private String url;
	
	 public AnchorElement(String url, String linkText, String attributes)//constructor
	 {
		 super("a", true, null, attributes); 
		 this.url = url;
		 link = linkText;
	 }
	 
	 public String genHTML(int indentation)//prints html for anchorElement
	 {
			String indent = "";
			indent = Utilities.spaces(indentation);
			
			return indent + super.getStartTag() + " href=\"" + url + "\">" + link + super.getEndTag();	
	}

	public String getUrlText()//returns url
	{
		return url;
	}
	
	public String getLinkText() //return link
	{
		return link;
	}
}
