package model;

public class TableElement extends TagElement
{
	private int rows;
	private int cols;
	private double usedCells;
	private Element tableRays[][];
	
	public TableElement(int rows, int cols, String attributes)//constructor
	{
		super("table", true, null, attributes);
		this.rows = rows;
		this.cols = cols;
	    this.tableRays = new Element[this.rows][this.cols];
	}
	
	public void addItem(int rowIndex, int colIndex, Element item)//adds item to 2D array
	{
		tableRays[rowIndex][colIndex] = item;
		usedCells++;
	}
	
	public String genHTML(int indentation) //prints html for tableElement
	{
		
		String spacing = Utilities.spaces(indentation);
		String insideTable = "";
		
		for(int i = 0; i < rows; i++) //loops through rows
		{
			insideTable +=  "\n" + spacing + Utilities.spaces(Utilities.DEFAULT_INDENTATION) + "<tr>";
			for(int j = 0; j < cols; j++) //loops through cols
			{
				insideTable += "<td>"; //adds tag in front of element
 				if(tableRays[i][j] != null)
				{
					insideTable += tableRays[i][j].genHTML(0);//prints element html into string
				}
				 insideTable += "</td>";//adds tag after html
			}																					 
			insideTable += "</tr>"; //tag after the row
		}
			
		return spacing + getStartTag() + insideTable + "\n" + spacing + getEndTag(); 
	}
	
	public double getTableUtilization() //decimal for the amount of used cells (used over total)
	{
		return usedCells/(rows*cols);	
	}

}
