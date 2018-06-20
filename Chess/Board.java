package Chess;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import Graphics.ScreenComponent;

public class Board implements ScreenComponent {
	
	private Cell cells[][];
	private boolean isAnyCellSelected = false;
	private Position selectedCellPosition = new Position(0,0);
	private ArrayList<Position> highlightedCells = new ArrayList<Position>();
	
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
	
	public int getBoardDimension() {
		return 8;
	}
	
	public boolean isAnyCellSelected() {
		return isAnyCellSelected;
	}
	
	public Position getSelectedPosition() {
		return selectedCellPosition;
	}
	
	public int getSelectedPieceId() {
		if (isAnyCellSelected) {
			return cells[selectedCellPosition.row][selectedCellPosition.column].getPieceId();
		}
		return -1;
	}
	
	public boolean isSelectedCellOccupied() {
		return cells[selectedCellPosition.row][selectedCellPosition.column].getPieceId() != -1;
	}
	
	public boolean isCellOccupied(Position position) {
		return cells[position.row][position.column].getPieceId() != -1;
	}
	
	public int getCellPieceId(Position position) {
		return cells[position.row][position.column].getPieceId();
	}
	
	public void selectCell(Position position) {
		cells[position.row][position.column].selectCell();
		selectedCellPosition.row = position.row;
		selectedCellPosition.column = position.column;
		isAnyCellSelected = true;
	}
	
	public void highlightCell(Position position) {
		cells[position.row][position.column].selectCell();
		highlightedCells.add(position);
	}
	
	public void deselectCell() {
		cells[selectedCellPosition.row][selectedCellPosition.column].deselectCell();
		isAnyCellSelected = false;
		for (Position pos : highlightedCells) {
			cells[pos.row][pos.column].deselectCell();
		}
		highlightedCells.clear();
	}
	
	public void putPieceAt(int pieceId, Position newPosition) {
		cells[newPosition.row][newPosition.column].movePieceIn(pieceId);
	}
	
	public void movePieceTo(Position fromPosition, Position toPosition) {
		int pieceId = cells[selectedCellPosition.row][selectedCellPosition.column].getPieceId();
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
