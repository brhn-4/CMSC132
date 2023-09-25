package model;

import java.util.Random;

/* This class must extend Game */
public class ClearCellGame extends Game 
{
	
	protected Random random;
	protected int strategy;
	protected int score;
	
	public ClearCellGame(int maxRows, int maxCols, Random random, int strategy) //constructor
	{
		super(maxRows,maxCols);
		this.random = random;
		this.strategy = strategy;
		score = 0;
	}
	
	public int getScore() //returns score of the game
	{
		return score;
	}
	
	public void nextAnimationStep() //shifts current cells down 1 row and adds a new row of random
	{								//cells if the game is not over
		if(isGameOver())
		{
			return;
		}
		
		for(int x = maxRows-1; x > 0 ; x--) 
		{
			for(int y = 0; y < maxCols; y++)
			{
				board[x][y] = board[x-1][y];
			}
		}
		
		for(int y = 0; y < maxCols; y++)
		{
			board[0][y] = BoardCell.getNonEmptyRandomBoardCell(random);
		}	
	}
	
	public void processCell(int rowIndex, int colIndex) //clear cell and continues clearing similar cells as well as adjusting score
	{													//reset the continuation index after continuing in a direction
		BoardCell cellTemp = board[rowIndex][colIndex];
		cellTemp.getColor();
		board[rowIndex][colIndex] = BoardCell.EMPTY;
		int contIndex = 1;
		score++;
		
		//Continuing left
		while(colIndex - contIndex >= 0 && (board[rowIndex][colIndex - contIndex]).getColor() == cellTemp.getColor())
		{
			board[rowIndex][colIndex - contIndex] = BoardCell.EMPTY;
			
			contIndex++;
			score++;
		}
		contIndex = 1;
		
		//Continuing right
		while(contIndex + colIndex < maxCols && (board[rowIndex][colIndex + contIndex]).getColor() == cellTemp.getColor())
		{
			board[rowIndex][colIndex + contIndex] = BoardCell.EMPTY;
			
			contIndex++;
			score++;
		}
		contIndex = 1;
		
		//Continuing up
		while(rowIndex - contIndex >= 0 && (board[rowIndex - contIndex][colIndex]).getColor() == cellTemp.getColor())
		{
			board[rowIndex - contIndex][colIndex] = BoardCell.EMPTY;
			
			contIndex++;
			score++;
		}
		contIndex = 1;
		
		//Continuing down
		while(rowIndex + contIndex < maxRows && (board[rowIndex + contIndex][colIndex]).getColor() == cellTemp.getColor())
		{
			board[rowIndex + contIndex][colIndex] = BoardCell.EMPTY;
			
			contIndex++;
			score++;
		}
		contIndex = 1;
		
		//Continuing up right
		while(((rowIndex - contIndex >= 0)&&(colIndex + contIndex < maxCols))&& (board[rowIndex - contIndex][colIndex + contIndex]).getColor() == cellTemp.getColor())
		{
			board[rowIndex - contIndex][colIndex + contIndex] = BoardCell.EMPTY;
			
			contIndex++;
			score++;
		}
		contIndex = 1;
		
		//Continuing up left
		while(((rowIndex - contIndex >= 0)&&(colIndex - contIndex >= 0))&& (board[rowIndex - contIndex][colIndex - contIndex]).getColor() == cellTemp.getColor())
		{
			board[rowIndex - contIndex][colIndex - contIndex] = BoardCell.EMPTY;
			
			contIndex++;
			score++;
		}
		contIndex = 1;
		
		//Continuing down right
		while(((rowIndex + contIndex < maxRows)&&(colIndex + contIndex < maxCols))&& (board[rowIndex + contIndex][colIndex + contIndex]).getColor() == cellTemp.getColor())
		{
			board[rowIndex + contIndex][colIndex + contIndex] = BoardCell.EMPTY;
			
			contIndex++;
			score++;
		}
		contIndex = 1;
		
		//Continuing down left
		while(((rowIndex + contIndex < maxRows)&&(colIndex - contIndex >= 0))&& (board[rowIndex + contIndex][colIndex - contIndex]).getColor() == cellTemp.getColor())
		{
			board[rowIndex + contIndex][colIndex - contIndex] = BoardCell.EMPTY;
			
			contIndex++;
			score++;
		}
		contIndex = 1;
		
		collapseRow(); //after processing cell collapse any rows that are empty
		
	}
		
	private int rowEmpty() //returns index of an empty row (doesn't check the last row)
	{					   
		boolean rowEmpty;
		int emptyRowIndex = -1;
		
		for(int x = 0; x < maxRows-1; x++) //assumes empty. if a row has an element, then sets rowEmpty = false
		{
			rowEmpty = true;
			for(int y = 0; y < maxCols; y++)
			{
				if(board[x][y] != BoardCell.EMPTY)
				{
					rowEmpty = false;
				}	
			}
			
			if(rowEmpty) //if row empty = true, set the index = to that row and return the index
			{
				emptyRowIndex = x;
						
				return emptyRowIndex;
			}	
		}	
		return emptyRowIndex;
	}
	
	private boolean elementAfter(int rowIndex) //returns true if there is a non empty element after the emptyRow index, else false
	{
		boolean elementAfter = false;
		
		for(int i = rowIndex; i < maxRows-1; i++) //looping through rows and cols
		{
			for(int j = 0; j < maxCols; j++)
			{
				
				if(board[i][j] != BoardCell.EMPTY)
				{
					elementAfter = true;
					
					return elementAfter;
				}
			}
		}
		return elementAfter;
		
	}
	
	private void collapseRow() //uses rowEmpty to find an empty row and shift all cells up one row if there is an empty row
	{
	
		while(rowEmpty() != maxRows-1  && rowEmpty() != -1 && elementAfter(rowEmpty())) //checks to see if there are any empty rows left that arent the last row
		{																				//and are still non empty elements after the empty index as well
			for(int x = rowEmpty(); x < maxRows-1; x++)
			{
				for(int y = 0; y < maxCols; y++)
				{
					board[x][y] = board[x+1][y];
				}
			}
		}
	}
	
	public boolean isGameOver() //checks to see if the game is over and returns true if it is over otherwise false
	{
		for(int x = 0; x < maxCols; x++) //loops through last row and checks to see if they're all empty
		{
			if(board[maxRows-1][x] != BoardCell.EMPTY)
			{
				return true;
			}
		}
		
		return false;
	}
}