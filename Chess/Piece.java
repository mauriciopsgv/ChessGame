package Chess;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Graphics.ScreenComponent;

public class Piece implements ImageObserver, ScreenComponent {
	
	private int id;
	
	protected Side side = Side.BLANK;
	
	protected BufferedImage image;
	
	protected Position position;
	
	protected Piece(int id, Side side, int row, int column) {
		this.id = id;
		this.side = side;
		this.position = new Position(row, column);
	}
	
	protected void loadImage(String imagePath) {
		try {                
			image = ImageIO.read(new File(imagePath));
		} catch (IOException ex) {
			System.out.println("Couldn't find image source file");
		}
	}
	
	public int getId() {
		return this.id;
	}
	
	public int getRow() {
		return this.position.row;
	}
	
	public int getColumn() {
		return this.position.column;
	}
	
	public boolean isSamePosition(Position newPosition) {
		return newPosition == position;
	}
	
	public boolean isNear(Position newPosition, int numSquares) {
		return Math.abs(newPosition.column - position.column) <= numSquares &&
			Math.abs(newPosition.row - position.row) <= numSquares;
	}
	
	public boolean isVertical(Position newPosition) {
		return (newPosition.column - position.column) == 0;
	}
	
	public boolean isHorizontal(Position newPosition) {
		return (newPosition.row - position.row) == 0;
	}
	
	public boolean isDiagonal(Position newPosition) {
		return Math.abs(newPosition.row - position.row) == Math.abs(newPosition.column - position.column);
	}
	
	protected boolean movePiece(Position newPosition) {
		position.row = newPosition.row;
		position.column = newPosition.column;
		return true;
	}
	
	public void draw(Graphics g, int cellHeight, int cellWidth) {
		// TODO Auto-generated method stub
		int horizontalPadding = (cellWidth - 64)/2;
		int verticalPadding = (cellHeight - 64)/2;
		g.drawImage(image, position.column * cellHeight + verticalPadding, 
						   position.row * cellWidth + horizontalPadding, this);
	}
	
	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		return false;
	}
}
