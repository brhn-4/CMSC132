package tests;

import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Test;

import model.BoardCell;
import model.ClearCellGame;
import model.Game;

public class StudentTests {
	
	@Test
	public void collapseCells() {
		int maxRows = 8, maxCols = 8, strategy = 1;
		Game ccGame = new ClearCellGame(maxRows, maxCols, new Random(1L), strategy);
	   
		ccGame.setBoardWithColor(BoardCell.BLUE);
//		ccGame.setRowWithColor(maxRows - 1, BoardCell.EMPTY);
//		ccGame.setRowWithColor(1, BoardCell.YELLOW);
//		ccGame.setBoardCell(1, maxCols - 1, BoardCell.RED);
//		ccGame.setRowWithColor(3, BoardCell.GREEN);
//		ccGame.setRowWithColor(6, BoardCell.RED);
		
		String answer = "Before processCell\n\n";
		answer += getBoardStr(ccGame);
		ccGame.processCell(1, 4);
		answer += "\nAfter processCell\n";
		answer += getBoardStr(ccGame);
		ccGame.processCell(1, maxCols - 1);
		answer += "\nAfter processCell\n";
		answer += getBoardStr(ccGame);
		
		answer += "\\nClearCellGameTest";
		assertTrue(TestsSupport.isCorrect("pubCollapseCells.txt", answer));
	}
	
	private static String getBoardStr(Game game) {
		int maxRows = game.getMaxRows(), maxCols = game.getMaxCols();

		String answer = "Board(Rows: " + maxRows + ", Columns: " + maxCols + ")\n";
		for (int row = 0; row < maxRows; row++) {
			for (int col = 0; col < maxCols; col++) {
				if(game.getBoardCell(row, col) != null) {
					answer += game.getBoardCell(row, col).getName();
				}
				else {
					answer += "null";
				}
			}
			answer += "\n";
		}
		System.out.println(answer);
		return answer;
	}


}
