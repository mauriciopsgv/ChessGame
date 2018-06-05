package Chess;

import java.awt.Color;
import java.awt.Graphics;

import Graphics.ScreenComponent;

public class Board implements ScreenComponent {
	
	private Cell cells[][];
	
	boolean isAnyCellHighlighted = false;
	Position highlightedCellPosition = new Position(0,0);
	
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
	
	public boolean isAnyCellSelected() {
		return isAnyCellHighlighted;
	}
	
	public Position getSelectedPosition() {
		return highlightedCellPosition;
	}
	
	public int getSelectedPieceId() {
		if (isAnyCellHighlighted) {
			return cells[highlightedCellPosition.row][highlightedCellPosition.column].getPieceId();
		}
		return -1;
	}
	
	public boolean isSelectedCellOccupied() {
		return cells[highlightedCellPosition.row][highlightedCellPosition.column].getPieceId() != -1;
	}
	
	public boolean isCellOccupied(Position position) {
		return cells[position.row][position.column].getPieceId() != -1;
	}
	
	public int getCellPieceId(Position position) {
		return cells[position.row][position.column].getPieceId();
	}
	
	public void selectCell(Position position) {
		cells[position.row][position.column].selectCell();
		highlightedCellPosition.row = position.row;
		highlightedCellPosition.column = position.column;
		isAnyCellHighlighted = true;
	}
	
	public void deselectCell() {
		cells[highlightedCellPosition.row][highlightedCellPosition.column].deselectCell();
		isAnyCellHighlighted = false;
	}
	
	public void putPieceAt(int pieceId, Position newPosition) {
		cells[newPosition.row][newPosition.column].movePieceIn(pieceId);
	}
	
	public void movePieceTo(Position fromPosition, Position toPosition) {
		int pieceId = cells[highlightedCellPosition.row][highlightedCellPosition.column].getPieceId();
		cells[fromPosition.row][fromPosition.column].movePieceOut();
		cells[toPosition.row][toPosition.column].movePieceIn(pieceId);
	}
	
	public void draw(Graphics g, int cellHeight, int cellWidth) {
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				cells[row][col].draw(g, cellHeight, cellWidth);
			}
		}
	}
}
