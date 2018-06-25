package Chess;

import java.awt.Color;
import java.awt.Graphics;

import Graphics.ScreenComponent;

public class Cell implements ScreenComponent {
	private int row;
	private int column;
	private int pieceId = -1;
	private Color color;
	private boolean isSelected;
	private Color highlightColor = new Color(255,0,0);
	
	Cell(int row, int column, Color color) {
		this.row = row;
		this.column = column;
		this.color = color;
		this.isSelected = false;
	}
	
	Cell(int row, int column, Color color, int pieceId, boolean isSelected) {
		this.row = row;
		this.column = column;
		this.color = color;
		this.pieceId = pieceId;
		this.isSelected = isSelected;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
	
	public int getPieceId() {
		return pieceId;
	}
	
	public void movePieceOut() {
		pieceId = -1;
	}
	
	public void movePieceIn(int pieceId) {
		this.pieceId = pieceId;
	}
	
	public void selectCell() {
		isSelected = true;
	}
	
	public void deselectCell() {
		isSelected = false;
	}
	
	public boolean isCellSelected() {
		return isSelected;
	}
	
	public void draw(Graphics g, int cellHeight, int cellWidth) {
		if (isSelected) {
			g.setColor(highlightColor);
		} else {
			g.setColor(color);
		}
		g.fillRect(cellHeight*column, cellWidth*row, cellHeight - 5, cellWidth - 5);
	}
}
