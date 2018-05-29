package Chess;

import java.awt.Color;
import java.awt.Graphics;

import Graphics.ScreenComponent;

public class Board implements ScreenComponent {
	
	private Cell cells[][];
	
	public Board() {
		cells = new Cell[8][8];
		boolean isBlackSquare = true;
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if (isBlackSquare) {
					cells[row][col] = new Cell(row, col, new Color(0,0,0));
				} else {
					cells[row][col] = new Cell(row, col, new Color(255,255,255));
				}
				isBlackSquare = toggleBool(isBlackSquare);
			}
			isBlackSquare = toggleBool(isBlackSquare);
		}
	}
	
	private boolean toggleBool(boolean bool) {
		return !bool;
	}
	
	public void draw(Graphics g, int cellHeight, int cellWidth) {
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				cells[row][col].draw(g, cellHeight, cellWidth);
			}
		}
	}
}
