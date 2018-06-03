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
	
	protected int position[];
	
	protected Piece(int id, Side side, int row, int column) {
		this.id = id;
		this.side = side;
		position = new int[2];
		position[0] = row;
		position[1] = column;
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
		return this.position[0];
	}
	
	public int getColumn() {
		return this.position[1];
	}
	
	protected void movePiece(int newRow, int newColumn) {
		position[0] = newRow;
		position[1] = newColumn;
	}
	
	public void draw(Graphics g, int cellHeight, int cellWidth) {
		// TODO Auto-generated method stub
		int horizontalPadding = (cellWidth - 64)/2;
		int verticalPadding = (cellHeight - 64)/2;
		g.drawImage(image, position[1] * cellHeight + verticalPadding, 
						   position[0] * cellWidth + horizontalPadding, this);
	}
	
	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		return false;
	}
}
