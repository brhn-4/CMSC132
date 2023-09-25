package model;

/**
 * This class represents the logic of a game where a board is updated on each
 * step of the game animation. The board can also be updated by selecting a
 * board cell.
 * 
 * @author Dept of Computer Science, UMCP
 */

public abstract class Game 
{
	protected BoardCell[][] board;
	protected int maxRows;
	protected int maxCols;

	/**
	 * Defines a board with BoardCell.EMPTY cells.
	 * 
	 * @param maxRows
	 * @param maxCols
	 */
	public Game(int maxRows, int maxCols) //creates 2D array with boardCell objects, all empty,
	{									  //and maxRows and maxCols dimensions
		this.maxRows = maxRows;
		this.maxCols = maxCols;
		board = new BoardCell [this.maxRows][this.maxCols];
		
		for(int x = 0; x < maxRows; x++)
		{
			for(int y = 0; y < maxCols; y++)
			{
				board[x][y] = BoardCell.EMPTY;
			}
		}
	}

	public int getMaxRows() // returns rows dimension
	{
		return maxRows;
		
	}

	public int getMaxCols() //returns cols dimension
	{
		return maxCols;
	}

	public void setBoardCell(int rowIndex, int colIndex, BoardCell boardCell) //puts a boardCell into board at the param index
	{
		board[rowIndex][colIndex] = boardCell;
	}

	public BoardCell getBoardCell(int rowIndex, int colIndex) //returns boardCell obj at given index
	{
		return board[rowIndex][colIndex];
	}

	/**
	 * Initializes row with the specified color.
	 * @param rowIndex
	 * @param cell
	 */
	public void setRowWithColor(int rowIndex, BoardCell cell) //loops through cols to set entire row with param color
	{
		for(int x = 0; x < maxCols; x++)
		{
			board[rowIndex][x] = cell;
		}
	}
	
	/**
	 * Initializes column with the specified color.
	 * @param colIndex
	 * @param cell
	 */
	public void setColWithColor(int colIndex, BoardCell cell) //loops through rows to set entire col with param color
	{
		for(int x = 0; x < maxRows; x++)
		{
			board[x][colIndex] = cell;
		}
	}
	
	/**
	 * Initializes the board with the specified color.
	 * @param cell
	 */
	public void setBoardWithColor(BoardCell cell) //sets entire board with param color by looping through rows and cols
	{
		for(int x = 0; x < maxRows; x++)
		{
			for(int y = 0; y < maxCols; y++)
			{
				board[x][y] = cell;
			}
		}
	}	
	
	public abstract boolean isGameOver();
	public abstract int getScore();

	/**
	 * Advances the animation one step.
	 */
	public abstract void nextAnimationStep();

	/**
	 * Adjust the board state according to the current board state and the
	 * selected cell.
	 * 
	 * @param rowIndex
	 * @param colIndex
	 */
	public abstract void processCell(int rowIndex, int colIndex);
}