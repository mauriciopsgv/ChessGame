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
	
	protected int nMovements;
	
	protected Piece(int id, Side side, int row, int column) {
		this.id = id;
		this.side = side;
		this.position = new Position(row, column);
		this.nMovements = 0;
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
	
	public Side getSide() {
		return this.side;
	}
	
	public int getRow() {
		return this.position.row;
	}
	
	public int getColumn() {
		return this.position.column;
	}
	
	public boolean isSamePosition(Position newPosition) {
		return Position.isSamePosition(newPosition, position);
	}
	
	public boolean isNear(Position newPosition, int numSquares) {
		return Position.isNear(newPosition, position, numSquares);
	}
	
	public boolean isVertical(Position newPosition) {
		return Position.isVertical(newPosition, position);
	}
	
	public boolean isHorizontal(Position newPosition) {
		return Position.isHorizontal(newPosition, position);
	}
	
	public boolean isDiagonal(Position newPosition) {
		return Position.isDiagonal(newPosition, position);
	}
	
	// Pieces should Override this method, think on a better way to force this
	protected boolean canMoveTo(Position newPosition) {
		return true;
	}
	
	protected boolean movePiece(Position newPosition) {
		if (this.canMoveTo(newPosition)) {
			position.row = newPosition.row;
			position.column = newPosition.column;
			nMovements = nMovements + 1;
			return true;
		}
		return false;
	}
	
	protected boolean canCapturePiece(Position newPosition) {
		return canMoveTo(newPosition);
	}
	
	protected boolean capturePiece(Position newPosition) {
		if (this.canCapturePiece(newPosition)) {
			position.row = newPosition.row;
			position.column = newPosition.column;
			return true;
		}
		return false;
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
