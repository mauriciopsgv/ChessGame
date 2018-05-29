package Chess;

import java.awt.Color;
import java.awt.Graphics;

import Graphics.ScreenComponent;

public class Cell implements ScreenComponent {
	private int row;
	private int column;
	private Color color;
	private boolean isSelected;
	private Color highlightColor = new Color(255,0,0);
	
	Cell(int row, int column, Color color) {
		this.row = row;
		this.column = column;
		this.color = color;
		this.isSelected = false;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
	
	public void selectCell() {
		isSelected = !isSelected;
	}
	
	public void deselectCell() {
		isSelected = false;
	}
	
	public void draw(Graphics g, int cellHeight, int cellWidth) {
		if (isSelected) {
			g.setColor(highlightColor);
		} else {
			g.setColor(color);
		}
		g.fillRect(cellHeight*column, cellWidth*row, cellHeight, cellWidth);
	}
}
